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
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialIn;
import com.thinkgem.jeesite.modules.material.service.SpecimenMaterialInService;

/**
 * 样品入库Controller
 * @author doonly
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/material/specimenMaterialIn")
public class SpecimenMaterialInController extends BaseController {

	@Autowired
	private SpecimenMaterialInService specimenMaterialInService;
	@ModelAttribute
	public SpecimenMaterialIn get(@RequestParam(required=false) String id) {
		SpecimenMaterialIn entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = specimenMaterialInService.get(id);
		}
		if (entity == null){
			entity = new SpecimenMaterialIn();
		}
		return entity;
	}
	
	@RequiresPermissions("material:specimenMaterialIn:view")
	@RequestMapping(value = {"list", ""})
	public String list(SpecimenMaterialIn specimenMaterialIn, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpecimenMaterialIn> page = specimenMaterialInService.findPage(new Page<SpecimenMaterialIn>(request, response), specimenMaterialIn); 
		model.addAttribute("page", page);
		return "modules/material/specimenMaterialInList";
	}

	@RequiresPermissions("material:specimenMaterialIn:view")
	@RequestMapping(value = "form")
	public String form(SpecimenMaterialIn specimenMaterialIn, Model model) {
		model.addAttribute("specimenMaterialIn", specimenMaterialIn);
		return "modules/material/specimenMaterialInForm";
	}

	@RequiresPermissions("material:specimenMaterialIn:edit")
	@RequestMapping(value = "save")
	public String save(SpecimenMaterialIn specimenMaterialIn, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, specimenMaterialIn)){
			return form(specimenMaterialIn, model);
		}
		specimenMaterialInService.save(specimenMaterialIn);
		addMessage(redirectAttributes, "保存样品入库成功");
		return "redirect:"+Global.getAdminPath()+"/material/specimenMaterialIn/?repage";
	}
	
	@RequiresPermissions("material:specimenMaterialIn:edit")
	@RequestMapping(value = "delete")
	public String delete(SpecimenMaterialIn specimenMaterialIn, RedirectAttributes redirectAttributes) {
		specimenMaterialInService.delete(specimenMaterialIn);
		addMessage(redirectAttributes, "删除样品入库成功");
		return "redirect:"+Global.getAdminPath()+"/material/specimenMaterialIn/?repage";
	}

}