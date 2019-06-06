package cn.digitalpublishing.dao.impl;

import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.BudgetDao;
import cn.digitalpublishing.domain.Budget;

/**
 * Budget Dao Implement
 */
@Repository
public class BudgetDaoImpl extends BaseDaoImpl<Budget, Integer> implements BudgetDao {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.BudgetDao#resetCheckById(int)
	 */
	@Override
	public int resetCheckById(int budgetId) {
		String sql = "UPDATE T_Budget SET budgetCheckMethod = 0, budgetCheckMoney = 0 where budgetId = ?";
		LOGGER.info(sql + " : " + budgetId);
		try {
			jdbcTemplate.update(sql, budgetId);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
