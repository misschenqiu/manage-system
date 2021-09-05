package com.starda.managesystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * manage_business_remark
 * @author 
 */

@Data
public class ManageBusinessRemark implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private Integer createAccountId;

    /**
     * 创建人名
     */
    private String createUserName;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Integer updateUser;

    /**
     * 修改人名
     */
    private String updateUserName;

    /**
     * 备注图片
     */
    private String remarkImg;

    /**
     * 业务详情id
     */
    private Integer businessInfoId;

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
        ManageBusinessRemark other = (ManageBusinessRemark) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateAccountId() == null ? other.getCreateAccountId() == null : this.getCreateAccountId().equals(other.getCreateAccountId()))
            && (this.getCreateUserName() == null ? other.getCreateUserName() == null : this.getCreateUserName().equals(other.getCreateUserName()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateUserName() == null ? other.getUpdateUserName() == null : this.getUpdateUserName().equals(other.getUpdateUserName()))
            && (this.getRemarkImg() == null ? other.getRemarkImg() == null : this.getRemarkImg().equals(other.getRemarkImg()))
            && (this.getBusinessInfoId() == null ? other.getBusinessInfoId() == null : this.getBusinessInfoId().equals(other.getBusinessInfoId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateAccountId() == null) ? 0 : getCreateAccountId().hashCode());
        result = prime * result + ((getCreateUserName() == null) ? 0 : getCreateUserName().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateUserName() == null) ? 0 : getUpdateUserName().hashCode());
        result = prime * result + ((getRemarkImg() == null) ? 0 : getRemarkImg().hashCode());
        result = prime * result + ((getBusinessInfoId() == null) ? 0 : getBusinessInfoId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", remark=").append(remark);
        sb.append(", createAccountId=").append(createAccountId);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateUserName=").append(updateUserName);
        sb.append(", remarkImg=").append(remarkImg);
        sb.append(", businessInfoId=").append(businessInfoId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}