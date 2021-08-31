package com.starda.managesystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * manage_reminder
 * @author 
 */

@Data
public class ManageReminder implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
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
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}