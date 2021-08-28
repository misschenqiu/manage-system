package com.starda.managesystem.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service
 * @ClassName: IFileService
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/28 1:03
 * @Version: 1.0
 */
public interface IFileService {

    /**
     * 上传文件
     * @param multipartFile
     * @param type 1.上传头像 2.上传工作照
     * @return
     * @throws Exception
     */
    String uploadImgFile(MultipartFile multipartFile, int type) throws Exception;

}
