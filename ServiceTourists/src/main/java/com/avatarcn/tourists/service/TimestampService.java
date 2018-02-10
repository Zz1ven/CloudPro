package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.mapper.TimestampMapper;
import com.avatarcn.tourists.model.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class TimestampService{
	@Autowired
	private TimestampMapper timestampMapper;

	public Timestamp insert(String start_time, String end_time) throws ErrorCodeException {
		Timestamp timestamp = new Timestamp();

		if(!start_time .matches("(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1}):([0-5]\\d{1})"))
			throw new ErrorCodeException(ErrorCodeException.TIME_PARAM_ERROR);
		if(!end_time .matches("(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1}):([0-5]\\d{1})"))
			throw new ErrorCodeException(ErrorCodeException.TIME_PARAM_ERROR);
		timestamp.setStart_time(Time.valueOf(start_time));
		timestamp.setEnd_time(Time.valueOf(end_time));
		timestampMapper.insert(timestamp);
		return timestamp;
	}

	public Timestamp update(Integer id,String start_time, String end_time) throws ErrorCodeException{
		Timestamp timestamp = timestampMapper.selectByPrimaryKey(id);
		if(timestamp == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		if(!start_time .matches("(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1}):([0-5]\\d{1})"))
			throw new ErrorCodeException(ErrorCodeException.TIME_PARAM_ERROR);
		if(!end_time .matches("(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1}):([0-5]\\d{1})"))
			throw new ErrorCodeException(ErrorCodeException.TIME_PARAM_ERROR);
		timestamp.setStart_time(Time.valueOf(start_time));
		timestamp.setEnd_time(Time.valueOf(end_time));
		timestampMapper.update(timestamp);
		return timestamp;
	}

	public Timestamp selectByPrimaryKey(Integer id) throws ErrorCodeException{
		Timestamp timestamp = timestampMapper.selectByPrimaryKey(id);
		if(timestamp == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return timestamp;
	}

	public PageResponse<Timestamp> selectPage(Integer offset,Integer pageSize){
		PageResponse<Timestamp> response = new PageResponse();
		response.setItem(timestampMapper.selectPage(offset,pageSize));
		response.setTotal(timestampMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public AllResponse<Timestamp> selectAll() {
		AllResponse<Timestamp> allResponse = new AllResponse<>();
		List<Timestamp> timestamps = timestampMapper.selectAll();
		allResponse.setItem(timestamps);
		allResponse.setTotal(timestamps.size());
		return allResponse;
	}

	public int deleteByPrimaryKey(Integer id) throws ErrorCodeException {
		if(timestampMapper.deleteByPrimaryKey(id) != 1){
			throw new ErrorCodeException(ErrorCodeException.DELETE_NO);
		}
		return 1;
	}

}
