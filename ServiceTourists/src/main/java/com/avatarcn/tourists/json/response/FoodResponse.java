package com.avatarcn.tourists.json.response;

import com.avatarcn.tourists.model.FoodType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by MDF on 2018-1-19.
 */
@JsonIgnoreProperties(value = {"handler"})
public class FoodResponse {
    private Integer id;
    private Integer fk_tb_food_type_id;
    private FoodType foodType;
    private String name;
    private String description;
    private Float price;
    private Integer rebate_id;
    private String img;
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFk_tb_food_type_id() {
        return fk_tb_food_type_id;
    }

    public void setFk_tb_food_type_id(Integer fk_tb_food_type_id) {
        this.fk_tb_food_type_id = fk_tb_food_type_id;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getRebate_id() {
        return rebate_id;
    }

    public void setRebate_id(Integer rebate_id) {
        this.rebate_id = rebate_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
