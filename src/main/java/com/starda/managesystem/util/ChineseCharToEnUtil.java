package com.starda.managesystem.util;

import lombok.extern.log4j.Log4j2;

import java.io.UnsupportedEncodingException;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.util
 * @ClassName: ChineseCharToEnUtil
 * @Author: chenqiu
 * @Description: 提取中文字符串首字母
 * @Date: 2021/8/27 23:06
 * @Version: 1.0
 */

@Log4j2
public class ChineseCharToEnUtil {

    private final static int[] li_SecPosValue = {1601, 1637, 1833, 2078, 2274,
            2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,
            4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590};
    private final static String[] lc_FirstLetter = {"a", "b", "c", "d", "e",
            "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "w", "x", "y", "z"};

    /**
     * 取得给定汉字串的首字母串,即声母串
     *
     * @param str 给定汉字串
     * @return 声母串
     */
    public String getAllFirstLetter(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }

        String _str = "";
        for (int i = 0; i < str.length(); i++) {
            _str = _str + this.getFirstLetter(str.substring(i, i + 1));
        }

        return _str;
    }

    /**
     * 取得给定汉字的首字母,即声母
     *
     * @param chinese 给定的汉字
     * @return 给定汉字的声母
     */
    public String getFirstLetter(String chinese) {
        if (chinese == null || chinese.trim().length() == 0) {
            return "";
        }
        chinese = this.conversionStr(chinese, "GB2312", "ISO8859-1");
        // 判断是不是汉字
        if (chinese.length() > 1) {
            // 汉字区码
            int li_SectorCode = (int) chinese.charAt(0);
            // 汉字位码
            int li_PositionCode = (int) chinese.charAt(1);
            li_SectorCode = li_SectorCode - 160;
            li_PositionCode = li_PositionCode - 160;
            // 汉字区位码
            int li_SecPosCode = li_SectorCode * 100 + li_PositionCode;
            if (li_SecPosCode > 1600 && li_SecPosCode < 5590) {
                for (int i = 0; i < 23; i++) {
                    if (li_SecPosCode >= li_SecPosValue[i]
                            && li_SecPosCode < li_SecPosValue[i + 1]) {
                        chinese = lc_FirstLetter[i];
                        break;
                    }
                }
            } else // 非汉字字符,如图形符号或ASCII码
            {
                chinese = this.conversionStr(chinese, "ISO8859-1", "GB2312");
                chinese = chinese.substring(0, 1);
            }
        }

        return chinese;
    }

    /**
     * 字符串编码转换
     *
     * @param str           要转换编码的字符串
     * @param charsetName   原来的编码
     * @param toCharsetName 转换后的编码
     * @return 经过编码转换后的字符串
     */
    private String conversionStr(String str, String charsetName, String toCharsetName) {
        try {
            str = new String(str.getBytes(charsetName), toCharsetName);
        } catch (UnsupportedEncodingException ex) {
            log.error("字符串编码转换异常：" + ex.getMessage());
            ex.printStackTrace();
        }
        return str;
    }

}
