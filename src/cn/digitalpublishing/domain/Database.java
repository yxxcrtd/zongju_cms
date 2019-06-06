package cn.digitalpublishing.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Database Object
 */
@SuppressWarnings("serial")
public class Database extends BaseDomain {

	/** 打包集ID */
	private int databaseId;

	/** 打包集名称 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String databaseName;
	
	/** 打包集路径 */
	private String databasePath;
	
	/** 打包集创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date databaseCreateTime;
	
	/** 打包集是否推荐 */
	private boolean databaseCommend;

	/**
	 * Default Constructor
	 */
	public Database() {
		//
	}

	public int getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(int databaseId) {
		this.databaseId = databaseId;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabasePath() {
		return databasePath;
	}

	public void setDatabasePath(String databasePath) {
		this.databasePath = databasePath;
	}

	public Date getDatabaseCreateTime() {
		return databaseCreateTime;
	}

	public void setDatabaseCreateTime(Date databaseCreateTime) {
		this.databaseCreateTime = databaseCreateTime;
	}

	public boolean isDatabaseCommend() {
		return databaseCommend;
	}

	public void setDatabaseCommend(boolean databaseCommend) {
		this.databaseCommend = databaseCommend;
	}

}
