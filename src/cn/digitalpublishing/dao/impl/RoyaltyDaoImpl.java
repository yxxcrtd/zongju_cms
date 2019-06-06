package cn.digitalpublishing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.RoyaltyDao;
import cn.digitalpublishing.domain.Royalty;
import cn.digitalpublishing.util.Pager;

/**
 * Royalty Dao Implement
 */
@Repository
public class RoyaltyDaoImpl extends BaseDaoImpl<Royalty, Integer> implements RoyaltyDao {
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.impl.BaseDaoImpl#findListByLeftJoin(cn.digitalpublishing.util.Pager, java.lang.String)
	 */
	@Override
	public List<Royalty> findListByLeftJoin(Pager pager, String keyword) {
		String sql = new StringBuffer()
			.append("SELECT r.ResourceId, r.ResourceName, r.ResourcePrice, r.ResourceRoyalty, ro.ResourceId, ro.RoyaltyId, ro.RoyaltyTotal, ro.RoyaltyMoney ")
			.append("FROM T_Resource r ")
			.append("LEFT JOIN T_Royalty ro ")
			.append("ON r.ResourceId = ro.ResourceId ")
			.append("ORDER BY r.ResourceCreateTime DESC LIMIT ")
			.append(pager.getPageSize())
			.append(" OFFSET ")
			.append(pager.getOffset()).toString();
		LOGGER.info(sql);
		return jdbcTemplate.query(sql, new RowMappers.ResourceAndRoyaltyMapper());
	}
	
}
