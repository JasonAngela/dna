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
import com.thinkgem.jeesite.modules.synth.entity.SynthGoods;
import com.thinkgem.jeesite.modules.synth.entity.SynthGoodsCategory;
import com.thinkgem.jeesite.modules.synth.service.SynthGoodsCategoryService;
import com.thinkgem.jeesite.modules.synth.service.SynthGoodsService;

/**
 * 药品管理Controller
 * @author zhuguli
 * @version 2017-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/synth/synthGoods")
public class SynthGoodsController extends BaseController {

	@Autowired
	private SynthGoodsService synthGoodsService;
	@Autowired
	private SynthGoodsCategoryService synthGoodsCategoryService;
	@ModelAttribute
	public SynthGoods get(@RequestParam(required=false) String id) {
		SynthGoods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = synthGoodsService.get(id);
		}
		if (entity == null){
			entity = new SynthGoods();
		}
		return entity;
	}
	
	@RequiresPermissions("synth:synthGoods:view")
	@RequestMapping(value = {"list", ""})
	public String list(SynthGoods synthGoods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SynthGoods> page = synthGoodsService.findPage(new Page<SynthGoods>(request, response), synthGoods); 
		model.addAttribute("page", page);
		return "modules/synth/synthGoodsList";
	}

	@RequiresPermissions("synth:synthGoods:view")
	@RequestMapping(value = "form")
	public String form(SynthGoods synthGoods, Model model) {
		model.addAttribute("synthGoods", synthGoods);
		List<SynthGoodsCategory> categoryList = synthGoodsCategoryService.findList(new SynthGoodsCategory());
		model.addAttribute("categoryList", categoryList);
		return "modules/synth/synthGoodsForm";
	}

	@RequiresPermissions("synth:synthGoods:edit")
	@RequestMapping(value = "save")
	public String save(SynthGoods synthGoods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, synthGoods)){
			return form(synthGoods, model);
		}
		synthGoodsService.save(synthGoods);
		addMessage(redirectAttributes, "保存药品管理成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthGoods/?repage";
	}
	
	@RequiresPermissions("synth:synthGoods:edit")
	@RequestMapping(value = "delete")
	public String delete(SynthGoods synthGoods, RedirectAttributes redirectAttributes) {
		synthGoodsService.delete(synthGoods);
		addMessage(redirectAttributes, "删除药品管理成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthGoods/?repage";
	}

}