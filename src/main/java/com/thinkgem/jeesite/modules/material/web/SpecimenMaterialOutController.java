/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialOut;
import com.thinkgem.jeesite.modules.material.service.SpecimenMaterialOutService;

/**
 * 物料领取Controller
 * @author zhuguli
 * @version 2017-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/material/specimenMaterialOut")
public class SpecimenMaterialOutController extends BaseController {

	@Autowired
	private SpecimenMaterialOutService specimenMaterialOutService;
	
	@ModelAttribute
	public SpecimenMaterialOut get(@RequestParam(required=false) String id) {
		SpecimenMaterialOut entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = specimenMaterialOutService.get(id);
		}
		if (entity == null){
			entity = new SpecimenMaterialOut();
		}
		return entity;
	}
	
	@RequiresPermissions("material:specimenMaterialOut:view")
	@RequestMapping(value = {"list", ""})
	public String list(SpecimenMaterialOut specimenMaterialOut, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpecimenMaterialOut> page = specimenMaterialOutService.findPage(new Page<SpecimenMaterialOut>(request, response), specimenMaterialOut); 
		model.addAttribute("page", page);
		return "modules/material/specimenMaterialOutList";
	}

	@RequiresPermissions("material:specimenMaterialOut:view")
	@RequestMapping(value = "form")
	public String form(SpecimenMaterialOut specimenMaterialOut, Model model) {
		model.addAttribute("specimenMaterialOut", specimenMaterialOut);
		return "modules/material/specimenMaterialOutForm";
	}

	@RequiresPermissions("material:specimenMaterialOut:edit")
	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		String[]ids =  request.getParameterValues("ids");
		specimenMaterialOutService.save(ids);
		addMessage(redirectAttributes, "保存物料领取成功");
		return "redirect:"+Global.getAdminPath()+"/material/specimenMaterialOut/?repage";
	}
	
	@RequiresPermissions("material:specimenMaterialOut:edit")
	@RequestMapping(value = "delete")
	public String delete(SpecimenMaterialOut specimenMaterialOut, RedirectAttributes redirectAttributes) {
		specimenMaterialOutService.delete(specimenMaterialOut);
		addMessage(redirectAttributes, "删除物料领取成功");
		return "redirect:"+Global.getAdminPath()+"/material/specimenMaterialOut/?repage";
	}

}