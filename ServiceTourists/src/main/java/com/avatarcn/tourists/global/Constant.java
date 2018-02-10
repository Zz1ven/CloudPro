package com.avatarcn.tourists.global;

/**
 * Created by z1ven on 2018/1/17 16:22
 */
public class Constant {
    public static final String TOURISTS_TMP_DIR = "tourists/tmp";
    public static final String TOURISTS_NEWS_DIR = "tourists/news";
    public static final String TOURISTS_ACTIVE_DIR = "tourists/active";
    public static final String TOURISTS_ROOM_DIR = "tourists/room";
    public static final String TOURISTS_FEEDBACK_DIR = "tourists/feedback";
    public static final String TOURISTS_SPECIALITY_DIR = "tourists/speciality";
    public static final String TOURISTS_RECOMMENDATION_DIR = "tourists/recommendation";
    public static final String TOURISTS_ATTRACTIONS_DIR = "tourists/attractions";

    //订单状态
    public static final int ORDER_COMMITTED = 1;//已提交
    public static final int ORDER_CANCELLED = 2;//已取消
    public static final int ORDER_PAID = 3;//已支付
    public static final int ORDER_COMPLETED = 4;//已完成

    //优惠券使用状态
    public static final int COUPON_UNUSED = 1;//未使用
    public static final int COUPON_USED = 2;//已使用

    //优惠券类型
    public static final int COUPON_TYPE_FOOD = 1;//餐饮券
    public static final int COUPON_TYPE_ACTIVE = 2;//活动券
    public static final int COUPON_TYPE_GOODS = 3;//商品券
}
