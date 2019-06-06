package cn.digitalpublishing.controller;

import java.util.Date;

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

import cn.digitalpublishing.domain.Log;
import cn.digitalpublishing.domain.Royalty;
import cn.digitalpublishing.util.Pager;

/**
 * Royalty Controller
 */
@Controller
@RequestMapping("manage/royalty")
public class RoyaltyController extends BaseController {

	/**
	 * List
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		Pager pager = new Pager();
		pager.setPageNo(p);
		pager.setTotalCount(royaltyService.findAllCount(royalty));
		mav.addObject("royaltyList", royaltyService.findListByLeftJoin(pager, k));
		mav.addObject("pager", pager);
		mav.setViewName("royalty/RoyaltyList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param resourceId
	 * @param royaltyId
	 * @return
	 */
	@RequestMapping("{resourceId:[\\d]+}/{royaltyId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "resourceId") int resourceId, @PathVariable(value = "royaltyId") int royaltyId) {
		if (0 == royaltyId) {
			royalty = new Royalty();
			royalty.setResourceId(resourceId);
		} else {
			royalty = royaltyService.findById(royaltyId);
		}
		return new ModelAndView("royalty/RoyaltyEdit", "royalty", royalty);
	}

	/**
	 * Save
	 * 
	 * @param Royalty
	 * @param type
	 * @param result
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("royalty") @Valid Royalty royalty, BindingResult result, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return new ModelAndView("royalty/RoyaltyEdit", "royalty", royalty);
		}

		resource = resourceService.findById(royalty.getResourceId());
		if (null != resource) {
			// 版税金额 = 资源的单价 x 资源的印数或销量 x 资源的版税率
			royalty.setRoyaltyMoney(resource.getResourcePrice() * royalty.getRoyaltyTotal() * resource.getResourceRoyalty() * 0.01);
		} else {
			LOGGER.error("版税金额计算出错了！");
			royalty.setRoyaltyMoney(.0);
		}

		if (0 == royalty.getRoyaltyId()) {
			royaltyService.save(royalty);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("新增版税结算");
			log.setLogTitle("新增的版税结算总额：" + royalty.getRoyaltyMoney());
			log.setLogCreateTime(new Date());
			logService.save(log);
		} else {
			royaltyService.update(royalty);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("修改版税结算");
			log.setLogTitle("修改的版税结算总额：" + royalty.getRoyaltyMoney());
			log.setLogCreateTime(new Date());
			logService.save(log);
		}
		return new ModelAndView("redirect:/manage/royalty");
	}

	/**
	 * Delete
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("{id:[\\d]+}")
	public String delete(@PathVariable(value = "id") int id, final RedirectAttributes redirectAttributes) {
		royaltyService.delete(id);
		redirectAttributes.addFlashAttribute("tips", "删除成功！");
		return "redirect:/manage/royalty";
	}

}
