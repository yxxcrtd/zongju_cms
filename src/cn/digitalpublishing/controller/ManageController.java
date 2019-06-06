package cn.digitalpublishing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Manage
 */
@Controller
@RequestMapping("manage/index")
public class ManageController extends BaseController {

	/**
	 * Manage
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "Manage";
	}

}
