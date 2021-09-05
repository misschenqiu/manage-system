package com.starda.managesystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * manage_business_info
 * @author 
 */

@Data
public class ManageBusinessInfo implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    /**
     * 父级id
     */
    private Integer pid;

    /**
     * 业务id
     */
    private Integer businessId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 员工id
     */
    private Integer staffId;

    /**
     * 员工名称
     */
    private String staffName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 描述
     */
    private String remark;

    /**
     * 员工提成
     */
    private BigDecimal money;

    /**
     * 业务地址
     */
    private String businessAddress;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 维度
     */
    private String dimension;

    /**
     * 业务图
     */
    private String businessFile;

    /**
     * 零件名字
     */
    private String partName;

    /**
     * 创建人
     */
    private Integer createAccountId;

    /**
     * 创建人名
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Integer updateAccount;

    /**
     * 0.未结束，1.终止  2. 未到款  3.完成 4.退回 5.驳回
     */
    private Integer finish;

    /**
     * 任务层次
     */
    private Integer level;

    /**
     * 是否可用,0:不可用，1：可用  
     */
    private Integer status;

    /**
     * 完成时间
     */
    private Date finishTime;

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
        ManageBusinessInfo other = (ManageBusinessInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getBusinessId() == null ? other.getBusinessId() == null : this.getBusinessId().equals(other.getBusinessId()))
            && (this.getTaskName() == null ? other.getTaskName() == null : this.getTaskName().equals(other.getTaskName()))
            && (this.getStaffId() == null ? other.getStaffId() == null : this.getStaffId().equals(other.getStaffId()))
            && (this.getStaffName() == null ? other.getStaffName() == null : this.getStaffName().equals(other.getStaffName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getBusinessAddress() == null ? other.getBusinessAddress() == null : this.getBusinessAddress().equals(other.getBusinessAddress()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getDimension() == null ? other.getDimension() == null : this.getDimension().equals(other.getDimension()))
            && (this.getBusinessFile() == null ? other.getBusinessFile() == null : this.getBusinessFile().equals(other.getBusinessFile()))
            && (this.getPartName() == null ? other.getPartName() == null : this.getPartName().equals(other.getPartName()))
            && (this.getCreateAccountId() == null ? other.getCreateAccountId() == null : this.getCreateAccountId().equals(other.getCreateAccountId()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateAccount() == null ? other.getUpdateAccount() == null : this.getUpdateAccount().equals(other.getUpdateAccount()))
            && (this.getFinish() == null ? other.getFinish() == null : this.getFinish().equals(other.getFinish()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getFinishTime() == null ? other.getFinishTime() == null : this.getFinishTime().equals(other.getFinishTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getBusinessId() == null) ? 0 : getBusinessId().hashCode());
        result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
        result = prime * result + ((getStaffId() == null) ? 0 : getStaffId().hashCode());
        result = prime * result + ((getStaffName() == null) ? 0 : getStaffName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getBusinessAddress() == null) ? 0 : getBusinessAddress().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getDimension() == null) ? 0 : getDimension().hashCode());
        result = prime * result + ((getBusinessFile() == null) ? 0 : getBusinessFile().hashCode());
        result = prime * result + ((getPartName() == null) ? 0 : getPartName().hashCode());
        result = prime * result + ((getCreateAccountId() == null) ? 0 : getCreateAccountId().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateAccount() == null) ? 0 : getUpdateAccount().hashCode());
        result = prime * result + ((getFinish() == null) ? 0 : getFinish().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getFinishTime() == null) ? 0 : getFinishTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", businessId=").append(businessId);
        sb.append(", taskName=").append(taskName);
        sb.append(", staffId=").append(staffId);
        sb.append(", staffName=").append(staffName);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append(", money=").append(money);
        sb.append(", businessAddress=").append(businessAddress);
        sb.append(", longitude=").append(longitude);
        sb.append(", dimension=").append(dimension);
        sb.append(", businessFile=").append(businessFile);
        sb.append(", partName=").append(partName);
        sb.append(", createAccountId=").append(createAccountId);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateAccount=").append(updateAccount);
        sb.append(", finish=").append(finish);
        sb.append(", level=").append(level);
        sb.append(", status=").append(status);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}