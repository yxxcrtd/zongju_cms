package cn.digitalpublishing.dao;

import java.util.List;
import java.util.Map;

import cn.digitalpublishing.domain.TemplateNode;

/**
 * TemplateNode DAO
 */
public interface TemplateNodeDao extends BaseDao<TemplateNode, Integer> {

	List<Map<String, Object>> findTemplateNodeListByTypeId(int typeId);
	
	List<TemplateNode> findAllTemplateNodeList(int typeId);
	
	List<TemplateNode> findNodeParentList(int parent,int typeId);
	
	TemplateNode findByTemplateNodeParent(int parent,int typeId);
	
	TemplateNode findByTemplateNodeId(int parent,int typeId);

}
