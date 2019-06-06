package cn.digitalpublishing.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * User Object
 */
@SuppressWarnings("serial")
public class User extends BaseDomain {

	/** 用户ID */
	private int userId;

	/** 用户姓名 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String username;

	/** 用户密码 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String userPassword;

	/** 用户状态（0：未审核；1：已审核） */
	private int userStatus;

	/** 用户 角色（0：普通用户；1：待定	TODO） */
	private int userRole;

	/** 用户创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date userCreateTime;

	/**
	 * Default Constructor
	 */
	public User() {
		//
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public Date getUserCreateTime() {
		return userCreateTime;
	}

	public void setUserCreateTime(Date userCreateTime) {
		this.userCreateTime = userCreateTime;
	}

}
