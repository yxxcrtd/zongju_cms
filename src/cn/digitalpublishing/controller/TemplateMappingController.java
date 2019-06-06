package cn.digitalpublishing.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.Log;
import cn.digitalpublishing.domain.TemplateMapping;
import cn.digitalpublishing.util.Pager;

/**
 * TemplateMapping Controller
 */
@Controller
@RequestMapping("manage/cnonix")
public class TemplateMappingController extends BaseController {

	/**
	 * List
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p) {
		ModelAndView mav = new ModelAndView();
		Pager pager = new Pager();
		pager.setPageNo(p);
		pager.setTotalCount(templateNodeService.findAllCount(templateNode));
		mav.addObject("targetTemplateList", templateNodeService.findTemplateNodeListByTypeId(1));
		mav.addObject("userTemplateList", templateNodeService.findTemplateNodeListByTypeId(0));
		mav.addObject("MappingList", templateMappingService.findAllList());
		mav.addObject("pager", pager);
		mav.setViewName("templateMapping/TemplateMappingList");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody
	String save(@RequestParam(value = "templateMappingUserTemplateId") int templateMappingUserTemplateId, @RequestParam(value = "templateMappingTargetTemplateId") int templateMappingTargetTemplateId, final RedirectAttributes redirectAttributes) {
		TemplateMapping mapping = templateMappingService.findByTargetId(templateMappingTargetTemplateId);
		if (0 == templateMappingUserTemplateId) {
			if (null != mapping) {
				templateMappingService.delete(mapping);
				return "delsuccess";
			} else {
				return "delfalse";
			}
		} else {
			if (null == mapping) {
				templateMapping = new TemplateMapping();
				templateMapping.setTemplateMappingUserTemplateId(templateMappingUserTemplateId);
				templateMapping.setTemplateMappingTargetTemplateId(templateMappingTargetTemplateId);
				templateMappingService.save(templateMapping);
				
				// 保存日志
				log = new Log();
				log.setLogObject("新增模板映射");
				log.setLogTitle("新增模板映射的目标节点ID：" + templateMapping.getTemplateMappingTargetTemplateId());
				log.setLogCreateTime(new Date());
				logService.save(log);
				
				return "savesuccess";
			} else {
				mapping.setTemplateMappingUserTemplateId(templateMappingUserTemplateId);
				templateMappingService.update(mapping);
				
				// 保存日志
				log = new Log();
				log.setLogObject("修改模板映射");
				log.setLogTitle("修改模板映射的目标节点ID：" + templateMapping.getTemplateMappingTargetTemplateId());
				log.setLogCreateTime(new Date());
				logService.save(log);
				
				return "updatesuccess";
			}
		}
	}
	
	/**
	 * Delete
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(final RedirectAttributes redirectAttributes) {
		List<TemplateMapping> templateMappingList = this.templateMappingService.findAllList();
		for(int i=0;i<templateMappingList.size();i++){
			this.templateMappingService.delete(templateMappingList.get(i).getTemplateMappingId());
		}
		redirectAttributes.addFlashAttribute("tips", "删除成功！");
		return "redirect:/manage/cnonix";
	}

}
