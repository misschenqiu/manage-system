package com.starda.managesystem.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config
 * @ClassName: CustomIdGenerator
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/7/29 11:14
 * @Version: 1.0
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Long nextId(Object entity) {
        //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
        String bizKey = entity.getClass().getName();
        //根据bizKey调用分布式ID生成
        Long id = 1L;
        //返回生成的id值即可.
        return id;
    }
}
