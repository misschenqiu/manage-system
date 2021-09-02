package com.starda.managesystem.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * manage_reminder
 * @author 
 */
public class ManageReminder implements Serializable {
    private Integer id;

    /**
     * 消息名称
     */
    private String reminderName;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 是否可用,0:不可用，1：可用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人id
     */
    private Integer createAccountId;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否开启 0 否  1.是 
     */
    private Integer reminderOpen;

    /**
     * 重复提醒次数
     */
    private Integer againNumber;

    /**
     * 创建人信息
     */
    private String createUserName;

    /**
     * 是否重复提醒 0.否 1.是
     */
    private Integer again;

    /**
     * 描述
     */
    private String remark;

    /**
     * 提醒时间
     */
    private Date reminderTime;

    /**
     * 1.单次提醒，2.周期提醒
     */
    private Integer oneWeek;

    /**
     * 周期提醒类型 1.月，2.季，3.半年 4。全年
     */
    private Integer weekType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReminderName() {
        return reminderName;
    }

    public void setReminderName(String reminderName) {
        this.reminderName = reminderName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateAccountId() {
        return createAccountId;
    }

    public void setCreateAccountId(Integer createAccountId) {
        this.createAccountId = createAccountId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getReminderOpen() {
        return reminderOpen;
    }

    public void setReminderOpen(Integer reminderOpen) {
        this.reminderOpen = reminderOpen;
    }

    public Integer getAgainNumber() {
        return againNumber;
    }

    public void setAgainNumber(Integer againNumber) {
        this.againNumber = againNumber;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getAgain() {
        return again;
    }

    public void setAgain(Integer again) {
        this.again = again;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Date reminderTime) {
        this.reminderTime = reminderTime;
    }

    public Integer getOneWeek() {
        return oneWeek;
    }

    public void setOneWeek(Integer oneWeek) {
        this.oneWeek = oneWeek;
    }

    public Integer getWeekType() {
        return weekType;
    }

    public void setWeekType(Integer weekType) {
        this.weekType = weekType;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ManageReminder other = (ManageReminder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getReminderName() == null ? other.getReminderName() == null : this.getReminderName().equals(other.getReminderName()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateAccountId() == null ? other.getCreateAccountId() == null : this.getCreateAccountId().equals(other.getCreateAccountId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getReminderOpen() == null ? other.getReminderOpen() == null : this.getReminderOpen().equals(other.getReminderOpen()))
            && (this.getAgainNumber() == null ? other.getAgainNumber() == null : this.getAgainNumber().equals(other.getAgainNumber()))
            && (this.getCreateUserName() == null ? other.getCreateUserName() == null : this.getCreateUserName().equals(other.getCreateUserName()))
            && (this.getAgain() == null ? other.getAgain() == null : this.getAgain().equals(other.getAgain()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getReminderTime() == null ? other.getReminderTime() == null : this.getReminderTime().equals(other.getReminderTime()))
            && (this.getOneWeek() == null ? other.getOneWeek() == null : this.getOneWeek().equals(other.getOneWeek()))
            && (this.getWeekType() == null ? other.getWeekType() == null : this.getWeekType().equals(other.getWeekType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getReminderName() == null) ? 0 : getReminderName().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateAccountId() == null) ? 0 : getCreateAccountId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getReminderOpen() == null) ? 0 : getReminderOpen().hashCode());
        result = prime * result + ((getAgainNumber() == null) ? 0 : getAgainNumber().hashCode());
        result = prime * result + ((getCreateUserName() == null) ? 0 : getCreateUserName().hashCode());
        result = prime * result + ((getAgain() == null) ? 0 : getAgain().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getReminderTime() == null) ? 0 : getReminderTime().hashCode());
        result = prime * result + ((getOneWeek() == null) ? 0 : getOneWeek().hashCode());
        result = prime * result + ((getWeekType() == null) ? 0 : getWeekType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reminderName=").append(reminderName);
        sb.append(", content=").append(content);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createAccountId=").append(createAccountId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", reminderOpen=").append(reminderOpen);
        sb.append(", againNumber=").append(againNumber);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", again=").append(again);
        sb.append(", remark=").append(remark);
        sb.append(", reminderTime=").append(reminderTime);
        sb.append(", oneWeek=").append(oneWeek);
        sb.append(", weekType=").append(weekType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}