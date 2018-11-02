package com.xjsoftware.com.info.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjsoftware.com.info.manager.CacheKeys;
import com.xjsoftware.com.info.service.IValidCodeService;
import com.xjsoftware.com.info.volobj.ValidCodeInfo;
import com.xjsoftware.com.info.volobj.enums.ValidCodeSentStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Primary
public class ValidCodeServiceImpl implements IValidCodeService {
	
	@Autowired
	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	
	
	private ObjectMapper objectMapper;
	
	private StringRedisTemplate redisTemplate;
	
	Logger logger = LoggerFactory.getLogger (ValidCodeServiceImpl .class);
	
	//private  final static String PHONENUMBER_PATH_PREFIX="/phonenumber/%s";
	private  final static Integer PHOENUMBER_REGISTLIMIT=3;
	private  final static Integer PHOENUMBER_EXPIRE_HOURS=24;
	private  final static Integer PHONENUMBER_FREQUENCY_LIMIT=60;
	private  final static Integer ACQUIRE_TIME=5;
	
	
	/**
	 * @author haste
	 * @date 2018/8/23 10:56
	 * @param phoneNumber
	 * @return String
	 */
	//@Filter(key = "validinfo")
	@Override
	public ValidCodeInfo sendValidCode(String phoneNumber) throws Exception
	{
	logger.info (phoneNumber);
		ValidCodeInfo validCodeInfo=new ValidCodeInfo ();
			
			validCodeInfo=isValidCodeSent (phoneNumber);
			if(validCodeInfo!=null&&(
					validCodeInfo.getStatus ()== ValidCodeSentStatusEnum.AlreadySentInLimitedTime||
							validCodeInfo.getStatus ()==ValidCodeSentStatusEnum.OffLimit||
							validCodeInfo.getStatus ()==ValidCodeSentStatusEnum.OldUser))
			{
				logger.info ("faile cause status");
//				lock.release ();
				return validCodeInfo;
			}
			else
			{
				if(validCodeInfo==null)
				{
					validCodeInfo=new ValidCodeInfo ();
					logger.info ("phone number is new");
					logger.info (Integer.toString (validCodeInfo.getSentTimes ()));
					validCodeInfo.setSentTimes (1);
				}
				else {
					logger.info (Integer.toString (validCodeInfo.getSentTimes ()));
					logger.info ("phone number is used");
					//TODO 验证码过期是在第一次还是最后一次发送的时间加上24小时
					validCodeInfo.setSentTimes (validCodeInfo.getSentTimes ()+1);
				}
				
				Integer validCode=new Random ().nextInt (1000000);
				logger.info ("valid code is :" +validCode);
				validCodeInfo.setPhoneNumber (phoneNumber);
				LocalDateTime expireDateTime=LocalDateTime.now ().plusSeconds (PHONENUMBER_FREQUENCY_LIMIT);
				validCodeInfo.setCacheExpireTimes (expireDateTime);
				validCodeInfo.setValidCode (validCode.toString ());
				validCodeInfo.setStatus (ValidCodeSentStatusEnum.SendSuccess);
				
				saveValidCodeInfo (validCodeInfo);
				logger.info (validCode.toString ());
				//smsHelper.sendMsg (phoneNumber,String.format ("您的验证码为:%s",validCode.toString ()));
			}
	
		
		return validCodeInfo;
	}
	
	/**
	 * @author haste
	 * @date 2018/8/23 10:23
	 * @param phoneNumber
	 * @return ValidCodeInfo
	 */
	//@Override
	private ValidCodeInfo  isValidCodeSent(String phoneNumber)
	{
		ValidCodeInfo validCodeInfo=new ValidCodeInfo ();
		String key= CacheKeys.getPhoneNumberValidCodeCacheKey (phoneNumber);
		logger.info ("key:"+key);
		if(!redisTemplate.hasKey (key))
		{
			return null;
		}
		
		validCodeInfo=getValidCodeInfoFromCache (phoneNumber);
		
		if(validCodeInfo==null)
		{
			return null;
		}
		
		if(LocalDateTime.now ().isBefore (validCodeInfo.getCacheExpireTimes ()))
		{
			logger.info ("valid code is sent in 60 secods");
			validCodeInfo.setStatus (ValidCodeSentStatusEnum.AlreadySentInLimitedTime);
			return validCodeInfo;
		}
		if(validCodeInfo.getSentTimes ()>=PHOENUMBER_REGISTLIMIT)
		{
			validCodeInfo.setStatus (ValidCodeSentStatusEnum.OffLimit);
			logger.info ("valid code is sent over 3 times");
			//return 2;
			return validCodeInfo;
		}
		return validCodeInfo;
	}
	
	private void saveValidCodeInfo(ValidCodeInfo validCodeInfo) throws JsonProcessingException
	{
		String key= CacheKeys.getPhoneNumberValidCodeCacheKey (validCodeInfo.getPhoneNumber ());
		
		LocalDateTime expireDateTime=LocalDateTime.now ().plusHours (PHOENUMBER_EXPIRE_HOURS);
		
		
		String validInfoJson=objectMapper.writeValueAsString (validCodeInfo);
		logger.info ("save json :"+validInfoJson);
		
		//String validInfoJson= JsonUtils.getJSON (validCodeInfo);
		
		long expireSeconds=expireDateTime.toInstant (ZoneOffset.of ("+8")).getEpochSecond ();
		logger.info (String.format ("expireSeconds:%s",expireSeconds));
		
		
		redisTemplate.opsForValue ().set (key,validInfoJson,expireSeconds,TimeUnit.SECONDS);
	}
	
	
	/**
	 * @author haste
	 * @date 2018/8/31 18:40
	 * @param phoneNumber,validCode
	 * @return Boolean
	 */
	@Override
	public Boolean isValidCodeCorrect(String phoneNumber,String validCode)
	{
		
		ValidCodeInfo validCodeInfo=getValidCodeInfoFromCache (phoneNumber);
		
		if(validCodeInfo==null)
		{
			return false;
		}
		
		if(validCodeInfo.getValidCode ().equals (validCode))
		{
			return true;
		}
		return false;
	}
	
	private ValidCodeInfo getValidCodeInfoFromCache(String phoneNumber)
	{
		String key= CacheKeys.getPhoneNumberValidCodeCacheKey (phoneNumber);
		
		String validCodeInfoJson=redisTemplate.opsForValue ().get (key);
		
		if(validCodeInfoJson==null||validCodeInfoJson.length ()<=0)
		{
			logger.info ("key: "+key);
			logger.info ("validCodeInfo is null in cache");
			return null;
		}
		ValidCodeInfo validCodeInfo;
		logger.info ("validCodeInfoJson:"+validCodeInfoJson);
		try
		{
			validCodeInfo=objectMapper.readValue (validCodeInfoJson,ValidCodeInfo.class);
		}
		catch (IOException ioe)
		{
			logger.error (String .format ("prase json exception,json :%s",validCodeInfoJson));
			return  null;
		}
		return validCodeInfo;
	}
	
	
}
