package cn.digitalpublishing.dao;

import cn.digitalpublishing.domain.TemplateMapping;

/**
 * TemplateMapping DAO
 */
public interface TemplateMappingDao extends BaseDao<TemplateMapping, Integer> {

	TemplateMapping findByTargetId(int targetId);

}
