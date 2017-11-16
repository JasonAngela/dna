/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.drew.lang.StringUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.dna.dao.DnaExperimentStrDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperimentStr;
import com.thinkgem.jeesite.modules.dna.entity.DnaPiResult;
import com.thinkgem.jeesite.modules.dna.service.DnaPiResultService;
import com.thinkgem.jeesite.modules.entrust.dao.MappingDao;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustAbstracts;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustExpertOpinion;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;
import com.thinkgem.jeesite.modules.entrust.entity.Licensed;
import com.thinkgem.jeesite.modules.entrust.entity.Mapping;
import com.thinkgem.jeesite.modules.entrust.service.EntrustAbstractsService;
import com.thinkgem.jeesite.modules.entrust.service.EntrustExpertOpinionService;
import com.thinkgem.jeesite.modules.entrust.service.EntrustRegisterService;
import com.thinkgem.jeesite.modules.entrust.service.LicensedService;
import com.thinkgem.jeesite.modules.entrust.service.MappingService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 鉴定意见Controller
 * @author zhuguli
 * @version 2017-05-11
 */
@Controller
@RequestMapping(value = "${adminPath}/entrust/entrustExpertOpinion")
public class EntrustExpertOpinionController extends BaseController {

	@Autowired
	private EntrustExpertOpinionService entrustExpertOpinionService;
	@Autowired
	private EntrustRegisterService entrustRegisterService;
	@Autowired
	private DnaPiResultService dnaPiResultService;
	@Autowired
	private LicensedService licensedService;
	@Autowired
	private DnaExperimentStrDao dnaExperimentStrDao;
	@Autowired
	private EntrustAbstractsService entrustAbstractsService;
	@Autowired
	private MappingService mappingService;
    @Autowired
    private MappingDao mappingDao;
	
	
	@ModelAttribute
	public EntrustExpertOpinion get(@RequestParam(required=false) String id) {
		EntrustExpertOpinion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = entrustExpertOpinionService.get(id);
		}
		if (entity == null){
			entity = new EntrustExpertOpinion();
		}
		return entity;
	}
	
	@RequiresPermissions("entrust:entrustExpertOpinion:view")
	@RequestMapping(value = {"list", ""})
	public String list(EntrustExpertOpinion entrustExpertOpinion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EntrustExpertOpinion> page = entrustExpertOpinionService.findPage(new Page<EntrustExpertOpinion>(request, response), entrustExpertOpinion); 
		model.addAttribute("page", page);
		return "modules/entrust/entrustExpertOpinionList";
	}
	
	
	@RequiresPermissions("entrust:entrustExpertOpinion:view")
	@RequestMapping(value = "form")
	public String form(EntrustExpertOpinion entrustExpertOpinion, Model model) {
		
		EntrustRegister register = entrustRegisterService.get(entrustExpertOpinion.getAct().getBusinessId());
		Act act  = entrustExpertOpinion.getAct();
		List<DnaPiResult> dnaResultList = dnaPiResultService.selectByRegisterId(register.getId());
		List<DnaExperimentStr>strList=dnaExperimentStrDao.getByRegisterId(register.getId());
		Map<String,Map<String,String>> strMap = genateMapFromList(strList);
		model.addAttribute("str", strMap);
		List<EntrustAbstracts> entrustAbstracts= entrustAbstractsService.findAllentrustAbstracts(register.getId());
		model.addAttribute("entrustAbstracts",entrustAbstracts);                                    
		
		model.addAttribute("dnaResultList",dnaResultList);
		for(DnaPiResult result:dnaResultList){
			EntrustAbstracts parentAbstracts = getAbstracts(register.getEntrustAbstractsList(),result.getParentCode());
			result.setParentAbstracts(parentAbstracts);
			EntrustAbstracts childAbstracts = getAbstracts(register.getEntrustAbstractsList(),result.getChildCode());
			result.setChildAbstracts(childAbstracts);
		}
		List<EntrustExpertOpinion> opinionList = entrustExpertOpinionService.getByRegisterId(register.getId());
		if(!opinionList.isEmpty()){
			entrustExpertOpinion = opinionList.get(0);
			entrustExpertOpinion.setAct(act);
		}else{                                          
			  String[] explain = generateDraftFromResult(dnaResultList);
			  entrustExpertOpinion.setDraftRemark(explain[0]);
			  entrustExpertOpinion.setDraft(explain[0]);
			  entrustExpertOpinion.setExplain("   "+explain[1]);
			  entrustExpertOpinion.setExplainRemark(explain[1]);
		}
		entrustExpertOpinion.setRegister(register);
		model.addAttribute("entrustExpertOpinion", entrustExpertOpinion);
		String taskKey = entrustExpertOpinion.getAct().getTaskDefKey();
		Mapping mapping= mappingDao.findEntrsut(register.getId());
		entrustExpertOpinion.setMapping(mapping);
		//"/jeesite/userfiles/1/images/entrust/mapping/2017/09/1111.jpg"
		List<String>pics=new ArrayList<String>();
		 if(mapping!=null){
			String []d=mapping.getPic().split("\\|");
			for (String pic : d) {
				 if(pic.equals("")){
				 
				 	}else{
					pics.add(pic);
				 }
			}
		 }
		model.addAttribute("pic",pics);
		if("reportInit".equals(taskKey)){
			 Licensed licensed=new Licensed();
			 licensed.setEntrustId(register.getId());
			 licensed.setUser(UserUtils.getUser());
			 licensedService.save(licensed);
			 model.addAttribute("user", UserUtils.getUser().getName());
			return "modules/entrust/entrustExpertOpinionInitForm";
		}else if("SecondPersonCheck".equals(taskKey)){
			 Licensed licensed=new Licensed();
			 licensed.setEntrustId(register.getId());
			 licensed.setUser(UserUtils.getUser());
			 licensedService.save(licensed);
			 model.addAttribute("user", UserUtils.getUser().getName());
			return "modules/entrust/entrustExpertOpinionSecondForm";
		}else if("managerCheck".equals(taskKey)){
			 model.addAttribute("user", UserUtils.getUser().getName());
			return "modules/entrust/entrustExpertOpinionManagerForm";
		}else{
			 model.addAttribute("user", UserUtils.getUser().getName());
			entrustExpertOpinion.setFinalVersion(StringEscapeUtils.unescapeHtml4(entrustExpertOpinion.getExplain().trim()));
			return "modules/entrust/entrustExpertOpinionEndForm";
		}
	}

	
//图谱页面	
	@RequiresPermissions("entrust:entrustExpertOpinion:view")
	@RequestMapping(value = "analyze")
	public String analyze(EntrustExpertOpinion entrustExpertOpinion, Model model) {
		// /jeesite/userfiles/1/images/entrust/mapping/2017/09/1111.jpg /jeesite/userfiles/1/images/entrust/mapping/2017/09/2222.jpg
		EntrustRegister register = entrustRegisterService.get(entrustExpertOpinion.getAct().getBusinessId());
		Act act  = entrustExpertOpinion.getAct();
		model.addAttribute("registerId", register.getId());
		List<DnaPiResult> dnaResultList = dnaPiResultService.selectByRegisterId(register.getId());
		List<DnaExperimentStr>strList=dnaExperimentStrDao.getByRegisterId(register.getId());
		Map<String,Map<String,String>> strMap = genateMapFromList(strList);
		model.addAttribute("str", strMap);
		List<EntrustAbstracts> entrustAbstracts= entrustAbstractsService.findAllentrustAbstracts(register.getId());
		model.addAttribute("entrustAbstracts",entrustAbstracts);                                    
		model.addAttribute("dnaResultList",dnaResultList);
		for(DnaPiResult result:dnaResultList){
			EntrustAbstracts parentAbstracts = getAbstracts(register.getEntrustAbstractsList(),result.getParentCode());
			result.setParentAbstracts(parentAbstracts);
			EntrustAbstracts childAbstracts = getAbstracts(register.getEntrustAbstractsList(),result.getChildCode());
			result.setChildAbstracts(childAbstracts);                                     
		}
		return "modules/entrust/entrustExpertOpinionAnalyze";
	}
	
	//上传图谱
	@RequiresPermissions("entrust:entrustExpertOpinion:edit")
	@RequestMapping(value = "saveAnalyze")
	public String saveAnalyze(EntrustExpertOpinion entrustExpertOpinion, Model model, RedirectAttributes redirectAttributes) {
		
		Mapping mapping=new Mapping();
		mapping.setPic(entrustExpertOpinion.getMapping().getPic());
		mapping.setEntrustId(entrustExpertOpinion.getRegister().getId());
		mapping.setId(entrustExpertOpinion.getMapping().getId());
		mappingService.save(mapping);
		entrustExpertOpinionService.saveAnalyze(entrustExpertOpinion);
		addMessage(redirectAttributes, "上传图谱成功");
		return "redirect:" + adminPath + "/act/task/todo/";
		//return "redirect:"+Global.getAdminPath()+"/entrust/entrustExpertOpinion/?repage";
	}
	
	
	private Map<String,Map<String,String>> genateMapFromList(List<DnaExperimentStr> strList) {
		Map<String,Map<String,String>> strMapList =  new TreeMap<String, Map<String,String>>();
		for(DnaExperimentStr str :strList){
			String geneLoci = str.getGeneLoci();
			String specimenCode = str.getSpecimenCode();
			String x = str.getX();
			String y = str.getY();
			if(strMapList.containsKey(geneLoci)){
				Map<String, String> map = strMapList.get(geneLoci);
				map.put(specimenCode, x+" "+(x.equals(y)?"":y));
			}else{
				Map<String, String> map =  new TreeMap<String, String>();
				map.put(specimenCode, x+" "+(x.equals(y)?"":y));
				strMapList.put(geneLoci, map);
			}
		}
		return strMapList;
	}
	
	private EntrustAbstracts getAbstracts(List<EntrustAbstracts> entrustAbstractsList, String code) {
		code=code.substring(2, code.indexOf("("));
		for(EntrustAbstracts abstracts:entrustAbstractsList){
			if(code.equals(abstracts.getSpecimenCode())){
				return abstracts;
			}
		}
	
		return null;
	} 
	private String[] generateDraftFromResult(List<DnaPiResult> dnaResultList) {
		String[] explain = new String[2];
		StringBuffer draft = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		sb.append("&nbsp;&nbsp;根据孟德尔遗传定律，亲代各自将一半的遗传信息给子代；以上21个基因组的检验结果显示:"+"\n");
		//"样本WZ201707-01602-F(杨焱火)具有成为样本WZ201707-01602-C(杨洋)生物学父亲的遗传学条件，两者之间的累计亲权指数（CPI值）达1.99×108以上，亲权关系概率达99.99%以上，故支持杨焱火为杨洋的生物学父亲。";
		String trueTemplate = "%s具有成为%s生物学%s的遗传学条件，两者之间的累计亲权指数（CPI值）达%s以上，亲权关系概率达%s以上，"+"\n";
		String falseTemplate = "%s不具有成为%s生物学%s的遗传学条件，两者之间的累计亲权指数（CPI值）为%s，亲权关系概率达%s，"+"\n";
		
		/*String trueDratTemplate = "支持%s为%s的生物学%s;\n";
		String falseDratTempalte = "排除%s为%s的生物学%s;\n";*/
		
		
		
		Map<String,List<String>> trueMap = new HashMap<String,List<String>> ();
		Map<String,List<String>> falseMap = new HashMap<String,List<String>> ();
		
		for(DnaPiResult result:dnaResultList){
			
			
			String relation = DictUtils.getDictLabel(result.getParentAbstracts().getAppellation(), "appellationCode", "父亲");
			String template=result.getCpi().doubleValue()>0.99?trueTemplate:falseTemplate;
			
			
			if(result.getCpi().doubleValue()>0.99){
				String parentName = result.getParentAbstracts().getClientName();
				if(trueMap.containsKey(parentName+"_"+relation )){
					trueMap.get(parentName+"_"+relation ).add(result.getChildAbstracts().getClientName());
				}else{
					List<String> list = new ArrayList<String>();
					list.add(result.getChildAbstracts().getClientName());
					trueMap.put(parentName+"_"+relation, list);
				}
			}else{
				String parentName = result.getParentAbstracts().getClientName();
				if(falseMap.containsKey(parentName+"_"+relation )){
					falseMap.get(parentName+"_"+relation).add(result.getChildAbstracts().getClientName());
				}else{
					List<String> list = new ArrayList<String>();
					list.add(result.getChildAbstracts().getClientName());
					falseMap.put(parentName+"_"+relation, list);
					
					
				}
			}
			String zuo="";
			Integer n=0;
			String cpi="";
			int temp=0;
		
			if(result.getCpi().doubleValue()>10000){
				String ji=String.valueOf(result.getCpi().doubleValue()).toString();
			if(ji!=null){
				if(ji.contains("E")){
					 temp=ji.indexOf("E");
					n=Integer.parseInt(ji.substring(temp+1, ji.length()));
					zuo=ji.substring(0,4);
				}
				else{
				    temp=ji.indexOf(".");
					n=temp-1;
					zuo=ji.substring(0,1)+"."+ji.substring(1,2);
				}
				
			}
			if(!zuo.equals("")&&n!=0){
				cpi=zuo+"×10^"+n;
			}
			}
			else{
				cpi=String.valueOf(result.getCpi().doubleValue()).toString();
			}
			String rcp="";
			if(result.getRcp().doubleValue()>0.9){
				rcp=String.valueOf(result.getRcp().doubleValue()*100).substring(0, 5)+"%";
			}else{
				rcp=String.valueOf(result.getRcp());
			}
			String value = String.format(template+"\n", 
							result.getParentCode(),
							result.getChildCode(),
							relation,
							cpi,
							rcp,
							result.getParentAbstracts().getClientName(),
							result.getChildAbstracts().getClientName(),
							relation
							);
			sb.append(value);
		}
		
		String chirdsName="";
		String draftValue="";
		String parentNames="";
		String trueDratTemplate = "支持%s为%s的生物学%s;\n";
		String falseDratTempalte = "排除%s为%s的生物学%s;\n";
		List<String>valueKey=new ArrayList<String>();
		
		if(!trueMap.isEmpty()){
			for(String key : trueMap.keySet()){ 
				valueKey= trueMap.get(key); 
			}
			for (String chirdsNames : valueKey) {
				if(valueKey.size()>1){
					if(!StringUtils.isEmpty(chirdsNames)||chirdsNames!=null){
						 chirdsName+=chirdsNames+",";
	 				}
					for (String string : trueMap.keySet()) {
						parentNames+=string;
					}
					draftValue = String.format(trueDratTemplate,parentNames.substring(0, parentNames.indexOf("_")),chirdsName.substring(0, chirdsName.length()-1),parentNames.substring(parentNames.length()-2,parentNames.length()));
				}else{
					
					for (int i = 0; i < dnaResultList.size(); i++) {
						if(dnaResultList.size()==0){
							 draftValue = String.format(trueDratTemplate, dnaResultList.get(0).getParentAbstracts().getClientName(),dnaResultList.get(0).getChildAbstracts().getClientName(),DictUtils.getDictLabel(dnaResultList.get(0).getParentAbstracts().getAppellation(), "appellationCode", "父亲"));
						}else{
							 draftValue+= String.format(trueDratTemplate, dnaResultList.get(i).getParentAbstracts().getClientName(),dnaResultList.get(i).getChildAbstracts().getClientName(),DictUtils.getDictLabel(dnaResultList.get(i).getParentAbstracts().getAppellation(), "appellationCode", "父亲"))+"\n";
						}
					}
				}
			}
		}
		
		
		if(!falseMap.isEmpty()){
			for(String key : falseMap.keySet()){ 
				valueKey= falseMap.get(key); 
			}
			for (String chirdsNames : valueKey) {
				if(valueKey.size()>1){
					if(!StringUtils.isEmpty(chirdsNames)||chirdsNames!=null){
						 chirdsName+=chirdsNames+",";
	 				}
					for (String string : falseMap.keySet()) {
						parentNames+=string;
					}
					draftValue = String.format(falseDratTempalte,parentNames.substring(0, parentNames.indexOf("_")),chirdsName.substring(0, chirdsName.length()-1),parentNames.substring(parentNames.length()-2,parentNames.length()));
				}else{
					for(DnaPiResult result:dnaResultList){
						 draftValue = String.format(falseDratTempalte, result.getParentAbstracts().getClientName(),result.getChildAbstracts().getClientName(),DictUtils.getDictLabel(result.getParentAbstracts().getAppellation(), "appellationCode", "父亲"));
						}
				}
			}
		}
		draft.append(draftValue);
		sb.append("故"+draftValue);
		explain[1] = sb.toString();
		explain[0]=draft.toString();
		return explain;
	}
	
	@RequiresPermissions("entrust:entrustExpertOpinion:edit")
	@RequestMapping(value = "save")
	public String save(EntrustExpertOpinion entrustExpertOpinion, Model model, RedirectAttributes redirectAttributes) {
		if(!StringUtils.isEmpty(entrustExpertOpinion.getDraftRemark())){
			entrustExpertOpinion.setDraftRemark(StringEscapeUtils.unescapeHtml4(entrustExpertOpinion.getDraftRemark()).trim());
			entrustExpertOpinion.setExplainRemark(StringEscapeUtils.unescapeHtml4(entrustExpertOpinion.getExplainRemark().trim()));
		}
		if (!beanValidator(model, entrustExpertOpinion)){
			return form(entrustExpertOpinion, model);
		}
		entrustExpertOpinionService.save(entrustExpertOpinion);
		addMessage(redirectAttributes, "保存鉴定意见成功");
		return "redirect:" + adminPath + "/act/task/todo/";
		//return "redirect:"+Global.getAdminPath()+"/entrust/entrustExpertOpinion/?repage";
	}
	
	@RequiresPermissions("entrust:entrustExpertOpinion:edit")
	@RequestMapping(value = "delete")
	public String delete(EntrustExpertOpinion entrustExpertOpinion, RedirectAttributes redirectAttributes) {
		entrustExpertOpinionService.delete(entrustExpertOpinion);
		addMessage(redirectAttributes, "删除鉴定意见成功");
		return "redirect:"+Global.getAdminPath()+"/entrust/entrustExpertOpinion/?repage";
	}
	 
	public static void main(String[] args) {
		String pic="|/jeesite/userfiles/1/images/entrust/mapping/2017/09/1111.jpg|/jeesite/userfiles/1/images/entrust/mapping/2017/09/2222.jpg";
		String []d=pic.split("\\|");
		for (String c : d) {
			 if(c.equals("")){
			 }else{
				 System.out.println(c);
			 }
		}
		///jeesite/userfiles/1/images/entrust/mapping/2017/09/1111.jpg
		///jeesite/userfiles/1/images/entrust/mapping/2017/09/2222.jpg
	}
	


}