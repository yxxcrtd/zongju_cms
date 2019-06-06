package cn.digitalpublishing.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.WatermarkDao;
import cn.digitalpublishing.domain.Watermark;

/**
 * Watermark Dao Implement
 */
@Repository
public class WatermarkDaoImpl extends BaseDaoImpl<Watermark, Integer> implements WatermarkDao {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.WatermarkDao#findActiveWatermark()
	 */
	@Override
	public Watermark findActiveWatermark() {
		String sql = "SELECT * FROM T_Watermark WHERE WatermarkActive = 1 ORDER BY WatermarkCreateTime DESC";
		LOGGER.info(sql);
		List<Watermark> watermarkList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Watermark.class));
		return 0 == watermarkList.size() ? null : watermarkList.get(0);
	}

}
