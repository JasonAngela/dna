/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.web;

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
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipment;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipmentUsageRecord;
import com.thinkgem.jeesite.modules.synth.service.SynthEquipmentService;
import com.thinkgem.jeesite.modules.synth.service.SynthEquipmentUsageRecordService;

/**
 * 设备使用记录Controller
 * @author zhuguli
 * @version 2017-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/synth/synthEquipmentUsageRecord")
public class SynthEquipmentUsageRecordController extends BaseController {

	@Autowired
	private SynthEquipmentUsageRecordService synthEquipmentUsageRecordService;
	@Autowired
	private SynthEquipmentService synthEquipmentService;
	@ModelAttribute
	public SynthEquipmentUsageRecord get(@RequestParam(required=false) String id) {
		SynthEquipmentUsageRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = synthEquipmentUsageRecordService.get(id);
		}
		if (entity == null){
			entity = new SynthEquipmentUsageRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("synth:synthEquipmentUsageRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(SynthEquipmentUsageRecord synthEquipmentUsageRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SynthEquipmentUsageRecord> page = synthEquipmentUsageRecordService.findPage(new Page<SynthEquipmentUsageRecord>(request, response), synthEquipmentUsageRecord); 
		model.addAttribute("page", page);
		return "modules/synth/synthEquipmentUsageRecordList";
	}

	@RequiresPermissions("synth:synthEquipmentUsageRecord:view")
	@RequestMapping(value = "form")
	public String form(SynthEquipmentUsageRecord synthEquipmentUsageRecord, Model model) {
		List<SynthEquipment> equipmentList = synthEquipmentService.findList(new SynthEquipment());
		model.addAttribute("equipmentList", equipmentList);
		model.addAttribute("synthEquipmentUsageRecord", synthEquipmentUsageRecord);
		return "modules/synth/synthEquipmentUsageRecordForm";
	}

	@RequiresPermissions("synth:synthEquipmentUsageRecord:edit")
	@RequestMapping(value = "save")
	public String save(SynthEquipmentUsageRecord synthEquipmentUsageRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, synthEquipmentUsageRecord)){
			return form(synthEquipmentUsageRecord, model);
		}
		synthEquipmentUsageRecordService.save(synthEquipmentUsageRecord);
		addMessage(redirectAttributes, "保存设备使用记录成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthEquipmentUsageRecord/?repage";
	}
	
	@RequiresPermissions("synth:synthEquipmentUsageRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(SynthEquipmentUsageRecord synthEquipmentUsageRecord, RedirectAttributes redirectAttributes) {
		synthEquipmentUsageRecordService.delete(synthEquipmentUsageRecord);
		addMessage(redirectAttributes, "删除设备使用记录成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthEquipmentUsageRecord/?repage";
	}

}