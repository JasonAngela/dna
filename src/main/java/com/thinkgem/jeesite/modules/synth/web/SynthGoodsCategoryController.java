/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.web;

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
import com.thinkgem.jeesite.modules.synth.entity.SynthGoodsCategory;
import com.thinkgem.jeesite.modules.synth.service.SynthGoodsCategoryService;

/**
 * 药品分类Controller
 * @author zhuguli
 * @version 2017-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/synth/synthGoodsCategory")
public class SynthGoodsCategoryController extends BaseController {

	@Autowired
	private SynthGoodsCategoryService synthGoodsCategoryService;
	
	@ModelAttribute
	public SynthGoodsCategory get(@RequestParam(required=false) String id) {
		SynthGoodsCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = synthGoodsCategoryService.get(id);
		}
		if (entity == null){
			entity = new SynthGoodsCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("synth:synthGoodsCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(SynthGoodsCategory synthGoodsCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SynthGoodsCategory> page = synthGoodsCategoryService.findPage(new Page<SynthGoodsCategory>(request, response), synthGoodsCategory); 
		model.addAttribute("page", page);
		return "modules/synth/synthGoodsCategoryList";
	}

	@RequiresPermissions("synth:synthGoodsCategory:view")
	@RequestMapping(value = "form")
	public String form(SynthGoodsCategory synthGoodsCategory, Model model) {
		model.addAttribute("synthGoodsCategory", synthGoodsCategory);
		return "modules/synth/synthGoodsCategoryForm";
	}

	@RequiresPermissions("synth:synthGoodsCategory:edit")
	@RequestMapping(value = "save")
	public String save(SynthGoodsCategory synthGoodsCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, synthGoodsCategory)){
			return form(synthGoodsCategory, model);
		}
		synthGoodsCategoryService.save(synthGoodsCategory);
		addMessage(redirectAttributes, "保存药品分类成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthGoodsCategory/?repage";
	}
	
	@RequiresPermissions("synth:synthGoodsCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(SynthGoodsCategory synthGoodsCategory, RedirectAttributes redirectAttributes) {
		synthGoodsCategoryService.delete(synthGoodsCategory);
		addMessage(redirectAttributes, "删除药品分类成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthGoodsCategory/?repage";
	}

}