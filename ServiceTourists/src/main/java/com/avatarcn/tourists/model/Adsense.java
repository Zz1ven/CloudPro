package com.avatarcn.tourists.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("")
public class Adsense{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String name;
	@ApiModelProperty("")
	private String remark;
	@ApiModelProperty("")
	private String url;
	@ApiModelProperty("")
	private Date time;
	@ApiModelProperty("")
	private String detailurl;
	@ApiModelProperty("")
	private String content;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

	public String getDetailurl(){
		return detailurl;
	}

	public void setDetailurl(String detailurl){
		this.detailurl = detailurl;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}

}
