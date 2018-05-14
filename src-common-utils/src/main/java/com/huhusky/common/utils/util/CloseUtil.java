package com.huhusky.common.utils.util;


import java.io.Closeable;

/**
 * @author wuhuhu
 * @create 2017/5/31 16:37
 */
public class CloseUtil {

    public static void closeCloseable(Closeable obj) {
        try {
            if (obj != null) {
                obj.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
