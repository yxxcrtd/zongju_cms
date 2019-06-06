package cn.digitalpublishing.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

/**
 * Royalty Object
 */
@SuppressWarnings("serial")
public class Royalty extends BaseDomain {

	/** 版税ID */
	private int royaltyId;
	
	/** 资源ID */
	private int resourceId;

	/** 版税印量 */
	@NotNull
	@Range(min = 1, max = 99999, message = "{message.value.error}")
	private int royaltyTotal;

	/** 版税金额 */
	private Double royaltyMoney;

	/**
	 * Default Constructor
	 */
	public Royalty() {
		//
	}

	public int getRoyaltyId() {
		return royaltyId;
	}

	public void setRoyaltyId(int royaltyId) {
		this.royaltyId = royaltyId;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getRoyaltyTotal() {
		return royaltyTotal;
	}

	public void setRoyaltyTotal(int royaltyTotal) {
		this.royaltyTotal = royaltyTotal;
	}

	public Double getRoyaltyMoney() {
		return royaltyMoney;
	}

	public void setRoyaltyMoney(Double royaltyMoney) {
		this.royaltyMoney = royaltyMoney;
	}
	
}
