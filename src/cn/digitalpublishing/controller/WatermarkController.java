package cn.digitalpublishing.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.Log;
import cn.digitalpublishing.domain.Watermark;

/**
 * Watermark Controller
 */
@Controller
@RequestMapping("manage/watermark")
public class WatermarkController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("watermark/WatermarkList", "watermarkList", watermarkService.findAllList());
	}

	/**
	 * Add
	 * 
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		Map<String, String> activeMap = new LinkedHashMap<String, String>();
		activeMap.put("false", "否");
		activeMap.put("true", "是");
		mav.addObject("activeMap", activeMap);
		mav.setViewName("watermark/WatermarkEdit");
		mav.addObject("watermark", new Watermark());
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
		ModelAndView mav = new ModelAndView();
		Map<String, String> activeMap = new LinkedHashMap<String, String>();
		activeMap.put("false", "否");
		activeMap.put("true", "是");
		mav.addObject("activeMap", activeMap);
		mav.setViewName("watermark/WatermarkEdit");
		mav.addObject("watermark", watermarkService.findById(id));
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param watermark
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("watermark") @Valid Watermark watermark, BindingResult result, final RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			Map<String, String> activeMap = new LinkedHashMap<String, String>();
			activeMap.put("false", "否");
			activeMap.put("true", "是");
			mav.addObject("activeMap", activeMap);
			mav.addObject("watermark", watermark);
			mav.setViewName("watermark/WatermarkEdit");
			return mav;
		}

		// 新增父分类或子分类
		if (0 == watermark.getWatermarkId()) {
			watermark.setWatermarkCreateTime(new Date());
			watermarkService.save(watermark);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("新增水印");
			log.setLogTitle("新增的水印名称：" + watermark.getWatermarkName());
			log.setLogCreateTime(new Date());
			logService.save(log);
		} else {
			watermarkService.update(watermark);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("修改水印");
			log.setLogTitle("修改的水印名称：" + watermark.getWatermarkName());
			log.setLogCreateTime(new Date());
			logService.save(log);
		}

		mav.setViewName("redirect:/manage/watermark");
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
		watermarkService.delete(id);
		redirectAttributes.addFlashAttribute("tips", "删除成功！");
		return "redirect:/manage/watermark";
	}

}
