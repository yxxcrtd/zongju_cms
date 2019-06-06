package cn.digitalpublishing.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

import cn.digitalpublishing.domain.Channel;
import cn.digitalpublishing.domain.Column;
import cn.digitalpublishing.domain.Content;
import cn.digitalpublishing.domain.Log;
import cn.digitalpublishing.domain.Website;
import cn.digitalpublishing.util.Pager;

/**
 * Content Controller
 */
@Controller
@RequestMapping("manage/content")
public class ContentController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		Pager pager = new Pager();
		pager.setPageNo(p);
		pager.setTotalCount(contentService.findAllCount(content));
		mav.addObject("contentList", contentService.findListByLeftJoin(pager, k));
		mav.addObject("pager", pager);
		mav.setViewName("content/ContentList");
		return mav;
	}

	/**
	 * Add
	 * 
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add() {
		Map<String, String> websiteMap = new LinkedHashMap<String, String>();
		Map<String, String> channelMap = new LinkedHashMap<String, String>();
		Map<String, String> columnMap = new LinkedHashMap<String, String>();
		List<Website> websiteList = new ArrayList<Website>();
		List<Channel> channelList = new ArrayList<Channel>();
		List<Column> columnList = new ArrayList<Column>();
		ModelAndView mav = new ModelAndView();
		mav.addObject("content", new Content());
		mav.addObject("column", new Column());
		mav.addObject("channel", new Channel());
		// 站点
		websiteList = websiteService.findAllList();
		for (Website w : websiteList) {
			websiteMap.put(String.valueOf(w.getWebsiteId()), w.getWebsiteName());
		}
		// 频道
		if (websiteList.size() > 0) {
			channelList = channelService.findAllListById(websiteList.get(0).getWebsiteId());
			for (Channel c : channelList) {
				channelMap.put(String.valueOf(c.getChannelId()), c.getChannelTitle());
			}
		}
		// 栏目
		if (channelList.size() > 0) {
			columnList = columnService.findAllListById(channelList.get(0).getChannelId());
			for (Column c : columnList) {
				columnMap.put(String.valueOf(c.getColumnId()), c.getColumnTitle());
			}
		}
		mav.addObject("websiteMap", websiteMap);
		mav.addObject("channelMap", channelMap);
		mav.addObject("columnMap", columnMap);
		mav.setViewName("content/ContentEdit");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "id") int id) {
		Content content = contentService.findById(id);
		if (null == column) {
			column = new Column();
			LOGGER.error("Column：" + id + " 不存在！");
		}
		ModelAndView mav = new ModelAndView();
		Column column = columnService.findById(content.getColumnId());
		Channel channel = channelService.findById(column.getChannelId());
		Website website = websiteService.findById(channel.getWebsiteId());
		mav.addObject("content", content);
		mav.addObject("column", column);
		mav.addObject("channel", channel);
		mav.addObject("website", website);
		// 站点
		Map<String, String> websiteMap = new LinkedHashMap<String, String>();
		for (Website w : websiteService.findAllList()) {
			websiteMap.put(String.valueOf(w.getWebsiteId()), w.getWebsiteName());
		}
		// 频道
		Map<String, String> channelMap = new LinkedHashMap<String, String>();
		for (Channel c : channelService.findAllListById(website.getWebsiteId())) {
			channelMap.put(String.valueOf(c.getChannelId()), c.getChannelTitle());
		}
		// 栏目
		Map<String, String> columnMap = new LinkedHashMap<String, String>();
		for (Column c : columnService.findAllListById(channel.getChannelId())) {
			columnMap.put(String.valueOf(c.getColumnId()), c.getColumnTitle());
		}
		mav.addObject("websiteMap", websiteMap);
		mav.addObject("channelMap", channelMap);
		mav.addObject("columnMap", columnMap);
		mav.setViewName("content/ContentEdit");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param content
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("content") @Valid Content content, BindingResult result, final RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		List<Website> websiteList = new ArrayList<Website>();
		List<Channel> channelList = new ArrayList<Channel>();
		List<Column> columnList = new ArrayList<Column>();
		Map<String, String> websiteMap = new LinkedHashMap<String, String>();
		Map<String, String> channelMap = new LinkedHashMap<String, String>();
		Map<String, String> columnMap = new LinkedHashMap<String, String>();
		if (result.hasErrors()) {
			// 站点
			websiteList = websiteService.findAllList();
			for (Website w : websiteList) {
				websiteMap.put(String.valueOf(w.getWebsiteId()), w.getWebsiteName());
			}
			// 频道
			if (websiteList.size() > 0) {
				channelList = channelService.findAllListById(websiteList.get(0).getWebsiteId());
				for (Channel c : channelList) {
					channelMap.put(String.valueOf(c.getChannelId()), c.getChannelTitle());
				}
			}
			// 栏目
			if (channelList.size() > 0) {
				columnList = columnService.findAllListById(channelList.get(0).getChannelId());
				for (Column c : columnList) {
					columnMap.put(String.valueOf(c.getColumnId()), c.getColumnTitle());
				}
			}
			mav.addObject("column", new Column());
			mav.addObject("channel", new Channel());
			mav.addObject("website", new Website());
			mav.addObject("content", content);
			mav.addObject("websiteMap", websiteMap);
			mav.addObject("channelMap", channelMap);
			mav.addObject("columnMap", columnMap);
			mav.setViewName("content/ContentEdit");
			return mav;
		}

		if (0 == content.getContentId()) {
			content.setContentCreateTime(new Date());
			contentService.save(content);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("新增内容");
			log.setLogTitle("新增的内容标题：" + content.getContentTitle());
			log.setLogCreateTime(new Date());
			logService.save(log);
		} else {
			contentService.update(content);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("修改内容");
			log.setLogTitle("修改的内容标题：" + content.getContentTitle());
			log.setLogCreateTime(new Date());
			logService.save(log);
		}
		mav.setViewName("redirect:/manage/content");
		return mav;
	}

	/**
	 * Delete
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("del/{id:[\\d]+}")
	public String delete(@PathVariable(value = "id") int id, final RedirectAttributes redirectAttributes) {
		contentService.delete(id);
		redirectAttributes.addFlashAttribute("tips", "删除成功！");
		return "redirect:/manage/content";
	}

	@RequestMapping(value = "changeChannels", produces = "application/json; charset=utf-8")
	public @ResponseBody String changeChannels(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject obj = new JSONObject();
		List<Channel> channelList = new ArrayList<Channel>();
		List<Column> columnList = new ArrayList<Column>();
		try {
			String websiteId = request.getParameter("websiteId");
			channelList = channelService.findAllListById(Integer.parseInt(websiteId));
			if (channelList.size() > 0) {
				columnList = columnService.findAllListById(channelList.get(0).getChannelId());
			}
			obj.put("columnList", columnList);
			obj.put("channelList", channelList);
			obj.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			throw e;
		}
		return obj.toString();
	}

	@RequestMapping(value = "changeColumns", produces = "application/json; charset=utf-8")
	public @ResponseBody
	String changeColumns(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		JSONObject obj = new JSONObject();
		List<Column> columnList = new ArrayList<Column>();
		try {
			String channelId = request.getParameter("channelId");
			columnList = columnService.findAllListById(Integer.parseInt(channelId));
			obj.put("columnList", columnList);
			obj.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			throw e;
		}
		return obj.toString();
	}

}
