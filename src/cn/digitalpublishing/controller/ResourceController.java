package cn.digitalpublishing.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.Category;
import cn.digitalpublishing.domain.Log;
import cn.digitalpublishing.domain.Resource;
import cn.digitalpublishing.domain.TemplateMapping;
import cn.digitalpublishing.domain.TemplateNode;
import cn.digitalpublishing.util.FileUtil;
import cn.digitalpublishing.util.PDFUtil;
import cn.digitalpublishing.util.Pager;

/**
 * Resource
 */
@Controller
@RequestMapping("manage/resource")
public class ResourceController extends BaseController {

	/**
	 * List
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		pager = new Pager();
		pager.setPageNo(p);
		pager.setTotalCount(resourceService.findAllCount(resource));
		mav.addObject("resourceList", resourceService.findListByLeftJoin(pager, null == k ? "" : k.trim()));
		mav.addObject("pager", pager);
		mav.addObject("k", k);
		mav.setViewName("resource/ResourceList");
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
		Map<String, String> categoryMap = new LinkedHashMap<String, String>();
		for (Category c : categoryService.findAllList()) {
			categoryMap.put(String.valueOf(c.getCategoryId()), c.getCategoryName());
		}
		mav.addObject("categoryMap", categoryMap);
		mav.addObject("resource", new Resource());
		mav.setViewName("resource/ResourceEdit");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "id") int id) {
		resource = resourceService.findById(id);

		if (null == resource) {
			resource = new Resource();
			LOGGER.error("Resource：" + id + " 不存在！");
		}

		ModelAndView mav = new ModelAndView();
		Map<String, String> categoryMap = new LinkedHashMap<String, String>();
		for (Category c : categoryService.findAllList()) {
			categoryMap.put(String.valueOf(c.getCategoryId()), c.getCategoryName());
		}
		mav.addObject("categoryMap", categoryMap);
		mav.addObject("resource", resource);
		mav.setViewName("resource/ResourceEdit");
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
	public ModelAndView save(@ModelAttribute("resource") @Valid Resource resource, BindingResult result, MultipartHttpServletRequest request, final RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			Map<String, String> categoryMap = new LinkedHashMap<String, String>();
			for (Category c : categoryService.findAllList()) {
				categoryMap.put(String.valueOf(c.getCategoryId()), c.getCategoryName());
			}
			mav.addObject("categoryMap", categoryMap);
			mav.addObject("resource", resource);
			mav.setViewName("resource/ResourceEdit");
			return mav;
		}

		String root = new StringBuffer().append(request.getServletContext().getRealPath("/")).append("upload").append(File.separator).toString();
		LOGGER.info("文件的存储全路径：" + root);
		
		List<MultipartFile> fileCovers = request.getFiles("fileCover");
		for (MultipartFile fileCover : fileCovers) {
			if (!fileCover.isEmpty()) {
				resource.setResourceCover(FileUtil.uploadFile(fileCover, root));
			}
		}
		
		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				resource.setResourceFile(file.getOriginalFilename());
				resource.setResourceLocation(FileUtil.uploadFile(file, root));
			}
		}

		resource.setCategoryId(resource.getCategory().getCategoryId());
		if (0 == resource.getResourceId()) {
			resource.setResourceCreateTime(new Date());
			resourceService.save(resource);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("新增资源");
			log.setLogTitle("新增的资源名称：" + resource.getResourceName());
			log.setLogCreateTime(new Date());
			logService.save(log);
		} else {
			resourceService.update(resource);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
			
			// 保存日志
			log = new Log();
			log.setLogObject("修改资源");
			log.setLogTitle("修改的资源名称：" + resource.getResourceName());
			log.setLogCreateTime(new Date());
			logService.save(log);
		}

		mav.setViewName("redirect:/manage/resource");
		return mav;
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
		resourceService.delete(id);
		redirectAttributes.addFlashAttribute("tips", "删除成功！");
		return "redirect:/manage/resource";
	}

	/**
	 * View
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("view/{id:[\\d]+}")
	public ModelAndView view(@PathVariable(value = "id") int id, HttpServletRequest request) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		resource = resourceService.findById(id);
		String swfFile = "";
		if (null != resource) {
			map.put("resource", resource);
			swfFile = new StringBuffer(resource.getResourceLocation().substring(0, resource.getResourceLocation().lastIndexOf("."))).append(".swf").toString();
			String destPath = new StringBuffer().append(request.getServletContext().getRealPath("/")).append("upload").append(File.separator).toString();
			String pdfFileFullPath = new StringBuffer(destPath).append(resource.getResourceLocation()).toString();

			// 先检查swf全文文件是否存在，如果不存在则需要将pdf文件转换成swf文件
			File swfFileFullPath = new File(new StringBuffer(destPath).append(swfFile).toString());
			if (!swfFileFullPath.exists()) {
				int returnValue = PDFUtil.convertPDF2SWF(SWFTOOLS_PDF2SWF, pdfFileFullPath, destPath, swfFile, SWFTOOLS_FONT);
				LOGGER.info("PDF2SWF转换试读文件后返回的值：" + returnValue);
			}
			map.put("swfFile", swfFile);
		}
		return new ModelAndView("resource/ResourceView", map);
	}

	/**
	 * Viewer
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("viewer/{id:[\\d]+}")
	public ModelAndView viewer(@PathVariable(value = "id") int id) throws IOException {
		resource = resourceService.findById(id);
		if (null != resource) {
			return new ModelAndView("resource/ResourceViewer", "pdf", resource.getResourceLocation());
		}
		return null;
	}

	/**
	 * Download PDF
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("download/pdf/{id:[\\d]+}")
	public ResponseEntity<byte[]> downloadPDF(@PathVariable(value = "id") int id, HttpServletRequest request) {
		// 获取下载资源的对象
		resource = resourceService.findById(id);
		if (null != resource) {
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDispositionFormData("attachment", new String(resource.getResourceFile().getBytes("UTF-8"), "ISO8859-1"));
				// 检查PDF文件是否存在
				String storeFullPath = new StringBuffer(request.getServletContext().getRealPath("/")).append("upload").append(File.separator).toString();
				File pdfFullPath = new File(new StringBuffer(storeFullPath).append(resource.getResourceLocation()).toString());
				// 水印文件的规则是：在系统生成的PDF文件前加一个下划线
				File pdfWatermarkFullPath = new File(new StringBuffer(storeFullPath).append("_").append(resource.getResourceLocation()).toString());
				if (!pdfFullPath.exists()) {
					throw new Exception("PDF文件不存在!");
				} else if (!pdfWatermarkFullPath.exists()) {
					// 加水印（获取已经激活的水印对象，如果有多个激活的话，取最新一个激活的）
					watermark = watermarkService.findActiveWatermark();
					if (null != watermark) {
						PDFUtil.watermark(watermark.getWatermarkName(), watermark.getWatermarkFont(), request.getRemoteAddr(), pdfFullPath.toString(),
								pdfWatermarkFullPath.toString(), Color.LIGHT_GRAY, "STSong-Light", "UniGB-UCS2-H", true);
					} else {
						throw new Exception("没有任何激活的水印!");
					}
				}
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(pdfWatermarkFullPath), headers, HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Download ZIP
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("download/zip/{id:[\\d]+}")
	public ResponseEntity<byte[]> downloadZIP(@PathVariable(value = "id") int id, HttpServletRequest request) {
		resource = resourceService.findById(id);
		if (null != resource) {
			try {
				// 存储的全路径
				String storeFullPath = new StringBuffer(request.getServletContext().getRealPath("/")).append("upload").append(File.separator).toString();
				// 打包文件的前缀
				String prefix = resource.getResourceLocation().substring(0, resource.getResourceLocation().lastIndexOf("."));

				// 1，设置zip的包名
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				String downloadShowZipFileName = new StringBuffer(resource.getResourceFile().substring(0, resource.getResourceFile().lastIndexOf("."))).append(".zip").toString();
				headers.setContentDispositionFormData("attachment", new String(downloadShowZipFileName.getBytes("UTF-8"), "ISO8859-1"));

				// 2，检查将要打包的zip文件是否存在
				File downloadZipFile = new File(new StringBuffer(storeFullPath).append(prefix).append(".zip").toString());
				LOGGER.info("以将要被打包的文件:" + downloadZipFile.toString());
				// 如果将要被下载的zip文件不存在，就要打包
				if (!downloadZipFile.exists()) {
					// 3，检查原始文件是否存在（不管什么格式的文件都可以打成zip包，并加密）
					File storeFileFullPath = new File(new StringBuffer(storeFullPath).append(resource.getResourceLocation()).toString());
					if (!storeFileFullPath.exists()) {
						throw new Exception("将要被打包的原始文件不存在!");
					} else {
						// 4，如果是PDF格式的文件需要加水印（TODO，其他格式的暂不考虑水印问题）
						File pdfFullPath = new File(new StringBuffer(storeFullPath).append(resource.getResourceLocation()).toString());
						File pdfWatermarkFullPath = null;
						if (pdfFullPath.toString().endsWith("pdf")) {
							// 检查水印文件是否存在，水印的规则是：在系统生成的PDF文件前加一个下划线
							pdfWatermarkFullPath = new File(new StringBuffer(storeFullPath).append("_").append(resource.getResourceLocation()).toString());
							// 如果水印文件不存在的话，需要先加水印
							if (!pdfWatermarkFullPath.exists()) {
								// 加水印（获取已经激活的水印对象，如果有多个激活的话，取最新一个激活的）
								watermark = watermarkService.findActiveWatermark();
								if (null != watermark) {
									PDFUtil.watermark(watermark.getWatermarkName(), watermark.getWatermarkFont(), request.getRemoteAddr(), pdfFullPath.toString(), pdfWatermarkFullPath.toString(), Color.LIGHT_GRAY, "STSong-Light", "UniGB-UCS2-H", true);
								} else {
									throw new Exception("没有任何激活的水印!");
								}
							}
						}

						// 5，创建以将要被打包的文件前缀名命名的文件夹（检查该文件夹是否存在）
						File prefixFile = new File(new StringBuffer(storeFullPath).append(prefix).toString());
						if (!prefixFile.exists()) {
							prefixFile.mkdir();
						}

						// 6，将被打包的文件拷贝到刚创建的文件夹中
						File zipFilePath = new File(new StringBuffer(storeFullPath).append(prefix).append(File.separator).append(resource.getResourceFile()).toString());
						FileUtil.copyFileByFileChannel(pdfWatermarkFullPath, zipFilePath);

						// 7，将刚创建的文件夹打包（调用文件工具类）
						FileUtil.fileToZip(prefixFile.toString(), storeFullPath, prefix);

						// 8，删除之前创建的文件夹（包含里面的文件）
						// TODO
					}
				}
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(downloadZipFile), headers, HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Product XML
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("product/{id:[\\d]+}")
	public ResponseEntity<byte[]> downloadXML(@PathVariable(value = "id") int id, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		resource = resourceService.findById(id);
		try {
			// 存放路径
			String path = new StringBuffer(request.getServletContext().getRealPath("/")).append("upload").toString();
			// 判断路径是否存在
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 生成名称
			String fileName = resource.getResourceISBN() + ".xml";
			// 循环获取节点
			Document document = DocumentHelper.createDocument();
			XMLWriter writer = null;

			List<TemplateNode> templateNodeList = this.templateNodeService.findAllTemplateNodeList(1);
			for (int i = 0; i < templateNodeList.size(); i++) {
				// 找到根节点
				if (9999 == templateNodeList.get(0).getTemplateNodeParent()) {
					Element nodeElement = document.addElement(templateNodeList.get(0).getTemplateNodeName());
					getChild(templateNodeList.get(i), nodeElement, resource, map);
					break;
				}
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			writer = new XMLWriter(new FileWriter(path + File.separator + fileName), format);
			writer.write(document);
			writer.close();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", new String(resource.getResourceName().getBytes("UTF-8"), "ISO8859-1")+".xml");
			// 检查PDF文件是否存在
			String storeFullPath = new StringBuffer(request.getServletContext().getRealPath("/")).append("upload").append(File.separator).toString();
			File pdfFullPath = new File(new StringBuffer(storeFullPath).append(resource.getResourceISBN()) + ".xml".toString());
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(pdfFullPath), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 递归
	 * 
	 * @param node
	 * @param nodeElement
	 * @param resource
	 * @param map
	 * @throws Exception
	 */
	private void getChild(TemplateNode node, Element nodeElement, Resource resource, Map<String, String> map) throws Exception {
		// 查询用户模板节点对象
		List<TemplateNode> userNodeList = this.templateNodeService.findNodeParentList(node.getTemplateNodeId(), node.getTemplateNodeTypeId());
		for (int j = 0; j < userNodeList.size(); j++) {
			getChildNode(userNodeList.get(j), nodeElement, resource, map);
		}
	}

	/**
	 * 递归得到所有子节点
	 * 
	 * @param node
	 * @param nodeElement
	 * @param resource
	 * @param map
	 * @throws Exception
	 */
	private void getChildNode(TemplateNode node, Element nodeElement, Resource resource, Map<String, String> map) throws Exception {
		if (9999 != node.getTemplateNodeParent()) {
			nodeElement = nodeElement.addElement(node.getTemplateNodeName());
			// 获取用户上传的数据列表
			TemplateMapping templateMapping = this.templateMappingService.findByTargetId(node.getTemplateNodeId());
			if (null != templateMapping) {
				TemplateNode tempNode = this.templateNodeService.findByTemplateNodeId(templateMapping.getTemplateMappingUserTemplateId(), 0);
				if (null != tempNode) {
					nodeElement.setText(tempNode.getTemplateNodeValue());
				}
			}
		}
		
		// 查询用户模板节点对象
		List<TemplateNode> userNodeList = this.templateNodeService.findNodeParentList(node.getTemplateNodeId(), node.getTemplateNodeTypeId());
		if (null != userNodeList) {
			for (int j = 0; j < userNodeList.size(); j++) {
				getChildNode(userNodeList.get(j), nodeElement, resource, map);
			}
		}
	}

}
