package com.huhusky.common.utils.util;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by rzd on 2017/8/17.
 */
public class ValidateUtil {
    public static boolean isValidStringProperty(String content, int length) {
        if(StringUtils.isBlank(content)||content.length()>length){
            return false;
        }
        return true;
    }

    public static boolean isValidMobie(String content) {
        if (RegexUtil.isMatch(content, "^\\d{11}$")) {
            return true;
        }

        return false;
    }

    public static boolean isValidEmail(String content) {
        String pattern = "[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
        if(RegexUtil.isMatch(content, pattern)) { /*".+@\\w+.com$"*/
            return true;
        }
        return false;
    }
}
