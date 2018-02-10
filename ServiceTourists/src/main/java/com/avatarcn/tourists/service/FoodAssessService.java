package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.FoodAssessResponse;
import com.avatarcn.tourists.json.response.PageFoodAssessResponse;
import com.avatarcn.tourists.mapper.AccountMapper;
import com.avatarcn.tourists.mapper.FoodAssessMapper;
import com.avatarcn.tourists.mapper.OrderFoodMapper;
import com.avatarcn.tourists.model.FoodAssess;
import com.avatarcn.tourists.model.OrderFood;
import com.avatarcn.tourists.model.user.Account;
import com.avatarcn.tourists.model.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class FoodAssessService{
	@Autowired
	private UserServiceFeign userServiceFeign;
	@Autowired
	private OrderFoodMapper orderFoodMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private FoodAssessMapper foodAssessMapper;

	public FoodAssessResponse insert(Integer userId, Integer fk_tb_food_order_id, Float score, String assess)throws ErrorCodeException{
		JsonBean<UserInfo> userInfoJsonBean = userServiceFeign.info_get(userId);
		if (!userInfoJsonBean.isSuccess()) {
			throw new ErrorCodeException(new ErrorCode(userInfoJsonBean.getError_code(), userInfoJsonBean.getMsg()));
		}
		OrderFood orderFood=orderFoodMapper.selectByPrimaryKey(fk_tb_food_order_id);
		if (orderFood==null){
			throw new ErrorCodeException(TouristsErrorCode.ORDER_NULL);
		}

		Account account = accountMapper.selectByServerId(String.valueOf(userId));
		if (account == null) {
			throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
		}
		int count = foodAssessMapper.countByUserIdAndFoodId(account.getId(),fk_tb_food_order_id);
		if (count > 0) {//已经评论过
			throw new ErrorCodeException(TouristsErrorCode.ASSESS_REPEAT);
		}
		FoodAssess foodAssess = new FoodAssess();
		foodAssess.setFk_tb_user_id(account.getId());
		foodAssess.setFk_tb_food_order_id(fk_tb_food_order_id);
		foodAssess.setScore(score);
		foodAssess.setAssess(assess);
		foodAssess.setTime(new Date());
		foodAssessMapper.insert(foodAssess);
		FoodAssessResponse foodAssessResponse=new FoodAssessResponse();
		foodAssessResponse.setId(foodAssess.getId());
		foodAssessResponse.setScore(foodAssess.getScore());
		foodAssessResponse.setUser_id(account.getId());
		foodAssessResponse.setAssess(foodAssess.getAssess());
		foodAssessResponse.setNickname(userInfoJsonBean.getData().getNickname());
		foodAssessResponse.setUserUrl(userInfoJsonBean.getData().getImg());
		foodAssessResponse.setFood_order_id(foodAssess.getFk_tb_food_order_id());
		foodAssessResponse.setTime(foodAssess.getTime());
		return foodAssessResponse;
	}

	public FoodAssess update(Integer id,Integer fk_tb_user_id,Integer fk_tb_food_order_id,Float score,String assess) throws ErrorCodeException {
		FoodAssess foodAssess = foodAssessMapper.selectByPrimaryKey(id);
		if(foodAssess == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		if(fk_tb_user_id != null){
			foodAssess.setFk_tb_user_id(fk_tb_user_id);
		}
		if(fk_tb_food_order_id != null){
			foodAssess.setFk_tb_food_order_id(fk_tb_food_order_id);
		}
		if(score != null){
			foodAssess.setScore(score);
		}
		if(assess != null){
			foodAssess.setAssess(assess);
		}
			foodAssess.setTime(new Date());
		foodAssessMapper.update(foodAssess);
		return foodAssess;
	}

	public FoodAssess selectByPrimaryKey(Integer id) throws ErrorCodeException{
		FoodAssess foodAssess = foodAssessMapper.selectByPrimaryKey(id);
		if(foodAssess == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return foodAssess;
	}

	public PageFoodAssessResponse selectPage(Integer fk_tb_food_order_id,Integer offset, Integer pageSize)throws ErrorCodeException{
		PageFoodAssessResponse pageFoodAssessResponse=new PageFoodAssessResponse();
		List<FoodAssessResponse> foodAssessResponses=new ArrayList<>();
		List<FoodAssess>foodAssesses=foodAssessMapper.selectPage(fk_tb_food_order_id,offset,pageSize);
        for (FoodAssess foodAssess:foodAssesses){
			Account account = accountMapper.selectById(foodAssess.getFk_tb_user_id());
			if (account == null) {
				throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
			}
			JsonBean<UserInfo> userInfoJsonBean = userServiceFeign.info_get(Integer.valueOf(account.getServer_id()));
			if (!userInfoJsonBean.isSuccess()) {
				continue;
			}
			FoodAssessResponse foodAssessResponse=new FoodAssessResponse();
			foodAssessResponse.setId(foodAssess.getId());
			foodAssessResponse.setUser_id(foodAssess.getFk_tb_user_id());
			foodAssessResponse.setFood_order_id(foodAssess.getFk_tb_food_order_id());
			foodAssessResponse.setUserUrl(userInfoJsonBean.getData().getImg());
			foodAssessResponse.setNickname(userInfoJsonBean.getData().getNickname());
			foodAssessResponse.setScore(foodAssess.getScore());
			foodAssessResponse.setAssess(foodAssess.getAssess());
			foodAssessResponse.setTime(foodAssess.getTime());
			foodAssessResponses.add(foodAssessResponse);
		}
		int total=foodAssessMapper.count(fk_tb_food_order_id);
		pageFoodAssessResponse.setAssesses(foodAssessResponses);
		pageFoodAssessResponse.setAssessCount(total);
		pageFoodAssessResponse.setOffset(offset);
		pageFoodAssessResponse.setPageSize(pageSize);
		return pageFoodAssessResponse;
	}

	public int deleteByPrimaryKey(Integer id){
		return foodAssessMapper.deleteByPrimaryKey(id);
	}

}
