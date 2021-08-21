package com.starda.managesystem.util;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.util
 * @ClassName: ReadExcleUtil
 * @Author: chenqiu
 * @Description: 读取excle文件
 * @Date: 2021/7/30 11:32
 * @Version: 1.0
 */
public class ReadExcelUtil {

    /**
     * 读取excel 文件
     * @param filePath
     * @return
     */
    public static List<Object> readExcel(String filePath){

        List<Object> result = new ArrayList<>();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //获取sheet
            XSSFSheet sheet = sheets.getSheet("sheet1");
            ZipSecureFile.setMinInflateRatio(-1.0d);
            for(int i= 0; i<sheet.getLastRowNum();i++){
                XSSFRow row1 = sheet.getRow(i);
                for (int j = 0; j<row1.getLastCellNum();j++) {
                    String cell = row1.getCell(j).toString();
                    result.add(cell);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
