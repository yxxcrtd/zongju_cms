package cn.digitalpublishing.service.impl;

import org.springframework.stereotype.Service;

import cn.digitalpublishing.dao.TemplateMappingDao;
import cn.digitalpublishing.domain.TemplateMapping;
import cn.digitalpublishing.service.TemplateMappingService;

/**
 * TemplateMapping Service Implement
 */
@Service
public class TemplateMappingServiceImpl extends BaseServiceImpl<TemplateMapping, Integer> implements TemplateMappingService {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.TemplateMappingService#findByTargetId(int)
	 */
	@Override
	public TemplateMapping findByTargetId(int targetId) {
		if (0 == targetId) {
			return null;
		} else {
			return ((TemplateMappingDao) baseDao).findByTargetId(targetId);
		}
	}

}
