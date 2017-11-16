/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.web;

import java.util.List;

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
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneCassette;
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneLoci;
import com.thinkgem.jeesite.modules.dna.service.DnaGeneCassetteService;
import com.thinkgem.jeesite.modules.dna.service.DnaGeneLociService;

/**
 * 基因盒Controller
 * @author zhuguli
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/dna/dnaGeneCassette")
public class DnaGeneCassetteController extends BaseController {
	@Autowired
	private DnaGeneLociService dnaGeneLociService;
	@Autowired
	private DnaGeneCassetteService dnaGeneCassetteService;
	
	@ModelAttribute
	public DnaGeneCassette get(@RequestParam(required=false) String id) {
		DnaGeneCassette entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dnaGeneCassetteService.get(id);
		}
		if (entity == null){
			entity = new DnaGeneCassette();
		}
		return entity;
	}
	
	@RequiresPermissions("dna:dnaGeneCassette:view")
	@RequestMapping(value = {"list", ""})
	public String list(DnaGeneCassette dnaGeneCassette, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DnaGeneCassette> page = dnaGeneCassetteService.findPage(new Page<DnaGeneCassette>(request, response), dnaGeneCassette); 
		model.addAttribute("page", page);
		return "modules/dna/dnaGeneCassetteList";
	}

	@RequiresPermissions("dna:dnaGeneCassette:view")
	@RequestMapping(value = "form")
	public String form(DnaGeneCassette dnaGeneCassette, Model model) {
		model.addAttribute("dnaGeneCassette", dnaGeneCassette);
		List<DnaGeneLoci> lociList = dnaGeneLociService.findList(new DnaGeneLoci());
		model.addAttribute("lociList", lociList);
		return "modules/dna/dnaGeneCassetteForm";
	}

	@RequiresPermissions("dna:dnaGeneCassette:edit")
	@RequestMapping(value = "save")
	public String save(DnaGeneCassette dnaGeneCassette, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dnaGeneCassette)){
			return form(dnaGeneCassette, model);
		}
		dnaGeneCassetteService.save(dnaGeneCassette);
		addMessage(redirectAttributes, "保存基因盒成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaGeneCassette/?repage";
	}
	
	@RequiresPermissions("dna:dnaGeneCassette:edit")
	@RequestMapping(value = "delete")
	public String delete(DnaGeneCassette dnaGeneCassette, RedirectAttributes redirectAttributes) {
		dnaGeneCassetteService.delete(dnaGeneCassette);
		addMessage(redirectAttributes, "删除基因盒成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaGeneCassette/?repage";
	}

}