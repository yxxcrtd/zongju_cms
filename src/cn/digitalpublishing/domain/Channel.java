package cn.digitalpublishing.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Channel Object
 */
@SuppressWarnings("serial")
public class Channel extends BaseDomain {

	/** 频道ID */
	private int channelId;

	/** 站点ID */
	private int websiteId;

	/** 频道标题 */
	@NotBlank
	@Length(min = 1, max = 512, message = "{message.length.error}")
	private String channelTitle;

	/** 频道LOGO */
	private String channelLOGO;

	/**
	 * Default Constructor
	 */
	public Channel() {
		//
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}

	public String getChannelTitle() {
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public String getChannelLOGO() {
		return channelLOGO;
	}

	public void setChannelLOGO(String channelLOGO) {
		this.channelLOGO = channelLOGO;
	}

}
