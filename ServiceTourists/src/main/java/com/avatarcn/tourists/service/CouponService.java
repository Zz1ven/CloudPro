package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.mapper.CouponMapper;
import com.avatarcn.tourists.mapper.CouponsTypeMapper;
import com.avatarcn.tourists.mapper.CouponsUsersMapper;
import com.avatarcn.tourists.mapper.PromotionCouponMapper;
import com.avatarcn.tourists.model.Coupon;
import com.avatarcn.tourists.model.CouponsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CouponService{
	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private CouponsTypeMapper couponsTypeMapper;
	@Autowired
	private CouponsUsersMapper couponsUsersMapper;
	@Autowired
	private PromotionCouponMapper promotionCouponMapper;

	public Coupon insert(Integer fk_tb_coupons_type_id, String name, Float money, Float spend_money, String explains, long valid_start_time, long valid_end_time) throws ErrorCodeException{
        CouponsType couponsType=couponsTypeMapper.selectByPrimaryKey(fk_tb_coupons_type_id);
		if (couponsType==null)
			throw new ErrorCodeException(TouristsErrorCode.NO_COUPON_TYPE);
		Coupon coupon = new Coupon();
		coupon.setFk_tb_coupons_type_id(fk_tb_coupons_type_id);
		coupon.setName(name);
		coupon.setMoney(money);
		coupon.setSpend_money(spend_money);
		coupon.setExplains(explains);
		coupon.setValid_start_time(new Date(valid_start_time));
		coupon.setValid_end_time(new Date(valid_end_time));
		coupon.setCreate_time(new Date());
		couponMapper.insert(coupon);
		coupon.setCouponsType(couponsType);
		return coupon;
	}

	public Coupon update(Integer id,Integer fk_tb_coupons_type_id,String name,Float money,Float spend_money,String explains,long valid_start_time,long valid_end_time) throws ErrorCodeException{
		Coupon coupon = couponMapper.selectByPrimaryKey(id);
		if(coupon == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		if(fk_tb_coupons_type_id != null && coupon.getFk_tb_coupons_type_id().intValue() != fk_tb_coupons_type_id.intValue()){
			CouponsType couponsType=couponsTypeMapper.selectByPrimaryKey(fk_tb_coupons_type_id);
			if (couponsType==null) {
				throw new ErrorCodeException(TouristsErrorCode.NO_COUPON_TYPE);
			}
			coupon.setFk_tb_coupons_type_id(fk_tb_coupons_type_id);
			coupon.setCouponsType(couponsType);
		}
		if(name != null){
			coupon.setName(name);
		}
		if(money != null){
			coupon.setMoney(money);
		}
		if(spend_money != null){
			coupon.setSpend_money(spend_money);
		}
		if(explains != null){
			coupon.setExplains(explains);
		}
		coupon.setValid_start_time(new Date(valid_start_time));
		coupon.setValid_end_time(new Date(valid_end_time));
		coupon.setCreate_time(new Date());
		couponMapper.update(coupon);
		return coupon;
	}

	public Coupon selectByPrimaryKey(Integer id) throws ErrorCodeException{
		Coupon coupon = couponMapper.selectByPrimaryKey(id);
		if(coupon == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return coupon;
	}

	public PageResponse<Coupon> selectPage(Integer offset, Integer pageSize)throws ErrorCodeException{
		PageResponse<Coupon> response = new PageResponse<>();
		response.setItem(couponMapper.selectPage(offset,pageSize));
		response.setTotal(couponMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		//先删除用户下的优惠券
		couponsUsersMapper.deleteByCouponId(id);
		//删除关联的优惠活动的优惠券
		promotionCouponMapper.deleteByCouponId(id);
		return couponMapper.deleteByPrimaryKey(id);
	}

	public AllResponse<Coupon> selectAllValid() throws ErrorCodeException {
		AllResponse<Coupon> allResponse = new AllResponse<>();
		List<Coupon> couponList = couponMapper.selectAll();
		List<Coupon> validCouponList = new ArrayList<>();
		Date now = new Date();
		for (Coupon coupon : couponList) {
			if (now.before(coupon.getValid_end_time())) {
				validCouponList.add(coupon);
			}
		}
		allResponse.setItem(validCouponList);
		allResponse.setTotal(validCouponList.size());
		return allResponse;
	}

}
