package com.starda.managesystem.util;

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
public class ReadCSVUtil {

    public static ArrayList<String> readCsv(String filepath) {
        File csv = new File(filepath); // CSV文件路径
        csv.setReadable(true);//设置可读
        csv.setWritable(true);//设置可写
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
            while ((line = br.readLine()) != null) // 读取到的内容给line变量
            {
                everyLine = line;
                System.out.println(everyLine);
                allString.add(everyLine);
            }
            System.out.println("csv表格中所有行数：" + allString.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allString;
    }

    public static void main(String[] args) {
        readCsv("C:\\Users\\chenqiu\\Desktop\\cqqq.csv");
    }


}
