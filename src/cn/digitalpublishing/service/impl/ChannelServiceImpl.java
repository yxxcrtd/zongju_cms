package cn.digitalpublishing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.digitalpublishing.dao.ChannelDao;
import cn.digitalpublishing.domain.Channel;
import cn.digitalpublishing.service.ChannelService;

/**
 * Channel Service Implement
 */
@Service
public class ChannelServiceImpl extends BaseServiceImpl<Channel, Integer> implements ChannelService {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.ChannelService#findNewestChannel()
	 */
	@Override
	public List<Channel> findNewestChannel() {
		return ((ChannelDao) baseDao).findAllList();
	}

}
