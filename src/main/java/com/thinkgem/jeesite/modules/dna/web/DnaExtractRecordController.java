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
import com.thinkgem.jeesite.modules.dna.entity.DnaExtractRecord;
import com.thinkgem.jeesite.modules.dna.service.DnaExtractRecordService;
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;

/**
 * 提取室记录Controller
 * @author yunyun
 * @version 2017-08-19
 */
@Controller
@RequestMapping(value = "${adminPath}/dna/dnaExtractRecord")
public class DnaExtractRecordController extends BaseController {

	@Autowired
	private DnaExtractRecordService dnaExtractRecordService;
	
	@ModelAttribute
	public DnaExtractRecord get(@RequestParam(required=false) String id) {
		DnaExtractRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dnaExtractRecordService.get(id);
		}
		if (entity == null){
			entity = new DnaExtractRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("dna:dnaExtractRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(DnaExtractRecord dnaExtractRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DnaExtractRecord> page = dnaExtractRecordService.findPage(new Page<DnaExtractRecord>(request, response), dnaExtractRecord); 
		model.addAttribute("page", page);
		return "modules/dna/dnaExtractRecordList";
	}

	@RequiresPermissions("dna:dnaExtractRecord:view")
	@RequestMapping(value = "form")
	public String form(DnaExtractRecord dnaExtractRecord, Model model) {
		model.addAttribute("dnaExtractRecord", dnaExtractRecord);
		SynthLab lab=dnaExtractRecord.getLab();
		model.addAttribute("lab", lab);
		return "modules/dna/dnaExtractRecordForm";
	}
	
	
	

	@RequiresPermissions("dna:dnaExtractRecord:edit")
	@RequestMapping(value = "save")
	public String save(DnaExtractRecord dnaExtractRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dnaExtractRecord)){
			return form(dnaExtractRecord, model);
		}
		
		dnaExtractRecordService.save(dnaExtractRecord);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaExtractRecord/?repage";
	}
	
	@RequiresPermissions("dna:dnaExtractRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(DnaExtractRecord dnaExtractRecord, RedirectAttributes redirectAttributes) {
		dnaExtractRecordService.delete(dnaExtractRecord);
		addMessage(redirectAttributes, "删除保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaExtractRecord/?repage";
	}

}