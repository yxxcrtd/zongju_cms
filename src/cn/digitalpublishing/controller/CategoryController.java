package cn.digitalpublishing.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.Category;
import cn.digitalpublishing.domain.Log;

/**
 * Category Controller
 */
@Controller
@RequestMapping("manage/category")
public class CategoryController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("category/CategoryList", "categoryList", categoryService.findAllList());
	}

	/**
	 * Add
	 * 
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add() {
		return new ModelAndView("category/CategoryEdit", "category", new Category());
	}

	/**
	 * Add Sub Category
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "sub/{id:[\\d]+}")
	public ModelAndView sub(@PathVariable(value = "id") int id) {
		category = new Category();
		category.setCategoryParentId(id);
		category.setCategoryParentPath("/" + Integer.toString(id, 36) + "/");
		return new ModelAndView("category/CategoryEdit", "category", category);
	}

	/**
	 * Edit
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "id") int id) {
		category = categoryService.findById(id);
		if (null == category) {
			LOGGER.error("Category：" + id + " 不存在！");
			category = new Category();
		}
		return new ModelAndView("category/CategoryEdit", "category", category);
	}

	/**
	 * Save
	 * 
	 * @param category
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("category") @Valid Category category, BindingResult result, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return new ModelAndView("category/CategoryEdit", "category", category);
		}

		// 新增父分类或子分类
		if (0 == category.getCategoryId()) {
			// 如果是新增父分类，则默认是没有ParentId和ParentPath，否则是新增子分类
			if (0 == category.getCategoryParentId() && "".equals(category.getCategoryParentPath())) {
				category.setCategoryParentId(0);
				category.setCategoryParentPath("/");
			}
			categoryService.save(category);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("新增分类");
			log.setLogTitle("新增的分类名称：" + category.getCategoryName());
			log.setLogCreateTime(new Date());
			logService.save(log);
		} else {
			categoryService.update(category);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("修改分类");
			log.setLogTitle("修改的分类名称：" + category.getCategoryName());
			log.setLogCreateTime(new Date());
			logService.save(log);
		}
		return new ModelAndView("redirect:/manage/category");
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
		categoryService.delete(id);
		redirectAttributes.addFlashAttribute("tips", "删除成功！");
		return "redirect:/manage/category";
	}

}
