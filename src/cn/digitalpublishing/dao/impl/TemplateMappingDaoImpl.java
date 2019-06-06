package cn.digitalpublishing.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.TemplateMappingDao;
import cn.digitalpublishing.domain.TemplateMapping;
import cn.digitalpublishing.util.Pager;

/**
 * TemplateMapping Dao Implement
 */
@Repository
public class TemplateMappingDaoImpl extends BaseDaoImpl<TemplateMapping, Integer> implements TemplateMappingDao {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.TemplateMappingDao#findByTargetId(int)
	 */
	@Override
	public TemplateMapping findByTargetId(int targetId) {
		String sql = new StringBuffer("SELECT * FROM T_TemplateMapping WHERE templateMappingTargetTemplateId = ?").toString();
		LOGGER.info(sql + " : " + targetId);
		List<TemplateMapping> templateMappingList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TemplateMapping.class), targetId);
		return 0 == templateMappingList.size() ? null : templateMappingList.get(0);
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.impl.BaseDaoImpl#findListByLeftJoin(cn.digitalpublishing.util.Pager, java.lang.String)
	 */
	@Override
	public List<TemplateMapping> findListByLeftJoin(Pager pager, String keyword) {
		String sql = new StringBuffer()
			.append("SELECT m.TemplateMappingId, t.TemplateNodeId, t.TemplateNodePath ")
			.append("FROM T_TemplateMapping m ")
			.append("LEFT JOIN T_TemplateNode t ")
			.append("ON m.TemplateMappingUserTemplateId = t.TemplateNodeId ")
			.append("OR m.TemplateMappingTargetTemplateId = t.TemplateNodeId ")
			.append("ORDER BY m.TemplateMappingId LIMIT ")
			.append(pager.getPageSize())
			.append(" OFFSET ")
			.append(pager.getOffset()).toString();
		LOGGER.info(sql);
		return jdbcTemplate.query(sql, new RowMappers.TemplateMappingANDTemplateNodeMapper());
	}
	
}
