package cn.digitalpublishing.service;

import java.util.List;

import cn.digitalpublishing.domain.Channel;

/**
 * Channel Service Interface
 */
public interface ChannelService extends BaseService<Channel, Integer> {

	List<Channel> findNewestChannel();

}
