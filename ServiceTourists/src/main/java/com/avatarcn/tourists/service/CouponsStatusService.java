package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.mapper.CouponsStatusMapper;
import com.avatarcn.tourists.model.CouponsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CouponsStatusService{
	@Autowired
	private CouponsStatusMapper couponsStatusMapper;

	public CouponsStatus insert(String status_number, String description){
		CouponsStatus couponsStatus = new CouponsStatus();
		couponsStatus.setStatus_number(status_number);
		couponsStatus.setDescription(description);
		couponsStatus.setTime(new Date());
		couponsStatusMapper.insert(couponsStatus);
		return couponsStatus;
	}

	public CouponsStatus update(Integer id,String status_number,String description) throws ErrorCodeException {
		CouponsStatus couponsStatus = couponsStatusMapper.selectByPrimaryKey(id);
		if(couponsStatus == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		if(status_number != null){
			couponsStatus.setStatus_number(status_number);
		}
		if(description != null){
			couponsStatus.setDescription(description);
		}
			couponsStatus.setTime(new Date());
		couponsStatusMapper.update(couponsStatus);
		return couponsStatus;
	}

	public CouponsStatus selectByPrimaryKey(Integer id) throws ErrorCodeException{
		CouponsStatus couponsStatus = couponsStatusMapper.selectByPrimaryKey(id);
		if(couponsStatus == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return couponsStatus;
	}

	public PageResponse<CouponsStatus> selectPage(Integer offset, Integer pageSize){
		PageResponse<CouponsStatus> response = new PageResponse();
		response.setItem(couponsStatusMapper.selectPage(offset,pageSize));
		response.setTotal(couponsStatusMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public AllResponse<CouponsStatus> selectAll() {
		AllResponse<CouponsStatus> allResponse = new AllResponse<>();
		List<CouponsStatus> statusList = couponsStatusMapper.selectAll();
		allResponse.setItem(statusList);
		allResponse.setTotal(statusList.size());
		return allResponse;
	}

	public int deleteByPrimaryKey(Integer id){
		return couponsStatusMapper.deleteByPrimaryKey(id);
	}

}
