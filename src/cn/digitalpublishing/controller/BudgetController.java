package cn.digitalpublishing.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.Budget;
import cn.digitalpublishing.domain.Log;
import cn.digitalpublishing.util.Pager;

/**
 * Budget Controller
 */
@Controller
@RequestMapping("manage/budget")
public class BudgetController extends BaseController {

	/**
	 * List
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "{type}", method = RequestMethod.GET)
	public ModelAndView list(@PathVariable(value = "type") int type, @RequestParam(value = "p", required = true, defaultValue = "1") int p) {
		ModelAndView mav = new ModelAndView();
		Pager pager = new Pager();
		pager.setPageNo(p);
		pager.setTotalCount(budgetService.findAllCount(budget));
		mav.addObject("budgetList", budgetService.findByPager(pager));
		mav.addObject("pager", pager);
		mav.setViewName("budget/BudgetList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "edit/{id:[\\d]+}&type={type:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "id") int id, @PathVariable(value = "type") int type) {
		ModelAndView mav = new ModelAndView();

		// New
		if (0 == id) {
			budget = new Budget();
		} else {
			budget = budgetService.findById(id);
		}

		if (1 == type) {
			Map<String, String> methodMap = new LinkedHashMap<String, String>();
			methodMap.put("1", "实洋价");
			methodMap.put("2", "折扣价");
			methodMap.put("0", "其他方式");
			mav.addObject("methodMap", methodMap);
		}
		mav.addObject("budget", budget);
		mav.setViewName("budget/BudgetEdit");
		return mav;
	}

	/**
	 * Reset
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "reset/{id:[\\d]+}&type={type:[\\d]+}")
	public String reset(@PathVariable(value = "id") int id, @PathVariable(value = "type") int type, final RedirectAttributes redirectAttributes) {
		int result = budgetService.resetCheckById(id);
		redirectAttributes.addFlashAttribute("tips", 1 == result ? "重置核算成功！" : "操作删除！");
		return "redirect:/manage/budget/" + type;
	}

	/**
	 * Save
	 * 
	 * @param budget
	 * @param type
	 * @param result
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("budget") @Valid Budget budget, @RequestParam(value = "type", required = true, defaultValue = "0") int type, BindingResult result, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return new ModelAndView("budget/BudgetEdit", "budget", budget);
		}

		budget.setBudgetMoney(budget.getBudgetPrice() * budget.getBudgetQuantity());
		if (0 == budget.getBudgetId()) {
			if (null == budget.getBudgetCheckMoney()) {
				budget.setBudgetCheckMoney(new BigDecimal(0));
			}
			budgetService.save(budget);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("新增预算");
			log.setLogTitle("新增的预算项目：" + budget.getBudgetItem());
			log.setLogCreateTime(new Date());
			logService.save(log);
		} else {
			budgetService.update(budget);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("修改预算");
			log.setLogTitle("修改的预算项目：" + budget.getBudgetItem());
			log.setLogCreateTime(new Date());
			logService.save(log);
		}
		return new ModelAndView("redirect:/manage/budget/" + type);
	}

	/**
	 * Delete
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("del/{id:[\\d]+}&type={type:[\\d]+}")
	public String delete(@PathVariable(value = "id") int id, @PathVariable(value = "type") int type, final RedirectAttributes redirectAttributes) {
		budgetService.delete(id);
		redirectAttributes.addFlashAttribute("tips", "删除成功！");
		return "redirect:/manage/budget/" + type;
	}

}
