/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.entrust.entity.Mapping;
import com.thinkgem.jeesite.modules.entrust.service.MappingService;

/**
 * 图谱Controller
 * @author 好好
 * @version 2017-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/entrust/mapping")
public class MappingController extends BaseController {

	@Autowired
	private MappingService mappingService;
	
	@ModelAttribute
	public Mapping get(@RequestParam(required=false) String id) {
		Mapping entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mappingService.get(id);
		}
		if (entity == null){
			entity = new Mapping();
		}
		return entity;
	}

	@RequestMapping(value = {"list", ""})
	public String list(Mapping mapping, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Mapping> page = mappingService.findPage(new Page<Mapping>(request, response), mapping); 
		model.addAttribute("page", page);
		return "modules/entrust/mappingList";
	}

	
	@RequestMapping(value = "form")
	public String form(Mapping mapping, Model model) {
		model.addAttribute("mapping", mapping);
		return "modules/entrust/mappingForm";
	}

	
	@RequestMapping(value = "save")
	public String save(Mapping mapping, Model model, RedirectAttributes redirectAttributes) {
		
		if (!beanValidator(model, mapping)){
			return form(mapping, model);
		}
		mappingService.save(mapping);
		addMessage(redirectAttributes, "保存图谱成功");
		return "redirect:"+Global.getAdminPath()+"/entrust/mapping/?repage";
	}
	

	@RequestMapping(value = "delete")
	public String delete(Mapping mapping, RedirectAttributes redirectAttributes) {
		mappingService.delete(mapping);
		addMessage(redirectAttributes, "删除图谱成功");
		return "redirect:"+Global.getAdminPath()+"/entrust/mapping/?repage";
	}

}