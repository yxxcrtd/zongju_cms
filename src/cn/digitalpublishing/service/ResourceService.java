package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.digitalpublishing.domain.Resource;

/**
 * Resource Service Interface
 */
public interface ResourceService extends BaseService<Resource, Integer> {

	List<Map<String, Object>> findNewestResource();

}
