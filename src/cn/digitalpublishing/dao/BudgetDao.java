package cn.digitalpublishing.dao;

import cn.digitalpublishing.domain.Budget;

/**
 * Budget DAO
 */
public interface BudgetDao extends BaseDao<Budget, Integer> {

	int resetCheckById(int budgetId);

}
