package cn.digitalpublishing.service.impl;

import org.springframework.stereotype.Service;

import cn.digitalpublishing.dao.BudgetDao;
import cn.digitalpublishing.domain.Budget;
import cn.digitalpublishing.service.BudgetService;

/**
 * Budget Service Implement
 */
@Service
public class BudgetServiceImpl extends BaseServiceImpl<Budget, Integer> implements BudgetService {
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.BudgetService#resetCheckById(int)
	 */
	@Override
	public int resetCheckById(int budgetId) {
		if (0 == budgetId) {
			return 0;
		} else {
			return ((BudgetDao) baseDao).resetCheckById(budgetId);
		}
	}
	
}
