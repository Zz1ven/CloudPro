package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.mapper.PromotionCouponMapper;
import com.avatarcn.tourists.mapper.PromotionMapper;
import com.avatarcn.tourists.mapper.PromotionRecordMapper;
import com.avatarcn.tourists.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by z1ven on 2018/2/7 17:44
 */
@Service
public class PromotionService {

    @Autowired
    private PromotionMapper promotionMapper;

    @Autowired
    private PromotionCouponMapper promotionCouponMapper;

    @Autowired
    private PromotionRecordMapper promotionRecordMapper;

    public Promotion insert(String name, String description, String url) {
        Promotion promotion  = new Promotion();
        promotion.setName(name);
        promotion.setDescription(description);
        promotion.setUrl(url);
        promotion.setTime(new Date());
        promotionMapper.insert(promotion);
        return promotion;
    }

    public int deleteById(Integer id) throws ErrorCodeException {
        //删除关联的活动优惠券
        promotionCouponMapper.deleteByPromotionId(id);
        //删除关联的领取记录
        promotionRecordMapper.deleteByPromotionId(id);
        if (promotionMapper.delete(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_NO);
        }
        return 1;
    }

    public Promotion selectById(Integer id) throws ErrorCodeException {
        Promotion promotion = promotionMapper.selectById(id);
        if (promotion == null) {
            throw new ErrorCodeException(TouristsErrorCode.PROMOTION_NULL);
        }
        return promotion;
    }

    public PageResponse<Promotion> selectPage(Integer offset, Integer pageSize) {
        PageResponse<Promotion> promotionPageResponse = new PageResponse<>();
        promotionPageResponse.setItem(promotionMapper.selectPage(offset, pageSize));
        promotionPageResponse.setOffset(offset);
        promotionPageResponse.setPageSize(pageSize);
        promotionPageResponse.setTotal(promotionMapper.count());
        return promotionPageResponse;
    }

    public Promotion update(Integer id, String name, String description, String url) throws ErrorCodeException {
        Promotion promotion = promotionMapper.selectById(id);
        if (promotion == null) {
            throw new ErrorCodeException(TouristsErrorCode.PROMOTION_NULL);
        }
        if (name != null && !name.isEmpty()) {
            promotion.setName(name);
        }
        if (description != null && !description.isEmpty()) {
            promotion.setDescription(description);
        }
        if (url != null && !url.isEmpty()) {
            promotion.setUrl(url);
        }
        promotionMapper.update(promotion);
        return promotion;
    }
}
