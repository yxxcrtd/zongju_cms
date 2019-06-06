package cn.digitalpublishing.service;

import cn.digitalpublishing.domain.TemplateMapping;

/**
 * TemplateMapping Service Interface
 */
public interface TemplateMappingService extends BaseService<TemplateMapping, Integer> {

	TemplateMapping findByTargetId(int targetId);

}
