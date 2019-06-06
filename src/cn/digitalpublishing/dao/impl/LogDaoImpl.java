package cn.digitalpublishing.dao.impl;

import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.LogDao;
import cn.digitalpublishing.domain.Log;

/**
 * Log Dao Implement
 */
@Repository
public class LogDaoImpl extends BaseDaoImpl<Log, Integer> implements LogDao {

}
