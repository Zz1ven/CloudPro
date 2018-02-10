package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.json.response.FoodResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.mapper.FoodMapper;
import com.avatarcn.tourists.mapper.FoodTypeMapper;
import com.avatarcn.tourists.model.Food;
import com.avatarcn.tourists.model.FoodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class FoodService {
    @Autowired
    private FoodMapper foodMapper;
    @Autowired
    private FoodTypeMapper foodTypeMapper;
    @Autowired
    private OssService ossService;

    public Food insert(Integer fk_tb_food_type_id, String name, String description, Float price, Float rebate_id, String img) throws ErrorCodeException {
        FoodType foodType = foodTypeMapper.selectByPrimaryKey(fk_tb_food_type_id);
        if (foodType == null)
            throw new ErrorCodeException(TouristsErrorCode.NO_FOOD_TYPE);
        Food food = new Food();
        food.setFk_tb_food_type_id(fk_tb_food_type_id);
        food.setName(name);
        food.setDescription(description);
        food.setPrice(price);
        if (rebate_id == null) {
            rebate_id = 1f;
        }
        food.setRebate_id(rebate_id);
        //先移动文件位置
        String img_url = ossService.copyFileTo(img, "tourists/tmp", "tourists/food");
        food.setImg(img_url);
        food.setTime(new Date());
        foodMapper.insert(food);
        return food;
    }

    public Food update(Integer id, Integer fk_tb_food_type_id, String name, String description, Float price, Float  rebate_id, String img) throws ErrorCodeException {
        Food food = foodMapper.selectByPrimaryKey(id);
        if (food == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        if (fk_tb_food_type_id != null) {
            food.setFk_tb_food_type_id(fk_tb_food_type_id);
        }
        if (name != null) {
            food.setName(name);
        }
        if (description != null) {
            food.setDescription(description);
        }
        if (price != null) {
            food.setPrice(price);
        }
        if (rebate_id != null) {
            food.setRebate_id(rebate_id);
        }
        if (img != null && !food.getImg().equals(img)) {
            //删除以前老的广告
            String old_url = food.getImg();
            ossService.delete(old_url);
            //先移动文件位置
            String img_url = ossService.copyFileTo(img, "tourists/tmp", "tourists/food");
            food.setImg(img_url);
        }
        food.setTime(new Date());
        foodMapper.update(food);
        return food;
    }

    public Food selectByPrimaryKey(Integer id) throws ErrorCodeException {
        Food food = foodMapper.selectByPrimaryKey(id);
        if (food == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        return food;
    }

    public PageResponse<FoodResponse> selectPage(Integer fk_tb_food_type_id, Integer offset, Integer pageSize) throws ErrorCodeException{
        FoodType foodType=foodTypeMapper.selectByPrimaryKey(fk_tb_food_type_id);
        if (foodType==null)
            throw new ErrorCodeException(TouristsErrorCode.NO_FOOD_TYPE);
        PageResponse<FoodResponse> response = new PageResponse();
        response.setItem(foodMapper.selectPage(fk_tb_food_type_id,offset, pageSize));
        response.setTotal(foodMapper.count(fk_tb_food_type_id));
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    public int deleteByPrimaryKey(Integer id) {
        Food food=foodMapper.selectByPrimaryKey(id);
        String old_url =food.getImg();
        ossService.delete(old_url);
        return foodMapper.deleteByPrimaryKey(id);
    }

}
