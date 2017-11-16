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
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;
import com.thinkgem.jeesite.modules.synth.entity.SynthLabUsageRecord;
import com.thinkgem.jeesite.modules.synth.service.SynthLabService;
import com.thinkgem.jeesite.modules.synth.service.SynthLabUsageRecordService;

/**
 * 实验室使用记录Controller
 * @author zhuguli
 * @version 2017-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/synth/synthLabUsageRecord")
public class SynthLabUsageRecordController extends BaseController {

	@Autowired
	private SynthLabUsageRecordService synthLabUsageRecordService;
	@Autowired
	private SynthLabService synthLabService;
	@ModelAttribute
	public SynthLabUsageRecord get(@RequestParam(required=false) String id) {
		SynthLabUsageRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = synthLabUsageRecordService.get(id);
		}
		if (entity == null){
			entity = new SynthLabUsageRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("synth:synthLabUsageRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(SynthLabUsageRecord synthLabUsageRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SynthLabUsageRecord> page = synthLabUsageRecordService.findPage(new Page<SynthLabUsageRecord>(request, response), synthLabUsageRecord); 
		model.addAttribute("page", page);
		return "modules/synth/synthLabUsageRecordList";
	}

	@RequiresPermissions("synth:synthLabUsageRecord:view")
	@RequestMapping(value = "form")
	public String form(SynthLabUsageRecord synthLabUsageRecord, Model model) {
		List<SynthLab> labList = synthLabService.findList(new SynthLab());
		model.addAttribute("labList",labList);
		model.addAttribute("synthLabUsageRecord", synthLabUsageRecord);
		return "modules/synth/synthLabUsageRecordForm";
	}

	@RequiresPermissions("synth:synthLabUsageRecord:edit")
	@RequestMapping(value = "save")
	public String save(SynthLabUsageRecord synthLabUsageRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, synthLabUsageRecord)){
			return form(synthLabUsageRecord, model);
		}
		synthLabUsageRecordService.save(synthLabUsageRecord);
		addMessage(redirectAttributes, "保存实验室使用记录成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthLabUsageRecord/?repage";
	}
	
	@RequiresPermissions("synth:synthLabUsageRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(SynthLabUsageRecord synthLabUsageRecord, RedirectAttributes redirectAttributes) {
		synthLabUsageRecordService.delete(synthLabUsageRecord);
		addMessage(redirectAttributes, "删除实验室使用记录成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthLabUsageRecord/?repage";
	}

}