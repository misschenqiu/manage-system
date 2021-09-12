package com.starda.managesystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * manage_re_comp
 * @author 
 */

@Data
@ToString
public class ManageReComp implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    /**
     * 公司id
     */
    private Integer companyId;

    private Integer reminderId;

    private static final long serialVersionUID = 1L;

}