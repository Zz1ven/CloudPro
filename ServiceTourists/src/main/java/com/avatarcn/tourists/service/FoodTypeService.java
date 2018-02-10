package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.json.response.FoodTypeResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.mapper.FoodMapper;
import com.avatarcn.tourists.mapper.FoodTypeMapper;
import com.avatarcn.tourists.model.Food;
import com.avatarcn.tourists.model.FoodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class FoodTypeService{
	@Autowired
	private FoodTypeMapper foodTypeMapper;
	@Autowired
	private OssService ossService;
	@Autowired
	private FoodMapper foodMapper;

	public FoodType insert(String name, String type_img)throws ErrorCodeException{
		FoodType foodType = new FoodType();
		foodType.setName(name);
		//先移动文件位置
		if (type_img!=null) {
			String img_url = ossService.copyFileTo(type_img, "tourists/tmp", "tourists/food_type");
			foodType.setType_img(img_url);
		}
		foodType.setTime(new Date());
		foodTypeMapper.insert(foodType);
		return foodType;
	}

	public FoodType update(Integer id,String name,String type_img) throws ErrorCodeException {
		FoodType foodType = foodTypeMapper.selectByPrimaryKey(id);
		if(foodType == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		if(name != null){
			foodType.setName(name);
		}
		if(type_img != null&&!foodType.getType_img().equals(type_img)) {
			//删除以前老的广告
			String old_url = foodType.getType_img();
			ossService.delete(old_url);
			//先移动文件位置
			String img_url = ossService.copyFileTo(type_img,"tourists/tmp", "tourists/food_type");
			foodType.setType_img(img_url);
		}
		foodType.setTime(new Date());
		foodTypeMapper.update(foodType);
		return foodType;
	}

	public FoodType selectByPrimaryKey(Integer id) throws ErrorCodeException{
		FoodType foodType = foodTypeMapper.selectByPrimaryKey(id);
		if(foodType == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return foodType;
	}

	public PageResponse<FoodType> selectPage(Integer offset, Integer pageSize){
		PageResponse<FoodType> response = new PageResponse();
		response.setItem(foodTypeMapper.selectPage(offset,pageSize));
		response.setTotal(foodTypeMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public AllResponse<FoodType> selectAll() {
		AllResponse<FoodType> allResponse = new AllResponse<>();
		List<FoodType> foodTypeList = foodTypeMapper.selectAll();
		allResponse.setItem(foodTypeList);
		allResponse.setTotal(foodTypeList.size());
		return allResponse;
	}

  //列出类型下所有的菜系
	public AllResponse<FoodTypeResponse> selectTypeFoods() {
		AllResponse<FoodTypeResponse> allResponse = new AllResponse<>();
		List<FoodTypeResponse> foodTypeResponses=foodTypeMapper.selectTypeFoods();
		allResponse.setItem(foodTypeResponses);
		allResponse.setTotal(foodTypeResponses.size());
		return allResponse;
	}
	public int deleteByPrimaryKey(Integer id){
		//先删除菜系表及oss图片
		List<Food> foods=foodMapper.selectAll(id);
		for(Food food:foods){
			//删除oss
			ossService.delete(food.getImg());
			foodMapper.deleteByPrimaryKey(food.getId());
		}
		//删除
		FoodType foodType=foodTypeMapper.selectByPrimaryKey(id);
		String old_url = foodType.getType_img();
		ossService.delete(old_url);
		return foodTypeMapper.deleteByPrimaryKey(id);
	}

}
