package cn.digitalpublishing.service.impl;

import org.springframework.stereotype.Service;

import cn.digitalpublishing.dao.WatermarkDao;
import cn.digitalpublishing.domain.Watermark;
import cn.digitalpublishing.service.WatermarkService;

/**
 * Watermark Service Implement
 */
@Service
public class WatermarkServiceImpl extends BaseServiceImpl<Watermark, Integer> implements WatermarkService {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.WatermarkService#findActiveWatermark()
	 */
	@Override
	public Watermark findActiveWatermark() {
		return ((WatermarkDao) baseDao).findActiveWatermark();
	}

}
