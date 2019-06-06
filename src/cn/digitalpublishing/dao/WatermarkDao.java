package cn.digitalpublishing.dao;

import cn.digitalpublishing.domain.Watermark;

/**
 * Watermark DAO
 */
public interface WatermarkDao extends BaseDao<Watermark, Integer> {

	Watermark findActiveWatermark();

}
