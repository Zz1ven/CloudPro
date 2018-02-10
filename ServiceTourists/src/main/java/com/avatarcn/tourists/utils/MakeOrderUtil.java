package com.avatarcn.tourists.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by z1ven on 2018/1/19 16:30
 */
public class MakeOrderUtil {

    private static final Object lock = "lockOrder";
    //订单号计数器
    private static long orderNumCount = 0L;
    //每毫秒生成的订单号最大数量
    private static int maxPerMilliSecond = 1000;

    public static String makeOrderNum(String head) {
        synchronized (lock) {
            String nowStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            if (orderNumCount >= maxPerMilliSecond) {
                orderNumCount = 0L;
            }
            String orderNumber = String.valueOf(maxPerMilliSecond + orderNumCount).substring(1);
            orderNumCount ++;
            return head + nowStr.substring(2) + orderNumber;
        }
    }
}
