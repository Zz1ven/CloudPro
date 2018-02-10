package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("")
@JsonIgnoreProperties(value = {"handler"})
public class Speciality{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Integer fk_tb_speciality_type_id;
	@ApiModelProperty("")
	private String name;
	@ApiModelProperty("")
	private Float price;
	@ApiModelProperty("")
	private String url;
	@ApiModelProperty("")
	private Integer sale;
	@ApiModelProperty("")
	private String remark;
	@ApiModelProperty("")
	private Date time;

	private SpecialityType specialityType;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_tb_speciality_type_id() {
		return fk_tb_speciality_type_id;
	}

	public void setFk_tb_speciality_type_id(Integer fk_tb_speciality_type_id) {
		this.fk_tb_speciality_type_id = fk_tb_speciality_type_id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Float getPrice(){
		return price;
	}

	public void setPrice(Float price){
		this.price = price;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public Integer getSale() {
		return sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

	public SpecialityType getSpecialityType() {
		return specialityType;
	}

	public void setSpecialityType(SpecialityType specialityType) {
		this.specialityType = specialityType;
	}
}
