package cn.digitalpublishing.service.impl;

import org.springframework.stereotype.Service;

import cn.digitalpublishing.dao.UserDao;
import cn.digitalpublishing.domain.User;
import cn.digitalpublishing.service.UserService;

/**
 * User Service Implement
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.UserService#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String username) {
		if (null == username || "".equals(username)) {
			return null;
		} else {
			return ((UserDao) baseDao).findByUsername(username);
		}
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.UserService#auditUserByUserId(int)
	 */
	@Override
	public int auditUserByUserId(int userId) {
		if (0 > userId) {
			return 0;
		} else {
			return ((UserDao) baseDao).auditUserByUserId(userId);
		}
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.UserService#resetUserPasswordByUserId(int)
	 */
	@Override
	public int resetUserPasswordByUserId(int userId) {
		if (0 > userId) {
			return 0;
		} else {
			return ((UserDao) baseDao).resetUserPasswordByUserId(userId);
		}
	}

}
