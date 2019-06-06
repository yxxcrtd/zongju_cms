package cn.digitalpublishing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.ContentDao;
import cn.digitalpublishing.domain.Content;
import cn.digitalpublishing.util.Pager;

/**
 * Content Dao Implement
 */
@Repository
public class ContentDaoImpl extends BaseDaoImpl<Content, Integer> implements ContentDao {
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.impl.BaseDaoImpl#findListByLeftJoin(cn.digitalpublishing.util.Pager, java.lang.String)
	 */
	@Override
	public List<Content> findListByLeftJoin(Pager pager, String keyword) {
		String sql = new StringBuffer("SELECT a.ContentId, a.ContentTitle, a.ContentContent, a.ContentViews, a.ContentCreateTime, b.ColumnId, b.ColumnTitle, c.ChannelId, c.ChannelTitle, d.WebsiteId, d.WebsiteTitle ")
			.append("FROM T_Content a ")
			.append("LEFT JOIN T_Column b ON a.ColumnId = b.ColumnId ")
			.append("LEFT JOIN T_Channel c ON c.ChannelId = b.ChannelId ")
			.append("LEFT JOIN T_Website d ON d.WebsiteId = c.WebsiteId ")
			.append("ORDER BY a.ContentId DESC LIMIT ")
			.append(pager.getPageSize())
			.append(" OFFSET ")
			.append(pager.getOffset()).toString();
		LOGGER.info(sql);
		return jdbcTemplate.query(sql, new RowMappers.ContentMapper());
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.impl.BaseDaoImpl#findAllListById(int)
	 */
	@Override
	public List<Content> findAllListById(int ColumnId) {
		StringBuffer sql = new StringBuffer()
			.append("SELECT a.ContentId, a.ContentTitle, a.ContentContent, a.ContentViews, a.ContentCreateTime, b.ColumnId, b.ColumnTitle, c.ChannelId, c.ChannelTitle, w.WebsiteId, w.WebsiteTitle ")
			.append("FROM T_Content a ")
			.append("LEFT JOIN T_Column b ON a.ColumnId = b.ColumnId ")
			.append("LEFT JOIN T_Website w ON w.WebsiteId = b.ColumnId ")
			.append("LEFT JOIN T_Channel c ON c.ChannelId = b.ChannelId ")
			.append("WHERE b.ColumnId = " + ColumnId + " ")
			.append("ORDER BY a.ColumnId DESC");
		LOGGER.info(sql.toString());
		return jdbcTemplate.query(sql.toString(), new RowMappers.ContentMapper());
	}

}
