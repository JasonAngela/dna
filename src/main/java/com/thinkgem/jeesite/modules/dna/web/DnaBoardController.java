/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.web;

import java.util.ArrayList;
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
import com.thinkgem.jeesite.modules.dna.entity.DnaBoard;
import com.thinkgem.jeesite.modules.dna.entity.DnaBoardJgg;
import com.thinkgem.jeesite.modules.dna.service.DnaBoardService;
import com.thinkgem.jeesite.modules.synth.entity.SynthGoodsStock;
import com.thinkgem.jeesite.modules.synth.service.SynthGoodsStockService;

/**
 * 电泳分型板Controller
 * @author zhuguli
 * @version 2017-06-08
 */
@Controller
@RequestMapping(value = "${adminPath}/dna/dnaBoard")
public class DnaBoardController extends BaseController {

	@Autowired
	private DnaBoardService dnaBoardService;
	@Autowired
	private SynthGoodsStockService synthGoodsStockService;
	@ModelAttribute
	public DnaBoard get(@RequestParam(required=false) String id) {
		DnaBoard entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dnaBoardService.get(id);
		}
		if (entity == null){
			entity = new DnaBoard();
		}
		return entity;
	}
	

	@RequiresPermissions("dna:dnaBoard:view")
	@RequestMapping(value = {"list", ""})
	public String list(DnaBoard dnaBoard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DnaBoard> page = dnaBoardService.findPage(new Page<DnaBoard>(request, response), dnaBoard); 
		model.addAttribute("page", page);
		return "modules/dna/dnaBoardList";
	}

	@RequiresPermissions("dna:dnaBoard:view")
	@RequestMapping(value = "form")
	public String form(DnaBoard dnaBoard, Model model) {
		DnaBoard board = dnaBoardService.get(dnaBoard.getId());
		model.addAttribute("board", board);
		return "modules/dna/dnaBoardForm";
	}

	@RequiresPermissions("dna:dnaBoard:edit")
	@RequestMapping(value = "save")
	public String save(DnaBoard dnaBoard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dnaBoard)){
			return form(dnaBoard, model);
		}
		dnaBoardService.save(dnaBoard);
		addMessage(redirectAttributes, "保存电泳分型板成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaBoard/?repage";
	}
	
	@RequiresPermissions("dna:dnaBoard:edit")
	@RequestMapping(value = "delete")
	public String delete(DnaBoard dnaBoard, RedirectAttributes redirectAttributes) {
		dnaBoardService.delete(dnaBoard);
		addMessage(redirectAttributes, "删除电泳分型板成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaBoard/?repage";
	}

}