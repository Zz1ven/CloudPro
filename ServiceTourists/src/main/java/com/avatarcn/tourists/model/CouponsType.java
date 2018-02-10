package com.avatarcn.tourists.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("")
public class CouponsType{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String code;
	@ApiModelProperty("")
	private String remark;
	@ApiModelProperty("")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
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

}
