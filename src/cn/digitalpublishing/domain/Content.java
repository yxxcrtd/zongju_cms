package cn.digitalpublishing.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Content Object
 */
@SuppressWarnings("serial")
public class Content extends BaseDomain {

	/** 内容ID */
	private int contentId;

	/** 内容ID */
	@NotNull
	@Range(min = 1, message = "请选择栏目")
	private int columnId;

	/** 内容标题 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String contentTitle;

	/** 内容主题 */
	@NotBlank
	@Length(min = 1, max = 512, message = "{长度必须在1-512之间}")
	private String contentContent;

	/** 点击量 */
	private int contentViews;

	/** 创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date contentCreateTime;

	/**
	 * Default Constructor
	 */
	public Content() {
		//
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getContentContent() {
		return contentContent;
	}

	public void setContentContent(String contentContent) {
		this.contentContent = contentContent;
	}

	public int getContentViews() {
		return contentViews;
	}

	public void setContentViews(int contentViews) {
		this.contentViews = contentViews;
	}

	public Date getContentCreateTime() {
		return contentCreateTime;
	}

	public void setContentCreateTime(Date contentCreateTime) {
		this.contentCreateTime = contentCreateTime;
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

}
