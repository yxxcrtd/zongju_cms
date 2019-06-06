package cn.digitalpublishing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * LoginController
 */
@Controller
public class LoginController extends BaseController {

	/**
	 * Login
	 * 
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "Login";
	}

	/**
	 * LoginCheck
	 * 
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "loginCheck", method = RequestMethod.POST)
	public String loginCheck(@PathVariable(value = "username") String username, @PathVariable(value = "password") String password, final RedirectAttributes redirectAttributes) {
		if (null == user.getUsername() || "".equals(user.getUsername())) {
			redirectAttributes.addFlashAttribute("tips", "用户名不能为空");
		} else {
			user = userService.findByUsername(username);
			if (password.equals(user.getUserPassword())) {
				// 登陆成功
				redirectAttributes.addFlashAttribute("tips", "登录成功！");
			} else {
				redirectAttributes.addFlashAttribute("tips", "用户名或密码错误!！");
			}
		}
		return "redirect:/index.html";
	}

}
