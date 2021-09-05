package com.starda.managesystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * manage_business
 * @author 
 */

@Data
public class ManageBusiness implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    /**
     * 业务名
     */
    private String businessName;

    /**
     * 描述
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Integer updateAccountId;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 合作单位
     */
    private Integer companyId;

    /**
     * 单位名
     */
    private String companyName;

    /**
     * 0.未完成 1.完成
     */
    private Integer businessSucess;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 0. 回款 1.未回款
     */
    private Integer collectMoney;

    /**
     * 保险单号
     */
    private String insuranceNumber;

    /**
     * 对应人
     */
    private String dockPeople;

    /**
     * 对应人电话
     */
    private String dockPhone;

    /**
     * 图片说明
     */
    private String businessImg;

    /**
     * 该单金额
     */
    private String money;

    /**
     * 保险公司
     */
    private String insuranceCompany;

    /**
     * 保险联系人
     */
    private String insurancePeople;

    /**
     * 保险联系电话
     */
    private String insurancePhone;

    /**
     * 是否可用,0:不可用，1：可用
     */
    private Integer status;

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
        ManageBusiness other = (ManageBusiness) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBusinessName() == null ? other.getBusinessName() == null : this.getBusinessName().equals(other.getBusinessName()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateAccountId() == null ? other.getCreateAccountId() == null : this.getCreateAccountId().equals(other.getCreateAccountId()))
            && (this.getCreateUserName() == null ? other.getCreateUserName() == null : this.getCreateUserName().equals(other.getCreateUserName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateAccountId() == null ? other.getUpdateAccountId() == null : this.getUpdateAccountId().equals(other.getUpdateAccountId()))
            && (this.getBusinessType() == null ? other.getBusinessType() == null : this.getBusinessType().equals(other.getBusinessType()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getBusinessSucess() == null ? other.getBusinessSucess() == null : this.getBusinessSucess().equals(other.getBusinessSucess()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getCollectMoney() == null ? other.getCollectMoney() == null : this.getCollectMoney().equals(other.getCollectMoney()))
            && (this.getInsuranceNumber() == null ? other.getInsuranceNumber() == null : this.getInsuranceNumber().equals(other.getInsuranceNumber()))
            && (this.getDockPeople() == null ? other.getDockPeople() == null : this.getDockPeople().equals(other.getDockPeople()))
            && (this.getDockPhone() == null ? other.getDockPhone() == null : this.getDockPhone().equals(other.getDockPhone()))
            && (this.getBusinessImg() == null ? other.getBusinessImg() == null : this.getBusinessImg().equals(other.getBusinessImg()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getInsuranceCompany() == null ? other.getInsuranceCompany() == null : this.getInsuranceCompany().equals(other.getInsuranceCompany()))
            && (this.getInsurancePeople() == null ? other.getInsurancePeople() == null : this.getInsurancePeople().equals(other.getInsurancePeople()))
            && (this.getInsurancePhone() == null ? other.getInsurancePhone() == null : this.getInsurancePhone().equals(other.getInsurancePhone()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBusinessName() == null) ? 0 : getBusinessName().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateAccountId() == null) ? 0 : getCreateAccountId().hashCode());
        result = prime * result + ((getCreateUserName() == null) ? 0 : getCreateUserName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateAccountId() == null) ? 0 : getUpdateAccountId().hashCode());
        result = prime * result + ((getBusinessType() == null) ? 0 : getBusinessType().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getBusinessSucess() == null) ? 0 : getBusinessSucess().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getCollectMoney() == null) ? 0 : getCollectMoney().hashCode());
        result = prime * result + ((getInsuranceNumber() == null) ? 0 : getInsuranceNumber().hashCode());
        result = prime * result + ((getDockPeople() == null) ? 0 : getDockPeople().hashCode());
        result = prime * result + ((getDockPhone() == null) ? 0 : getDockPhone().hashCode());
        result = prime * result + ((getBusinessImg() == null) ? 0 : getBusinessImg().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getInsuranceCompany() == null) ? 0 : getInsuranceCompany().hashCode());
        result = prime * result + ((getInsurancePeople() == null) ? 0 : getInsurancePeople().hashCode());
        result = prime * result + ((getInsurancePhone() == null) ? 0 : getInsurancePhone().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", businessName=").append(businessName);
        sb.append(", remark=").append(remark);
        sb.append(", createAccountId=").append(createAccountId);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateAccountId=").append(updateAccountId);
        sb.append(", businessType=").append(businessType);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", businessSucess=").append(businessSucess);
        sb.append(", endTime=").append(endTime);
        sb.append(", collectMoney=").append(collectMoney);
        sb.append(", insuranceNumber=").append(insuranceNumber);
        sb.append(", dockPeople=").append(dockPeople);
        sb.append(", dockPhone=").append(dockPhone);
        sb.append(", businessImg=").append(businessImg);
        sb.append(", money=").append(money);
        sb.append(", insuranceCompany=").append(insuranceCompany);
        sb.append(", insurancePeople=").append(insurancePeople);
        sb.append(", insurancePhone=").append(insurancePhone);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}