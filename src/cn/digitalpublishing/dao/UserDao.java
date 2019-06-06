package cn.digitalpublishing.dao;

import cn.digitalpublishing.domain.User;

/**
 * User DAO
 */
public interface UserDao extends BaseDao<User, Integer> {

	User findByUsername(String username);

	int auditUserByUserId(int userId);

	int resetUserPasswordByUserId(int userId);

}
