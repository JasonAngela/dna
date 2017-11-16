/**
   * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust_flow_sheet.web.entrust;

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
import com.thinkgem.jeesite.modules.entrust_flow_sheet.entity.entrust.EntrustFlowSheet;
import com.thinkgem.jeesite.modules.entrust_flow_sheet.service.entrust.EntrustFlowSheetService;

/**
 * 流转单Controller
 * @author fu
 * @version 2017-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/entrust_flow_sheet/entrust/entrustFlowSheet")
public class EntrustFlowSheetController extends BaseController {

	@Autowired
	private EntrustFlowSheetService entrustFlowSheetService;
	
	@ModelAttribute
	public EntrustFlowSheet get(@RequestParam(required=false) String id) {
		EntrustFlowSheet entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = entrustFlowSheetService.get(id);
		}
		if (entity == null){
			entity = new EntrustFlowSheet();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(EntrustFlowSheet entrustFlowSheet, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EntrustFlowSheet> page = entrustFlowSheetService.findPage(new Page<EntrustFlowSheet>(request, response), entrustFlowSheet); 
		model.addAttribute("page", page);
		return "modules/entrust_flow_sheet/entrust/entrustFlowSheetList";
	}
	@RequiresPermissions("entrust_flow_sheet:entrust:entrustFlowSheet:view")
	@RequestMapping(value = "form")
	public String form(EntrustFlowSheet entrustFlowSheet, Model model) {
		model.addAttribute("entrustFlowSheet", entrustFlowSheet);
		return "modules/entrust_flow_sheet/entrust/entrustFlowSheetForm";
	}

	@RequestMapping(value = "save")
	public String save(EntrustFlowSheet entrustFlowSheet, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, entrustFlowSheet)){
			return form(entrustFlowSheet, model);
		}
		entrustFlowSheetService.save(entrustFlowSheet);
		addMessage(redirectAttributes, "保存流转单成功");
		return "redirect:"+Global.getAdminPath()+"/entrust_flow_sheet/entrust/entrustFlowSheet/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(EntrustFlowSheet entrustFlowSheet, RedirectAttributes redirectAttributes) {
		entrustFlowSheetService.delete(entrustFlowSheet);
		addMessage(redirectAttributes, "删除流转单成功");
		return "redirect:"+Global.getAdminPath()+"/entrust_flow_sheet/entrust/entrustFlowSheet/?repage";
	}

}