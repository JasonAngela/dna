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
import com.thinkgem.jeesite.modules.synth.dao.SynthEquipmentDao;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipment;
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;
import com.thinkgem.jeesite.modules.synth.service.SynthLabService;

/**
 * 实验室Controller
 * @author zhuguli
 * @version 2017-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/synth/synthLab")
public class SynthLabController extends BaseController {

	@Autowired
	private SynthLabService synthLabService;
	@Autowired
	private SynthEquipmentDao synthEquipmentDao;
	
	@ModelAttribute
	public SynthLab get(@RequestParam(required=false) String id) {
		SynthLab entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = synthLabService.get(id);
		}
		if (entity == null){
			entity = new SynthLab();
		}
		return entity;
	}
	
	@RequiresPermissions("synth:synthLab:view")
	@RequestMapping(value = {"list", ""})
	public String list(SynthLab synthLab, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SynthLab> page = synthLabService.findPage(new Page<SynthLab>(request, response), synthLab); 
		
		model.addAttribute("page", page);
		return "modules/synth/synthLabList";
	}

	
	
	@RequiresPermissions("synth:synthLab:view")
	@RequestMapping(value = "form")
	public String form(SynthLab synthLab, Model model) {
		model.addAttribute("synthLab", synthLab);
		List<SynthEquipment>synthEquipments= synthEquipmentDao.findAll(synthLab.getId());
		model.addAttribute("synthEquipments", synthEquipments);
		return "modules/synth/synthLabForm";
	}

	@RequiresPermissions("synth:synthLab:edit")
	@RequestMapping(value = "save")
	public String save(SynthLab synthLab, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, synthLab)){
			return form(synthLab, model);
		}
		synthLabService.save(synthLab);
		addMessage(redirectAttributes, "保存实验室成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthLab/?repage";
	}
	
	@RequiresPermissions("synth:synthLab:edit")
	@RequestMapping(value = "delete")
	public String delete(SynthLab synthLab, RedirectAttributes redirectAttributes) {
		synthLabService.delete(synthLab);
		addMessage(redirectAttributes, "删除实验室成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthLab/?repage";
	}

}