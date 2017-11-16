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
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneFrequency;
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneLoci;
import com.thinkgem.jeesite.modules.dna.service.DnaGeneFrequencyService;
import com.thinkgem.jeesite.modules.dna.service.DnaGeneLociService;

/**
 * 等位基因频率Controller
 * @author zhuguli
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/dna/dnaGeneFrequency")
public class DnaGeneFrequencyController extends BaseController {

	@Autowired
	private DnaGeneFrequencyService dnaGeneFrequencyService;
	@Autowired
	private DnaGeneLociService dnaGeneLociService;
	@ModelAttribute
	public DnaGeneFrequency get(@RequestParam(required=false) String id) {
		DnaGeneFrequency entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dnaGeneFrequencyService.get(id);
		}
		if (entity == null){
			entity = new DnaGeneFrequency();
		}
		return entity;
	}
	
	@RequiresPermissions("dna:dnaGeneFrequency:view")
	@RequestMapping(value = {"list", ""})
	public String list(DnaGeneFrequency dnaGeneFrequency, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DnaGeneFrequency> page = dnaGeneFrequencyService.findPage(new Page<DnaGeneFrequency>(request, response), dnaGeneFrequency); 
		model.addAttribute("page", page);
		return "modules/dna/dnaGeneFrequencyList";
	}

	@RequiresPermissions("dna:dnaGeneFrequency:view")
	@RequestMapping(value = "form")
	public String form(DnaGeneFrequency dnaGeneFrequency, Model model) {
		model.addAttribute("dnaGeneFrequency", dnaGeneFrequency);
		DnaGeneLoci dnaGeneLoci=new DnaGeneLoci();
		dnaGeneLoci.setDelFlag("0");
		List<DnaGeneLoci>dnaGeneLocis=dnaGeneLociService.findList(dnaGeneLoci);
		model.addAttribute("dnaGeneLocis", dnaGeneLocis);
		return "modules/dna/dnaGeneFrequencyForm";
	}

	@RequiresPermissions("dna:dnaGeneFrequency:edit")
	@RequestMapping(value = "save")
	public String save(DnaGeneFrequency dnaGeneFrequency, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dnaGeneFrequency)){
			return form(dnaGeneFrequency, model);
		}
		
		dnaGeneFrequencyService.save(dnaGeneFrequency);
		addMessage(redirectAttributes, "保存等位基因频率成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaGeneFrequency/?repage";
	}
	
	@RequiresPermissions("dna:dnaGeneFrequency:edit")
	@RequestMapping(value = "delete")
	public String delete(DnaGeneFrequency dnaGeneFrequency, RedirectAttributes redirectAttributes) {
		dnaGeneFrequencyService.delete(dnaGeneFrequency);
		addMessage(redirectAttributes, "删除等位基因频率成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaGeneFrequency/?repage";
	}

}