package com.eeepay.superbanker.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Author：Wq
 * Date：2018/1/10 15:34
 * Description：//todo
 */

public class StringUtils {

    /**
     * @// TODO: 2018/1/10  字符串保留两位小数 
     * 
     * */
    public static String float2(String value){
        if (null==value||TextUtils.isEmpty(value) || ".".equals(value))
            return "0.00";
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.toString();
    }

    /**
     * 字符型数字转化成double类型：保留两位小数
     *
     * @param str
     * @return
     */
    public static double str2Double(String str) {//11.0  11.25896
        double digit;
        str = ".".equals(str) ? "0" : str;
        str = TextUtils.isEmpty(str) ? "0" : str;
        str = float2(str);//11.00   11.26
        digit = Double.valueOf(str);
        return digit;//11.0   11.26
    }



    /**
     * 规则：至少包含大小写字母及数字中的两种
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }



    /**
     * 规则：至少包含大小写字母及数字中的两种
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigitUserLength(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        boolean isLength=false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        if(6<=str.length()&&str.length()<=16){
            isLength=true;
        }

        String regex = "^[a-zA-Z0-9]+$";

        boolean isRight =isLength && isDigit && isLetter && str.matches(regex);
        return isRight;
    }
}
