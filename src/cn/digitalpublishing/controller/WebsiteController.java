package cn.digitalpublishing.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.Channel;
import cn.digitalpublishing.domain.Column;
import cn.digitalpublishing.domain.Content;
import cn.digitalpublishing.domain.Log;
import cn.digitalpublishing.domain.Website;
import cn.digitalpublishing.util.FileUtil;
import cn.digitalpublishing.util.Pager;

/**
 * Website Controller
 */
@Controller
@RequestMapping("manage/website")
public class WebsiteController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p) {
		ModelAndView mav = new ModelAndView();
		Pager pager = new Pager();
		pager.setPageNo(p);
		pager.setTotalCount(websiteService.findAllCount(website));
		mav.addObject("websiteList", websiteService.findByPager(pager));
		mav.addObject("pager", pager);
		mav.setViewName("website/WebsiteList");
		return mav;
	}

	/**
	 * Index
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Index");
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
		mav.addObject("website", new Website());
		mav.setViewName("website/WebsiteEdit");
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
		website = websiteService.findById(id);
		if (null == website) {
			website = new Website();
			LOGGER.error("website：" + id + " 不存在！");
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("website", website);
		mav.setViewName("website/websiteEdit");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param website
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("website") @Valid Website website, BindingResult result, MultipartHttpServletRequest request, final RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			mav.addObject("website", website);
			mav.setViewName("website/WebsiteEdit");
			return mav;
		}
		String root = new StringBuffer().append(request.getServletContext().getRealPath("/")).append("upload").append(File.separator).append(website.getWebsiteName()).append(File.separator).toString();
		LOGGER.info("文件的存储全路径：" + root);
		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				website.setWebsiteLOGO(FileUtil.uploadFile(file, root));
			}
		}
		if (0 == website.getWebsiteId()) {
			websiteService.save(website);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("新增网站");
			log.setLogTitle("新增的名称：" + website.getWebsiteName());
			log.setLogCreateTime(new Date());
			logService.save(log);
		} else {
			websiteService.update(website);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("修改网站");
			log.setLogTitle("修改的名称：" + website.getWebsiteName());
			log.setLogCreateTime(new Date());
			logService.save(log);
		}
		mav.setViewName("redirect:/manage/website");
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
		if (channelService.findAllListById(id).size() > 0) {
			redirectAttributes.addFlashAttribute("tips", "站点下存在频道，删除失败！");
		} else {
			websiteService.delete(id);
			redirectAttributes.addFlashAttribute("tips", "删除成功！");
		}
		return "redirect:/manage/website";
	}

	/**
	 * Pub
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("pub/{id:[\\d]+}")
	public String pub(@PathVariable(value = "id") int id, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		website = websiteService.findById(id);
		if (null != website) {
			try {
				// 存储的全路径
				File websiteFolder = new File(new StringBuffer(request.getServletContext().getRealPath("/")).append("upload").append(File.separator).append(website.getWebsiteName()).toString());
				// 如果站点文件夹不存在的话，则需要创建
				if (!websiteFolder.exists()) {
					websiteFolder.mkdir();
				}
				
				// 生成站点下的静态文件
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("request", request);
				map.put("w", website);

				// 生成一级页面
				List<Channel> channelList = channelService.findAllListById(website.getWebsiteId());
				map.put("channelList", channelList);
				map.put("resourceList", resourceService.findAllList());
				map.put("contentList", contentService.findAllList());
				FileUtil.generateHTML("WEB-INF/ftl/website", "WebsiteTemplateIndex.ftl", new StringBuffer("upload").append(File.separator).append(website.getWebsiteName()).append(File.separator).append("index.html").toString(), map, request.getServletContext());
				
				// 生成二级页面
				for (Channel channel : channelList) {
					map.put("c", channel);
					// 获取当前频道下的所有栏目列表
					List<Column> columnList = columnService.findAllListById(channel.getChannelId());
					map.put("columnList", columnList);
					// 生成该栏目下的所有发布的内容
					for (Column column : columnList) {
						List<Content> contentList = contentService.findAllListById(column.getColumnId());
						for (Content content : contentList) {
							map.put("content", content);
							FileUtil.generateHTML("WEB-INF/ftl/website", "Content.ftl", new StringBuffer("upload").append(File.separator).append(website.getWebsiteName()).append(File.separator).append(column.getColumnId()).append("_").append(content.getContentId()).append(".html").toString(), map, request.getServletContext());
						}
					}
					
					map.put("active", true);
					FileUtil.generateHTML("WEB-INF/ftl/website", "WebsiteTemplateSecond.ftl", new StringBuffer("upload").append(File.separator).append(website.getWebsiteName()).append(File.separator).append(channel.getChannelId()).append(".html").toString(), map, request.getServletContext());
				}
				
				return "redirect:/upload/" + website.getWebsiteName();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		redirectAttributes.addFlashAttribute("tips", "站点发布异常！");
		return "redirect:/manage/website";
	}

}
