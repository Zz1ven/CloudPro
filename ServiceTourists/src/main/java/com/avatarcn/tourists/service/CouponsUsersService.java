package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.global.Constant;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.mapper.*;
import com.avatarcn.tourists.model.*;
import com.avatarcn.tourists.model.user.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CouponsUsersService{
	@Autowired
	private CouponsUsersMapper couponsUsersMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private ActiveOrderMapper activeOrderMapper;
	@Autowired
	private CouponsStatusMapper couponsStatusMapper;
	@Autowired
	private OrderFoodMapper orderFoodMapper;
	@Autowired
	private CouponsTypeMapper couponsTypeMapper;


	public CouponsUsers insert(Integer fk_tb_order_active_id, Integer fk_tb_coupons_status_id,Integer user_id, Integer fk_tb_coupon_id, Integer fk_tb_order_food_id)throws ErrorCodeException{
		CouponsUsers couponsUsers = new CouponsUsers();
		//判断活动订单是否存在...
		if (fk_tb_order_active_id!=null) {
			ActiveOrder activeOrder = activeOrderMapper.selectById(fk_tb_order_active_id);
			if (activeOrder == null)
				throw new ErrorCodeException(TouristsErrorCode.ACTIVE_NULL);
		}
		//判断优惠劵状态
		CouponsStatus couponsStatus=couponsStatusMapper.selectByPrimaryKey(fk_tb_coupons_status_id);
		if (couponsStatus==null)
			throw new ErrorCodeException(TouristsErrorCode.STATUS_NULL);

		//判断用户是否存在
		Account account=accountMapper.selectByServerId(String.valueOf(user_id));
		if (account==null)
			throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
		if(fk_tb_order_food_id!=null) {
			OrderFood orderFood = orderFoodMapper.selectByPrimaryKey(fk_tb_order_food_id);
			if (orderFood == null)
				throw new ErrorCodeException(TouristsErrorCode.NO_FOOD_ORDER);
		}
		couponsUsers.setFk_tb_order_active_id(fk_tb_order_active_id);
		couponsUsers.setFk_tb_coupons_status_id(fk_tb_coupons_status_id);
		couponsUsers.setFk_tb_user_id(account.getId());


		couponsUsers.setFk_tb_order_food_id(fk_tb_order_food_id);
		//查找优惠券是否存在以及是否在有效期内
		Coupon coupon = couponMapper.selectByPrimaryKey(fk_tb_coupon_id);
		if (coupon==null)
			throw new ErrorCodeException(TouristsErrorCode.NO_COUPON);
		couponsUsers.setFk_tb_coupon_id(fk_tb_coupon_id);
		Date now = new Date();
		if (now.after(coupon.getValid_start_time()) && now.before(coupon.getValid_end_time())) {
			couponsUsers.setVisible(true);
		} else if (now.before(coupon.getValid_start_time())) {
			couponsUsers.setVisible(false);
		} else {
			throw new ErrorCodeException(TouristsErrorCode.COUPON_OVERDUE);
		}
		couponsUsers.setTime(new Date());
		couponsUsersMapper.insert(couponsUsers);
		return couponsUsers;
	}

	public CouponsUsers update(Integer id,Integer fk_tb_order_active_id,Integer fk_tb_coupons_status_id,Integer fk_tb_user_id,Integer fk_tb_coupon_id,Integer fk_tb_order_food_id) throws ErrorCodeException{
		CouponsUsers couponsUsers = couponsUsersMapper.selectByPrimaryKey(id);
		if (couponsUsers == null) {
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		if (fk_tb_coupons_status_id != null) {
			couponsUsers.setFk_tb_coupons_status_id(fk_tb_coupons_status_id);
		}
		//判断用户是否存在
		if (fk_tb_user_id != null) {
			Account account = accountMapper.selectById(fk_tb_user_id);
			if (account == null) {
				throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
			}
			couponsUsers.setFk_tb_user_id(account.getId());
		}
		//查找优惠券是否存在以及是否在有效期内
		if (fk_tb_coupon_id != null) {
			Coupon coupon = couponMapper.selectByPrimaryKey(fk_tb_coupon_id);
			if (coupon == null) {
				throw new ErrorCodeException(TouristsErrorCode.NO_COUPON);
			}
			couponsUsers.setFk_tb_coupon_id(fk_tb_coupon_id);
			Date now = new Date();
			if (now.after(coupon.getValid_start_time()) && now.before(coupon.getValid_end_time())) {
				couponsUsers.setVisible(true);
			} else {
				couponsUsers.setVisible(false);
			}
		}
		//活动订单和餐饮订单关联
		couponsUsers.setFk_tb_order_active_id(fk_tb_order_active_id);
		couponsUsers.setFk_tb_order_food_id(fk_tb_order_food_id);
		couponsUsersMapper.update(couponsUsers);
		return couponsUsers;
	}

	public CouponsUsers selectByPrimaryKey(Integer id) throws ErrorCodeException{
		CouponsUsers couponsUsers = couponsUsersMapper.selectByPrimaryKey(id);
		if(couponsUsers == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		//判断用户的优惠劵是否有效
		Coupon coupon = couponsUsers.getCoupon();
		if (coupon==null){
			throw new ErrorCodeException(TouristsErrorCode.NO_COUPON);
		}
		Date now = new Date();
		if (now.after(coupon.getValid_start_time()) && now.before(coupon.getValid_end_time())){
			if (!couponsUsers.getVisible()) {
				couponsUsers.setVisible(true);
				couponsUsersMapper.update(couponsUsers);
			}
		}else {
			if (couponsUsers.getVisible()) {
				couponsUsers.setVisible(false);
				couponsUsersMapper.update(couponsUsers);
			}
		}
		return couponsUsers;
	}

	public PageResponse<CouponsUsers> selectPage(Integer offset, Integer pageSize){
		PageResponse<CouponsUsers> response = new PageResponse<>();
		List<CouponsUsers> couponsUsersList = couponsUsersMapper.selectPage(offset,pageSize);
		Date now = new Date();
		for (CouponsUsers couponsUsers : couponsUsersList) {
			Coupon coupon = couponsUsers.getCoupon();
			boolean visible = now.after(coupon.getValid_start_time()) && now.before(coupon.getValid_end_time());
			if (!visible && couponsUsers.getVisible()) {
				couponsUsers.setVisible(false);
				couponsUsersMapper.update(couponsUsers);
			} else if (visible && !couponsUsers.getVisible()) {
				couponsUsers.setVisible(true);
				couponsUsersMapper.update(couponsUsers);
			}
		}
		response.setItem(couponsUsersList);
		response.setTotal(couponsUsersMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	//获得用户下有效的优惠券
	public AllResponse<CouponsUsers> selectByUser(Integer user_id)throws ErrorCodeException{
		Account account=accountMapper.selectByServerId(String.valueOf(user_id));
		if (account==null)
			throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
		List<CouponsUsers> couponsUsersList = couponsUsersMapper.selectByUser(account.getId());
		Date now = new Date();
		for (CouponsUsers couponsUsers : couponsUsersList) {
			Coupon coupon = couponsUsers.getCoupon();
			boolean visible = now.after(coupon.getValid_start_time()) && now.before(coupon.getValid_end_time());
			if (!visible && couponsUsers.getVisible()) {
				couponsUsers.setVisible(false);
				couponsUsersMapper.update(couponsUsers);
			} else if (visible && !couponsUsers.getVisible()) {
				couponsUsers.setVisible(true);
				couponsUsersMapper.update(couponsUsers);
			}
		}
		//查找用户下有效的优惠券
		List<CouponsUsers> couponsUsersListVlid = couponsUsersMapper.selectByUserAndValid(account.getId());
		AllResponse<CouponsUsers> allResponse = new AllResponse<>();
		allResponse.setItem(couponsUsersListVlid);
		allResponse.setTotal(couponsUsersListVlid.size());

		return allResponse;
	}

	//获取用户下失效的优惠券
	public AllResponse<CouponsUsers> selectByUserUnvalid(Integer user_id)throws ErrorCodeException{
		Account account=accountMapper.selectByServerId(String.valueOf(user_id));
		if (account==null)
			throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
		List<CouponsUsers> couponsUsersList = couponsUsersMapper.selectByUser(account.getId());
		Date now = new Date();
		for (CouponsUsers couponsUsers : couponsUsersList) {
			Coupon coupon = couponsUsers.getCoupon();
			boolean visible = now.after(coupon.getValid_start_time()) && now.before(coupon.getValid_end_time());
			if (!visible && couponsUsers.getVisible()) {
				couponsUsers.setVisible(false);
				couponsUsersMapper.update(couponsUsers);
			} else if (visible && !couponsUsers.getVisible()) {
				couponsUsers.setVisible(true);
				couponsUsersMapper.update(couponsUsers);
			}
		}
		//查找用户下失效的优惠券
		List<CouponsUsers> couponsUsersListVlid = couponsUsersMapper.selectByUserAndUnValid(account.getId());
		AllResponse<CouponsUsers> allResponse = new AllResponse<>();
		allResponse.setItem(couponsUsersListVlid);
		allResponse.setTotal(couponsUsersListVlid.size());
		return allResponse ;
	}

	public AllResponse<CouponsUsers> selectUserCouponsByOrderInfo(Integer userId, Integer coupon_type_id, float money) throws ErrorCodeException {
		Account account = accountMapper.selectByServerId(String.valueOf(userId));
		if (account == null) {
			throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
		}
		CouponsType couponsType = couponsTypeMapper.selectByPrimaryKey(coupon_type_id);
		if (couponsType == null) {
			throw new ErrorCodeException(TouristsErrorCode.NO_COUPON_TYPE);
		}
		List<CouponsUsers> couponsUsersList = couponsUsersMapper.selectByUserAndValid(account.getId());
		List<CouponsUsers> couponsUsersValid = new ArrayList<>();
		Date now = new Date();
		for (CouponsUsers couponsUsers : couponsUsersList) {
			Coupon coupon = couponsUsers.getCoupon();
			boolean visible = now.after(coupon.getValid_start_time()) && now.before(coupon.getValid_end_time());
			if (visible && money >= coupon.getSpend_money() && coupon.getFk_tb_coupons_type_id().intValue() == coupon_type_id) {
				couponsUsersValid.add(couponsUsers);
			}
		}
		AllResponse<CouponsUsers> allResponse = new AllResponse<>();
		allResponse.setItem(couponsUsersValid);
		allResponse.setTotal(couponsUsersValid.size());
		return allResponse;
	}

	public int deleteByPrimaryKey(Integer id){
		return couponsUsersMapper.deleteByPrimaryKey(id);
	}

	public List<CouponsUsers> selectByActiveOrderId(Integer activeOrderId) {
		return couponsUsersMapper.selectByActiveOrderId(activeOrderId);
	}

	/**
	 * 活动订单使用一个用户优惠券,返回订单实际金额
	 * @param id
	 * @param accountId
	 * @param active_order_id
	 * @return
	 */
	public float activeUseCoupons(Integer id, Integer accountId, Integer active_order_id) throws ErrorCodeException {
		CouponsUsers couponsUsers = couponsUsersMapper.selectByPrimaryKey(id);
		if (couponsUsers == null) {
			throw new ErrorCodeException(TouristsErrorCode.NO_COUPON);
		}
		//是否为活动优惠券
		if (couponsUsers.getCoupon().getFk_tb_coupons_type_id() != Constant.COUPON_TYPE_ACTIVE) {
			throw new ErrorCodeException(TouristsErrorCode.COUPON_INVALID);
		}
		ActiveOrder activeOrder = activeOrderMapper.selectById(active_order_id);
		if (activeOrder == null) {
			throw new ErrorCodeException(TouristsErrorCode.ORDER_NULL);
		}
		//当前用户的优惠券
		if (accountId != couponsUsers.getFk_tb_user_id().intValue()) {
			throw new ErrorCodeException(TouristsErrorCode.NO_COUPON);
		}
		//优惠券是否有效
		Date now = new Date();
		if (!(now.after(couponsUsers.getCoupon().getValid_start_time()) && now.before(couponsUsers.getCoupon().getValid_end_time()))) {
			throw new ErrorCodeException(TouristsErrorCode.COUPON_INVALID);
		}
		//优惠券是否可使用
		if (couponsUsers.getFk_tb_coupons_status_id() != Constant.COUPON_UNUSED) {
			throw new ErrorCodeException(TouristsErrorCode.COUPON_USED);
		}
		//订单是否达到最低消费限额
		if (activeOrder.getReal_money() < couponsUsers.getCoupon().getSpend_money()) {
			throw new ErrorCodeException(TouristsErrorCode.COUPON_INVALID);
		}
		couponsUsers.setFk_tb_order_active_id(active_order_id);
		couponsUsers.setFk_tb_order_food_id(null);
		couponsUsersMapper.update(couponsUsers);
		//计算订单的实际金额
		float realMoney = activeOrder.getReal_money() - couponsUsers.getCoupon().getMoney();
		activeOrder.setReal_money(realMoney);
		activeOrderMapper.update(activeOrder);
		return realMoney;
	}

	/**
	 * 餐饮订单使用一个用户优惠券,返回订单实际金额
	 * @param id
	 * @param accountId
	 * @param food_order_id
	 * @return
	 */
	public float foodUseCoupons(Integer id, Integer accountId, Integer food_order_id) throws ErrorCodeException {
		CouponsUsers couponsUsers = couponsUsersMapper.selectByPrimaryKey(id);
		if (couponsUsers == null) {
			throw new ErrorCodeException(TouristsErrorCode.NO_COUPON);
		}
		//是否为餐饮优惠券
		if (couponsUsers.getCoupon().getFk_tb_coupons_type_id() != Constant.COUPON_TYPE_FOOD) {
			throw new ErrorCodeException(TouristsErrorCode.COUPON_INVALID);
		}
		OrderFood orderFood = orderFoodMapper.selectByPrimaryKey(food_order_id);
		if (orderFood == null) {
			throw new ErrorCodeException(TouristsErrorCode.NO_FOOD_ORDER);
		}
		//当前用户的优惠券
		if (accountId != couponsUsers.getFk_tb_user_id().intValue()) {
			throw new ErrorCodeException(TouristsErrorCode.NO_COUPON);
		}
		//优惠券是否有效
		Date now = new Date();
		if (!(now.after(couponsUsers.getCoupon().getValid_start_time()) && now.before(couponsUsers.getCoupon().getValid_end_time()))) {
			throw new ErrorCodeException(TouristsErrorCode.COUPON_INVALID);
		}
		//优惠券是否可使用
		if (couponsUsers.getFk_tb_coupons_status_id() != Constant.COUPON_UNUSED) {
			throw new ErrorCodeException(TouristsErrorCode.COUPON_USED);
		}
		//订单是否达到最低消费限额
		if (orderFood.getReal_money() < couponsUsers.getCoupon().getSpend_money()) {
			throw new ErrorCodeException(TouristsErrorCode.COUPON_INVALID);
		}
		couponsUsers.setFk_tb_order_active_id(null);
		couponsUsers.setFk_tb_order_food_id(food_order_id);
		couponsUsersMapper.update(couponsUsers);
		//计算订单的实际金额
		float realMoney = orderFood.getReal_money() - couponsUsers.getCoupon().getMoney();
		orderFood.setReal_money(realMoney);
		orderFoodMapper.update(orderFood);
		return realMoney;
	}
}
