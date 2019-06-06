package cn.digitalpublishing.dao.impl;

import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.CategoryDao;
import cn.digitalpublishing.domain.Category;

/**
 * Category Dao Implement
 */
@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category, Integer> implements CategoryDao {

}
