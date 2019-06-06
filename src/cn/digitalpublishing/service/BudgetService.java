package cn.digitalpublishing.service;

import cn.digitalpublishing.domain.Budget;

/**
 * Budget Service Interface
 */
public interface BudgetService extends BaseService<Budget, Integer> {

	int resetCheckById(int budgetId);

}
