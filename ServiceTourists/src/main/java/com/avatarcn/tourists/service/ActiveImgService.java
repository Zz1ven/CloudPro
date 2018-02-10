package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.global.Constant;
import com.avatarcn.tourists.mapper.ActiveImgMapper;
import com.avatarcn.tourists.mapper.ActiveMapper;
import com.avatarcn.tourists.model.ActiveImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by z1ven on 2018/1/20 09:59
 */
@Service
public class ActiveImgService {

    @Autowired
    private ActiveImgMapper activeImgMapper;

    @Autowired
    private ActiveMapper activeMapper;

    @Autowired
    private OssService ossService;

    public ActiveImg addActiveImg(Integer activeId, String image) throws ErrorCodeException {
        if (activeMapper.selectById(activeId) == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACTIVE_NULL);
        }
        String imgUrl = ossService.copyFileTo(image, Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_ACTIVE_DIR);
        ActiveImg activeImg = new ActiveImg(activeId, imgUrl, new Date());
        activeImgMapper.insert(activeImg);
        return activeImg;
    }

    public int deleteActiveImg(Integer activeId, String image) throws ErrorCodeException {
        List<String> urls = activeImgMapper.selectUrlByActiveId(activeId);
        if (urls.contains(image)) {
            ossService.delete(image);
            activeImgMapper.deleteByActiveIdAndUrl(activeId, image);
        }
        return 1;
    }
}
