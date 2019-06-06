package cn.digitalpublishing.domain;


/**
 * templateNode Object
 */
@SuppressWarnings("serial")
public class TemplateNode extends BaseDomain {

	/** 模板Id */
	private int templateNodeId;
	
	/** 模板节点编码 */
	private String templateNodeCode;
	
	/** 模板节点路径 */
	private String templateNodePath;
	
	/** 模版节点名称 */
	private String templateNodeName;
	
	/** 模版节点值 */
	private String templateNodeValue;
	
	/** 模板原始名 */
	private String templateNodeOriginalName;
	
	/** 模板分类 */
	private int templateNodeTypeId;
	
	/** 模板父类节点 */
	private int templateNodeParent;
	
	public TemplateNode() {
		//
	}

	public int getTemplateNodeId() {
		return templateNodeId;
	}

	public void setTemplateNodeId(int templateNodeId) {
		this.templateNodeId = templateNodeId;
	}

	public String getTemplateNodeCode() {
		return templateNodeCode;
	}

	public void setTemplateNodeCode(String templateNodeCode) {
		this.templateNodeCode = templateNodeCode;
	}

	public String getTemplateNodePath() {
		return templateNodePath;
	}

	public void setTemplateNodePath(String templateNodePath) {
		this.templateNodePath = templateNodePath;
	}

	public String getTemplateNodeName() {
		return templateNodeName;
	}

	public void setTemplateNodeName(String templateNodeName) {
		this.templateNodeName = templateNodeName;
	}

	public String getTemplateNodeValue() {
		return templateNodeValue;
	}

	public void setTemplateNodeValue(String templateNodeValue) {
		this.templateNodeValue = templateNodeValue;
	}

	public String getTemplateNodeOriginalName() {
		return templateNodeOriginalName;
	}

	public void setTemplateNodeOriginalName(String templateNodeOriginalName) {
		this.templateNodeOriginalName = templateNodeOriginalName;
	}

	public int getTemplateNodeTypeId() {
		return templateNodeTypeId;
	}

	public void setTemplateNodeTypeId(int templateNodeTypeId) {
		this.templateNodeTypeId = templateNodeTypeId;
	}

	public int getTemplateNodeParent() {
		return templateNodeParent;
	}

	public void setTemplateNodeParent(int templateNodeParent) {
		this.templateNodeParent = templateNodeParent;
	}

}
