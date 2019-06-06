package cn.digitalpublishing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.ChannelDao;
import cn.digitalpublishing.domain.Channel;
import cn.digitalpublishing.util.Pager;

/**
 * Channel Dao Implement
 */
@Repository
public class ChannelDaoImpl extends BaseDaoImpl<Channel, Integer> implements ChannelDao {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.impl.BaseDaoImpl#findListByLeftJoin(cn.digitalpublishing.util.Pager, java.lang.String)
	 */
	@Override
	public List<Channel> findListByLeftJoin(Pager pager, String keyword) {
		String sql = new StringBuffer("SELECT c.ChannelId, c.ChannelTitle, c.ChannelLOGO, w.WebsiteId, w.WebsiteName, w.WebsiteTitle ")
			.append("FROM T_Channel c ")
			.append("LEFT JOIN T_Website w ")
			.append("ON c.WebsiteId = w.WebsiteId ")
			.append("ORDER BY w.WebsiteId DESC LIMIT ")
			.append(pager.getPageSize())
			.append(" OFFSET ")
			.append(pager.getOffset()).toString();
		LOGGER.info(sql);
		return jdbcTemplate.query(sql, new RowMappers.ChannelMapper());
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.impl.BaseDaoImpl#findAllListById(int)
	 */
	@Override
	public List<Channel> findAllListById(int websiteId) {
		StringBuffer sql = new StringBuffer("SELECT c.ChannelId, c.ChannelTitle, c.ChannelLOGO, w.WebsiteId, w.WebsiteName, w.WebsiteTitle ")
			.append("FROM T_Channel c ")
			.append("LEFT JOIN T_Website w ")
			.append("ON c.WebsiteId = w.WebsiteId ")
			.append("WHERE w.WebsiteId = " + websiteId + " ")
			.append("ORDER BY c.ChannelId DESC");
		LOGGER.info(sql.toString());
		return jdbcTemplate.query(sql.toString(), new RowMappers.ChannelMapper());
	}
	
}
