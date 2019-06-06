package cn.digitalpublishing.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.TemplateNodeDao;
import cn.digitalpublishing.domain.TemplateNode;

/**
 * TemplateNode Dao Implement
 */
@Repository
public class TemplateNodeDaoImpl extends BaseDaoImpl<TemplateNode, Integer> implements TemplateNodeDao {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.TemplateNodeDao#findTemplateNodeListByTypeId(int)
	 */
	@Override
	public List<Map<String, Object>> findTemplateNodeListByTypeId(int typeId) {
		String sql = "SELECT * FROM T_TemplateNode WHERE TemplateNodeTypeId = ? ORDER BY TemplateNodeCode";
		LOGGER.info(sql + " : " + typeId);
		return jdbcTemplate.queryForList(sql, typeId);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.TemplateMappingDao#findByTargetId(int)
	 */
	@Override
	public List<TemplateNode> findAllTemplateNodeList(int typeId) {
		String sql = new StringBuffer("SELECT * FROM T_TemplateNode WHERE templateNodeTypeId = ?").toString();
		LOGGER.info(sql + " : " + typeId);
		List<TemplateNode> templateNodeList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TemplateNode.class), typeId);
		return 0 == templateNodeList.size() ? null : templateNodeList;
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.TemplateMappingDao#findByTargetId(int)
	 */
	@Override
	public List<TemplateNode> findNodeParentList(int parent,int typeId) {
		String sql = new StringBuffer("SELECT * FROM T_TemplateNode WHERE templateNodeParent = ? AND templateNodeTypeId = ?").toString();
		LOGGER.info(sql + " : " + parent + " - " + typeId);
		List<TemplateNode> templateNodeList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TemplateNode.class), parent, typeId);
		return 0 == templateNodeList.size() ? null : templateNodeList;
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.TemplateMappingDao#findByTargetId(int)
	 */
	@Override
	public TemplateNode findByTemplateNodeParent(int parent, int typeId) {
		String sql = new StringBuffer("SELECT * FROM T_TemplateNode WHERE templateNodeParent = ? AND templateNodeTypeId = ?").toString();
		LOGGER.info(sql + " : " + parent + " - " + typeId);
		List<TemplateNode> templateNodeList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TemplateNode.class), parent, typeId);
		return 0 == templateNodeList.size() ? null : templateNodeList.get(0);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.TemplateMappingDao#findByTargetId(int)
	 */
	@Override
	public TemplateNode findByTemplateNodeId(int parent,int typeId) {
		String sql = new StringBuffer("SELECT * FROM T_TemplateNode WHERE templateNodeId = ? and templateNodeTypeId = ?").toString();
		LOGGER.info(sql + " : " + parent + " - " + typeId);
		List<TemplateNode> templateNodeList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TemplateNode.class), parent, typeId);
		return 0 == templateNodeList.size() ? null : templateNodeList.get(0);
	}

}
