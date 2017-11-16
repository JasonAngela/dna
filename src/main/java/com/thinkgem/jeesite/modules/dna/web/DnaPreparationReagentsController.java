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
import com.thinkgem.jeesite.modules.dna.entity.DnaPreparationReagents;
import com.thinkgem.jeesite.modules.dna.service.DnaPreparationReagentsService;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipment;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipmentUsageRecord;
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;
import com.thinkgem.jeesite.modules.synth.service.SynthEquipmentUsageRecordService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 试剂配制记录表Controller
 * @author fyun
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/dna/dnaPreparationReagents")
public class DnaPreparationReagentsController extends BaseController {

	@Autowired
	private DnaPreparationReagentsService dnaPreparationReagentsService;
	@Autowired
	private SynthEquipmentUsageRecordService synthEquipmentUsageRecordService;
	@ModelAttribute
	public DnaPreparationReagents get(@RequestParam(required=false) String id) {
		DnaPreparationReagents entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dnaPreparationReagentsService.get(id);
		}
		if (entity == null){
			entity = new DnaPreparationReagents();
		}
		return entity;
	}
	
	@RequiresPermissions("dna:dnaPreparationReagents:view")
	@RequestMapping(value = {"list", ""})
	public String list(DnaPreparationReagents dnaPreparationReagents, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DnaPreparationReagents> page = dnaPreparationReagentsService.findPage(new Page<DnaPreparationReagents>(request, response), dnaPreparationReagents); 
		model.addAttribute("page", page);
		return "modules/dna/dnaPreparationReagentsList";
	}

	@RequiresPermissions("dna:dnaPreparationReagents:view")
	@RequestMapping(value = "form")
	public String form(DnaPreparationReagents dnaPreparationReagents, Model model) {
		SynthLab lab=dnaPreparationReagents.getLab();
		model.addAttribute("lab", lab);
		model.addAttribute("dnaPreparationReagents", dnaPreparationReagents);
		return "modules/dna/dnaPreparationReagentsForm";
	}

	@RequiresPermissions("dna:dnaPreparationReagents:edit")
	@RequestMapping(value = "save")
	public String save(DnaPreparationReagents dnaPreparationReagents, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dnaPreparationReagents)){
			return form(dnaPreparationReagents, model);
		}
		User user=UserUtils.getUser();
		for (SynthEquipment iterable_element : dnaPreparationReagents.getSynthEquipments()) {
			SynthEquipmentUsageRecord synthEquipmentUsageRecord=new SynthEquipmentUsageRecord();
			SynthEquipment equipment=new SynthEquipment();
			equipment.setId(iterable_element.getId());
			synthEquipmentUsageRecord.setEquipment(equipment);
			synthEquipmentUsageRecord.setOperator(user);
			synthEquipmentUsageRecordService.save(synthEquipmentUsageRecord);
		}
		dnaPreparationReagents.setOperator(user);
		dnaPreparationReagentsService.save(dnaPreparationReagents);
		addMessage(redirectAttributes, "保存试剂配制记录表成功");
		return "redirect:" + adminPath + "/dna/dnaExperiment/form";
	}
	
	@RequiresPermissions("dna:dnaPreparationReagents:edit")
	@RequestMapping(value = "delete")
	public String delete(DnaPreparationReagents dnaPreparationReagents, RedirectAttributes redirectAttributes) {
		dnaPreparationReagentsService.delete(dnaPreparationReagents);
		addMessage(redirectAttributes, "删除试剂配制记录表成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaPreparationReagents/?repage";
	}

}