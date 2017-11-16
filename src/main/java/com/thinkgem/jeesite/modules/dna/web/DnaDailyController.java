/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.web;

import java.text.SimpleDateFormat;
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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dna.entity.DnaDaily;
import com.thinkgem.jeesite.modules.dna.entity.DnaDailyItem;
import com.thinkgem.jeesite.modules.dna.service.DnaDailyService;
import com.thinkgem.jeesite.modules.synth.dao.SynthLabDao;
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * dna日常记录Controller
 * @author fuyun
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/dna/dnaDaily")
public class DnaDailyController extends BaseController {

	@Autowired
	private DnaDailyService dnaDailyService;
	@Autowired
	private SynthLabDao synthLabDao;
	@ModelAttribute
	public DnaDaily get(@RequestParam(required=false) String id) {
		DnaDaily entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dnaDailyService.get(id);
		}
		if (entity == null){
			entity = new DnaDaily();
		}
		return entity;
	}
	
	@RequiresPermissions("dna:dnaDaily:view")
	@RequestMapping(value = {"list", ""})
	public String list(DnaDaily dnaDaily, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DnaDaily> page = dnaDailyService.findPage(new Page<DnaDaily>(request, response), dnaDaily); 
		model.addAttribute("page", page);
		return "modules/dna/dnaDailyList";
	}

	@RequiresPermissions("dna:dnaDaily:view")
	@RequestMapping(value = "form")
	public String form(DnaDaily dnaDaily, Model model) {
		List<SynthLab>list=synthLabDao.findAll();
		List<DnaDailyItem> dnaDailyItemList=dnaDaily.getDnaDailyItemList();
		if(dnaDailyItemList.size()==0){
			for (int i = 0; i < list.size(); i++) {
				DnaDailyItem dnaDailyItem=new DnaDailyItem();
				dnaDailyItem.setLab(list.get(i));
				dnaDailyItemList.add(dnaDailyItem);
			}
		}
		model.addAttribute("dnaDaily", dnaDaily);
		return "modules/dna/dnaDailyForm";
	}

	@RequiresPermissions("dna:dnaDaily:edit")
	@RequestMapping(value = "save")
	public String save(DnaDaily dnaDaily, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dnaDaily)){
			return form(dnaDaily, model);
		}
		User user=UserUtils.getUser();
		dnaDaily.setOperator(user);

		dnaDailyService.save(dnaDaily);
		addMessage(redirectAttributes, "保存dna日常记录成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaDaily/?repage";
	}
	
	@RequiresPermissions("dna:dnaDaily:edit")
	@RequestMapping(value = "delete")
	public String delete(DnaDaily dnaDaily, RedirectAttributes redirectAttributes) {
		dnaDailyService.delete(dnaDaily);
		addMessage(redirectAttributes, "删除dna日常记录成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaDaily/?repage";
	}

}