package com.starda.managesystem.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.exceptions.ManageStarException;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.util
 * @ClassName: FileUtil
 * @Author: chenqiu
 * @Description: 文件操作类
 * @Date: 2021/8/23 10:20
 * @Version: 1.0
 */
public class FileLoadOrDownUtil {

    /**
     * 上传文件
     *
     * @param multipartFile
     * @param folder 文件地址
     * @return
     * @throws IOException
     */
    public static String uploadFileImg(MultipartFile multipartFile, String folder) throws IOException {
        // 参数检验
        if (multipartFile == null) {
            throw new ManageStarException(ExceptionEnums.NO_FILE_NULL.getCode(), ExceptionEnums.NO_FILE_NULL.getMessage());
        }
        // 文件限制10M
        long size = multipartFile.getSize();
        // 此处做文件大小限制
//        if (size > MAX_POST_SIZE) {
//            throw new ManageStarException("length exceeds limit of 10M");
//        }
        if (StrUtil.isBlank(folder)) {
            // 默认文件上传地址
            folder = Constant.BaseStringInfoManage.BASE_FILE_PATH;
        }
        if (!FileUtil.exist(folder)) {
            FileUtil.mkdir(folder);
        }
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmssSSS")) + File.separator + multipartFile.getOriginalFilename();
        File directory = new File("");//参数为空
        String courseFile = directory.getCanonicalPath() ;
        String path = courseFile + folder + fileName;
        File file = new File(path);
        if (FileUtil.exist(file)) {
            throw new ManageStarException(ExceptionEnums.FILE_EXIST.getCode(), ExceptionEnums.FILE_EXIST.getMessage());
        }
        File file1 = FileUtil.writeBytes(multipartFile.getBytes(), path);
        if (file1.length() < 0) {
            throw new ManageStarException(ExceptionEnums.FILE_LOAD_FAIL.getCode(), ExceptionEnums.FILE_LOAD_FAIL.getMessage());
        }
        return path;
    }

    /**
     * 下载文件
     * @param response
     * @param fileName 文件名
     * @param fileUrl 文件路径
     * @throws Exception
     */
    public static void downFile(HttpServletResponse response, String fileName, String fileUrl) throws Exception{
        File file = FileUtil.file(fileUrl);
        if (StringUtils.isEmpty(fileUrl) || file == null || !file.exists()) {
            throw new ManageStarException(ExceptionEnums.NO_FILE_NULL.getCode(), ExceptionEnums.NO_FILE_NULL.getMessage());
        }
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/force-download");
        // 设置编码，避免文件名中文乱码
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.toString().getBytes("gb2312"), "ISO8859-1"));
        outputStream.write(FileUtil.readBytes(file));
        IoUtil.close(outputStream);

    }

    /**
     * 文件流下载
     * @param response
     * @param fileBytes 文件流
     * @param fileName 文件名
     * @throws Exception
     */
    public static void downFile(HttpServletResponse response, byte[] fileBytes, String fileName) throws Exception{
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/force-download");
        // 设置编码，避免文件名中文乱码
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.toString().getBytes("gb2312"), "ISO8859-1"));
        outputStream.write(fileBytes);
        IoUtil.close(outputStream);

    }

}
