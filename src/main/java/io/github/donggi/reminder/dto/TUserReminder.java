package io.github.donggi.reminder.dto;

import java.util.Date;
import javax.annotation.Generated;
import io.github.donggi.reminder.enums.CommonFlag;

public class TUserReminder {

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long reminderId;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long userId;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String title;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String attachFile;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private CommonFlag completeFlg;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private CommonFlag delFlg;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date addDate;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getReminderId() {
        return reminderId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setReminderId(Long reminderId) {
        this.reminderId = reminderId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getUserId() {
        return userId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getTitle() {
        return title;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTitle(String title) {
        this.title = title;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAttachFile() {
        return attachFile;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public CommonFlag getCompleteFlg() {
        return completeFlg;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCompleteFlg(CommonFlag completeFlg) {
        this.completeFlg = completeFlg;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public CommonFlag getDelFlg() {
        return delFlg;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDelFlg(CommonFlag delFlg) {
        this.delFlg = delFlg;
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