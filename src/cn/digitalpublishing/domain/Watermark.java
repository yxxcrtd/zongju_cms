package cn.digitalpublishing.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Watermark Object
 */
@SuppressWarnings("serial")
public class Watermark extends BaseDomain {
	
	/** 水印ID */
	private int watermarkId;

	/** 水印名称 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String watermarkName;
	
	/** 水印字体大小 */
	@NotNull
	@Range(min = 30, max = 80, message = "{message.value.error}")
	private int watermarkFont;
	
	/** 水印是否激活 */
	private boolean watermarkActive;

	/** 水印创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date watermarkCreateTime;
	
	/**
	 * Default Constructor
	 */
	public Watermark() {
		//
	}

	public int getWatermarkId() {
		return watermarkId;
	}

	public void setWatermarkId(int watermarkId) {
		this.watermarkId = watermarkId;
	}

	public String getWatermarkName() {
		return watermarkName;
	}

	public void setWatermarkName(String watermarkName) {
		this.watermarkName = watermarkName;
	}

	public int getWatermarkFont() {
		return watermarkFont;
	}

	public void setWatermarkFont(int watermarkFont) {
		this.watermarkFont = watermarkFont;
	}

	public boolean isWatermarkActive() {
		return watermarkActive;
	}

	public void setWatermarkActive(boolean watermarkActive) {
		this.watermarkActive = watermarkActive;
	}

	public Date getWatermarkCreateTime() {
		return watermarkCreateTime;
	}

	public void setWatermarkCreateTime(Date watermarkCreateTime) {
		this.watermarkCreateTime = watermarkCreateTime;
	}

}
