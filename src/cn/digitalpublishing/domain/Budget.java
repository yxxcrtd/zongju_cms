package cn.digitalpublishing.domain;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * Budget Object
 */
@SuppressWarnings("serial")
public class Budget extends BaseDomain {

	/** 预算ID */
	private int budgetId;

	/** 预算项目名称 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String budgetItem;

	/** 预算单价 */
	// interger指定整数精度，fraction指定小数精度
	@NotNull
	@Digits(integer = 5, fraction = 2)
	private Double budgetPrice;

	/** 预算数量 */
	@NotNull
	@Range(min = 1, max = 99999, message = "{message.value.error}")
	private int budgetQuantity;

	/** 预算金额 */
	private Double budgetMoney;

	/** 预算备注 */
	@Length(max = 512, message = "长度不能超过512")
	private String budgetRemarks;
	
	/** 核算定价方式（0：其他；1：实洋；2：折扣） */
	private int budgetCheckMethod;
	
	/** 核算价 */
	@DecimalMin(value="0")
	@DecimalMax(value="9999999.99")
	private BigDecimal budgetCheckMoney;

	/**
	 * Default Constructor
	 */
	public Budget() {
		//
	}

	public int getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(int budgetId) {
		this.budgetId = budgetId;
	}

	public String getBudgetItem() {
		return budgetItem;
	}

	public void setBudgetItem(String budgetItem) {
		this.budgetItem = budgetItem;
	}

	public Double getBudgetPrice() {
		return budgetPrice;
	}

	public void setBudgetPrice(Double budgetPrice) {
		this.budgetPrice = budgetPrice;
	}

	public int getBudgetQuantity() {
		return budgetQuantity;
	}

	public void setBudgetQuantity(int budgetQuantity) {
		this.budgetQuantity = budgetQuantity;
	}

	public Double getBudgetMoney() {
		return budgetMoney;
	}

	public void setBudgetMoney(Double budgetMoney) {
		this.budgetMoney = budgetMoney;
	}

	public String getBudgetRemarks() {
		return budgetRemarks;
	}

	public void setBudgetRemarks(String budgetRemarks) {
		this.budgetRemarks = budgetRemarks;
	}

	public int getBudgetCheckMethod() {
		return budgetCheckMethod;
	}

	public void setBudgetCheckMethod(int budgetCheckMethod) {
		this.budgetCheckMethod = budgetCheckMethod;
	}

	public BigDecimal getBudgetCheckMoney() {
		return budgetCheckMoney;
	}

	public void setBudgetCheckMoney(BigDecimal budgetCheckMoney) {
		this.budgetCheckMoney = budgetCheckMoney;
	}
	
}
