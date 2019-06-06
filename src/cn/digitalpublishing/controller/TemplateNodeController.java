package cn.digitalpublishing.controller;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.TemplateNode;

/**
 * TemplateNode Controller
 */
@Controller
@RequestMapping("manage/templateNode")
public class TemplateNodeController extends BaseController {

	/**
	 * List
	 * 
	 * @param typeId
	 * @return
	 */
	@RequestMapping("{typeId}")
	public ModelAndView list(@PathVariable(value = "typeId") int typeId) {
		return new ModelAndView("templateNode/TemplateNodeList", "templateNodeList", templateNodeService.findTemplateNodeListByTypeId(typeId));
	}

	/**
	 * Add
	 * 
	 * @return
	 */
	@RequestMapping(value = "add/{typeId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "typeId") int typeId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("templateNode", new TemplateNode());
		mav.setViewName("templateNode/TemplateNodeEdit");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param resource
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(TemplateNode templateNode, MultipartHttpServletRequest request, @RequestParam(value = "typeId", required = true, defaultValue = "0") int typeId, final RedirectAttributes redirectAttributes) {
		try {
			List<MultipartFile> files = request.getFiles("file");
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					InputStream is = file.getInputStream();
					templateNodeService.templateAddNode(is, file.getOriginalFilename(), typeId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("tips", "保存成功！");
		return "redirect:/manage/templateNode/" + typeId;
	}
	
	/**
	 * Delete
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("delete/{typeId:[\\d]+}")
	public String delete(@PathVariable(value = "typeId") int typeId, final RedirectAttributes redirectAttributes) {
		List<TemplateNode> templateNodeList = this.templateNodeService.findAllTemplateNodeList(typeId);
		for(int i=0;i<templateNodeList.size();i++){
			this.templateNodeService.delete(templateNodeList.get(i).getTemplateNodeId());
		}
		redirectAttributes.addFlashAttribute("tips", "删除成功！");
		return "redirect:/manage/templateNode/" + typeId;
	}

}
