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
import com.thinkgem.jeesite.modules.entrust.entity.EntrustAbstracts;
import com.thinkgem.jeesite.modules.entrust.service.EntrustAbstractsService;

/**
 * 摘要登记Controller
 * @author zhuguli
 * @version 2017-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/entrust/entrustAbstracts")
public class EntrustAbstractsController extends BaseController {

	@Autowired
	private EntrustAbstractsService entrustAbstractsService;
	
	@ModelAttribute
	public EntrustAbstracts get(@RequestParam(required=false) String id) {
		EntrustAbstracts entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = entrustAbstractsService.get(id);
		}
		if (entity == null){
			entity = new EntrustAbstracts();
		}
		return entity;
	}
	
	@RequiresPermissions("entrust:entrustAbstracts:view")
	@RequestMapping(value = {"list", ""})
	public String list(EntrustAbstracts entrustAbstracts, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EntrustAbstracts> page = entrustAbstractsService.findPage(new Page<EntrustAbstracts>(request, response), entrustAbstracts); 
		model.addAttribute("page", page);
		return "modules/entrust/entrustAbstractsList";
	}

	@RequiresPermissions("entrust:entrustAbstracts:view")
	@RequestMapping(value = "form")
	public String form(EntrustAbstracts entrustAbstracts, Model model) {
		model.addAttribute("entrustAbstracts", entrustAbstracts);
		return "modules/entrust/entrustAbstractsForm";
	}

	@RequiresPermissions("entrust:entrustAbstracts:edit")
	@RequestMapping(value = "save")
	public String save(EntrustAbstracts entrustAbstracts, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, entrustAbstracts)){
			return form(entrustAbstracts, model);
		}
		
		
		entrustAbstractsService.save(entrustAbstracts);
		addMessage(redirectAttributes, "保存保存摘要登记成功成功");
		return "redirect:"+Global.getAdminPath()+"/entrust/entrustAbstracts/?repage";
	}
	
	@RequiresPermissions("entrust:entrustAbstracts:edit")
	@RequestMapping(value = "delete")
	public String delete(EntrustAbstracts entrustAbstracts, RedirectAttributes redirectAttributes) {
		entrustAbstractsService.delete(entrustAbstracts);
		addMessage(redirectAttributes, "删除保存摘要登记成功成功");
		return "redirect:"+Global.getAdminPath()+"/entrust/entrustAbstracts/?repage";
	}

}