package cn.digitalpublishing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.digitalpublishing.domain.Category;
import cn.digitalpublishing.domain.Channel;
import cn.digitalpublishing.domain.Column;
import cn.digitalpublishing.domain.Content;
import cn.digitalpublishing.domain.Resource;
import cn.digitalpublishing.domain.Royalty;
import cn.digitalpublishing.domain.TemplateMapping;
import cn.digitalpublishing.domain.TemplateNode;
import cn.digitalpublishing.domain.Website;

/**
 * Row Mappers
 */
public class RowMappers {

	/**
	 * 分类和资源的数据包装
	 */
	public static final class CategoryAndResourceMapper implements RowMapper<Resource> {
		@Override
		public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
			Resource resource = new Resource();
			resource.setResourceId(rs.getInt("ResourceId"));
			resource.setResourceName(rs.getString("ResourceName"));
			resource.setResourceFile(rs.getString("ResourceFile"));
			resource.setResourceISBN(rs.getString("ResourceISBN"));
			resource.setResourceAuthor(rs.getString("ResourceAuthor"));
			resource.setResourcePrice(rs.getDouble("ResourcePrice"));
			Category category = new Category();
			category.setCategoryId(rs.getInt("CategoryId"));
			category.setCategoryName(rs.getString("CategoryName"));
			resource.setCategory(category);
			return resource;
		}
	}

	/**
	 * 资源和版税的数据包装
	 */
	public static final class ResourceAndRoyaltyMapper implements RowMapper<Royalty> {
		@Override
		public Royalty mapRow(ResultSet rs, int rowNum) throws SQLException {
			Resource resource = new Resource();
			resource.setResourceId(rs.getInt("ResourceId"));
			resource.setResourceName(rs.getString("ResourceName"));
			resource.setResourcePrice(rs.getDouble("ResourcePrice"));
			resource.setResourceRoyalty(rs.getInt("ResourceRoyalty"));
			Royalty royalty = new Royalty();
			royalty.setRoyaltyId(rs.getInt("RoyaltyId"));
			royalty.setResourceId(rs.getInt("ResourceId"));
			royalty.setRoyaltyTotal(rs.getInt("RoyaltyTotal"));
			royalty.setRoyaltyMoney(rs.getDouble("RoyaltyMoney"));
			royalty.setResource(resource);
			return royalty;
		}
	}

	/**
	 * 频道和站点的数据包装
	 */
	public static final class ChannelMapper implements RowMapper<Channel> {
		@Override
		public Channel mapRow(ResultSet rs, int rowNum) throws SQLException {
			Channel channel = new Channel();
			channel.setChannelId(rs.getInt("ChannelId"));
			channel.setChannelTitle(rs.getString("ChannelTitle"));
			channel.setChannelLOGO(rs.getString("channelLOGO"));
			Website website = new Website();
			website.setWebsiteId(rs.getInt("WebsiteId"));
			website.setWebsiteName(rs.getString("WebsiteName"));
			website.setWebsiteTitle(rs.getString("WebsiteTitle"));
			channel.setWebsite(website);
			return channel;
		}
	}
	
	/**
	 * 站点、频道和栏目的数据包装
	 */
	public static final class ColumnMapper implements RowMapper<Column> {
		@Override
		public Column mapRow(ResultSet rs, int rowNum) throws SQLException {
			Column column = new Column();
			column.setColumnId(rs.getInt("ColumnId"));
			column.setColumnTitle(rs.getString("ColumnTitle"));
			Channel channel = new Channel();
			channel.setChannelId(rs.getInt("ChannelId"));
			channel.setChannelTitle(rs.getString("ChannelTitle"));
			column.setChannel(channel);
			Website website = new Website();
			website.setWebsiteId(rs.getInt("WebsiteId"));
			website.setWebsiteTitle(rs.getString("WebsiteTitle"));
			column.setWebsite(website);
			return column;
		}
	}

	/**
	 * 内容和栏目的数据包装
	 */
	public static final class ContentMapper implements RowMapper<Content> {
		@Override
		public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
			Content content = new Content();
			content.setContentId(rs.getInt("ContentId"));
			content.setContentTitle(rs.getString("ContentTitle"));
			content.setContentContent(rs.getString("ContentContent"));
			content.setContentViews(rs.getInt("ContentViews"));
			content.setContentCreateTime(rs.getDate("contentCreateTime"));
			Column column = new Column();
			column.setColumnId(rs.getInt("ColumnId"));
			column.setColumnTitle(rs.getString("ColumnTitle"));
			content.setColumn(column);
			Channel channel = new Channel();
			channel.setChannelId(rs.getInt("ChannelId"));
			channel.setChannelTitle(rs.getString("ChannelTitle"));
			content.setChannel(channel);
			Website website = new Website();
			website.setWebsiteId(rs.getInt("WebsiteId"));
			website.setWebsiteTitle(rs.getString("WebsiteTitle"));
			content.setWebsite(website);
			return content;
		}
	}
	
	/**
	 * 模板和映射节点的数据包装
	 */
	public static final class TemplateMappingANDTemplateNodeMapper implements RowMapper<TemplateMapping> {
		@Override
		public TemplateMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
			TemplateMapping mapping = new TemplateMapping();
			mapping.setTemplateMappingId(rs.getInt("TemplateMappingId"));
			TemplateNode templateNode = new TemplateNode();
			templateNode.setTemplateNodeId(rs.getInt("TemplateNodeId"));
			templateNode.setTemplateNodePath(rs.getString("TemplateNodePath"));
			mapping.setTemplateNode(templateNode);
			return mapping;
		}
	}

}
