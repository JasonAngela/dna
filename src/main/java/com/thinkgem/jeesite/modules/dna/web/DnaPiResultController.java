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
import com.thinkgem.jeesite.modules.dna.entity.DnaPiResult;
import com.thinkgem.jeesite.modules.dna.service.DnaPiResultService;

/**
 * 亲权指数Controller
 * @author zhuguli
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/dna/dnaPiResult")
public class DnaPiResultController extends BaseController {

	@Autowired
	private DnaPiResultService dnaPiResultService;
	
	@ModelAttribute
	public DnaPiResult get(@RequestParam(required=false) String id) {
		DnaPiResult entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dnaPiResultService.get(id);
		}
		if (entity == null){
			entity = new DnaPiResult();
		}
		return entity;
	}
	
	@RequiresPermissions("dna:dnaPiResult:view")
	@RequestMapping(value = {"list", ""})
	public String list(DnaPiResult dnaPiResult, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DnaPiResult> page = dnaPiResultService.findPage(new Page<DnaPiResult>(request, response), dnaPiResult); 
		model.addAttribute("page", page);
		return "modules/dna/dnaPiResultList";
	}

	@RequiresPermissions("dna:dnaPiResult:view")
	@RequestMapping(value = "form")
	public String form(DnaPiResult dnaPiResult, Model model) {
		model.addAttribute("dnaPiResult", dnaPiResult);
		return "modules/dna/dnaPiResultForm";
	}

	@RequiresPermissions("dna:dnaPiResult:edit")
	@RequestMapping(value = "save")
	public String save(DnaPiResult dnaPiResult, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dnaPiResult)){
			return form(dnaPiResult, model);
		}
		dnaPiResultService.save(dnaPiResult);
		addMessage(redirectAttributes, "保存二联体成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaPiResult/?repage";
	}
	
	@RequiresPermissions("dna:dnaPiResult:edit")
	@RequestMapping(value = "delete")
	public String delete(DnaPiResult dnaPiResult, RedirectAttributes redirectAttributes) {
		dnaPiResultService.delete(dnaPiResult);
		addMessage(redirectAttributes, "删除二联体成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaPiResult/?repage";
	}

}