package cn.digitalpublishing.controller;

import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import cn.digitalpublishing.domain.Log;
import cn.digitalpublishing.domain.Website;
import cn.digitalpublishing.util.FileUtil;
import cn.digitalpublishing.util.Pager;

/**
 * Channel
 */
@Controller
@RequestMapping("manage/channel")
public class ChannelController extends BaseController {

	/**
	 * List
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		Pager pager = new Pager();
		pager.setPageNo(p);
		pager.setTotalCount(channelService.findAllCount(channel));
		mav.addObject("channelList", channelService.findListByLeftJoin(pager, k));
		mav.addObject("pager", pager);
		mav.setViewName("channel/ChannelList");
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
		for (Website w : websiteService.findAllList()) {
			websiteMap.put(String.valueOf(w.getWebsiteId()), w.getWebsiteName());
		}
		mav.addObject("websiteMap", websiteMap);
		mav.addObject("channel", new Channel());
		mav.setViewName("channel/ChannelEdit");
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
		channel = channelService.findById(id);
		if (null == channel) {
			channel = new Channel();
			LOGGER.error("Channel：" + id + " 不存在！");
		}
		ModelAndView mav = new ModelAndView();
		Map<String, String> websiteMap = new LinkedHashMap<String, String>();
		for (Website w : websiteService.findAllList()) {
			websiteMap.put(String.valueOf(w.getWebsiteId()), w.getWebsiteName());
		}
		mav.addObject("websiteMap", websiteMap);
		mav.addObject("channel", channel);
		mav.addObject("channelId", channel.getChannelId());
		mav.setViewName("channel/ChannelEdit");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param channel
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("channel") @Valid Channel channel, BindingResult result, MultipartHttpServletRequest request, final RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		Map<String, String> websiteMap = new LinkedHashMap<String, String>();
		if (result.hasErrors()) {
			for (Website w : websiteService.findAllList()) {
				websiteMap.put(String.valueOf(w.getWebsiteId()), w.getWebsiteName());
			}
			mav.addObject("website", new Website());
			mav.addObject("channel", channel);
			mav.addObject("websiteMap", websiteMap);
			mav.setViewName("channel/ChannelEdit");
			return mav;
		}
		
		// 获取站点对象，以取得站点的路径名称
		website = websiteService.findById(channel.getWebsiteId());
		
		String root = new StringBuffer().append(request.getServletContext().getRealPath("/")).append("upload").append(File.separator).append(website.getWebsiteName()).append(File.separator).toString();
		LOGGER.info("文件的存储全路径：" + root);
		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				channel.setChannelLOGO(FileUtil.uploadFile(file, root));
			}
		}
		channel.setWebsiteId(channel.getWebsiteId());
		if (0 == channel.getChannelId()) {
			channelService.save(channel);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("新增频道");
			log.setLogTitle("新增的频道标题：" + channel.getChannelTitle());
			log.setLogCreateTime(new Date());
			logService.save(log);
		} else {
			channelService.update(channel);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("修改频道");
			log.setLogTitle("修改的频道标题：" + channel.getChannelTitle());
			log.setLogCreateTime(new Date());
			logService.save(log);
		}
		mav.setViewName("redirect:/manage/channel");
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
		if (columnService.findAllListById(id).size() > 0) {
			redirectAttributes.addFlashAttribute("tips", "频道下存在栏目，删除失败！");
		} else {
			channelService.delete(id);
			redirectAttributes.addFlashAttribute("tips", "删除成功！");
		}
		return "redirect:/manage/channel";
	}

}
