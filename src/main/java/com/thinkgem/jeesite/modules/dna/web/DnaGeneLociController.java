/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.web;

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
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneLoci;
import com.thinkgem.jeesite.modules.dna.service.DnaGeneLociService;

/**
 * 基因座Controller
 * @author zhuguli
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/dna/dnaGeneLoci")
public class DnaGeneLociController extends BaseController {

	@Autowired
	private DnaGeneLociService dnaGeneLociService;
	
	@ModelAttribute
	public DnaGeneLoci get(@RequestParam(required=false) String id) {
		DnaGeneLoci entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dnaGeneLociService.get(id);
		}
		if (entity == null){
			entity = new DnaGeneLoci();
		}
		return entity;
	}
	
	@RequiresPermissions("dna:dnaGeneLoci:view")
	@RequestMapping(value = {"list", ""})
	public String list(DnaGeneLoci dnaGeneLoci, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DnaGeneLoci> page = dnaGeneLociService.findPage(new Page<DnaGeneLoci>(request, response), dnaGeneLoci); 
		model.addAttribute("page", page);
		return "modules/dna/dnaGeneLociList";
	}

	@RequiresPermissions("dna:dnaGeneLoci:view")
	@RequestMapping(value = "form")
	public String form(DnaGeneLoci dnaGeneLoci, Model model) {
		model.addAttribute("dnaGeneLoci", dnaGeneLoci);
		return "modules/dna/dnaGeneLociForm";
	}

	@RequiresPermissions("dna:dnaGeneLoci:edit")
	@RequestMapping(value = "save")
	public String save(DnaGeneLoci dnaGeneLoci, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dnaGeneLoci)){
			return form(dnaGeneLoci, model);
		}
		dnaGeneLociService.save(dnaGeneLoci);
		addMessage(redirectAttributes, "保存基因座成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaGeneLoci/?repage";
	}
	
	@RequiresPermissions("dna:dnaGeneLoci:edit")
	@RequestMapping(value = "delete")
	public String delete(DnaGeneLoci dnaGeneLoci, RedirectAttributes redirectAttributes) {
		dnaGeneLociService.delete(dnaGeneLoci);
		addMessage(redirectAttributes, "删除基因座成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaGeneLoci/?repage";
	}

}