/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.xueka.web;

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
import com.thinkgem.jeesite.modules.xueka.entity.SpecimenXueka;
import com.thinkgem.jeesite.modules.xueka.service.SpecimenXuekaService;

/**
 * 血卡Controller
 * @author fuyunyou
 * @version 2017-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/xueka/specimenXueka")
public class SpecimenXuekaController extends BaseController {

	@Autowired
	private SpecimenXuekaService specimenXuekaService;
	
	@ModelAttribute
	public SpecimenXueka get(@RequestParam(required=false) String id) {
		SpecimenXueka entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = specimenXuekaService.get(id);
		}
		if (entity == null){
			entity = new SpecimenXueka();
		}
		return entity;
	}
	
	@RequiresPermissions("xueka:specimenXueka:view")
	@RequestMapping(value = {"list", ""})
	public String list(SpecimenXueka specimenXueka, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpecimenXueka> page = specimenXuekaService.findPage(new Page<SpecimenXueka>(request, response), specimenXueka); 
		model.addAttribute("page", page);
		return "modules/xueka/specimenXuekaList";
	}

	@RequiresPermissions("xueka:specimenXueka:view")
	@RequestMapping(value = "form")
	public String form(SpecimenXueka specimenXueka, Model model) {
		model.addAttribute("specimenXueka", specimenXueka);
		return "modules/xueka/specimenXuekaForm";
	}

	@RequiresPermissions("xueka:specimenXueka:edit")
	@RequestMapping(value = "save")
	public String save(SpecimenXueka specimenXueka, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, specimenXueka)){
			return form(specimenXueka, model);
		}
		specimenXuekaService.save(specimenXueka);
		addMessage(redirectAttributes, "保存血卡成功");
		return "redirect:"+Global.getAdminPath()+"/xueka/specimenXueka/?repage";
	}
	
	@RequiresPermissions("xueka:specimenXueka:edit")
	@RequestMapping(value = "delete")
	public String delete(SpecimenXueka specimenXueka, RedirectAttributes redirectAttributes) {
		specimenXuekaService.delete(specimenXueka);
		addMessage(redirectAttributes, "删除血卡成功");
		return "redirect:"+Global.getAdminPath()+"/xueka/specimenXueka/?repage";
	}

}