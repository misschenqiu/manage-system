package com.starda.managesystem.controller;

import com.starda.managesystem.config.Result;
import com.starda.managesystem.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.controller
 * @ClassName: FileLoadupController
 * @Author: chenqiu
 * @Description: 文件上传
 * @Date: 2021/8/28 0:50
 * @Version: 1.0
 */

@RestController
@RequestMapping("/upload/file")
public class FileUploadController {

    @Autowired
    private IFileService fileService;

    /**
     * 上传文件
     * @param file
     * @param type 1.上传头像 2.上传工作照
     * @return
     * @throws Exception
     */
    @PostMapping("/img/{type}")
    public Result uploadImgFile(MultipartFile file, @PathVariable Integer type) throws Exception{

        String imgUrl = this.fileService.uploadImgFile(file, type);

        return Result.ok().resultPage(imgUrl);
    }

}
