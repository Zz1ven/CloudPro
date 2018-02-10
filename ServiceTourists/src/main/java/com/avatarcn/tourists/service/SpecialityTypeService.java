package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.global.Constant;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.mapper.SpecialityMapper;
import com.avatarcn.tourists.mapper.SpecialityTypeMapper;
import com.avatarcn.tourists.model.Speciality;
import com.avatarcn.tourists.model.SpecialityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by z1ven on 2018/1/30 13:08
 */
@Service
public class SpecialityTypeService {

    @Autowired
    private SpecialityTypeMapper specialityTypeMapper;

    @Autowired
    private SpecialityMapper specialityMapper;

    @Autowired
    private OssService ossService;

    public SpecialityType insert(String name, String icon) throws ErrorCodeException {
        SpecialityType specialityType = new SpecialityType();
        if (specialityTypeMapper.countByName(name) > 0) {
            throw new ErrorCodeException(TouristsErrorCode.SPECIALITY_TYPE_REPEAT);
        }
        specialityType.setName(name);
        if (icon != null && !icon.isEmpty()) {
            String url = ossService.copyFileTo(icon, Constant.TOURISTS_TMP_DIR ,Constant.TOURISTS_SPECIALITY_DIR);
            specialityType.setIcon(url);
        }
        specialityTypeMapper.insert(specialityType);
        return specialityType;
    }

    public int deleteById(Integer id) throws ErrorCodeException {
        SpecialityType specialityType = specialityTypeMapper.selectById(id);
        if (specialityType == null) {
            throw new ErrorCodeException(TouristsErrorCode.SPECIALITY_TYPE_NULL);
        }
        //删除关联的特色商品
        List<Speciality> specialities = specialityMapper.selectByTypeId(id);
        for (Speciality speciality : specialities) {
            ossService.delete(speciality.getUrl());
        }
        specialityMapper.deleteByTypeId(id);
        if (specialityType.getIcon() != null && !specialityType.getIcon().isEmpty()) {
            ossService.delete(specialityType.getIcon());
        }
        if (specialityTypeMapper.deleteById(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_NO);
        }
        return 1;
    }

    public SpecialityType selectById(Integer id) {
        return specialityTypeMapper.selectById(id);
    }

    public AllResponse<SpecialityType> selectByAll() {
        List<SpecialityType> specialityTypes = specialityTypeMapper.selectAll();
        AllResponse<SpecialityType> specialityTypeAllResponse = new AllResponse<>();
        specialityTypeAllResponse.setItem(specialityTypes);
        specialityTypeAllResponse.setTotal(specialityTypes.size());
        return specialityTypeAllResponse;
    }

    public SpecialityType update(Integer id, String name, String icon) throws ErrorCodeException {
        SpecialityType specialityType = specialityTypeMapper.selectById(id);
        if (specialityType == null) {
            throw new ErrorCodeException(TouristsErrorCode.SPECIALITY_TYPE_NULL);
        }
        if (name != null && !name.equals(specialityType.getName())) {
            if (specialityTypeMapper.countByName(name) > 0) {
                throw new ErrorCodeException(TouristsErrorCode.SPECIALITY_TYPE_REPEAT);
            }
            specialityType.setName(name);
        }
        if (icon != null && !icon.isEmpty()) {
            if (specialityType.getIcon() == null || !icon.equals(specialityType.getIcon())) {
                String url = ossService.copyFileTo(icon, Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_SPECIALITY_DIR);
                specialityType.setIcon(url);
            }
        }
        specialityTypeMapper.update(specialityType);
        return specialityType;
    }
}
