package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.global.Constant;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.mapper.RecommendationMapper;
import com.avatarcn.tourists.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by z1ven on 2018/1/31 16:15
 */
@Service
public class RecommendationService {

    @Autowired
    private RecommendationMapper recommendationMapper;

    @Autowired
    private OssService ossService;

    public Recommendation addRecommendation(String image) throws ErrorCodeException {
        String url = ossService.copyFileTo(image, Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_RECOMMENDATION_DIR);
        Recommendation recommendation = new Recommendation();
        recommendation.setImage(url);
        recommendationMapper.insert(recommendation);
        return recommendation;
    }

    public int deleteById(int id) throws ErrorCodeException {
        Recommendation recommendation = recommendationMapper.selectById(id);
        if (recommendation == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        ossService.delete(recommendation.getImage());
        if (recommendationMapper.deleteById(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_NO);
        }
        return 1;
    }

    public Recommendation selectById(int id) throws ErrorCodeException {
        Recommendation recommendation = recommendationMapper.selectById(id);
        if (recommendation == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        return recommendation;
    }

    public AllResponse<Recommendation> selectAll() {
        AllResponse<Recommendation> allResponse = new AllResponse<>();
        List<Recommendation> recommendationList = recommendationMapper.selectAll();
        allResponse.setItem(recommendationList);
        allResponse.setTotal(recommendationList.size());
        return allResponse;
    }

    public Recommendation update(int id, String image) throws ErrorCodeException {
        Recommendation recommendation = recommendationMapper.selectById(id);
        if (recommendation == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        if (image != null && !image.isEmpty() && !image.equals(recommendation.getImage())) {
            ossService.delete(recommendation.getImage());
            String url = ossService.copyFileTo(image, Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_RECOMMENDATION_DIR);
            recommendation.setImage(url);
        }
        recommendationMapper.update(recommendation);
        return recommendation;
    }
}
