package com.starda.managesystem.service.impl;

import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.service.IFileService;
import com.starda.managesystem.util.FileLoadOrDownUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: FileServiceImpl
 * @Author: chenqiu
 * @Description: 文件 操作
 * @Date: 2021/8/28 1:03
 * @Version: 1.0
 */

@Service
@Log4j2
public class FileServiceImpl implements IFileService {


    @Override
    public String uploadImgFile(MultipartFile multipartFile, int type) throws Exception {
        String imgUrl = "";
        switch (type){
            // 头像
            case Constant
                    .BaseNumberManage.ONE:
                imgUrl = FileLoadOrDownUtil.uploadFileImg(multipartFile, null);
                break;
            // 工作照
            case Constant.BaseNumberManage.TWO:
                imgUrl = FileLoadOrDownUtil.uploadFileImg(multipartFile, Constant.BaseStringInfoManage.BASE_HEADER_PATH);
                break;
        }
        return imgUrl;
    }
}
