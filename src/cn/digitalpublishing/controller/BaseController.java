package cn.digitalpublishing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cn.digitalpublishing.domain.Budget;
import cn.digitalpublishing.domain.Category;
import cn.digitalpublishing.domain.Channel;
import cn.digitalpublishing.domain.Column;
import cn.digitalpublishing.domain.Content;
import cn.digitalpublishing.domain.Database;
import cn.digitalpublishing.domain.Log;
import cn.digitalpublishing.domain.Resource;
import cn.digitalpublishing.domain.Royalty;
import cn.digitalpublishing.domain.TemplateMapping;
import cn.digitalpublishing.domain.TemplateNode;
import cn.digitalpublishing.domain.User;
import cn.digitalpublishing.domain.Watermark;
import cn.digitalpublishing.domain.Website;
import cn.digitalpublishing.service.BudgetService;
import cn.digitalpublishing.service.CategoryService;
import cn.digitalpublishing.service.ChannelService;
import cn.digitalpublishing.service.ColumnService;
import cn.digitalpublishing.service.ContentService;
import cn.digitalpublishing.service.DatabaseService;
import cn.digitalpublishing.service.LogService;
import cn.digitalpublishing.service.ResourceService;
import cn.digitalpublishing.service.RoyaltyService;
import cn.digitalpublishing.service.TemplateMappingService;
import cn.digitalpublishing.service.TemplateNodeService;
import cn.digitalpublishing.service.UserService;
import cn.digitalpublishing.service.WatermarkService;
import cn.digitalpublishing.service.WebsiteService;
import cn.digitalpublishing.util.Pager;

/**
 * Base Controller
 */
public class BaseController {

	/**
	 * Log
	 */
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	/**
	 * swftools.pdf2swf
	 */
	@Value("${swftools.pdf2swf}")
	protected String SWFTOOLS_PDF2SWF;

	/**
	 * swftools.font
	 */
	@Value("${swftools.font}")
	protected String SWFTOOLS_FONT;

	/**
	 * Pager Object
	 */
	protected Pager pager;

	/**
	 * User Object
	 */
	protected User user;

	/**
	 * Category Object
	 */
	protected Category category;

	/**
	 * Resource Object
	 */
	protected Resource resource;

	/**
	 * Watermark Object
	 */
	protected Watermark watermark;

	/**
	 * Royalty Object
	 */
	protected Royalty royalty;

	/**
	 * Budget Object
	 */
	protected Budget budget;

	/**
	 * Content Object
	 */
	protected Content content;

	/**
	 * Column Object
	 */
	protected Column column;

	/**
	 * Website Object
	 */
	protected Website website;

	/**
	 * Channel Object
	 */
	protected Channel channel;

	/**
	 * Database Object
	 */
	protected Database database;

	/**
	 * TemplateNode Object
	 */
	protected TemplateNode templateNode;

	/**
	 * TemplateMapping Object
	 */
	protected TemplateMapping templateMapping;
	
	/**
	 * Log Object
	 */
	protected Log log;

	/**
	 * User Service
	 */
	@Autowired
	protected UserService userService;

	/**
	 * Category Service
	 */
	@Autowired
	protected CategoryService categoryService;

	/**
	 * Resource Service
	 */
	@Autowired
	protected ResourceService resourceService;

	/**
	 * Watermark Service
	 */
	@Autowired
	protected WatermarkService watermarkService;

	/**
	 * Royalty Service
	 */
	@Autowired
	protected RoyaltyService royaltyService;

	/**
	 * Budget Service
	 */
	@Autowired
	protected BudgetService budgetService;

	/**
	 * Content Service
	 */
	@Autowired
	protected ContentService contentService;

	/**
	 * Column Service
	 */
	@Autowired
	protected ColumnService columnService;

	/**
	 * Website Service
	 */
	@Autowired
	protected WebsiteService websiteService;

	/**
	 * Channel Service
	 */
	@Autowired
	protected ChannelService channelService;

	/**
	 * Database Service
	 */
	@Autowired
	protected DatabaseService databaseService;

	/**
	 * TemplateNode Service
	 */
	@Autowired
	protected TemplateNodeService templateNodeService;

	/**
	 * TemplateMapping Service
	 */
	@Autowired
	protected TemplateMappingService templateMappingService;
	
	/**
	 * Log Service
	 */
	@Autowired
	protected LogService logService;

}
