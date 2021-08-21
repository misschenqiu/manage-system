package com.starda.managesystem.util;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.ArrayList;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.util
 * @ClassName: ReadCSV
 * @Author: chenqiu
 * @Description: 读取csv文件、
 * @Date: 2021/7/30 11:19
 * @Version: 1.0
 */
@Log4j2
public class ReadCSVUtil {

    public static ArrayList<String> readCsv(String filepath) {
        // CSV文件路径
        File csv = new File(filepath);
        //设置可读
        csv.setReadable(true);
        //设置可写
        csv.setWritable(true);
        BufferedReader br = null;
        try {
            InputStreamReader gbReader = new InputStreamReader( new FileInputStream(csv), "GBK" );
            br = new BufferedReader(gbReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        ArrayList<String> allString = new ArrayList<>();
        try {
            // 读取到的内容给line变量
            while ((line = br.readLine()) != null) {
                everyLine = line;
                System.out.println(everyLine);
                allString.add(everyLine);
            }
            log.info("csv表格中所有行数：" + allString.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allString;
    }

    public static void main(String[] args) {
        readCsv("C:\\Users\\chenqiu\\Desktop\\cqqq.csv");
    }


}
