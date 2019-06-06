package cn.digitalpublishing.service;

import cn.digitalpublishing.domain.User;

/**
 * User Service Interface
 */
public interface UserService extends BaseService<User, Integer> {

	User findByUsername(String username);

	int auditUserByUserId(int userId);

	int resetUserPasswordByUserId(int userId);

}
