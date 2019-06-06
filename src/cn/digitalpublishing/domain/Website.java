package cn.digitalpublishing.domain;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Website Object
 */
@SuppressWarnings("serial")
public class Website extends BaseDomain{
	/** 站点ID */
	private int websiteId;

	/** 站点标识（只能是英文） */
	@NotBlank
	@Pattern(regexp = "[a-z]+")
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String websiteName;

	/** 站点标题 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String websiteTitle;

	/** 站点Logo */
	private String websiteLOGO;

	public int getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}

	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public String getWebsiteTitle() {
		return websiteTitle;
	}

	public void setWebsiteTitle(String websiteTitle) {
		this.websiteTitle = websiteTitle;
	}

	public String getWebsiteLOGO() {
		return websiteLOGO;
	}

	public void setWebsiteLOGO(String websiteLOGO) {
		this.websiteLOGO = websiteLOGO;
	}
	
	
}
