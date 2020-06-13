package io.github.donggi.reminder.dto;

import java.util.Date;
import javax.annotation.Generated;

public class TUser {

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long userId;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String nickname;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String pwdHash;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date addDate;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getUserId() {
        return userId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getNickname() {
        return nickname;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getPwdHash() {
        return pwdHash;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getAddDate() {
        return addDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getUpdDate() {
        return updDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }
}