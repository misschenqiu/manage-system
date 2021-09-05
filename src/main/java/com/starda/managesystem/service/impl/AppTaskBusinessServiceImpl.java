package com.starda.managesystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starda.managesystem.mapper.business.ManageBusinessInfoMapper;
import com.starda.managesystem.pojo.ManageBusinessInfo;
import com.starda.managesystem.service.IAppTaskBusinessService;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: AppTaskBusinessServiceImpl
 * @Author: chenqiu
 * @Description: 业务实现类（app端）
 * @Date: 2021/9/1 15:35
 * @Version: 1.0
 */

@Service
public class AppTaskBusinessServiceImpl extends ServiceImpl<ManageBusinessInfoMapper, ManageBusinessInfo> implements IAppTaskBusinessService {
}
