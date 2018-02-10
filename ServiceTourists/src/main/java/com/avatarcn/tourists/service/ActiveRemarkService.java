package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.mapper.ActiveMapper;
import com.avatarcn.tourists.mapper.ActiveRemarkMapper;
import com.avatarcn.tourists.mapper.RemarkMapper;
import com.avatarcn.tourists.model.ActiveRemark;
import com.avatarcn.tourists.model.Remark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by z1ven on 2018/1/20 09:25
 */
@Service
public class ActiveRemarkService {

    @Autowired
    private ActiveRemarkMapper activeRemarkMapper;

    @Autowired
    private ActiveMapper activeMapper;

    @Autowired
    private RemarkMapper remarkMapper;

    public ActiveRemark addActiveRemark(Integer activeId, Integer remarkId) throws ErrorCodeException {
        if (activeMapper.selectById(activeId) == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACTIVE_NULL);
        }
        Remark remark = remarkMapper.selectById(remarkId);
        if (remark == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        ActiveRemark activeRemark = activeRemarkMapper.selectByActiveIdAndRemarkId(activeId, remarkId);
        if (activeRemark == null) {
            activeRemark = new ActiveRemark();
            activeRemark.setFk_tb_active_id(activeId);
            activeRemark.setFk_tb_remark_id(remarkId);
            activeRemarkMapper.insert(activeRemark);
            activeRemark.setRemark_name(remark.getName());
            return activeRemark;
        }
        return activeRemark;
    }

    public int deleteActiveRemark(Integer activeId, Integer remarkId) throws ErrorCodeException {
        return activeRemarkMapper.deleteByRemarkIdAndActiveId(remarkId, activeId);
    }
}
