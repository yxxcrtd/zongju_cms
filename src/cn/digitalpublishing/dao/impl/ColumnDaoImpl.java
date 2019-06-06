package cn.digitalpublishing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.ColumnDao;
import cn.digitalpublishing.domain.Column;
import cn.digitalpublishing.util.Pager;

/**
 * Column Dao Implement
 */
@Repository
public class ColumnDaoImpl extends BaseDaoImpl<Column, Integer> implements ColumnDao {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.impl.BaseDaoImpl#findListByLeftJoin(cn.digitalpublishing.util.Pager, java.lang.String)
	 */
	@Override
	public List<Column> findListByLeftJoin(Pager pager, String keyword) {
		String sql = new StringBuffer("SELECT a.ColumnId, a.ColumnTitle, b.ChannelId, b.ChannelTitle, w.WebsiteId, w.WebsiteTitle ")
			.append("FROM T_Column a ")
			.append("LEFT JOIN T_Channel b ")
			.append("ON a.ChannelId = b.ChannelId ")
			.append("LEFT JOIN T_Website w ")
			.append("ON w.WebsiteId = b.WebsiteId ")
			.append("ORDER BY a.ColumnId DESC LIMIT ")
			.append(pager.getPageSize())
			.append(" OFFSET ")
			.append(pager.getOffset()).toString();
		LOGGER.info(sql);
		return jdbcTemplate.query(sql, new RowMappers.ColumnMapper());
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.impl.BaseDaoImpl#findAllListById(int)
	 */
	@Override
	public List<Column> findAllListById(int ChannelId) {
		StringBuffer sql = new StringBuffer("SELECT a.ColumnId, a.ColumnTitle, b.ChannelId, b.ChannelTitle, w.WebsiteId, w.WebsiteTitle ")
			.append("FROM T_Column a ")
			.append("LEFT JOIN T_Channel b ")
			.append("ON a.ChannelId = b.ChannelId ")
			.append("LEFT JOIN T_Website w ")
			.append("ON w.WebsiteId = b.WebsiteId ")
			.append("WHERE b.ChannelId = " + ChannelId + " ")
			.append("ORDER BY a.ColumnId DESC ");
		LOGGER.info(sql.toString());
		return jdbcTemplate.query(sql.toString(), new RowMappers.ColumnMapper());
	}
	
}
