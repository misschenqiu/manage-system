package com.starda.managesystem.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * manage_message_log
 * @author 
 */
@Data
public class ManageMessageLog implements Serializable {
    private Integer id;

    /**
     * 内容
     */
    private String content;

    /**
     * 发送时间
     */
    private Date createTime;

    /**
     * 发送电话号码
     */
    private String phone;

    /**
     * 是否成功 0，否 1.是
     */
    private Integer messageSeccuss;

    /**
     * 重发时间
     */
    private Date againTime;

    /**
     * 发送次数
     */
    private Integer number;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 发送平台标识
     */
    private String messageSign;

    /**
     * 消息id
     */
    private Integer reminderId;

    private static final long serialVersionUID = 1L;

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
        ManageMessageLog other = (ManageMessageLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getMessageSeccuss() == null ? other.getMessageSeccuss() == null : this.getMessageSeccuss().equals(other.getMessageSeccuss()))
            && (this.getAgainTime() == null ? other.getAgainTime() == null : this.getAgainTime().equals(other.getAgainTime()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getErrorMessage() == null ? other.getErrorMessage() == null : this.getErrorMessage().equals(other.getErrorMessage()))
            && (this.getMessageSign() == null ? other.getMessageSign() == null : this.getMessageSign().equals(other.getMessageSign()))
            && (this.getReminderId() == null ? other.getReminderId() == null : this.getReminderId().equals(other.getReminderId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getMessageSeccuss() == null) ? 0 : getMessageSeccuss().hashCode());
        result = prime * result + ((getAgainTime() == null) ? 0 : getAgainTime().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getErrorMessage() == null) ? 0 : getErrorMessage().hashCode());
        result = prime * result + ((getMessageSign() == null) ? 0 : getMessageSign().hashCode());
        result = prime * result + ((getReminderId() == null) ? 0 : getReminderId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(createTime);
        sb.append(", phone=").append(phone);
        sb.append(", messageSeccuss=").append(messageSeccuss);
        sb.append(", againTime=").append(againTime);
        sb.append(", number=").append(number);
        sb.append(", errorMessage=").append(errorMessage);
        sb.append(", messageSign=").append(messageSign);
        sb.append(", reminderId=").append(reminderId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}