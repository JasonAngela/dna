/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustAbstracts;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;
import com.thinkgem.jeesite.modules.entrust.entity.ReadCard;
import com.thinkgem.jeesite.modules.entrust.service.EntrustRegisterService;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegister;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegisterItem;
import com.thinkgem.jeesite.modules.material.service.SpecimenMaterialRegisterItemService;
import com.thinkgem.jeesite.modules.material.service.SpecimenMaterialRegisterService;
import com.thinkgem.jeesite.modules.sys.service.SysCodeRuleService;
import com.thinkgem.jeesite.modules.xueka.dao.SpecimenXuekaDao;
import com.thinkgem.jeesite.modules.xueka.entity.SpecimenXueka;
import com.thinkgem.jeesite.modules.xueka.entity.SpecimenXuekaLsit;

/**
 * 物证登记Controller
 * @author zhuguli
 * @version 2017-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/material/specimenMaterialRegister")
public class SpecimenMaterialRegisterController extends BaseController {

	@Autowired
	private SpecimenMaterialRegisterService specimenMaterialRegisterService;
	@Autowired
	private EntrustRegisterService entrustRegisterService;
	@Autowired
	private SpecimenMaterialRegisterItemService specimenMaterialRegisterItemService;
	@Autowired
	private SpecimenXuekaDao specimenXuekaDao;
	@Autowired
	private SysCodeRuleService sysCodeRuleService;
	@ModelAttribute
	public SpecimenMaterialRegister get(@RequestParam(required=false) String id) {
		SpecimenMaterialRegister entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = specimenMaterialRegisterService.get(id);
		}
		if (entity == null){
			entity = new SpecimenMaterialRegister();
		}
		return entity;
	}

	@RequiresPermissions("material:specimenMaterialRegister:view")
	@RequestMapping(value = {"collectForm"})
	public String collectForm(SpecimenMaterialRegisterItem specimenMaterialRegisterItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpecimenMaterialRegisterItem> page = specimenMaterialRegisterItemService.findPage(new Page<SpecimenMaterialRegisterItem>(request, response), specimenMaterialRegisterItem); 
		model.addAttribute("page", page);
		return "modules/material/specimenMaterialRegisterCollectList";
	}



	@RequiresPermissions("material:specimenMaterialRegister:view")
	@RequestMapping(value = {"list", ""})
	public String list(SpecimenMaterialRegister specimenMaterialRegister, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpecimenMaterialRegister> page = specimenMaterialRegisterService.findPage(new Page<SpecimenMaterialRegister>(request, response), specimenMaterialRegister); 
		model.addAttribute("page", page);
		return "modules/material/specimenMaterialRegisterList";
	}
	@RequiresPermissions("material:specimenMaterialRegister:view")
	@RequestMapping(value = "form")
	public String form(SpecimenMaterialRegister specimenMaterialRegister, Model model) {
		String registerId;
		if(specimenMaterialRegister.getEntrustRegister()!=null){
			registerId = specimenMaterialRegister.getEntrustRegister().getId();
		}else{
			registerId = specimenMaterialRegister.getAct().getBusinessId();
		}
		EntrustRegister entrustRegister = entrustRegisterService.get(registerId);
		entrustRegister.setStatus("3");
		specimenMaterialRegister.setEntrustRegister(entrustRegister);
		String code = sysCodeRuleService.generateCode("specimen_code");
		List<EntrustAbstracts>entrustAbs =  entrustRegister.getEntrustAbstractsList();
		markCode(entrustAbs,code);
		
		model.addAttribute("specimenMaterialRegister", specimenMaterialRegister);
		model.addAttribute("entrustAbs", entrustAbs);
		return "modules/material/specimenMaterialRegisterForm";
	}
	private void markCode(List<EntrustAbstracts> entrustAbs,String code) {
		int f=0;
		int m = 0;
		int c = 0;
		for(EntrustAbstracts abstracts:entrustAbs){
			if(abstracts.getAppellation().equals("1")){//父亲
				abstracts.setSpecimenCode(code+"-F"+(f>0?f:""));
				f++;
			}else if(abstracts.getAppellation().equals("2")){//母亲
				abstracts.setSpecimenCode(code+"-M"+(m>0?f:""));
				m++;
			}else if(abstracts.getAppellation().equals("3")){//小孩
				abstracts.setSpecimenCode(code+"-C"+(c>0?f:""));
				c++;
			}else if(abstracts.getAppellation().equals("4")){//其他
				if(abstracts.getGender().equals("1")){
					abstracts.setSpecimenCode(code+"-F"+(f>0?f:""));
					f++;
				}else{
					abstracts.setSpecimenCode(code+"-M"+(m>0?f:""));
					m++;
				}
			}
			 
		}
	}

	@RequiresPermissions("material:specimenMaterialRegister:edit")
	@RequestMapping(value = "save")
	public String save(SpecimenMaterialRegister specimenMaterialRegister,SpecimenXuekaLsit specimenXuekaLsit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, specimenMaterialRegister)){
			return form(specimenMaterialRegister, model);
		}
		boolean b=false;
		
		for (int i = 0; i < specimenXuekaLsit.getSpecimenXuekas().size(); i++) {
			String cf=specimenXuekaLsit.getSpecimenXuekas().get(i).getXuekaId();
			for (int j = 0; j <  specimenXuekaLsit.getSpecimenXuekas().size(); j++) {
				String cf1= specimenXuekaLsit.getSpecimenXuekas().get(j).getXuekaId();
				if(!cf1.equals(cf)){
					b=true;
				}
			}
		}

		specimenMaterialRegisterService.save(specimenMaterialRegister,specimenXuekaLsit);
		addMessage(redirectAttributes, "保存物证登记成功");
		return "redirect:" + adminPath + "/act/task/todo/";

	}
	@RequiresPermissions("material:specimenMaterialRegister:edit")
	@RequestMapping(value = "delete")
	public String delete(SpecimenMaterialRegister specimenMaterialRegister, RedirectAttributes redirectAttributes) {
		specimenMaterialRegisterService.delete(specimenMaterialRegister);
		addMessage(redirectAttributes, "删除物证登记成功");
		return "redirect:"+Global.getAdminPath()+"/material/specimenMaterialRegister/?repage";
	}
	@RequestMapping(value="readcard")
	@ResponseBody
	public String  readcard(){
		String number=null;
		ReadCard t = new ReadCard();
		for (int i = 0; i < 10000; i++) {
			number=t.ss();
			if(number!=null&&!number.equals("")){
				break;
			}
		}
		SpecimenXueka entity=new SpecimenXueka();	
		entity.setXuekaId(number);
		List<SpecimenXueka>list=specimenXuekaDao.findList(entity);
		if(list.size()>0){
			return "已存在";
		}else{
			return number;
		}
	}
}