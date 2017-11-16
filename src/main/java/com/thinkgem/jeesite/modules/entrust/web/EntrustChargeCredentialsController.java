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
import com.thinkgem.jeesite.modules.entrust.entity.EntrustChargeCredentials;
import com.thinkgem.jeesite.modules.entrust.service.EntrustChargeCredentialsService;

/**
 * 收费凭据Controller
 * @author 浮云
 * @version 2017-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/entrust/entrustChargeCredentials")
public class EntrustChargeCredentialsController extends BaseController {

	@Autowired
	private EntrustChargeCredentialsService entrustChargeCredentialsService;
	
	@ModelAttribute
	public EntrustChargeCredentials get(@RequestParam(required=false) String id) {
		EntrustChargeCredentials entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = entrustChargeCredentialsService.get(id);
		}
		if (entity == null){
			entity = new EntrustChargeCredentials();
		}
		return entity;
	}
	
	@RequiresPermissions("entrust:entrustChargeCredentials:view")
	@RequestMapping(value = {"list", ""})
	public String list(EntrustChargeCredentials entrustChargeCredentials, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EntrustChargeCredentials> page = entrustChargeCredentialsService.findPage(new Page<EntrustChargeCredentials>(request, response), entrustChargeCredentials); 
		model.addAttribute("page", page);
		return "modules/entrust/entrustChargeCredentialsList";
	}

	@RequiresPermissions("entrust:entrustChargeCredentials:view")
	@RequestMapping(value = "form")
	public String form(EntrustChargeCredentials entrustChargeCredentials, Model model) {
		model.addAttribute("entrustChargeCredentials", entrustChargeCredentials);
		return "modules/entrust/entrustChargeCredentialsForm";
	}

	@RequiresPermissions("entrust:entrustChargeCredentials:edit")
	@RequestMapping(value = "save")
	public String save(EntrustChargeCredentials entrustChargeCredentials, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, entrustChargeCredentials)){
			return form(entrustChargeCredentials, model);
		}
		entrustChargeCredentialsService.save(entrustChargeCredentials);
		addMessage(redirectAttributes, "保存收费凭据成功");
		return "redirect:"+Global.getAdminPath()+"/entrust/entrustChargeCredentials/?repage";
	}
	
	@RequiresPermissions("entrust:entrustChargeCredentials:edit")
	@RequestMapping(value = "delete")
	public String delete(EntrustChargeCredentials entrustChargeCredentials, RedirectAttributes redirectAttributes) {
		entrustChargeCredentialsService.delete(entrustChargeCredentials);
		addMessage(redirectAttributes, "删除收费凭据成功");
		return "redirect:"+Global.getAdminPath()+"/entrust/entrustChargeCredentials/?repage";
	}

}