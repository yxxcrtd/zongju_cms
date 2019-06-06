package cn.digitalpublishing.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.UserDao;
import cn.digitalpublishing.domain.User;

/**
 * User Dao Implement
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.UserDao#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String username) {
		String sql = new StringBuffer("SELECT * FROM T_User WHERE username = ?").toString();
		LOGGER.info(sql + " : " + username);
		List<User> userList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class), username);
		return 0 == userList.size() ? null : userList.get(0);
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.UserDao#auditUserByUserId(int)
	 */
	@Override
	public int auditUserByUserId(int userId) {
		String sql = "UPDATE T_User SET userStatus = 1 WHERE userId = ?";
		LOGGER.info(sql + " : " + userId);
		try {
			jdbcTemplate.update(sql, userId);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.UserDao#resetUserPasswordByUserId(int)
	 */
	@Override
	public int resetUserPasswordByUserId(int userId) {
		String sql = "UPDATE T_User SET userPassword = '123456' WHERE userId = ?";
		LOGGER.info(sql + " : " + userId);
		try {
			jdbcTemplate.update(sql, userId);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
