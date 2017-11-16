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
import com.thinkgem.jeesite.modules.synth.entity.SynthLabDisinfectRecord;
import com.thinkgem.jeesite.modules.synth.service.SynthLabDisinfectRecordService;
import com.thinkgem.jeesite.modules.synth.service.SynthLabService;

/**
 * 实验室消毒记录Controller
 * @author zhuguli
 * @version 2017-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/synth/synthLabDisinfectRecord")
public class SynthLabDisinfectRecordController extends BaseController {

	@Autowired
	private SynthLabDisinfectRecordService synthLabDisinfectRecordService;
	@Autowired
	private SynthLabService synthLabService;
	@ModelAttribute
	public SynthLabDisinfectRecord get(@RequestParam(required=false) String id) {
		SynthLabDisinfectRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = synthLabDisinfectRecordService.get(id);
		}
		if (entity == null){
			entity = new SynthLabDisinfectRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("synth:synthLabDisinfectRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(SynthLabDisinfectRecord synthLabDisinfectRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SynthLabDisinfectRecord> page = synthLabDisinfectRecordService.findPage(new Page<SynthLabDisinfectRecord>(request, response), synthLabDisinfectRecord); 
		model.addAttribute("page", page);
		return "modules/synth/synthLabDisinfectRecordList";
	}

	@RequiresPermissions("synth:synthLabDisinfectRecord:view")
	@RequestMapping(value = "form")
	public String form(SynthLabDisinfectRecord synthLabDisinfectRecord, Model model) {
		List<SynthLab> labList = synthLabService.findList(new SynthLab());
		model.addAttribute("labList",labList);
		model.addAttribute("synthLabDisinfectRecord", synthLabDisinfectRecord);
		return "modules/synth/synthLabDisinfectRecordForm";
	}

	@RequiresPermissions("synth:synthLabDisinfectRecord:edit")
	@RequestMapping(value = "save")
	public String save(SynthLabDisinfectRecord synthLabDisinfectRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, synthLabDisinfectRecord)){
			return form(synthLabDisinfectRecord, model);
		}
		synthLabDisinfectRecordService.save(synthLabDisinfectRecord);
		addMessage(redirectAttributes, "保存实验室消毒记录成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthLabDisinfectRecord/?repage";
	}
	
	@RequiresPermissions("synth:synthLabDisinfectRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(SynthLabDisinfectRecord synthLabDisinfectRecord, RedirectAttributes redirectAttributes) {
		synthLabDisinfectRecordService.delete(synthLabDisinfectRecord);
		addMessage(redirectAttributes, "删除实验室消毒记录成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthLabDisinfectRecord/?repage";
	}

}