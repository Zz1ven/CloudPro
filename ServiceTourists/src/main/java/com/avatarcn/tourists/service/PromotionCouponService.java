package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.global.Constant;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.mapper.CouponMapper;
import com.avatarcn.tourists.mapper.PromotionCouponMapper;
import com.avatarcn.tourists.mapper.PromotionMapper;
import com.avatarcn.tourists.mapper.PromotionRecordMapper;
import com.avatarcn.tourists.model.Coupon;
import com.avatarcn.tourists.model.PromotionCoupon;
import com.avatarcn.tourists.model.PromotionRecord;
import com.avatarcn.tourists.model.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by z1ven on 2018/2/7 16:25
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 60000, rollbackFor = Exception.class)
public class PromotionCouponService {

    @Autowired
    private PromotionCouponMapper promotionCouponMapper;

    @Autowired
    private PromotionMapper promotionMapper;

    @Autowired
    private PromotionRecordMapper promotionRecordMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private UserServiceFeign userServiceFeign;

    @Autowired
    private CouponsUsersService couponsUsersService;

    public PromotionCoupon insert(Integer promotionId, Integer couponId) throws ErrorCodeException {
        if (promotionMapper.selectById(promotionId) == null) {
            throw new ErrorCodeException(TouristsErrorCode.PROMOTION_NULL);
        }
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        if (coupon == null) {
            throw new ErrorCodeException(TouristsErrorCode.NO_COUPON);
        }
        Date now = new Date();
        if (now.after(coupon.getValid_end_time())) {
            throw new ErrorCodeException(TouristsErrorCode.COUPON_OVERDUE);
        }
        PromotionCoupon promotionCoupon = new PromotionCoupon();
        promotionCoupon.setFk_tb_promotion_id(promotionId);
        promotionCoupon.setFk_tb_coupon_id(couponId);
        promotionCouponMapper.insert(promotionCoupon);
        promotionCoupon.setCoupon(coupon);
        return promotionCoupon;
    }

    public PromotionCoupon selectById(Integer id) throws ErrorCodeException {
        PromotionCoupon promotionCoupon = promotionCouponMapper.selectById(id);
        if (promotionCoupon == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        promotionCoupon.setCoupon(couponMapper.selectByPrimaryKey(promotionCoupon.getFk_tb_coupon_id()));
        return promotionCoupon;
    }

    public AllResponse<PromotionCoupon> selectAllByPromotionId(Integer promotionId) throws ErrorCodeException {
        if (promotionMapper.selectById(promotionId) == null) {
            throw new ErrorCodeException(TouristsErrorCode.PROMOTION_NULL);
        }
        List<PromotionCoupon> promotionCouponList = promotionCouponMapper.selectByPromotionId(promotionId);
        AllResponse<PromotionCoupon> promotionCouponAllResponse = new AllResponse<>();
        promotionCouponAllResponse.setItem(promotionCouponList);
        promotionCouponAllResponse.setTotal(promotionCouponList.size());
        return promotionCouponAllResponse;
    }

    public int deleteById(Integer id) throws ErrorCodeException {
        if (promotionCouponMapper.delete(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_NO);
        }
        return 1;
    }

    public PromotionCoupon update(Integer id, Integer promotionId, Integer couponId) throws ErrorCodeException {
        PromotionCoupon promotionCoupon = promotionCouponMapper.selectById(id);
        if (promotionCoupon == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        if (promotionId != promotionCoupon.getFk_tb_promotion_id()) {
            if (promotionMapper.selectById(promotionId) == null) {
                throw new ErrorCodeException(TouristsErrorCode.PROMOTION_NULL);
            }
            promotionCoupon.setFk_tb_promotion_id(promotionId);
        }
        if (couponId != promotionCoupon.getFk_tb_coupon_id()) {
            Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
            if (coupon == null) {
                throw new ErrorCodeException(TouristsErrorCode.NO_COUPON);
            }
            Date now = new Date();
            if (now.after(coupon.getValid_end_time())) {
                throw new ErrorCodeException(TouristsErrorCode.COUPON_OVERDUE);
            }
            promotionCoupon.setFk_tb_coupon_id(couponId);
            promotionCoupon.setCoupon(coupon);
        }
        promotionCouponMapper.update(promotionCoupon);
        return promotionCoupon;
    }

    public AllResponse<Coupon> receivePromotionCoupon(Integer promotion_id, String phone) throws ErrorCodeException {
        if (promotionMapper.selectById(promotion_id) == null) {
            throw new ErrorCodeException(TouristsErrorCode.PROMOTION_NULL);
        }
        JsonBean<UserInfo> userInfoJsonBean = userServiceFeign.getUserByPhone(phone);
        if (!userInfoJsonBean.isSuccess()) {
            throw new ErrorCodeException(new ErrorCode(userInfoJsonBean.getError_code(), userInfoJsonBean.getMsg()));
        }
        PromotionRecord promotionRecord = promotionRecordMapper.selectByPromotionIdAndPhone(promotion_id, phone);
        //已领取过
        if (promotionRecord != null) {
            throw new ErrorCodeException(TouristsErrorCode.COUPON_RECEIVED);
        }
        List<PromotionCoupon> promotionCouponList = promotionCouponMapper.selectByPromotionId(promotion_id);
        List<Coupon> couponList = new ArrayList<>();
        for (PromotionCoupon promotionCoupon : promotionCouponList) {
            couponsUsersService.insert(null, Constant.COUPON_UNUSED, userInfoJsonBean.getData().getUser_id(), promotionCoupon.getFk_tb_coupon_id(), null);
            couponList.add(promotionCoupon.getCoupon());
        }
        //添加领取记录
        promotionRecord = new PromotionRecord();
        promotionRecord.setFk_tb_promotion_id(promotion_id);
        promotionRecord.setPhone(phone);
        promotionRecord.setTime(new Date());
        promotionRecordMapper.insert(promotionRecord);
        AllResponse<Coupon> couponAllResponse = new AllResponse<>();
        couponAllResponse.setItem(couponList);
        couponAllResponse.setTotal(couponList.size());
        return couponAllResponse;
    }
}
