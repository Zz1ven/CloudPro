package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.mapper.SpecialityMapper;
import com.avatarcn.tourists.mapper.SpecialityTypeMapper;
import com.avatarcn.tourists.model.Speciality;
import com.avatarcn.tourists.model.SpecialityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class SpecialityService{
	@Autowired
	private SpecialityMapper specialityMapper;
	@Autowired
	private SpecialityTypeMapper specialityTypeMapper;
	@Autowired
	private OssService ossService;

	public Speciality insert(Integer speciality_type_id, String name, Float price, String url, String remark)throws ErrorCodeException{
		Speciality speciality = new Speciality();
		SpecialityType specialityType = specialityTypeMapper.selectById(speciality_type_id);
		if (specialityType == null) {
			throw new ErrorCodeException(TouristsErrorCode.SPECIALITY_TYPE_NULL);
		}
		speciality.setFk_tb_speciality_type_id(speciality_type_id);
		speciality.setName(name);
		speciality.setPrice(price);
		//先移动文件位置
		String img_url=ossService.copyFileTo(url,"tourists/tmp","tourists/speciality");
		speciality.setUrl(img_url);
		speciality.setSale(0);
		speciality.setRemark(remark);
		speciality.setTime(new Date());
		specialityMapper.insert(speciality);
		speciality.setSpecialityType(specialityType);
		return speciality;
	}

	public Speciality update(Integer id, Integer speciality_type_id, String name, Float price, String url, String remark) throws ErrorCodeException{
		Speciality speciality = specialityMapper.selectByPrimaryKey(id);
		if(speciality == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		if (speciality.getFk_tb_speciality_type_id().intValue() != speciality_type_id) {
			SpecialityType specialityType = specialityTypeMapper.selectById(speciality_type_id);
			if (specialityType == null) {
				throw new ErrorCodeException(TouristsErrorCode.SPECIALITY_TYPE_NULL);
			}
			speciality.setFk_tb_speciality_type_id(speciality_type_id);
			speciality.setSpecialityType(specialityType);
		}
		if(name != null){
			speciality.setName(name);
		}
		if(price != null){
			speciality.setPrice(price);
		}
		if(url!= null&&!url.equals(speciality.getUrl())){
			//删除以前老的广告
			String old_url = speciality.getUrl();
			ossService.delete(old_url);
			//先移动文件位置
			String img_url = ossService.copyFileTo(url, "kindergarten/tmp", "kindergarten/speciality");
			speciality.setUrl(img_url);
		}
		if(remark != null){
			speciality.setRemark(remark);
		}
		speciality.setTime(new Date());
		specialityMapper.update(speciality);
		return speciality;
	}

	public Speciality selectByPrimaryKey(Integer id) throws ErrorCodeException{
		Speciality speciality = specialityMapper.selectByPrimaryKey(id);
		if(speciality == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return speciality;
	}

	public PageResponse<Speciality> selectPage(Integer offset, Integer pageSize){
		PageResponse<Speciality> response = new PageResponse<>();
		response.setItem(specialityMapper.selectPage(offset,pageSize));
		response.setTotal(specialityMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public PageResponse<Speciality> selectPageByType(Integer speciality_type_id, Integer offset, Integer pageSize) {
		PageResponse<Speciality> specialityPageResponse = new PageResponse<>();
		specialityPageResponse.setItem(specialityMapper.selectPageByTypeId(speciality_type_id, offset, pageSize));
		specialityPageResponse.setTotal(specialityMapper.countByTypeId(speciality_type_id));
		specialityPageResponse.setOffset(offset);
		specialityPageResponse.setPageSize(pageSize);
		return specialityPageResponse;
	}

	public int deleteByPrimaryKey(Integer id){
		Speciality speciality = specialityMapper.selectByPrimaryKey(id);
		String old_url = speciality.getUrl();
		//删除oss中文件
		ossService.delete(old_url);
		return specialityMapper.deleteByPrimaryKey(id);
	}

}
