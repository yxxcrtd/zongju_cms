package cn.digitalpublishing.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * Column Object
 */
@SuppressWarnings("serial")
public class Column extends BaseDomain {
	
	/**
	 * 栏目ID
	 */
	private int columnId;
	
	/**
	 * 频道ID
	 */
	@NotNull
	@Range(min = 1, message = "请选择频道")
	private int channelId;
	
	/**
	 * 栏目标题
	 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String columnTitle;

	/**
	 * Default Constructor
	 */
	public Column() {
		//
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getColumnTitle() {
		return columnTitle;
	}

	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}

}