/**



 */
package com.thinkgem.jeesite.modules.dna.web;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.dna.dao.DnaDailyItemDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaExperimentSpecimenDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaExperimentStrDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaExtractRecordItemDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaPiResultDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaPiResultItemDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaBoard;
import com.thinkgem.jeesite.modules.dna.entity.DnaDaily;
import com.thinkgem.jeesite.modules.dna.entity.DnaDailyItem;
import com.thinkgem.jeesite.modules.dna.entity.DnaElectrophoresisParting;
import com.thinkgem.jeesite.modules.dna.entity.DnaElectrophoresisPartingIteam;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperiment;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperimentSpecimen;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperimentStr;
import com.thinkgem.jeesite.modules.dna.entity.DnaExtractRecord;
import com.thinkgem.jeesite.modules.dna.entity.DnaExtractRecordItem;
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneCassette;
import com.thinkgem.jeesite.modules.dna.entity.DnaPiResult;
import com.thinkgem.jeesite.modules.dna.entity.DnaPiResultItem;
import com.thinkgem.jeesite.modules.dna.entity.DnaPreparationReagents;
import com.thinkgem.jeesite.modules.dna.entity.DnaPreparationReagentsIteam;
import com.thinkgem.jeesite.modules.dna.entity.DnaSpe;
import com.thinkgem.jeesite.modules.dna.entity.DnaSpeIteam;
import com.thinkgem.jeesite.modules.dna.entity.Temporary;
import com.thinkgem.jeesite.modules.dna.service.DnaBoardService;
import com.thinkgem.jeesite.modules.dna.service.DnaExperimentService;
import com.thinkgem.jeesite.modules.dna.service.DnaExtractRecordService;
import com.thinkgem.jeesite.modules.dna.service.DnaGeneCassetteService;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustAbstracts;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;
import com.thinkgem.jeesite.modules.entrust.service.EntrustAbstractsService;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialRegisterItemDao;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegisterItem;
import com.thinkgem.jeesite.modules.synth.dao.SynthEquipmentDao;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipment;
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;
import com.thinkgem.jeesite.modules.synth.entity.SynthLabUsageRecord;
import com.thinkgem.jeesite.modules.synth.service.SynthLabService;
import com.thinkgem.jeesite.modules.synth.service.SynthLabUsageRecordService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * dna试验Controller
 * @author zhuguli
 * @version 2017-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/dna/dnaExperiment")
public class DnaExperimentController extends BaseController {
	
	@Autowired
	private DnaExperimentSpecimenDao dnaExperimentSpecimenDao;
	@Autowired
	private DnaExperimentService dnaExperimentService;
	@Autowired
	private SynthLabService synthLabService;
	@Autowired
	private DnaGeneCassetteService dnaGeneCassetteService;
	@Autowired
	private DnaBoardService dnaBoardService;
	@Autowired
	private SpecimenMaterialRegisterItemDao  specimenMaterialRegisterItemDao;
	@Autowired
	private SynthLabUsageRecordService synthLabUsageRecordService;
	@Autowired
	private SynthEquipmentDao synthEquipmentDao;
	@Autowired
	private DnaExtractRecordService dnaExtractRecordService;
	@Autowired
	private DnaDailyItemDao dnaDailyItemDao;
	@Autowired
	private DnaExperimentStrDao dnaExperimentStrDao;
	@Autowired
	private DnaPiResultDao dnaPiResultDao;
	@Autowired
	private DnaPiResultItemDao dnaPiResultItemDao;
	
	 
	
	@ModelAttribute
	public DnaExperiment get(@RequestParam(required=false) String id) {
		DnaExperiment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dnaExperimentService.get(id);
			List<DnaExperimentSpecimen> dnaExperimentSpecimenList = dnaExperimentSpecimenDao.findList(new DnaExperimentSpecimen(entity));
			entity.setDnaExperimentSpecimenList(dnaExperimentSpecimenList);
		}
		if (entity == null){
			entity = new DnaExperiment();
		}
		return entity;
	}

	@RequiresPermissions("dna:dnaExperiment:view")
	@RequestMapping(value = {"list", ""})
	public String list(DnaExperiment dnaExperiment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DnaExperiment> page = dnaExperimentService.findPage(new Page<DnaExperiment>(request, response), dnaExperiment); 
		model.addAttribute("page", page);
		return " ";
	}

	@RequiresPermissions("dna:dnaExperiment:view")
	@RequestMapping(value = "form")
	public String form(DnaExperiment dnaExperiment, Model model) {
		dnaExperiment.setStartTime(new Date());
		List<SynthLab> labList = synthLabService.findList(new SynthLab());
		List<SynthLab>agoExtractionChambers=new ArrayList<SynthLab>();
		List<SynthLab>extractionChambers=new ArrayList<SynthLab>();
		List<SynthLab>expansionChambers=new ArrayList<SynthLab>();
		List<SynthLab>elctrophoresisChambers=new ArrayList<SynthLab>();
		
		for (SynthLab synthLabOnly : labList) {
			if(synthLabOnly.getName().contains("提取室")) {
				extractionChambers.add(synthLabOnly);
			}
			if(synthLabOnly.getName().contains("扩增室")) {
				expansionChambers.add(synthLabOnly);
			}
			if(synthLabOnly.getName().contains("电泳室")) {
				elctrophoresisChambers.add(synthLabOnly);
			}
		}
		
		//agoExtractionChambers,extractionChambers,expansionChambers.elctrophoresisChambers
		model.addAttribute("agoExtractionChambers", agoExtractionChambers);
		model.addAttribute("extractionChambers", extractionChambers);
		model.addAttribute("expansionChambers", expansionChambers);
		model.addAttribute("elctrophoresisChambers", elctrophoresisChambers);
		model.addAttribute("labList", labList);
		List<DnaGeneCassette> cassetteList = dnaGeneCassetteService.findList(new DnaGeneCassette());
		model.addAttribute("cassetteList", cassetteList);
		model.addAttribute("dnaExperiment", dnaExperiment);
		List<DnaBoard> boardList = dnaBoardService.findList(new DnaBoard());
		if(boardList.isEmpty()){
			//为空时自动添加一块板子
			DnaBoard board =  dnaBoardService.autoMakeBoard(null);
			boardList.add(board);
		}
		
		model.addAttribute("boardList", boardList);
		List<SpecimenMaterialRegisterItem> notExperimentList =  specimenMaterialRegisterItemDao.getNotExperiment();
		for(SpecimenMaterialRegisterItem item:notExperimentList){
			DnaExperimentSpecimen specimen = new DnaExperimentSpecimen();
			specimen.setSpecimenCode(item.getCode());
			dnaExperiment.getDnaExperimentSpecimenList().add(specimen);
		}
		List<SpecimenMaterialRegisterItem> all =  specimenMaterialRegisterItemDao.findAllList();
		model.addAttribute("notExperimentList",all);
		return "modules/dna/dnaExperimentForm";
	}
	
	@RequiresPermissions("dna:dnaExperiment:view")
	@RequestMapping(value = "addNewBoard")
	public String addNewBoard(){
		dnaBoardService.autoMakeBoard(null);
		return "redirect:" + adminPath + "/dna/dnaExperiment/form";
	}
	
	//查看提取室
	@RequiresPermissions("dna:dnaExperiment:edit")
	@RequestMapping(value = "findExtraction")
	public String findExtraction(String fid,Model model,DnaExtractRecord dnaExtractRecord, RedirectAttributes redirectAttributes){
		boolean n=false;
		DnaDailyItem dailyItem=new DnaDailyItem();
		List<DnaDailyItem>dnaDailyItems= dnaDailyItemDao.findDate();
		for (DnaDailyItem dnaDailyItem : dnaDailyItems) {
			if(dnaDailyItem.getLab().getId()!=null){
				if(dnaDailyItem.getLab().getId().equals(fid)) {
					n=true;
					dailyItem=dnaDailyItem;
					break;
				}
			}
		}
		if(n==true){
			dailyItem.getRelativeHumidity();//相对湿度
			dailyItem.getTemperature();//温度
			dailyItem.getRefrigeratorTemperature();//冰箱温度
			dnaExtractRecord.setTemperature(dailyItem.getTemperature());
			dnaExtractRecord.setHumidity(dailyItem.getRelativeHumidity());
			dnaExtractRecord.setRefrigeratorTemperature(dailyItem.getRefrigeratorTemperature());
			List<SynthEquipment>synthEquipments= synthEquipmentDao.findAll(fid);
			model.addAttribute("extraction", synthEquipments);
			SynthLab lab=  synthLabService.get(fid);
			model.addAttribute("dnaExtractRecord", dnaExtractRecord);
			List<SpecimenMaterialRegisterItem> notExperimentList =  specimenMaterialRegisterItemDao.getNotExperiment();
				for(SpecimenMaterialRegisterItem item:notExperimentList){
					DnaExtractRecordItem specimen = new DnaExtractRecordItem();
					specimen.setSampleNumber(item.getCode());
					dnaExtractRecord.getDnaExtractRecordItemList().add(specimen);
				}
					model.addAttribute("lab", lab);
					return "modules/dna/dnaExtractRecordForm";
		}else{
			addMessage(redirectAttributes, "你未添加今日实验室温度，相对湿度及冰箱温度的记录");
			return "redirect:" + adminPath + "/dna/dnaDaily";
		}
	}
	//提取室的保存
	@RequiresPermissions("dna:dnaExperiment:edit")
	@RequestMapping(value = "addExtraction")
	public String addExtraction(DnaExtractRecord dnaExtractRecord, Model model, RedirectAttributes redirectAttributes){
		User user=UserUtils.getUser();
		dnaExtractRecord.setOperator(user);
		
		dnaExtractRecordService.save(dnaExtractRecord);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/dna/dnaExperiment/form";
	}
	
	//查看扩增室expansion
	@RequiresPermissions("dna:dnaExperiment:edit")
	@RequestMapping(value = "findExpansion")
 	public String findExpansion(String fid,Model model,DnaPreparationReagents dnaPreparationReagents, RedirectAttributes redirectAttributes){
		boolean n=false;
		DnaDailyItem dailyItem=new DnaDailyItem();
		List<DnaDailyItem>dnaDailyItems= dnaDailyItemDao.findDate();
		for (DnaDailyItem dnaDailyItem : dnaDailyItems) {
			if(dnaDailyItem.getLab().getId()!=null){
				if(dnaDailyItem.getLab().getId().equals(fid)) {
					n=true;
					dailyItem=dnaDailyItem;
					break;
				}
			}
		}
		List<SpecimenMaterialRegisterItem> notExperimentList =  specimenMaterialRegisterItemDao.getNotExperiment();
		if(n==true){
			for(SpecimenMaterialRegisterItem item:notExperimentList){
				DnaPreparationReagentsIteam specimen = new DnaPreparationReagentsIteam();
				specimen.setSampleNumber(item.getCode());
				dnaPreparationReagents.getDnaPreparationReagentsIteamList().add(specimen);
			}
			dnaPreparationReagents.setTemperature(dailyItem.getTemperature());
			dnaPreparationReagents.setHumidity(dailyItem.getRelativeHumidity());
			dnaPreparationReagents.setRefrigeratorTemperature(dailyItem.getRefrigeratorTemperature());
			List<SynthEquipment>synthEquipments= synthEquipmentDao.findAll(fid);
			model.addAttribute("extraction", synthEquipments);
			SynthLab lab=  synthLabService.get(fid);
			model.addAttribute("dnaPreparationReagents", dnaPreparationReagents);
			model.addAttribute("lab", lab);
			return "modules/dna/dnaPreparationReagentsForm";
		}else{
			addMessage(redirectAttributes, "你未添加今日实验室温度，相对湿度及冰箱温度的记录");
			return "redirect:" + adminPath + "/dna/dnaDaily";
		}
	}
	
	
	
	
	
	
	//查看电泳室elctrophoresis
	@RequiresPermissions("dna:dnaExperiment:edit")
	@RequestMapping(value = "findElctrophoresi")
	public String findElctrophoresi(String fid,Model model,DnaElectrophoresisParting dnaElectrophoresisParting, RedirectAttributes redirectAttributes){
		boolean n=false;
		DnaDailyItem dailyItem=new DnaDailyItem();
		List<DnaDailyItem>dnaDailyItems= dnaDailyItemDao.findDate();
		for (DnaDailyItem dnaDailyItem : dnaDailyItems) {
			if(dnaDailyItem.getLab().getId()!=null){
				if(dnaDailyItem.getLab().getId().equals(fid)) {
					n=true;
					dailyItem=dnaDailyItem;
					break;
				}
			}
		}
		List<SpecimenMaterialRegisterItem> notExperimentList =  specimenMaterialRegisterItemDao.getNotExperiment();
		if(n==true){
			for(SpecimenMaterialRegisterItem item:notExperimentList){
				DnaElectrophoresisPartingIteam specimen = new DnaElectrophoresisPartingIteam();
				specimen.setSampleNumber(item.getCode());
				dnaElectrophoresisParting.getDnaElectrophoresisPartingIteamList().add(specimen);
			}
			dnaElectrophoresisParting.setTemperature(dailyItem.getTemperature());
			dnaElectrophoresisParting.setHumidity(dailyItem.getRelativeHumidity());
			dnaElectrophoresisParting.setRefrigeratorTemperature(dailyItem.getRefrigeratorTemperature());
			List<SynthEquipment>synthEquipments= synthEquipmentDao.findAll(fid);
			model.addAttribute("extraction", synthEquipments);
			SynthLab lab=  synthLabService.get(fid);
			model.addAttribute("dnaPreparationReagents", dnaElectrophoresisParting);
			model.addAttribute("lab", lab);
			return "modules/dna/dnaElectrophoresisPartingForm";
		}else{
			addMessage(redirectAttributes, "你未添加今日实验室温度，相对湿度及冰箱温度的记录");
			return "redirect:" + adminPath + "/dna/dnaDaily";
		}
	}

	
	
	
	
	@RequiresPermissions("dna:dnaExperiment:edit")
	@RequestMapping(value = "save")
	public String save(DnaExperiment dnaExperiment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dnaExperiment)){
			return form(dnaExperiment, model);
		}
		//添加实验室使用记录
		User user=UserUtils.getUser();
		dnaExperiment.setOperator(user);
		dnaExperimentService.save(dnaExperiment);
		addMessage(redirectAttributes, "保存dna试验成功");
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	
	
	@RequiresPermissions("dna:dnaExperiment:edit")
	@RequestMapping(value = "board")
	public String board(DnaExperiment dnaExperiment,Model model){
		Act act = dnaExperiment.getAct();
		dnaExperiment = dnaExperimentService.get(act.getBusinessId());
		dnaExperiment.setAct(act);
		String dnaExperimentId = dnaExperiment.getAct().getBusinessId();
		DnaBoard board = dnaExperimentService.getLastBoard(dnaExperimentId);
		dnaExperiment.setBoard(board);
		model.addAttribute("dnaExperiment", dnaExperiment);
		return "modules/dna/dnaExperimentBoard"; 
	}
	@RequiresPermissions("dna:dnaExperiment:edit")
	@RequestMapping(value = "board/save")
	public String boardSave(DnaExperiment dnaExperiment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dnaExperiment)){
			return form(dnaExperiment, model);
		}
		dnaExperimentService.saveBoard(dnaExperiment);
		addMessage(redirectAttributes, "保存dna试验成功");
		return "redirect:" + adminPath + "/act/task/todo/";
	}

	@RequiresPermissions("dna:dnaExperiment:edit")
	@RequestMapping(value = "delete")
	public String delete(DnaExperiment dnaExperiment, RedirectAttributes redirectAttributes) {
		dnaExperimentService.delete(dnaExperiment);
		addMessage(redirectAttributes, "删除dna试验成功");
		return "redirect:"+Global.getAdminPath()+"/dna/dnaExperiment/?repage";
	}
	@RequiresPermissions("dna:dnaExperiment:view")
	@RequestMapping(value = "importForm")
	public String importForm(DnaExperiment dnaExperiment,Model model){
		Act act = dnaExperiment.getAct();
		dnaExperiment = dnaExperimentService.get(act.getBusinessId());
		dnaExperiment.setAct(act);
		List<SynthLab> labList = synthLabService.findList(new SynthLab());
		model.addAttribute("labList", labList);
		List<DnaGeneCassette> cassetteList = dnaGeneCassetteService.findList(new DnaGeneCassette());
		model.addAttribute("cassetteList", cassetteList);
		model.addAttribute("dnaExperiment", dnaExperiment);
		return "modules/dna/dnaExperimentImportForm";
	}

	@RequiresPermissions("dna:dnaExperiment:view")
	@RequestMapping(value = "import")
	public String importData(DnaExperiment dnaExperiment,HttpServletRequest request,Model model){
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String path = request.getContextPath();
		realPath = realPath.substring(0,realPath.length()-path.length()-1);
		String importDataAddress = dnaExperiment.getImportDataAddress();
		importDataAddress = realPath+importDataAddress.substring(1);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		dnaExperiment.setImportDataAddress(importDataAddress );
		dnaExperimentService.importData(dnaExperiment);
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	
	
	@RequiresPermissions("dna:dnaExperiment:view")
	@RequestMapping(value = "batchImportForm")
	public String batchImportForm(DnaExperiment dnaExperiment,Model model){
		List<DnaGeneCassette> cassetteList = dnaGeneCassetteService.findList(new DnaGeneCassette());
		model.addAttribute("cassetteList", cassetteList);
		model.addAttribute("dnaExperiment", dnaExperiment);
		return "modules/dna/dnaExperimentBatchImportForm";
	}

	
	
	@RequiresPermissions("dna:dnaExperiment:view")
	@RequestMapping(value = "batchImport")
	public String batchImport(DnaExperiment dnaExperiment,HttpServletRequest request,Model model){
		dnaExperimentStrDao.delect();
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String path = request.getContextPath();
		realPath = realPath.substring(0,realPath.length()-path.length()-1);
		String importDataAddress = dnaExperiment.getImportDataAddress();
		importDataAddress = realPath+importDataAddress.substring(1);
		dnaExperiment.setImportDataAddress(importDataAddress );
		dnaExperimentService.batchImportData(dnaExperiment);
		return "redirect:" + adminPath + "/dna/dnaExperiment/calcuForm?id="+dnaExperiment;
	}
	@RequiresPermissions("dna:dnaExperiment:view")
	@RequestMapping(value = "calcuForm")
	public String calcu(DnaExperiment dnaExperiment,HttpServletRequest request,Model model){
		model.addAttribute("dnaExperiment", dnaExperiment);
		return "modules/dna/dnaExperimentCalcuForm";
	}
	@RequiresPermissions("dna:dnaExperiment:view")
	@RequestMapping(value = "calcu")
	public String calcu(HttpServletRequest request,String id,Model model,DnaSpe dnaSpe,HttpServletResponse response){

			List<DnaExperimentStr> daExperimentStrs=new ArrayList<DnaExperimentStr>();
			Map<String,Map<String,String>> strMapList=new HashMap<String, Map<String,String>>();
			List<DnaSpeIteam>dnaSpeIteams=new ArrayList<DnaSpeIteam>();
			for (int i = 0; i < dnaSpe.getDnaSpeIteams().size(); i++) {
				if(dnaSpe.getDnaSpeIteams().get(i) .getSpecimen()!=null){
					dnaSpeIteams.add(dnaSpe.getDnaSpeIteams().get(i));
				}
			}



			List<Map<String,Map<String,String>>> mapList = new ArrayList<Map<String, Map<String, String>>>();
		 for (DnaSpeIteam dnaSpeIteam :dnaSpeIteams) {
				Map<String,Map<String,String>> strMap=new HashMap<String, Map<String,String>>();
				daExperimentStrs.addAll(dnaExperimentStrDao.getById( dnaSpeIteam.getSpecimen()));
				List<DnaExperimentStr> a=dnaExperimentStrDao.getById( dnaSpeIteam.getSpecimen());
				//在这里申明 带进去  不要重新生成新的map
				strMap= genateMapFromList(a,strMapList);
				model.addAttribute("str", strMap);
				model.addAttribute("daExperimentStrs", daExperimentStrs);

			    mapList.add(strMap);
			 	dnaExperimentService.export(strMap, daExperimentStrs, response);
		 }

		dnaExperimentService.calculate(dnaSpeIteams,id);	
		List<DnaPiResultItem> dnaPiResultItem=dnaPiResultItemDao.findAll();
		List<DnaPiResult>  dnaPiResult=dnaPiResultDao.findAll();
		model.addAttribute("dnaPiResultItem", dnaPiResultItem);
		double pi=1;
		for (int i = 0; i < dnaPiResultItem.size(); i++) {
			pi*=dnaPiResultItem.get(i).getPi();
		}
		model.addAttribute("dnaPiResult", dnaPiResult);
		model.addAttribute("pi", pi);

		return "modules/dna/dnaExperimentCalcuList"; 
	}
	
	//后面加一个map参数即可
	private Map<String,Map<String,String>> genateMapFromList(List<DnaExperimentStr> strList,Map<String,Map<String,String>> strMapList) {
		for(DnaExperimentStr str :strList){
			String geneLoci = str.getGeneLoci();
			String specimenCode = str.getSpecimenCode();
			String x = str.getX();
			String y = str.getY();
			Map<String, String> map = null;
			if(strMapList.containsKey(geneLoci)){
				map = strMapList.get(geneLoci);
				map.put(specimenCode, x+" "+y);
			}else{
				map =  new TreeMap<String, String>();
				map.put(specimenCode, x+" "+y);
			}
			if(map!=null){
				strMapList.put(geneLoci, map);
			}
		}
 		return strMapList;
	}
	

}