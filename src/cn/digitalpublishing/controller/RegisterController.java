package cn.digitalpublishing.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.Log;
import cn.digitalpublishing.domain.User;

/**
 * Register Controller
 */
@Controller
public class RegisterController extends BaseController {

	/**
	 * Register
	 * 
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public ModelAndView add() {
		return new ModelAndView("Register", "user", new User());
	}

	/**
	 * Save
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("registerSave")
	public ModelAndView save(@ModelAttribute("user") @Valid User user, final RedirectAttributes redirectAttributes) throws Exception {
		ModelAndView mav = new ModelAndView();
		try {
			User u = userService.findByUsername(user.getUsername());
			if (null != u) {
				redirectAttributes.addFlashAttribute("tips", "此名称已被其他人使用!！");
			} else {
				// 新增用户
				user.setUserRole(0);
				user.setUserStatus(0);
				user.setUserCreateTime(new Date());
				userService.save(user);
				redirectAttributes.addFlashAttribute("tips", "恭喜你！新用户注册成功！");
				
				// 保存日志
				log = new Log();
				log.setLogObject("新增用户");
				log.setLogTitle("新增的用户名：" + user.getUsername());
				log.setLogCreateTime(new Date());
				logService.save(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("tips", "新用户注册失败！");
		}
		mav.setViewName("redirect:/index");
		return mav;
	}

}
