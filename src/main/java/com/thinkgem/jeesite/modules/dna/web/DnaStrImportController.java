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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dna.entity.DnaStrImport;
import com.thinkgem.jeesite.modules.dna.service.DnaStrImportService;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;
import com.thinkgem.jeesite.modules.entrust.service.EntrustRegisterService;

/**
 * str导入记录Controller
 * @author zhuguli
 * @version 2017-05-11
 */
@Controller
@RequestMapping(value = "${adminPath}/dna/dnaStrImport")
public class DnaStrImportController extends BaseController {

	@Autowired
	private DnaStrImportService dnaStrImportService;
	@Autowired
	private EntrustRegisterService entrustRegisterService;
	@ModelAttribute
	public DnaStrImport get(@RequestParam(required=false) String id) {
		DnaStrImport entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dnaStrImportService.get(id);
		}
		if (entity == null){
			entity = new DnaStrImport();
		}
		return entity;
	}
	
	@RequiresPermissions("dna:dnaStrImport:view")
	@RequestMapping(value = {"list", ""})
	public String list(DnaStrImport dnaStrImport, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DnaStrImport> page = dnaStrImportService.findPage(new Page<DnaStrImport>(request, response), dnaStrImport); 
		model.addAttribute("page", page);
		return "modules/dna/dnaStrImportList";
	}

	@RequiresPermissions("dna:dnaStrImport:view")
	@RequestMapping(value = "form")
	public String form(DnaStrImport dnaStrImport, Model model) {
		String registerId = dnaStrImport.getAct().getBusinessId();
		EntrustRegister entrustRegister = entrustRegisterService.get(registerId);
		dnaStrImport.setRegister(entrustRegister);
		model.addAttribute("dnaStrImport", dnaStrImport);
		return "modules/dna/dnaStrImportForm";
	}

	@RequiresPermissions("dna:dnaStrImport:edit")
	@RequestMapping(value = "save")
	public String save(DnaStrImport dnaStrImport, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dnaStrImport)){
			return form(dnaStrImport, model);
		}
		dnaStrImportService.save(dnaStrImport);
		addMessage(redirectAttributes, "保存str导入记录成功");
		return "redirect:" + adminPath + "/act/task/todo/";
		//return "redirect:"+Global.getAdminPath()+"/dna/dnaStrImport/?repage";
	}
	
	@RequiresPermissions("dna:dnaStrImport:edit")
	@RequestMapping(value = "delete")
	public String delete(DnaStrImport dnaStrImport, RedirectAttributes redirectAttributes) {
		dnaStrImportService.delete(dnaStrImport);
		addMessage(redirectAttributes, "删除str导入记录成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaStrImport/?repage";
	}

}