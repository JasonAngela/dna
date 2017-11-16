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
import com.thinkgem.jeesite.modules.synth.entity.SynthLabInspectRecord;
import com.thinkgem.jeesite.modules.synth.service.SynthLabInspectRecordService;
import com.thinkgem.jeesite.modules.synth.service.SynthLabService;

/**
 * 实验室检查记录Controller
 * @author zhuguli
 * @version 2017-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/synth/synthLabInspectRecord")
public class SynthLabInspectRecordController extends BaseController {

	@Autowired
	private SynthLabInspectRecordService synthLabInspectRecordService;
	@Autowired
	private SynthLabService synthLabService;
	@ModelAttribute
	public SynthLabInspectRecord get(@RequestParam(required=false) String id) {
		SynthLabInspectRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = synthLabInspectRecordService.get(id);
		}
		if (entity == null){
			entity = new SynthLabInspectRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("synth:synthLabInspectRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(SynthLabInspectRecord synthLabInspectRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SynthLabInspectRecord> page = synthLabInspectRecordService.findPage(new Page<SynthLabInspectRecord>(request, response), synthLabInspectRecord); 
		model.addAttribute("page", page);
		return "modules/synth/synthLabInspectRecordList";
	}

	@RequiresPermissions("synth:synthLabInspectRecord:view")
	@RequestMapping(value = "form")
	public String form(SynthLabInspectRecord synthLabInspectRecord, Model model) {
		List<SynthLab> labList = synthLabService.findList(new SynthLab());
		model.addAttribute("labList",labList);
		model.addAttribute("synthLabInspectRecord", synthLabInspectRecord);
		
		return "modules/synth/synthLabInspectRecordForm";
	}

	@RequiresPermissions("synth:synthLabInspectRecord:edit")
	@RequestMapping(value = "save")
	public String save(SynthLabInspectRecord synthLabInspectRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, synthLabInspectRecord)){
			return form(synthLabInspectRecord, model);
		}
		synthLabInspectRecordService.save(synthLabInspectRecord);
		addMessage(redirectAttributes, "保存实验室检查记录成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthLabInspectRecord/?repage";
	}
	
	@RequiresPermissions("synth:synthLabInspectRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(SynthLabInspectRecord synthLabInspectRecord, RedirectAttributes redirectAttributes) {
		synthLabInspectRecordService.delete(synthLabInspectRecord);
		addMessage(redirectAttributes, "删除实验室检查记录成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthLabInspectRecord/?repage";
	}

}