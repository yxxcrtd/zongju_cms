package cn.digitalpublishing.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.ResourceDao;
import cn.digitalpublishing.domain.Resource;
import cn.digitalpublishing.util.Pager;

/**
 * Resource Dao Implement
 */
@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource, Integer> implements ResourceDao {
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.ResourceDao#findNewestResource()
	 */
	@Override
	public List<Map<String, Object>> findNewestResource() {
		String sql = "SELECT * FROM T_Resource ORDER BY ResourceCreateTime DESC LIMIT 10 OFFSET 0";
		LOGGER.info(sql);
		return jdbcTemplate.queryForList(sql);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.impl.BaseDaoImpl#findListByLeftJoin(cn.digitalpublishing.util.Pager, java.lang.String)
	 */
	@Override
	public List<Resource> findListByLeftJoin(Pager pager, String keyword) {
		String sql = new StringBuffer()
			.append("SELECT r.ResourceId, r.ResourceName, r.ResourceFile, r.ResourceISBN, r.ResourceAuthor, r.ResourcePrice, c.CategoryId, c.CategoryName ")
			.append("FROM T_Resource r ")
			.append("LEFT JOIN T_Category c ")
			.append("ON r.CategoryId = c.CategoryId ")
			.append("WHERE r.ResourceName LIKE '%" + keyword + "%' OR r.ResourceAuthor LIKE '%" + keyword + "%' OR r.ResourceISBN LIKE '%" + keyword + "%' ")
			.append("ORDER BY r.ResourceCreateTime DESC LIMIT ")
			.append(pager.getPageSize())
			.append(" OFFSET ")
			.append(pager.getOffset()).toString();
		LOGGER.info(sql);
		return jdbcTemplate.query(sql, new RowMappers.CategoryAndResourceMapper());
	}

}
