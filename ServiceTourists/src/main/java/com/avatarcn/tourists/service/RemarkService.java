package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.mapper.ActiveRemarkMapper;
import com.avatarcn.tourists.mapper.RemarkMapper;
import com.avatarcn.tourists.model.Remark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by z1ven on 2018/1/18 14:57
 */
@Service
public class RemarkService {

    @Autowired
    private RemarkMapper remarkMapper;

    @Autowired
    private ActiveRemarkMapper activeRemarkMapper;

    public Remark addRemark(String name) throws ErrorCodeException {
        if (remarkMapper.selectByName(name) != null) {
            throw new ErrorCodeException(TouristsErrorCode.REMARK_REPEAT);
        }
        Remark remark = new Remark();
        remark.setName(name);
        remark.setTime(new Date());
        remarkMapper.insert(remark);
        return remark;
    }



    public int deleteById(Integer id) throws ErrorCodeException {
        //删除活动依赖的标签
        activeRemarkMapper.deleteByRemarkId(id);
        if (remarkMapper.deleteById(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        return 1;
    }

    public Remark selectById(Integer id) throws ErrorCodeException {
        Remark remark = remarkMapper.selectById(id);
        if (remark == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        return remark;
    }

    public AllResponse<Remark> selectAll() {
        AllResponse<Remark> remarkAllResponse = new AllResponse<>();
        List<Remark> remarkList = remarkMapper.selectAll();
        remarkAllResponse.setItem(remarkList);
        remarkAllResponse.setTotal(remarkList.size());
        return remarkAllResponse;
    }

    public Remark update(Integer id, String name) throws ErrorCodeException {
        if (remarkMapper.selectByName(name) != null) {
            throw new ErrorCodeException(TouristsErrorCode.REMARK_REPEAT);
        }
        Remark remark = new Remark();
        remark.setId(id);
        remark.setName(name);
        remark.setTime(new Date());
        remarkMapper.update(remark);
        return remark;
    }
}
