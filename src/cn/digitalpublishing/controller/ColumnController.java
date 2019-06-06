package cn.digitalpublishing.controller;

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

import cn.digitalpublishing.domain.Channel;
import cn.digitalpublishing.domain.Column;
import cn.digitalpublishing.domain.Log;
import cn.digitalpublishing.domain.Website;
import cn.digitalpublishing.util.Pager;

import com.alibaba.fastjson.JSONObject;

/**
 * Column Controller
 */
@Controller
@RequestMapping("manage/column")
public class ColumnController extends BaseController {

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
		pager.setTotalCount(columnService.findAllCount(column));
		mav.addObject("columnList", columnService.findListByLeftJoin(pager, k));
		mav.addObject("pager", pager);
		mav.setViewName("column/ColumnList");
		return mav;
	}

	/**
	 * Add
	 * 
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		Map<String, String> websiteMap = new LinkedHashMap<String, String>();
		Map<String, String> channelMap = new LinkedHashMap<String, String>();

		// 站点
		List<Website> websiteList = websiteService.findAllList();
		for (Website w : websiteList) {
			websiteMap.put(String.valueOf(w.getWebsiteId()), w.getWebsiteName());
		}

		// 频道
		if (websiteList.size() > 0) {
			for (Channel c : channelService.findAllListById(websiteList.get(0).getWebsiteId())) {
				channelMap.put(String.valueOf(c.getChannelId()), c.getChannelTitle());
			}
		}
		mav.addObject("websiteMap", websiteMap);
		mav.addObject("channelMap", channelMap);
		mav.addObject("column", new Column());
		mav.addObject("channel", new Channel());
		mav.setViewName("column/ColumnEdit");
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
		column = columnService.findById(id);
		if (null == column) {
			column = new Column();
			LOGGER.error("Column：" + id + " 不存在！");
		}
		ModelAndView mav = new ModelAndView();
		Channel channel = channelService.findById(column.getChannelId());
		Website website = websiteService.findById(channel.getWebsiteId());
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
		mav.addObject("websiteMap", websiteMap);
		mav.addObject("channelMap", channelMap);
		mav.setViewName("column/ColumnEdit");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param Column
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("column") @Valid Column column, BindingResult result, final RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		Map<String, String> websiteMap = new LinkedHashMap<String, String>();
		Map<String, String> channelMap = new LinkedHashMap<String, String>();
		if (result.hasErrors()) {
			// 站点
			List<Website> websiteList = websiteService.findAllList();
			for (Website w : websiteList) {
				websiteMap.put(String.valueOf(w.getWebsiteId()), w.getWebsiteName());
			}
			// 频道
			if (websiteList.size() > 0) {
				for (Channel c : channelService.findAllListById(websiteList.get(0).getWebsiteId())) {
					channelMap.put(String.valueOf(c.getChannelId()), c.getChannelTitle());
				}
			}
			mav.addObject("websiteMap", websiteMap);
			mav.addObject("channelMap", channelMap);
			mav.addObject("column", column);
			mav.addObject("channel", new Channel());
			mav.setViewName("column/ColumnEdit");
			return mav;
		}
		if (0 == column.getColumnId()) {
			columnService.save(column);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("新增栏目");
			log.setLogTitle("新增的栏目标题：" + column.getColumnTitle());
			log.setLogCreateTime(new Date());
			logService.save(log);
		} else {
			columnService.update(column);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("修改栏目");
			log.setLogTitle("修改的栏目标题：" + column.getColumnTitle());
			log.setLogCreateTime(new Date());
			logService.save(log);
		}
		mav.setViewName("redirect:/manage/column");
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
		if (contentService.findAllListById(id).size() > 0) {
			redirectAttributes.addFlashAttribute("tips", "该栏目下存在内容，删除失败！");
		} else {
			columnService.delete(id);
			redirectAttributes.addFlashAttribute("tips", "删除成功！");
		}
		return "redirect:/manage/column";
	}

	@RequestMapping(value = "changeChannels", produces = "application/json; charset=UTF-8")
	public @ResponseBody
	String insertMyFavourite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject obj = new JSONObject();
		try {
			String websiteId = request.getParameter("websiteId");
			obj.put("channelList", channelService.findAllListById(Integer.parseInt(websiteId)));
			obj.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			throw e;
		}
		return obj.toString();
	}

}
