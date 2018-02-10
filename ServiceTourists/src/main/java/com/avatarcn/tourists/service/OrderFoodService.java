package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.global.Constant;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.mapper.*;
import com.avatarcn.tourists.model.*;
import com.avatarcn.tourists.model.user.Account;
import com.avatarcn.tourists.utils.MakeOrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 60000, rollbackFor = Exception.class)
public class OrderFoodService{
	@Autowired
	private OrderFoodMapper orderFoodMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private RoomMapper roomMapper;
	@Autowired
	private OrderStatusMapper orderStatusMapper;
	@Autowired
	private FoodMapper foodMapper;
	@Autowired
	private OrderFoodMenuMapper orderFoodMenuMapper;
	@Autowired
	private CouponsUsersMapper couponsUsersMapper;
	@Autowired
	private TimestampMapper timestampMapper;
	@Autowired
	private RoomTimeMapper roomTimeMapper;
	@Autowired
	private CouponsUsersService couponsUsersService;


	public OrderFood insert(Integer fk_tb_room_id,Integer fk_tb_time_id, Integer fk_tb_order_status_id, Integer fk_tb_user_id,List<FoodMenu> foodMenuList, long reserve_date, Integer coupons_user_id)throws ErrorCodeException{
		Account account = accountMapper.selectByServerId(String.valueOf(fk_tb_user_id));
		if (account == null) {
			throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
		}
		Room room=roomMapper.selectByPrimaryKey(fk_tb_room_id);
		if (room==null){
			throw new ErrorCodeException(TouristsErrorCode.NO_ROOM);
		}
		OrderStatus orderStatus = orderStatusMapper.selectById(fk_tb_order_status_id);
		if (orderStatus == null) {
			throw new ErrorCodeException(TouristsErrorCode.ORDER_NULL);
		}
        Timestamp timestamp = timestampMapper.selectByPrimaryKey(fk_tb_time_id);
		if (timestamp==null){
			throw new ErrorCodeException(TouristsErrorCode.NO_ROOM_TIME );
		}
		//判断预定的日期是否超过当前日期
		Date now=new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
        Date reserve=new Date(reserve_date);
		String res = simpleDateFormat.format(reserve);
		String start=simpleDateFormat1.format(timestamp.getStart_time());
		//拼接转换成时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(res+" "+start);
			boolean visible = now.after(date);
			if (visible){
				throw new ErrorCodeException(TouristsErrorCode.ORDER_TIME_OUT);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}


		//生成订单
		OrderFood orderFood = new OrderFood();
		//计算价格
		if (foodMenuList != null && !foodMenuList.isEmpty()) {
			Float total_money = 0f;
			Float real_money = 0f;
			for (FoodMenu foodMenu : foodMenuList) {
				Food food = foodMapper.selectByPrimaryKey(foodMenu.getFood_id());
				total_money = total_money + food.getPrice() * foodMenu.getAmount();
				real_money = real_money + food.getPrice() * foodMenu.getAmount() * food.getRebate_id();
			}
			orderFood.setTotal_money(total_money);
			orderFood.setReal_money(real_money);
		}
		//生成房间号与时间明细
		RoomTime roomTime=new RoomTime();
		roomTime.setFk_tb_room_id(fk_tb_room_id);
		roomTime.setFk_tb_time_id(fk_tb_time_id);
		roomTime.setTimestamp(timestamp);
		roomTime.setRoom(room);
		roomTime.setTime(new java.sql.Date(reserve_date));
		if (roomTimeMapper.selectByRoomAndTimeId(roomTime) > 0) {
			throw new ErrorCodeException(TouristsErrorCode.ROOM_NO_FREE);
		}
		roomTimeMapper.insert(roomTime);
		orderFood.setFk_tb_room_time_id(roomTime.getId());
		orderFood.setFk_tb_order_status_id(fk_tb_order_status_id);
		orderFood.setFk_tb_user_id(account.getId());
		orderFood.setNumber(MakeOrderUtil.makeOrderNum("food"));
		orderFood.setTime(new Date());
		orderFoodMapper.insert(orderFood);
		//使用优惠券
		if (coupons_user_id != null) {
			float realMoney = couponsUsersService.foodUseCoupons(coupons_user_id, account.getId(), orderFood.getId());
			orderFood.setReal_money(realMoney);
		}
		//生成订单明细
		if (foodMenuList != null && !foodMenuList.isEmpty()) {
			for (FoodMenu foodMenu : foodMenuList) {
				OrderFoodMenu orderFoodMenu=new OrderFoodMenu();
				orderFoodMenu.setFk_tb_order_food_id(orderFood.getId());
				orderFoodMenu.setFk_tb_food_id(foodMenu.getFood_id());
				orderFoodMenu.setAmount(foodMenu.getAmount());
				orderFoodMenuMapper.insert(orderFoodMenu);
			}
		}
       	orderFood.setRoomTime(roomTime);
		orderFood.setOrderStatus(orderStatus);
		orderFood.setOrderFoodMenus(orderFoodMenuMapper.selectAll(orderFood.getId()));

		return orderFood;
	}

	public OrderFood update(Integer id,Integer fk_tb_order_status_id,Float real_money) throws ErrorCodeException{
		OrderFood orderFood = orderFoodMapper.selectCascadeById(id);
		if(orderFood == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		OrderStatus orderStatus = orderStatusMapper.selectById(fk_tb_order_status_id);
		if (orderStatus == null) {
			throw new ErrorCodeException(TouristsErrorCode.STATUS_NULL);
		}
		orderFood.setFk_tb_order_status_id(fk_tb_order_status_id);
		orderFood.setReal_money(real_money);
		orderFood.setTime(new Date());
		orderFoodMapper.update(orderFood);
		orderFood.setOrderStatus(orderStatus);
		return orderFood;
	}

	public OrderFood selectByPrimaryKey(Integer id) throws ErrorCodeException{
		OrderFood orderFood = orderFoodMapper.selectCascadeById(id);
		if(orderFood == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return orderFood;
	}

	public PageResponse<OrderFood> selectPage(Integer userId,Integer offset, Integer pageSize)throws ErrorCodeException{
		Account account = accountMapper.selectByServerId(String.valueOf(userId));
		if (account == null) {
			throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
		}
		PageResponse<OrderFood> response = new PageResponse<>();
		List<OrderFood> orderFoods=orderFoodMapper.selectPage(account.getId(),offset,pageSize);
		response.setItem(orderFoods);
		response.setTotal(orderFoodMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id)throws ErrorCodeException{
		//删除该订单使用的优惠券
		couponsUsersMapper.deleteByActiveOrderId(id);
		//删除订单明细表
		orderFoodMenuMapper.deleteByOrderFoodId(id);
		if (orderFoodMapper.deleteByPrimaryKey(id) != 1) {
			throw new ErrorCodeException(ErrorCodeException.DELETE_NO);
		}
		return 1;
	}

	/**
	 * 支付完成后的菜系订单回调
	 * @param id
	 * @return
	 */
	public OrderFood payFoodOrder(Integer id) throws ErrorCodeException {
		OrderFood orderFood = orderFoodMapper.selectCascadeById(id);
		if (orderFood == null) {
			throw new ErrorCodeException(TouristsErrorCode.ORDER_NULL);
		}
		List<CouponsUsers> couponsUsersList = couponsUsersMapper.selectByFoodOrderId(id);
		for (CouponsUsers couponsUsers : couponsUsersList) {
			couponsUsers.setFk_tb_coupons_status_id(Constant.COUPON_USED);
			couponsUsersMapper.update(couponsUsers);
		}
		orderFood.setFk_tb_order_status_id(Constant.ORDER_PAID);
		orderFoodMapper.update(orderFood);
		return orderFood;
	}

}
