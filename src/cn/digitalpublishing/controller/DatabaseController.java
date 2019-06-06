package cn.digitalpublishing.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.Database;
import cn.digitalpublishing.domain.Log;
import cn.digitalpublishing.util.FileUtil;
import cn.digitalpublishing.util.Pager;

/**
 * Database Controller
 */
@Controller
@RequestMapping("manage/database")
public class DatabaseController extends BaseController {

	/**
	 * List
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p) {
		ModelAndView mav = new ModelAndView();
		Pager pager = new Pager();
		pager.setPageNo(p);
		pager.setTotalCount(databaseService.findAllCount(database));
		mav.addObject("databaseList", databaseService.findByPager(pager));
		mav.addObject("pager", pager);
		mav.setViewName("database/DatabaseList");
		return mav;
	}

	/**
	 * Add
	 * 
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		Map<String, String> commendMap = new LinkedHashMap<String, String>();
		commendMap.put("false", "否");
		commendMap.put("true", "是");
		mav.addObject("commendMap", commendMap);
		mav.setViewName("database/DatabaseEdit");
		mav.addObject("database", new Database());
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
	public ModelAndView save(@ModelAttribute("database") @Valid Database database, BindingResult result, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// 当前资源的前缀命名
		long prefix = System.nanoTime();
		// 存储的全路径
		String path = new StringBuffer(request.getServletContext().getRealPath("/")).append("upload").append(File.separator).append(prefix).append(File.separator).toString();
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		if (result.hasErrors()) {
			Map<String, String> commendMap = new LinkedHashMap<String, String>();
			commendMap.put("false", "否");
			commendMap.put("true", "是");
			mav.addObject("commendMap", commendMap);
			mav.addObject("database", database);
			mav.setViewName("database/DatabaseEdit");
			return mav;
		}
		database.setDatabasePath(Long.toString(prefix));
		database.setDatabaseCreateTime(new Date());
		databaseService.save(database);
		redirectAttributes.addFlashAttribute("tips", "保存成功！");
		
		// 保存日志
		log = new Log();
		log.setLogObject("新增主题数据库");
		log.setLogTitle("新增主题数据库的名称：" + database.getDatabaseName());
		log.setLogCreateTime(new Date());
		logService.save(log);
		
		mav.setViewName("redirect:/manage/database");
		return mav;
	}

	/**
	 * Delete
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("delete/{id:[\\d]+}")
	public String delete(@PathVariable(value = "id") int id, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		database = this.databaseService.findById(id);
		String prefixFile = new StringBuffer(request.getServletContext().getRealPath("/")).append("upload").append(File.separator)
				.append(database.getDatabasePath()).append(File.separator).toString();
		delFolder(prefixFile);
		databaseService.delete(id);
		redirectAttributes.addFlashAttribute("tips", "删除成功！");
		return "redirect:/manage/database";
	}

	/**
	 * Edit
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "edit/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @PathVariable(value = "id") int id) {
		ModelAndView mav = new ModelAndView();
		pager = new Pager();
		pager.setPageNo(p);
		pager.setTotalCount(resourceService.findAllCount(resource));
		mav.addObject("resourceList", resourceService.findByPager(pager));
		mav.addObject("pager", pager);
		mav.addObject("id", id);
		mav.setViewName("database/CompressList");
		return mav;
	}

	/**
	 * move
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("move/{id:[\\d]+}&mid={mid:[\\d]+}")
	public String move(@PathVariable(value = "id") int id, @PathVariable(value = "mid") int mid, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		// 获取文件路径
		database = this.databaseService.findById(mid);
		resource = this.resourceService.findById(id);

		// 1.文件夹路径
		String path = new StringBuffer(request.getServletContext().getRealPath("/")).append("upload").append(File.separator).toString();

		// 2.文件夹全路径
		File zipFilePath = new File(new StringBuffer(path).append(database.getDatabasePath()).append(File.separator).append(resource.getResourceLocation())
				.toString());
		// 文件全路径
		File sourceFilePath = new File(new StringBuffer(path).append(resource.getResourceLocation()).toString());

		// 3.拷贝文件
		FileUtil.copyFileByFileChannel(sourceFilePath, zipFilePath);

		redirectAttributes.addFlashAttribute("tips", "添加成功！");
		return "redirect:/manage/database";
	}

	/**
	 * Download
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("download/{id:[\\d]+}")
	public ResponseEntity<byte[]> downloadZIP(@PathVariable(value = "id") int id, HttpServletRequest request) {
		database = this.databaseService.findById(id);
		if (null != database) {
			try {
				// 存储的全路径
				String path = new StringBuffer(request.getServletContext().getRealPath("/")).append("upload").append(File.separator).toString();
				// 文件夹的存储全路径
				String prefixFile = new StringBuffer(request.getServletContext().getRealPath("/")).append("upload").append(File.separator).append(database.getDatabasePath()).append(File.separator).toString();
				// 文件夹名称
				String prefix = database.getDatabasePath();

				// 1，设置zip的包名
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				String downloadShowZipFileName = new StringBuffer(database.getDatabaseName()).append(".zip").toString();
				headers.setContentDispositionFormData("attachment", new String(downloadShowZipFileName.getBytes("UTF-8"), "ISO8859-1"));

				// 2，检查将要打包的zip文件是否存在
				File downloadZipFile = new File(new StringBuffer(path).append(database.getDatabasePath()).append(".zip").toString());
				LOGGER.info("以将要被打包的文件:" + downloadZipFile.toString());

				// 3，将刚创建的文件夹打包（调用文件工具类，如果之前已经存在打过包的zip则要先删除，因为你不知道它又添加或删除了文件）
				FileUtil.fileToZip(prefixFile.toString(), path, prefix);

				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(downloadZipFile), headers, HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 删除新增时产生的临时文件夹
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			LOGGER.error("删除临时文件出错----------------");
		}
	}

	/**
	 * 删除新增时产生的临时文件夹里的内容
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			try {
				if (temp.isFile() || javax.imageio.ImageIO.read(temp) != null) {
					temp.delete();
				}
			} catch (IOException e) {
				LOGGER.error("删除临时文件出错----------------");
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

}
