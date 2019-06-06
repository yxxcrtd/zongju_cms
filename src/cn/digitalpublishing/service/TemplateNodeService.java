package cn.digitalpublishing.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cn.digitalpublishing.domain.TemplateNode;

/**
 * TemplateNode Service Interface
 */
public interface TemplateNodeService extends BaseService<TemplateNode, Integer> {

	public List<Map<String, Object>> findTemplateNodeListByTypeId(int typeId);

	public void templateAddNode(InputStream inputStream, String name, int typeId) throws Exception;
	
	public List<TemplateNode> findAllTemplateNodeList(int typeId);
	
	public List<TemplateNode> findNodeParentList(int parent,int typeId);
	
	public TemplateNode findByTemplateNodeParent(int parent,int typeId);
	
	public TemplateNode findByTemplateNodeId(int parent,int typeId);

}
