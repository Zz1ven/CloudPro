package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.global.Constant;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.json.response.active.ActiveResponse;
import com.avatarcn.tourists.mapper.*;
import com.avatarcn.tourists.model.Active;
import com.avatarcn.tourists.model.ActiveImg;
import com.avatarcn.tourists.model.ActiveOrder;
import com.avatarcn.tourists.model.ActiveRemark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by z1ven on 2018/1/18 15:31
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 60000, rollbackFor = Exception.class)
public class ActiveService {

    @Autowired
    private ActiveMapper activeMapper;

    @Autowired
    private ActiveRemarkMapper activeRemarkMapper;

    @Autowired
    private ActiveImgMapper activeImgMapper;

    @Autowired
    private RemarkMapper remarkMapper;

    @Autowired
    private ActiveAssessMapper activeAssessMapper;

    @Autowired
    private OssService ossService;

    @Autowired
    private ActiveOrderMapper activeOrderMapper;

    @Autowired
    private ActiveOrderService activeOrderService;

    public Active addActive(String name, String iconUrl, long startTime, long endTime, float price, String description, List<String> imageList, List<Integer> remarkList) throws ErrorCodeException {
        Active active = new Active();
        active.setName(name);
        active.setStart_time(new Time(startTime));
        active.setEnd_time(new Time(endTime));
        active.setPrice(price);
        active.setDescription(description);
        active.setTime(new Date());
        String url = ossService.copyFileTo(iconUrl, Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_ACTIVE_DIR);
        active.setIcon(url);
        activeMapper.insert(active);
        //插入关联图片
        List<String> newImageList = new ArrayList<>();
        if (imageList != null) {
            for (String imageUrl : imageList) {
                String newImageUrl = ossService.copyFileTo(imageUrl, Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_ACTIVE_DIR);
                ActiveImg activeImg = new ActiveImg(active.getId(), newImageUrl, new Date());
                activeImgMapper.insert(activeImg);
                newImageList.add(newImageUrl);
            }
        }
        //插入关联标签
        List<ActiveRemark> activeRemarkList = new ArrayList<>();
        if (remarkList != null) {
            for (Integer remarkId : remarkList) {
                ActiveRemark activeRemark = new ActiveRemark();
                activeRemark.setFk_tb_remark_id(remarkId);
                activeRemark.setFk_tb_active_id(active.getId());
                activeRemarkMapper.insert(activeRemark);
                activeRemark.setRemark_name(remarkMapper.selectNameById(remarkId));
                activeRemarkList.add(activeRemark);
            }
        }
        active.setImageList(newImageList);
        active.setActiveRemarkList(activeRemarkList);
        return active;
    }

    public int deleteActive(Integer id) throws ErrorCodeException {
        Active active = activeMapper.selectById(id);
        if (active == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACTIVE_NULL);
        }
        //删除关联标签
        activeRemarkMapper.deleteByActiveId(id);
        //删除关联图片
        List<String> urls = activeImgMapper.selectUrlByActiveId(id);
        for (String url : urls) {
            ossService.delete(url);
        }
        activeImgMapper.deleteByActiveId(id);
        ossService.delete(active.getIcon());
        //删除关联评论
        activeAssessMapper.deleteByActiveId(id);
        //删除关联订单
        List<ActiveOrder> activeOrderList = activeOrderMapper.selectByActiveId(id);
        for (ActiveOrder activeOrder : activeOrderList) {
            activeOrderService.deleteActiveOrder(activeOrder.getId());
        }
        if (activeMapper.deleteById(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_NO);
        }
        return 1;
    }

    public PageResponse<ActiveResponse> selectPageActive(Integer offset, Integer pageSize) {
        PageResponse<ActiveResponse> pageResponse = new PageResponse<>();
        List<ActiveResponse> activeResponseList = new ArrayList<>();
        List<Active> activeList = activeMapper.selectPage(offset, pageSize);
        for (Active active : activeList) {
            ActiveResponse activeResponse = new ActiveResponse();
            activeResponse.setId(active.getId());
            activeResponse.setName(active.getName());
            activeResponse.setIcon(active.getIcon());
            activeResponse.setDescription(active.getDescription());
            activeResponse.setTime(active.getTime());
            activeResponse.setActiveRemarkList(active.getActiveRemarkList());
            activeResponseList.add(activeResponse);
        }
        pageResponse.setItem(activeResponseList);
        pageResponse.setOffset(offset);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotal(activeMapper.count());
        return pageResponse;
    }

    public Active selectById(Integer id) throws ErrorCodeException {
        Active active = activeMapper.selectCascadeById(id);
        if (active == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACTIVE_NULL);
        }
        return active;
    }

    public Active updateActive(Integer id, String name, String iconUrl, long startTime, long endTime, float price, String description, List<String> imageList, List<Integer> remarkList) throws ErrorCodeException {
        Active active = activeMapper.selectCascadeById(id);
        active.setName(name);
        if (!active.getIcon().equals(iconUrl)) {
            String url = ossService.copyFileTo(iconUrl, Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_ACTIVE_DIR);
            if (!url.equals(active.getIcon())) {
                ossService.delete(active.getIcon());
            }
            active.setIcon(url);
        }
        active.setStart_time(new Time(startTime));
        active.setEnd_time(new Time(endTime));
        active.setPrice(price);
        active.setDescription(description);
        if (imageList == null) {
            imageList = new ArrayList<>();
        }
        //添加新的图片
        for (String imageUrl : imageList) {
            if (!active.getImageList().contains(imageUrl)) {
                String newImageUrl = ossService.copyFileTo(imageUrl, Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_ACTIVE_DIR);
                activeImgMapper.insert(new ActiveImg(id, newImageUrl, new Date()));
                active.getImageList().add(newImageUrl);
            }
        }
        //删除去掉的图片
        for (String imageUrl : active.getImageList()) {
            if (!imageList.contains(imageUrl)) {
                ossService.delete(imageUrl);
                activeImgMapper.deleteByActiveIdAndUrl(id, imageUrl);
                active.getImageList().remove(imageUrl);
            }
        }
        //添加新的标签
        if (remarkList == null) {
            remarkList = new ArrayList<>();
        }
        for (Integer remarkId : remarkList) {
            ActiveRemark activeRemark = activeRemarkMapper.selectByActiveIdAndRemarkId(id, remarkId);
            if (activeRemark == null) {
                activeRemark = new ActiveRemark();
                activeRemark.setFk_tb_active_id(id);
                activeRemark.setFk_tb_remark_id(remarkId);
                activeRemarkMapper.insert(activeRemark);
                activeRemark.setRemark_name(remarkMapper.selectNameById(activeRemark.getFk_tb_remark_id()));
                active.getActiveRemarkList().add(activeRemark);
            }
        }
        //删除去掉的标签
        for (Iterator<ActiveRemark> iterator = active.getActiveRemarkList().iterator(); iterator.hasNext();) {
            ActiveRemark activeRemark = iterator.next();
            if (!remarkList.contains(activeRemark.getFk_tb_remark_id())) {
                activeRemarkMapper.deleteByRemarkIdAndActiveId(activeRemark.getFk_tb_remark_id(), id);
                iterator.remove();
            }
        }
        activeMapper.update(active);
        return active;
    }
}
