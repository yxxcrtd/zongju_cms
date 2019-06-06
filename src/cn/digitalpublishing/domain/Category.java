package cn.digitalpublishing.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * Category Object
 * 
 * @NotEmpty 用在集合上
 * @NotBlank 用在String上
 * @NotNull 用在基本类型上
 */
@SuppressWarnings("serial")
public class Category extends BaseDomain {
	
	/** 分类ID */
	private int categoryId;

	/** 分类名称 */
//	@NotEmpty(message = "{category.name.null}")
	@NotBlank
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String categoryName;
	
	/** 分类父ID */
	private int categoryParentId;
	
	/** 分类父路径 */
	private String categoryParentPath;

	/** 分类排序 */
	@NotNull
	@Range(min = 0, max = 999, message = "{message.value.error}")
	private int categoryOrderby;
	
	/**
	 * Default Constructor
	 */
	public Category() {
		//
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryParentId() {
		return categoryParentId;
	}

	public void setCategoryParentId(int categoryParentId) {
		this.categoryParentId = categoryParentId;
	}

	public String getCategoryParentPath() {
		return categoryParentPath;
	}

	public void setCategoryParentPath(String categoryParentPath) {
		this.categoryParentPath = categoryParentPath;
	}

	public int getCategoryOrderby() {
		return categoryOrderby;
	}

	public void setCategoryOrderby(int categoryOrderby) {
		this.categoryOrderby = categoryOrderby;
	}

}
