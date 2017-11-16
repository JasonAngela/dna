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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.synth.entity.SynthGoods;
import com.thinkgem.jeesite.modules.synth.entity.SynthGoodsStock;
import com.thinkgem.jeesite.modules.synth.service.SynthGoodsService;
import com.thinkgem.jeesite.modules.synth.service.SynthGoodsStockService;

/**
 * 药品库存Controller
 * @author zhuguli
 * @version 2017-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/synth/synthGoodsStock")
public class SynthGoodsStockController extends BaseController {
	@Autowired
	private SynthGoodsService synthGoodsService;
	@Autowired
	private SynthGoodsStockService synthGoodsStockService;
	
	@ModelAttribute
	public SynthGoodsStock get(@RequestParam(required=false) String id) {
		SynthGoodsStock entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = synthGoodsStockService.get(id);
		}
		if (entity == null){
			entity = new SynthGoodsStock();
		}
		return entity;
	}
	
	@RequiresPermissions("synth:synthGoodsStock:view")
	@RequestMapping(value = {"list", ""})
	public String list(SynthGoodsStock synthGoodsStock, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SynthGoodsStock> page = synthGoodsStockService.findPage(new Page<SynthGoodsStock>(request, response), synthGoodsStock); 
		model.addAttribute("page", page);
		return "modules/synth/synthGoodsStockList";
	}

	@RequiresPermissions("synth:synthGoodsStock:view")
	@RequestMapping(value = "form")
	public String form(SynthGoodsStock synthGoodsStock, Model model) {
		model.addAttribute("synthGoodsStock", synthGoodsStock);
		List<SynthGoods> goodsList = synthGoodsService.findList(new SynthGoods());
		model.addAttribute("goodsList", goodsList);
		return "modules/synth/synthGoodsStockForm";
	}

	@RequiresPermissions("synth:synthGoodsStock:edit")
	@RequestMapping(value = "save")
	public String save(SynthGoodsStock synthGoodsStock, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, synthGoodsStock)){
			return form(synthGoodsStock, model);
		}
		synthGoodsStockService.save(synthGoodsStock);
		addMessage(redirectAttributes, "保存药品库存成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthGoodsStock/?repage";
	}
	
	@RequiresPermissions("synth:synthGoodsStock:edit")
	@RequestMapping(value = "delete")
	public String delete(SynthGoodsStock synthGoodsStock, RedirectAttributes redirectAttributes) {
		synthGoodsStockService.delete(synthGoodsStock);
		addMessage(redirectAttributes, "删除药品库存成功");
		return "redirect:"+Global.getAdminPath()+"/synth/synthGoodsStock/?repage";
	}

}