//package com.xjsoftware.com.info.aop;
//
//
//import com.xjsoftware.com.info.manager.CacheKeys;
//import com.xjsoftware.com.info.manager.IPHelper;
//import com.xjsoftware.com.info.volobj.ApiResult;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.util.concurrent.TimeUnit;
//
//@Aspect
//@Component
//public class ControllerInterceptor {
//
//	@Value ("${is_open_ipfilter}")
//	private Boolean IS_OPEN_IPFLTER;
//
//	@Pointcut(value = "@annotation(com.bitauto.i.wpibitautoinvitenewuser.aop.IPFilter)")
//	public void addAdvice() {
//	}
//
//	private static final Logger logger = LoggerFactory.getLogger (ControllerInterceptor.class);
//
//
//	@Autowired
//	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
//		this.redisTemplate = redisTemplate;
//	}
//
//	private StringRedisTemplate redisTemplate;
//
//	@Around(value = "addAdvice() && @annotation(ipFilter)")
//	public ApiResult Interceptor(ProceedingJoinPoint proceedingJoinPoint, IPFilter ipFilter)
//	{
//		Integer timesLimit = Integer.valueOf (ipFilter.TimesLimit ());
//
//		String ip = IPHelper.getIpAddress ();
//		String ipSetKey = CacheKeys.getAddressSetKey (ip);
//
//		if(!IS_OPEN_IPFLTER)
//		{
//			try {
//				return (ApiResult) proceedingJoinPoint.proceed ();
//			} catch (Throwable throwable) {
//
//			}
//		}
//		logger.info (String.format ("ipSetKey :%s",ipSetKey));
//		Object result = redisTemplate.opsForValue ().get (ipSetKey);
//
//		logger.info (String.format ("current ip result is %s",result));
//		if(result == null)
//		{
//			logger.info ("ip count cache is null");
//			redisTemplate.opsForValue ().set (ipSetKey,"1",Long.valueOf (ipFilter.ExpireSeconds ()), TimeUnit.SECONDS);
//		}
//		else
//		{
//			logger.info (result.toString ());
//			redisTemplate.opsForValue ().increment (ipSetKey,1);
//		}
//
//		logger.info (String.format ("result: %s, timesLimit ： %s",result,timesLimit));
//		if(IS_OPEN_IPFLTER&& ( result == null|| Integer.valueOf (result.toString ()) < timesLimit)) {
//			logger.info ("ip is legal");
//			try {
//				ApiResult apiResult = (ApiResult) proceedingJoinPoint.proceed ();
//				return apiResult;
//			} catch (Throwable throwable) {
//
//			}
//		}
//		ApiResult apiResult=new ApiResult ();
//		apiResult.setMsg ("请求频率太高，请稍后再试");
//		apiResult.setCode (-99);
//		return  apiResult;
//
//	}
//
//	public Method getMethod(ProceedingJoinPoint pjp) {
//		//获取参数的类型
//		Object[] args = pjp.getArgs ();
//		Class[] argTypes = new Class[pjp.getArgs ().length];
//		for (int i = 0; i < args.length; i++) {
//			if (args[i] != null) {
//				argTypes[i] = args[i].getClass ();
//				logger.info (String.format ("args is : %s", args[i]));
//			} else {
//				logger.info (String.format ("args is null, i: %s", i));
//			}
//		}
//		MethodSignature signature = (MethodSignature) pjp.getSignature ();
//		Method method = signature.getMethod ();
//		return method;
//
//	}
//}
