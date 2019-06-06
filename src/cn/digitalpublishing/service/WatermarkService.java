package cn.digitalpublishing.service;

import cn.digitalpublishing.domain.Watermark;

/**
 * Watermark Service Interface
 */
public interface WatermarkService extends BaseService<Watermark, Integer> {

	Watermark findActiveWatermark();

}
