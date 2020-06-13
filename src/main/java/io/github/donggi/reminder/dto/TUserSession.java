package io.github.donggi.reminder.dto;

import io.github.donggi.reminder.enums.CommonFlag;
import java.util.Date;
import javax.annotation.Generated;

public class TUserSession {

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long userId;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String nextToken;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date tokenLimitDate;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private CommonFlag alwaysLoginFlg;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private CommonFlag alwaysLogoutFlg;
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
    public String getNextToken() {
        return nextToken;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getTokenLimitDate() {
        return tokenLimitDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTokenLimitDate(Date tokenLimitDate) {
        this.tokenLimitDate = tokenLimitDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public CommonFlag getAlwaysLoginFlg() {
        return alwaysLoginFlg;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAlwaysLoginFlg(CommonFlag alwaysLoginFlg) {
        this.alwaysLoginFlg = alwaysLoginFlg;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public CommonFlag getAlwaysLogoutFlg() {
        return alwaysLogoutFlg;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAlwaysLogoutFlg(CommonFlag alwaysLogoutFlg) {
        this.alwaysLogoutFlg = alwaysLogoutFlg;
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