/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

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
import com.thinkgem.jeesite.modules.sys.entity.SysCodeRule;
import com.thinkgem.jeesite.modules.sys.entity.SysSequence;
import com.thinkgem.jeesite.modules.sys.service.SysCodeRuleService;
import com.thinkgem.jeesite.modules.sys.service.SysSequenceService;

/**
 * 编码规则Controller
 * @author zhuguli
 * @version 2017-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysCodeRule")
public class SysCodeRuleController extends BaseController {

	@Autowired
	private SysCodeRuleService sysCodeRuleService;
	@Autowired
	private SysSequenceService sysSequenceService;
	
	@ModelAttribute
	public SysCodeRule get(@RequestParam(required=false) String id) {
		SysCodeRule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysCodeRuleService.get(id);
		}
		if (entity == null){
			entity = new SysCodeRule();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:sysCodeRule:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysCodeRule sysCodeRule, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysCodeRule> page = sysCodeRuleService.findPage(new Page<SysCodeRule>(request, response), sysCodeRule); 
		model.addAttribute("page", page);
		return "modules/sys/sysCodeRuleList";
	}

	@RequiresPermissions("sys:sysCodeRule:view")
	@RequestMapping(value = "form")
	public String form(SysCodeRule sysCodeRule, Model model) {
		model.addAttribute("sysCodeRule", sysCodeRule);
		List<SysSequence> sequenceList = sysSequenceService.findList(new SysSequence());
		model.addAttribute("sequenceList", sequenceList);
		return "modules/sys/sysCodeRuleForm";
	}

	@RequiresPermissions("sys:sysCodeRule:edit")
	@RequestMapping(value = "save")
	public String save(SysCodeRule sysCodeRule, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysCodeRule)){
			return form(sysCodeRule, model);
		}
		sysCodeRuleService.save(sysCodeRule);
		addMessage(redirectAttributes, "保存编码规则成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysCodeRule/?repage";
	}
	
	@RequiresPermissions("sys:sysCodeRule:edit")
	@RequestMapping(value = "delete")
	public String delete(SysCodeRule sysCodeRule, RedirectAttributes redirectAttributes) {
		sysCodeRuleService.delete(sysCodeRule);
		addMessage(redirectAttributes, "删除编码规则成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysCodeRule/?repage";
	}

}