package cn.digitalpublishing.service.impl;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import cn.digitalpublishing.dao.TemplateNodeDao;
import cn.digitalpublishing.domain.TemplateNode;
import cn.digitalpublishing.service.TemplateNodeService;

/**
 * TemplateNode Service Implement
 */
@Service
public class TemplateNodeServiceImpl extends BaseServiceImpl<TemplateNode, Integer> implements TemplateNodeService {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.TemplateNodeService#templateAddNode(java.io.InputStream, java.lang.String, int)
	 */
	@Override
	public void templateAddNode(InputStream inputStream, String name, int typeId) throws Exception {
		try {
			// 读取xml
			Document document = load(inputStream);
			// 解析xml 返回节点信息
			getElementList(document.getRootElement(), name, typeId, 9999, "1001");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.TemplateMappingService#findByTargetId(int)
	 */
	@Override
	public TemplateNode findByTemplateNodeParent(int parent,int typeId) {
		
		return ((TemplateNodeDao) baseDao).findByTemplateNodeParent(parent,typeId);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.TemplateMappingService#findByTargetId(int)
	 */
	@Override
	public TemplateNode findByTemplateNodeId(int parent,int typeId) {
		
		return ((TemplateNodeDao) baseDao).findByTemplateNodeId(parent,typeId);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.TemplateNodeService#findTemplateNodeListByTypeId(int)
	 */
	@Override
	public List<Map<String, Object>> findTemplateNodeListByTypeId(int typeId) {
		return ((TemplateNodeDao) baseDao).findTemplateNodeListByTypeId(typeId);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.TemplateMappingService#findByTargetId(int)
	 */
	@Override
	public List<TemplateNode> findAllTemplateNodeList(int typeId){
		return ((TemplateNodeDao) baseDao).findAllTemplateNodeList(typeId);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.TemplateMappingService#findByTargetId(int)
	 */
	@Override
	public List<TemplateNode> findNodeParentList(int parent,int typeId){
		return ((TemplateNodeDao) baseDao).findNodeParentList(parent, typeId);
	}

	/**
	 * 一次全部加载xml文件
	 * 
	 * @param url
	 * @return
	 */
	public Document load(InputStream inputStream) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			// 读取XML文件,获得document对象
			document = saxReader.read(inputStream);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	/**
	 * 递归得到每个node节点path
	 * 
	 * @param element
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public void getElementList(Element element, String templateNodeOriginalName, int typeId, int templateNodeParent, String templateNodeCode) throws Exception {
		List elements = element.elements();
		String nodeName;
		TemplateNode templateNode = null;
		if (elements.size() == 0) {
			// 没有子元素
			String xpath = element.getPath();
			String value = element.getTextTrim();
			nodeName = element.getName();
			// 是否是根节点
			Boolean isRoot = element.isRootElement();
			templateNode = getTemplateNode(xpath, value, nodeName, templateNodeOriginalName, typeId, templateNodeParent, templateNodeCode);
			// 保存记录
			
			templateNode.setTemplateNodeTypeId(typeId);
			this.baseDao.save(templateNode);
		} else {
			// 存储当前节点
			String xpath = element.getPath();
			nodeName = element.getName();
			String value = element.getTextTrim();
			Boolean isRoot = element.isRootElement();
			if (!isRoot) {
				templateNode = getTemplateNode(xpath, value, nodeName, templateNodeOriginalName, typeId, templateNodeParent, templateNodeCode);
				// 保存记录
				templateNode.setTemplateNodeTypeId(typeId);
				this.baseDao.save(templateNode);
				TemplateNode parentNode = this.findByTemplateNodeParent(templateNodeParent,typeId);
				templateNodeParent = parentNode.getTemplateNodeId();
			} else {
				templateNode = getTemplateNode(xpath, value, nodeName, templateNodeOriginalName, typeId, templateNodeParent, templateNodeCode);
				// 保存记录
				
				templateNode.setTemplateNodeTypeId(typeId);
				this.baseDao.save(templateNode);
				TemplateNode parentNodes = this.findByTemplateNodeParent(templateNodeParent,typeId);
				templateNodeParent = parentNodes.getTemplateNodeId();
			}
			// 有子元素
			templateNodeCode = "1000";
			for (Iterator it = elements.iterator(); it.hasNext();) {
				Element elem = (Element) it.next();
				// 递归遍历
				Integer tempCode = Integer.valueOf(templateNodeCode);
				tempCode++;
				templateNodeCode = tempCode.toString();
				getElementList(elem, templateNodeOriginalName, typeId, templateNodeParent, templateNodeCode);
			}
		}
	}

	/**
	 * 返回封装TemplateNode对象
	 */
	public TemplateNode getTemplateNode(String path, String value, String name, String originalName, int typeId, int parent, String code) {
		TemplateNode templateNode = new TemplateNode();
		// 节点路径
		templateNode.setTemplateNodePath(path);
		templateNode.setTemplateNodeValue(value);
		templateNode.setTemplateNodeName(name);
		templateNode.setTemplateNodeOriginalName(originalName);
		if (parent != 9999) {
			TemplateNode nodeparent = this.findByTemplateNodeId(parent,typeId);
			templateNode.setTemplateNodeCode(nodeparent.getTemplateNodeCode() + code);
		} else {
			templateNode.setTemplateNodeCode(code);
		}
		// 设置父类关系
		templateNode.setTemplateNodeParent(parent);
		return templateNode;
	}

}
