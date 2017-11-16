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
import com.thinkgem.jeesite.modules.dna.entity.DnaElectrophoresisParting;
import com.thinkgem.jeesite.modules.dna.service.DnaElectrophoresisPartingService;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipment;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipmentUsageRecord;
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;
import com.thinkgem.jeesite.modules.synth.service.SynthEquipmentUsageRecordService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 电泳室Controller
 * @author fuyun
 * @version 2017-08-25
 */
@Controller
@RequestMapping(value = "${adminPath}/dna/dnaElectrophoresisParting")
public class DnaElectrophoresisPartingController extends BaseController {

	@Autowired
	private DnaElectrophoresisPartingService dnaElectrophoresisPartingService;
	@Autowired
	private SynthEquipmentUsageRecordService synthEquipmentUsageRecordService;
	
	
	@ModelAttribute
	public DnaElectrophoresisParting get(@RequestParam(required=false) String id) {
		DnaElectrophoresisParting entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dnaElectrophoresisPartingService.get(id);
		}
		if (entity == null){
			entity = new DnaElectrophoresisParting();
		}
		return entity;
	}
	
	@RequiresPermissions("dna:dnaElectrophoresisParting:view")
	@RequestMapping(value = {"list", ""})
	public String list(DnaElectrophoresisParting dnaElectrophoresisParting, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DnaElectrophoresisParting> page = dnaElectrophoresisPartingService.findPage(new Page<DnaElectrophoresisParting>(request, response), dnaElectrophoresisParting); 
		model.addAttribute("page", page);
		return "modules/dna/dnaElectrophoresisPartingList";
	}

	@RequiresPermissions("dna:dnaElectrophoresisParting:view")
	@RequestMapping(value = "form")
	public String form(DnaElectrophoresisParting dnaElectrophoresisParting, Model model) {
		SynthLab lab=dnaElectrophoresisParting.getLab();
		model.addAttribute("lab", lab);
		model.addAttribute("dnaElectrophoresisParting", dnaElectrophoresisParting);
		return "modules/dna/dnaElectrophoresisPartingForm";
	}

	@RequiresPermissions("dna:dnaElectrophoresisParting:edit")
	@RequestMapping(value = "save")
	public String save(DnaElectrophoresisParting dnaElectrophoresisParting, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dnaElectrophoresisParting)){
			return form(dnaElectrophoresisParting, model);
		}
		User user=UserUtils.getUser();
		for (SynthEquipment iterable_element : dnaElectrophoresisParting.getSynthEquipments()){
			SynthEquipmentUsageRecord synthEquipmentUsageRecord=new SynthEquipmentUsageRecord();
			SynthEquipment equipment=new SynthEquipment();
			equipment.setId(iterable_element.getId());
			synthEquipmentUsageRecord.setEquipment(equipment);
			synthEquipmentUsageRecord.setOperator(user);
			synthEquipmentUsageRecordService.save(synthEquipmentUsageRecord);
		}
		dnaElectrophoresisParting.setOperator(user);
		dnaElectrophoresisPartingService.save(dnaElectrophoresisParting);
		addMessage(redirectAttributes, "保存电泳室成功");
		return "redirect:" + adminPath + "/dna/dnaExperiment/form";
	}
	
	@RequiresPermissions("dna:dnaElectrophoresisParting:edit")
	@RequestMapping(value = "delete")
	public String delete(DnaElectrophoresisParting dnaElectrophoresisParting, RedirectAttributes redirectAttributes) {
		dnaElectrophoresisPartingService.delete(dnaElectrophoresisParting);
		addMessage(redirectAttributes, "删除电泳室成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaElectrophoresisParting/?repage";
	}

}