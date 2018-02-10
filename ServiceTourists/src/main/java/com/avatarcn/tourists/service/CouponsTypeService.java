package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.mapper.CouponMapper;
import com.avatarcn.tourists.mapper.CouponsTypeMapper;
import com.avatarcn.tourists.mapper.CouponsUsersMapper;
import com.avatarcn.tourists.model.Coupon;
import com.avatarcn.tourists.model.CouponsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponsTypeService{
	@Autowired
	private CouponsTypeMapper couponsTypeMapper;
	@Autowired
	private CouponsUsersMapper couponsUsersMapper;
	@Autowired
	private CouponMapper couponMapper;

	public CouponsType insert(String code, String remark){
		CouponsType couponsType = new CouponsType();
		couponsType.setCode(code);
		couponsType.setRemark(remark);
		couponsType.setTime(new Date());
		couponsTypeMapper.insert(couponsType);
		return couponsType;
	}

	public CouponsType update(Integer id,String code,String remark) throws ErrorCodeException {
		CouponsType couponsType = couponsTypeMapper.selectByPrimaryKey(id);
		if(couponsType == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		if(code != null){
			couponsType.setCode(code);
		}
		if(remark != null){
			couponsType.setRemark(remark);
		}
		couponsType.setTime(new Date());
		couponsTypeMapper.update(couponsType);
		return couponsType;
	}

	public CouponsType selectByPrimaryKey(Integer id) throws ErrorCodeException{
		CouponsType couponsType = couponsTypeMapper.selectByPrimaryKey(id);
		if(couponsType == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return couponsType;
	}

	public PageResponse<CouponsType> selectPage(Integer offset, Integer pageSize){
		PageResponse<CouponsType> response = new PageResponse();
		response.setItem(couponsTypeMapper.selectPage(offset,pageSize));
		response.setTotal(couponsTypeMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public AllResponse<CouponsType> selectAll() {
		AllResponse<CouponsType> couponsTypeAllResponse = new AllResponse<>();
		List<CouponsType> couponsTypeList = couponsTypeMapper.selectAll();
		couponsTypeAllResponse.setItem(couponsTypeList);
		couponsTypeAllResponse.setTotal(couponsTypeList.size());
		return couponsTypeAllResponse;
	}

	public int deleteByPrimaryKey(Integer id){
		List<Coupon> coupons=couponMapper.selectByType(id);
		for (Coupon coupon:coupons){
			//删除用户下的优惠劵
			couponsUsersMapper.deleteByCouponId(coupon.getId());
			//删除类型下的优惠劵
			couponMapper.deleteByPrimaryKey(coupon.getId());
		}
		return couponsTypeMapper.deleteByPrimaryKey(id);
	}

}
