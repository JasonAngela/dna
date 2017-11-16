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
import com.thinkgem.jeesite.modules.entrust.entity.Licensed;
import com.thinkgem.jeesite.modules.entrust.service.LicensedService;

/**
 * 执业证Controller
 * @author 浮云
 * @version 2017-09-13
 */
@Controller
@RequestMapping(value = "${adminPath}/entrust/licensed")
public class LicensedController extends BaseController {

	@Autowired
	private LicensedService licensedService;
	
	@ModelAttribute
	public Licensed get(@RequestParam(required=false) String id) {
		Licensed entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = licensedService.get(id);
		}
		if (entity == null){
			entity = new Licensed();
		}
		return entity;
	}
	
	@RequiresPermissions("entrust:licensed:view")
	@RequestMapping(value = {"list", ""})
	public String list(Licensed licensed, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Licensed> page = licensedService.findPage(new Page<Licensed>(request, response), licensed); 
		model.addAttribute("page", page);
		return "modules/entrust/licensedList";
	}

	@RequiresPermissions("entrust:licensed:view")
	@RequestMapping(value = "form")
	public String form(Licensed licensed, Model model) {
		model.addAttribute("licensed", licensed);
		return "modules/entrust/licensedForm";
	}

	@RequiresPermissions("entrust:licensed:edit")
	@RequestMapping(value = "save")
	public String save(Licensed licensed, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, licensed)){
			return form(licensed, model);
		}
		licensedService.save(licensed);
		addMessage(redirectAttributes, "保存执业证成功");
		return "redirect:"+Global.getAdminPath()+"/entrust/licensed/?repage";
	}
	
	@RequiresPermissions("entrust:licensed:edit")
	@RequestMapping(value = "delete")
	public String delete(Licensed licensed, RedirectAttributes redirectAttributes) {
		licensedService.delete(licensed);
		addMessage(redirectAttributes, "删除执业证成功");
		return "redirect:"+Global.getAdminPath()+"/entrust/licensed/?repage";
	}

}