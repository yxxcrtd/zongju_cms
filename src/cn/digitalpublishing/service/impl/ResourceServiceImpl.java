package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.digitalpublishing.dao.ResourceDao;
import cn.digitalpublishing.domain.Resource;
import cn.digitalpublishing.service.ResourceService;

/**
 * Resource Service Implement
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Integer> implements ResourceService {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.ResourceService#findNewestResource()
	 */
	@Override
	public List<Map<String, Object>> findNewestResource() {
		return ((ResourceDao) baseDao).findNewestResource();
	}

}
