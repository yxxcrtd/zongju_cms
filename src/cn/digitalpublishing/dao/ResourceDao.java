package cn.digitalpublishing.dao;

import java.util.List;
import java.util.Map;

import cn.digitalpublishing.domain.Resource;

/**
 * Resource DAO
 */
public interface ResourceDao extends BaseDao<Resource, Integer> {

	List<Map<String, Object>> findNewestResource();

}
