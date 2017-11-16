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
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;
import com.thinkgem.jeesite.modules.synth.service.SynthEquipmentService;
import com.thinkgem.jeesite.modules.synth.service.SynthLabService;

/**
 * 设备管理Controller
 * @author zhuguli
 * @version 2017-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/synth/synthEquipment")
public class SynthEquipmentController extends BaseController {

	@Autowired
	private SynthEquipmentService synthEquipmentService;
	@Autowired
	private SynthLabService synthLabService;
	@ModelAttribute
	public SynthEquipment get(@RequestParam(required=false) String id) {
		SynthEquipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = synthEquipmentService.get(id);
		}
		if (entity == null){
			entity = new SynthEquipment();
		}
		return entity;
	}
	
	@RequiresPermissions("synth:synthEquipment:view")
	@RequestMapping(value = {"list", ""})
	public String list(SynthEquipment synthEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SynthEquipment> page = synthEquipmentService.findPage(new Page<SynthEquipment>(request, response), synthEquipment); 
		model.addAttribute("page", page);
		return "modules/synth/synthEquipmentList";
	}

	@RequiresPermissions("synth:synthEquipment:view")
	@RequestMapping(value = "form")
	public String form(SynthEquipment synthEquipment, Model model) {
		List<SynthLab> labList = synthLabService.findList(new SynthLab());
		model.addAttribute("labList", labList);
		model.addAttribute("synthEquipment", synthEquipment);
		
		
		return "modules/synth/synthEquipmentForm";
	}

	@RequiresPermissions("synth:synthEquipment:edit")
	@RequestMapping(value = "save")
	public String save(SynthEquipment synthEquipment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, synthEquipment)){
			return form(synthEquipment, model);
		}
		synthEquipmentService.save(synthEquipment);
		addMessage(redirectAttributes, "保存设备管理成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthEquipment/?repage";
	}
	
	@RequiresPermissions("synth:synthEquipment:edit")
	@RequestMapping(value = "delete")
	public String delete(SynthEquipment synthEquipment, RedirectAttributes redirectAttributes) {
		synthEquipmentService.delete(synthEquipment);
		addMessage(redirectAttributes, "删除设备管理成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthEquipment/?repage";
	}

}