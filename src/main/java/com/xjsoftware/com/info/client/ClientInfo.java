package com.xjsoftware.com.info.client;

import com.xjsoftware.com.info.client.enums.StatusEnum;

import java.time.LocalDateTime;

public class ClientInfo {


    //姓名，身份证号，手机号，芝麻积分，当前职业，紧急联系人手机，姓名和关系
    private Integer id;
    private String name;
    private String phoneNumber;
    private Integer age;
    private String idCode;
    private Integer creaditScore;
    private String job;
    private String relativeName;
    private String relativePhone;

    public String getRelativeType() {
        return relativeType;
    }

    public void setRelativeType(String relativeType) {
        this.relativeType = relativeType;
    }

    private String relativeType;
    private LocalDateTime time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public Integer getCreaditScore() {
        return creaditScore;
    }

    public void setCreaditScore(Integer creaditScore) {
        this.creaditScore = creaditScore;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getRelativeName() {
        return relativeName;
    }

    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

    public String getRelativePhone() {
        return relativePhone;
    }

    public void setRelativePhone(String relativePhone) {
        this.relativePhone = relativePhone;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }


    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    private StatusEnum status;

}
