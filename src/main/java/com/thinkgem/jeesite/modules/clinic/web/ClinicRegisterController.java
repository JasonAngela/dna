/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.clinic.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.clinic.entity.ClinicRegister;
import com.thinkgem.jeesite.modules.clinic.service.ClinicRegisterService;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegister;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegisterItem;
import com.thinkgem.jeesite.modules.xueka.entity.SpecimenXueka;
import com.thinkgem.jeesite.modules.xueka.entity.SpecimenXuekaLsit;

/**
 * 临床登记Controller
 * @author zhuguli
 * @version 2017-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/clinic/clinicRegister")
public class ClinicRegisterController extends BaseController {
	@Autowired
	private ClinicRegisterService clinicRegisterService;
	@ModelAttribute
	public ClinicRegister get(@RequestParam(required=false) String id) {
		ClinicRegister entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = clinicRegisterService.get(id);
		}
		if (entity == null){
			entity = new ClinicRegister();
		}
		return entity;
	}
	
	@RequiresPermissions("clinic:clinicRegister:view")
	@RequestMapping(value = {"list", ""})
	public String list(ClinicRegister clinicRegister, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ClinicRegister> page = clinicRegisterService.findPage(new Page<ClinicRegister>(request, response), clinicRegister); 
		model.addAttribute("page", page);
		return "modules/clinic/clinicRegisterList";
	}

	@RequiresPermissions("clinic:clinicRegister:view")
	@RequestMapping(value = "form")
	public String form(ClinicRegister clinicRegister, Model model) {
		model.addAttribute("clinicRegister", clinicRegister);
		return "modules/clinic/clinicRegisterForm";
	}
	
	@RequiresPermissions("clinic:clinicRegister:view")
	@RequestMapping(value = "materialForm")
	public String materialRegisterForm(ClinicRegister clinicRegister, Model model) {
		model.addAttribute("clinicRegister", clinicRegister);
		return "modules/clinic/materialForm";
	}
	

	@RequiresPermissions("clinic:clinicRegister:view")
	@RequestMapping(value = "saveMaterial")
	public String saveMaterialRegister(ClinicRegister clinicRegister, Model model) {
		model.addAttribute("clinicRegister", clinicRegister);
		clinicRegisterService.saveMaterial(clinicRegister);
		return "redirect:"+Global.getAdminPath()+"/clinic/clinicRegister/?repage";
	}
	
	
	 
	@RequiresPermissions("clinic:clinicRegister:edit")
	@RequestMapping(value = "save")
	public String save(ClinicRegister clinicRegister, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, clinicRegister)){
			return form(clinicRegister, model);
		}
		clinicRegisterService.save(clinicRegister);
		addMessage(redirectAttributes, "保存临床登记成功");
		return "redirect:"+Global.getAdminPath()+"/clinic/clinicRegister/?repage";
	}
	
	@RequiresPermissions("clinic:clinicRegister:edit")
	@RequestMapping(value = "delete")
	public String delete(ClinicRegister clinicRegister, RedirectAttributes redirectAttributes) {
		clinicRegisterService.delete(clinicRegister);
		addMessage(redirectAttributes, "删除临床登记成功");
		return "redirect:"+Global.getAdminPath()+"/clinic/clinicRegister/?repage";
	}

}