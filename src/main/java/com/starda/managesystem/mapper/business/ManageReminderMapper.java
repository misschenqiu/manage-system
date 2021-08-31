package com.starda.managesystem.mapper.business;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.ManageReminder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageReminderMapper extends MPJBaseMapper<ManageReminder> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ManageReminder record);

    ManageReminder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageReminder record);

    int updateByPrimaryKey(ManageReminder record);
}