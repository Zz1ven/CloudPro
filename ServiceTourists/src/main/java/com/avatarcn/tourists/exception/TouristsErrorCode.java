package com.avatarcn.tourists.exception;

/**
 * Created by z1ven on 2018/1/18 10:48
 */
public class TouristsErrorCode extends ErrorCode {

    public static final TouristsErrorCode ACCOUNT_NULL = new TouristsErrorCode(101, "账号不存在");
    public static final TouristsErrorCode FEEDBACK_TYPE_REPEAT = new TouristsErrorCode(150, "反馈类型已存在");
    public static final TouristsErrorCode FEEDBACK_TYPE_NULL = new TouristsErrorCode(151, "反馈类型不存在");
    public static final TouristsErrorCode FEEDBACK_NULL = new TouristsErrorCode(152, "反馈信息不存在");

    public static final TouristsErrorCode REMARK_REPEAT = new TouristsErrorCode(201, "标签重复");
    public static final TouristsErrorCode ACTIVE_NULL = new TouristsErrorCode(202, "活动不存在");
    public static final TouristsErrorCode ASSESS_REPEAT = new TouristsErrorCode(203, "用户已评价过");
    public static final TouristsErrorCode ORDER_NULL = new TouristsErrorCode(204, "订单不存在");
    public static final TouristsErrorCode STATUS_NULL = new TouristsErrorCode(205, "状态不存在");

    public static final TouristsErrorCode NO_COUPON_TYPE = new TouristsErrorCode(301,"优惠劵类型不存在");
    public static final TouristsErrorCode NO_COUPON = new TouristsErrorCode(302,"优惠不存在");
    public static final TouristsErrorCode COUPON_OVERDUE = new TouristsErrorCode(303, "优惠券已过期");
    public static final TouristsErrorCode COUPON_INVALID = new TouristsErrorCode(304, "优惠券不可用");
    public static final TouristsErrorCode COUPON_USED = new TouristsErrorCode(305, "优惠券已使用");
    public static final TouristsErrorCode COUPON_RECEIVED = new TouristsErrorCode(306, "每个手机号只能领取一次");
    public static final TouristsErrorCode PROMOTION_NULL = new TouristsErrorCode(307, "优惠活动不存在");

    public static final TouristsErrorCode NO_FOOD_TYPE = new TouristsErrorCode(401,"菜系类型不存在");
    public static final TouristsErrorCode NO_ROOM = new TouristsErrorCode(402,"房间号不存在");
    public static final TouristsErrorCode NO_FOOD = new TouristsErrorCode(403,"菜系不存在");
    public static final TouristsErrorCode NO_FOOD_ORDER = new TouristsErrorCode(404,"菜系订单不存在");
    public static final TouristsErrorCode NO_ROOM_TIME = new TouristsErrorCode(405,"房间号时间不存在");
    public static final TouristsErrorCode ROOM_NO_FREE = new TouristsErrorCode(406, "房间已被预定");
    public static final TouristsErrorCode ORDER_TIME_OUT = new TouristsErrorCode(407, "预定时间已过现在时间");

    public static final TouristsErrorCode SPECIALITY_TYPE_NULL = new TouristsErrorCode(501, "特产类型不存在");
    public static final TouristsErrorCode SPECIALITY_TYPE_REPEAT = new TouristsErrorCode(502, "特产类型已存在");

    public static final TouristsErrorCode ATTRACTIONS_NULL = new TouristsErrorCode(601, "景点不存在");
    public static final TouristsErrorCode ATTRACTIONS_REPEAT = new TouristsErrorCode(602, "机器人已和其他景点绑定");

    public TouristsErrorCode(int code, String msg) {
        super(code, msg);
    }
}
