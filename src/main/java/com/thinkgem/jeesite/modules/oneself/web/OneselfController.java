/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oneself.web;

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
import com.thinkgem.jeesite.modules.oneself.entity.Oneself;
import com.thinkgem.jeesite.modules.oneself.service.OneselfService;

/**
 * 单独实验Controller
 * @author fuyun
 * @version 2017-10-18
 */
@Controller
@RequestMapping(value = "${adminPath}/oneself/oneself")
public class OneselfController extends BaseController {

	@Autowired
	private OneselfService oneselfService;
	
	@ModelAttribute
	public Oneself get(@RequestParam(required=false) String id) {
		Oneself entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oneselfService.get(id);
		}
		if (entity == null){
			entity = new Oneself();
		}
		return entity;
	}
	 
	@RequiresPermissions("oneself:oneself:view")
	@RequestMapping(value = {"list", ""})
	public String list(Oneself oneself, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Oneself> page = oneselfService.findPage(new Page<Oneself>(request, response), oneself); 
		model.addAttribute("page", page);
		return "modules/oneself/oneselfList";
	}

	@RequiresPermissions("oneself:oneself:view")
	@RequestMapping(value = "form")
	public String form(Oneself oneself, Model model) {
		model.addAttribute("oneself", oneself);
		return "modules/oneself/oneselfForm";
	}

	@RequiresPermissions("oneself:oneself:edit")
	@RequestMapping(value = "save")
	public String save(Oneself oneself, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oneself)){
			return form(oneself, model);
		}
		oneselfService.save(oneself);
		addMessage(redirectAttributes, "保存Dna实验成功");
		return "redirect:"+Global.getAdminPath()+"/oneself/oneself/?repage";
	}
	
	@RequiresPermissions("oneself:oneself:edit")
	@RequestMapping(value = "delete")
	public String delete(Oneself oneself, RedirectAttributes redirectAttributes) {
		oneselfService.delete(oneself);
		addMessage(redirectAttributes, "删除Dna实验成功");
		return "redirect:"+Global.getAdminPath()+"/oneself/oneself/?repage";
	}

}