package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.body.AdsenseBody;
import com.avatarcn.tourists.json.response.AdsenseResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.mapper.AdsenseMapper;
import com.avatarcn.tourists.model.Adsense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AdsenseService{
	@Autowired
	private AdsenseMapper adsenseMapper;
	@Autowired
	private OssService ossService;

	public Adsense insert(AdsenseBody adsenseBody)throws ErrorCodeException{
		Adsense adsense = new Adsense();
		adsense.setName(adsenseBody.getName());
		adsense.setRemark(adsenseBody.getRemark());
		//先移动文件位置
		String img_url=ossService.copyFileTo(adsenseBody.getUrl(),"tourists/tmp","tourists/adsense");
		adsense.setUrl(img_url);
		adsense.setTime(new Date());
		//把content内容拼接起来设配手机客户端
		String message="<head>"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> "
				+ "<style>img{max-width: 100%; width:auto; height:auto;}</style>"
				+ "</head>";
		adsense.setDetailurl("<html>" + message + "<body>" + adsenseBody.getDetailurl() + "</body></html>");
		adsense.setContent(adsenseBody.getDetailurl());
		adsenseMapper.insert(adsense);
		return adsense;
	}

	public Adsense update(Integer id, @RequestBody AdsenseBody adsenseBody) throws ErrorCodeException{
		Adsense adsense = adsenseMapper.selectByPrimaryKey(id);
		if(adsense == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		adsense.setName(adsenseBody.getName());
		adsense.setRemark(adsenseBody.getRemark());
		if(!adsenseBody.getDetailurl().equals(adsense.getContent())){
			//把content内容拼接起来设配手机客户端
			//把content内容拼接起来设配手机客户端
			String message="<head>"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> "
					+ "<style>img{max-width: 100%; width:auto; height:auto;}</style>"
					+ "</head>";
			adsense.setDetailurl("<html>" + message + "<body>" + adsenseBody.getDetailurl() + "</body></html>");
			adsense.setContent(adsenseBody.getDetailurl());
		}
		if(adsenseBody.getUrl()!=null&&!adsenseBody.getUrl().equals(adsense.getUrl())) {
			//删除以前老的广告
			String old_url = adsense.getUrl();
			ossService.delete(old_url);
			//先移动文件位置
			String img_url = ossService.copyFileTo(adsenseBody.getUrl(), "tourists/tmp", "tourists/adsense");
			adsense.setUrl(img_url);
		}
		adsenseMapper.update(adsense);
		return adsense;
	}

	public AdsenseResponse selectByPrimaryKey(Integer id) throws ErrorCodeException{
		Adsense adsense = adsenseMapper.selectByPrimaryKey(id);
		if(adsense == null)
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		AdsenseResponse adsenseResponse=new AdsenseResponse();
		adsenseResponse.setId(adsense.getId());
		adsenseResponse.setName(adsense.getName());
		adsenseResponse.setImg_url(adsense.getUrl());
		adsenseResponse.setRemark(adsense.getRemark());
		adsenseResponse.setTime(adsense.getTime());
		return adsenseResponse;
	}

	public PageResponse<AdsenseResponse> GetPageAdsense(int offset, int pageSize){
		PageResponse<AdsenseResponse> response = new PageResponse();
		List<AdsenseResponse> adsenseResponses=new ArrayList<>();
		List<Adsense> adsenses=adsenseMapper.selectPage(offset,pageSize);
		for(Adsense adsense:adsenses){
			AdsenseResponse adsenseResponse=new AdsenseResponse();
			adsenseResponse.setId(adsense.getId());
			adsenseResponse.setName(adsense.getName());
			adsenseResponse.setImg_url(adsense.getUrl());
			adsenseResponse.setRemark(adsense.getRemark());
			adsenseResponse.setTime(adsense.getTime());
			adsenseResponses.add(adsenseResponse);
		}
		response.setItem(adsenseResponses);
		response.setTotal(adsenseMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;

	}

	public int deleteByPrimaryKey(Integer id){
		Adsense adsense = adsenseMapper.selectByPrimaryKey(id);
		String old_url = adsense.getUrl();
		ossService.delete(old_url);
		return adsenseMapper.deleteByPrimaryKey(id);
	}

	public Adsense GetAdsense(Integer id) throws ErrorCodeException {
		Adsense adsense = adsenseMapper.selectByPrimaryKey(id);
		if(adsense == null)
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		return adsense;
	}

}
