package cn.digitalpublishing.domain;

/**
 * TemplateMapping Object
 */
@SuppressWarnings("serial")
public class TemplateMapping extends BaseDomain {

	/** 模板节点映射Id */
	private int templateMappingId;
	
	/** 模板节点映射用户模版名称 */
	private int templateMappingUserTemplateId;
	
	/** 模板节点映射目标模板名称 */
	private int templateMappingTargetTemplateId;
	
	public TemplateMapping() {
		//
	}

	public int getTemplateMappingId() {
		return templateMappingId;
	}

	public void setTemplateMappingId(int templateMappingId) {
		this.templateMappingId = templateMappingId;
	}

	public int getTemplateMappingUserTemplateId() {
		return templateMappingUserTemplateId;
	}

	public void setTemplateMappingUserTemplateId(int templateMappingUserTemplateId) {
		this.templateMappingUserTemplateId = templateMappingUserTemplateId;
	}

	public int getTemplateMappingTargetTemplateId() {
		return templateMappingTargetTemplateId;
	}

	public void setTemplateMappingTargetTemplateId(int templateMappingTargetTemplateId) {
		this.templateMappingTargetTemplateId = templateMappingTargetTemplateId;
	}

}
