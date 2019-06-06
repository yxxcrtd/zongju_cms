package cn.digitalpublishing.domain;

import java.util.Date;

/**
 * Log Object
 */
@SuppressWarnings("serial")
public class Log extends BaseDomain {
	
	/** 日志ID */
	private int logId;

	/** 日志对象 */
	private String logObject;

	/** 日志标题 */
	private String logTitle;

	/** 日志创建时间 */
	private Date logCreateTime;
	
	/**
	 * Default Constructor
	 */
	public Log() {
		//
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getLogObject() {
		return logObject;
	}

	public void setLogObject(String logObject) {
		this.logObject = logObject;
	}

	public String getLogTitle() {
		return logTitle;
	}

	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}

	public Date getLogCreateTime() {
		return logCreateTime;
	}

	public void setLogCreateTime(Date logCreateTime) {
		this.logCreateTime = logCreateTime;
	}

}
