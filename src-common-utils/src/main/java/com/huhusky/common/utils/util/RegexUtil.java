package com.huhusky.common.utils.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rzd on 2017/8/17.
 */
public class RegexUtil {
    public static boolean isMatch(String content,String regex)
    {
        if(content==null){
            return false;
        }
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(content);
        boolean isFind=matcher.find();
        if(isFind){
            return true;
        }
        return false;
    }
}
