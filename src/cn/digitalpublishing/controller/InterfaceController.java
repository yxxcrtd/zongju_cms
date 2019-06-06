package cn.digitalpublishing.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

/**
 * Interface Controller
 */
@Controller
@RequestMapping("manage/interface")
public class InterfaceController extends BaseController {

	/**
	 * List
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("scheme", request.getScheme());
		mav.addObject("serverName", request.getServerName());
		mav.addObject("port", request.getServerPort());
		mav.setViewName("interface/InterfaceList");
		return mav;
	}

	/**
	 * Invoke
	 * 
	 * @return
	 */
	@RequestMapping(value = "invoke", produces = "application/json;charset=UTF-8")
	public @ResponseBody String invoke() {
		JSONObject obj = new JSONObject();
		obj.put("resourceList", resourceService.findNewestResource());
		obj.put("status", "success");
		return obj.toString();
	}

}
