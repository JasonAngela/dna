/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.svggen.font.table.DirectoryEntry;
import org.apache.commons.io.FileUtils;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import com.thinkgem.jeesite.modules.dna.dao.DnaBoardJggDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaElectrophoresisPartingIteamDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaExperimentStrDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaExtractRecordItemDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaPreparationReagentsIteamDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaBoard;
import com.thinkgem.jeesite.modules.dna.entity.DnaBoardJgg;
import com.thinkgem.jeesite.modules.dna.entity.DnaElectrophoresisParting;
import com.thinkgem.jeesite.modules.dna.entity.DnaElectrophoresisPartingIteam;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperimentStr;
import com.thinkgem.jeesite.modules.dna.entity.DnaExtractRecord;
import com.thinkgem.jeesite.modules.dna.entity.DnaExtractRecordItem;
import com.thinkgem.jeesite.modules.dna.entity.DnaPreparationReagents;
import com.thinkgem.jeesite.modules.dna.entity.DnaPreparationReagentsIteam;
import com.thinkgem.jeesite.modules.dna.service.DnaBoardService;
import com.thinkgem.jeesite.modules.dna.service.DnaElectrophoresisPartingService;
import com.thinkgem.jeesite.modules.dna.service.DnaExtractRecordService;
import com.thinkgem.jeesite.modules.dna.service.DnaPreparationReagentsService;
import com.thinkgem.jeesite.modules.entrust.dao.EntrustChargeCredentialsDao;
import com.thinkgem.jeesite.modules.entrust.dao.EntrustExpertOpinionDao;
import com.thinkgem.jeesite.modules.entrust.dao.LicensedDao;
import com.thinkgem.jeesite.modules.entrust.dao.MappingDao;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustAbstracts;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustChargeCredentials;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustExpertOpinion;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;
import com.thinkgem.jeesite.modules.entrust.entity.Licensed;
import com.thinkgem.jeesite.modules.entrust.entity.Mapping;
import com.thinkgem.jeesite.modules.entrust.entity.ReadCard;
import com.thinkgem.jeesite.modules.entrust.service.EntrustAbstractsService;
import com.thinkgem.jeesite.modules.entrust.service.EntrustRegisterService;
import com.thinkgem.jeesite.modules.entrust.service.MappingService;
import com.thinkgem.jeesite.modules.entrust_flow_sheet.dao.entrust.EntrustFlowSheetDao;
import com.thinkgem.jeesite.modules.entrust_flow_sheet.entity.entrust.EntrustFlowSheet;
import com.thinkgem.jeesite.modules.material.service.SpecimenMaterialRegisterService;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.xueka.entity.SpecimenXueka;
import com.thinkgem.jeesite.modules.xueka.service.SpecimenXuekaService;

/**
 * 委托登记Controller
 * @author zhuguli
 * @version 2017-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/entrust/entrustRegister")
public class EntrustRegisterController extends BaseController {

	@Autowired
	private EntrustRegisterService entrustRegisterService;
	@Autowired
	private SpecimenXuekaService specimenXuekaService;
	@Autowired
	private EntrustAbstractsService entrustAbstractsService;
	@Autowired
	private SpecimenMaterialRegisterService specimenMaterialRegisterService;
	@Autowired
	private DnaExperimentStrDao dnaExperimentStrDao;
	@Autowired
	private EntrustExpertOpinionDao entrustExpertOpinionDao;
	@Autowired
	private DnaBoardJggDao dnaBoardJggDao;
	@Autowired
	private DnaBoardService dnaBoardService;
	@Autowired
	private DnaExtractRecordItemDao dnaExtractRecordItemDao;
	@Autowired
	private DnaExtractRecordService dnaExtractRecordService;
	@Autowired
	private DnaPreparationReagentsService dnaPreparationReagentsService;
	@Autowired
	private DnaPreparationReagentsIteamDao dnaPreparationReagentsIteamDao;
	@Autowired
	private DnaElectrophoresisPartingIteamDao dnaElectrophoresisPartingIteamDao;
	@Autowired
	private DnaElectrophoresisPartingService dnaElectrophoresisPartingService;
	@Autowired
	private MappingDao mappingDao;
	@Autowired
	private LicensedDao licensedDao;
	@Autowired
	private EntrustFlowSheetDao entrustFlowSheetDao;
	@Autowired
	private EntrustChargeCredentialsDao entrustChargeCredentialsDao;
	@Autowired
	private SystemService systemService;
	
	
	@ModelAttribute
	public EntrustRegister get(@RequestParam(required=false) String id) {
		EntrustRegister entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = entrustRegisterService.get(id);
		}
		if (entity == null){
			entity = new EntrustRegister();
		}
		return entity;
	}

	//
	@RequiresPermissions("entrust:entrustRegister:view")
	@RequestMapping(value = {"list", ""})
	public String list(EntrustRegister entrustRegister, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EntrustRegister> page = entrustRegisterService.findPage(new Page<EntrustRegister>(request, response), entrustRegister); 
		model.addAttribute("page", page);
		return "modules/entrust/entrustRegisterList";
	}

	//归档 archive
	@RequiresPermissions("entrust:entrustRegister:view")
	@RequestMapping(value = {"archive"})
	public String archive(EntrustRegister entrustRegister,Model model) {
		model.addAttribute("entrustRegister",entrustRegister);
		return "modules/entrust/entrustRegisterArchive";
	}
	@RequiresPermissions("entrust:entrustRegister:view")
	@RequestMapping(value = "form")
	public String form(EntrustRegister entrustRegister, Model model) {
		model.addAttribute("entrustRegister", entrustRegister);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		User user=UserUtils.getUser(); 
		model.addAttribute("user", user);
		model.addAttribute("entrustDate", df.format(new Date()));
		return "modules/entrust/entrustRegisterForm";
	}


	
	
	//      
	@RequiresPermissions("entrust:entrustRegister:view")
	@RequestMapping(value = "details")
	public String details(EntrustRegister entrustRegister, Model model) {
		String casecode= entrustRegister.getCaseCode().substring(10, 16);
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");                                       
		model.addAttribute("casecode", casecode);
		model.addAttribute("simple", simple.format( new Date()));
		model.addAttribute("entrustRegister", entrustRegister);
		List<EntrustAbstracts>allentrustAbstracts=entrustAbstractsService.findAllentrustAbstracts(entrustRegister.getId());
		model.addAttribute("allentrustAbstracts", allentrustAbstracts);
		return "modules/entrust/entrustRegisterDetails";
	}

	
	
	//当事人
	@RequiresPermissions("entrust:entrustRegister:view")
	@RequestMapping(value="parties")
	public String parties(EntrustRegister entrustRegister,Model model){
		List<EntrustAbstracts>allentrustAbstracts=entrustAbstractsService.findAllentrustAbstracts(entrustRegister.getId());
		List<String>pics=new ArrayList<String>();
		for (int i = 0; i < allentrustAbstracts.size(); i++) {
			if( allentrustAbstracts.get(i).getIdPic()!=null){
				String []d=allentrustAbstracts.get(i).getIdPic().split("\\|");
				for (String pic : d) {
					if(pic.equals("")){
					}else{
						pics.add(pic);
					}
				}
			}
		 }
		 model.addAttribute("allentrustAbstracts", allentrustAbstracts);
		 model.addAttribute("pic",pics); 
         return "modules/entrust/entrustParties";
	}
	
   //实验报告
	@RequiresPermissions("entrust:entrustRegister:view")
	@RequestMapping(value = "report")
	public String report(EntrustRegister entrustRegister, Model model) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");
		//DNA2017071100057
		String casecode= entrustRegister.getCaseCode().substring(10, 16);
		String fm="";
		List<EntrustAbstracts>allentrustAbstracts=entrustAbstractsService.findAllentrustAbstracts(entrustRegister.getId());
		List<String>father=new ArrayList<String>();
		List<String>mather=new ArrayList<String>();
		List<String>children=new ArrayList<String>();
		for (int i = 0; i < allentrustAbstracts.size(); i++) {
			String appellationname=allentrustAbstracts.get(i).getAppellation();
			String ClientName=allentrustAbstracts.get(i).getClientName();
			if(appellationname.equals("1")){
				father.add(ClientName);
			}
			if(appellationname.equals("2")){
				mather.add(ClientName);
			}
			if(appellationname.equals("3")){
				children.add(ClientName);
			}	
		}
		if(father.size()!=0){
			for (int j = 0; j < father.size(); j++) {
				for (int ds = 0; ds < children.size(); ds++) {
					fm+=father.get(j)+"是否为"+children.get(ds)+"生物学父亲"+"<br/>";				
				}
			}
		
		}
		if(mather.size()>0){
			for (int j = 0; j < mather.size(); j++) {
				for (int ds = 0; ds < children.size(); ds++) {
					fm+=mather.get(j)+"是否为"+children.get(ds)+"生物学母亲"+"<br/>";				
				}
			}
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//设置日期格式

		//数量
		int qty=0;
		//鉴定类型
		String materialType="";
		for (int ij = 0; ij < specimenMaterialRegisterService.findmaterial(entrustRegister.getId()).size(); ij++) {
			qty+=specimenMaterialRegisterService.findmaterial(entrustRegister.getId()).get(ij).getQty();
			materialType=specimenMaterialRegisterService.findmaterial(entrustRegister.getId()).get(ij).getMaterialType();
		}

		//被鉴定人员
		List<EntrustAbstracts> entrustAbstracts= entrustAbstractsService.findAllentrustAbstracts(entrustRegister.getId());

		//检案摘要
		String testcase=df.format(entrustRegister.getCreateDate())+",";
		for (String fathers : father) {
			for (String childrens : children) {
				testcase+=entrustRegister.getClientName()+"委托我鉴定中心对其与"+childrens+"进行亲子关系鉴定,由我鉴定中心工作人员采集"+fathers+"和"+childrens+"的手指血各一份，我鉴定中心仅对样本的检验负责。"+"<br/>";
			}
		}

		List<DnaExperimentStr>strList=dnaExperimentStrDao.getByRegisterId(entrustRegister.getId());
		Map<String,Map<String,String>> strMap = genateMapFromList(strList);

		//实验报告
		List<EntrustExpertOpinion> entrustExpertOpinionList = entrustExpertOpinionDao.getByRegisterId(entrustRegister.getId());

		//分析说明 
		String finaler="";                    
		//鉴定意见
		String explain="";
 		if(entrustExpertOpinionList.size()>0){
			for (int i = 0; i < entrustExpertOpinionList.size(); i++) {
				finaler=entrustExpertOpinionList.get(0).getFinalVersion();
				explain=entrustExpertOpinionList.get(0).getDraft();
			}
		}

		

		model.addAttribute("explain", explain);
		model.addAttribute("finaler", finaler);
		model.addAttribute("casecode", casecode);
		model.addAttribute("simple", simple.format( new Date()));
		model.addAttribute("testcase",testcase);
		model.addAttribute("entrustAbstracts",entrustAbstracts);
		model.addAttribute("materialType",materialType);
		model.addAttribute("qty",qty);
		model.addAttribute("df",df.format(entrustRegister.getCreateDate()));
		model.addAttribute("fm",fm);
		model.addAttribute("str", strMap);
		model.addAttribute("entrustRegister", entrustRegister);
		return "modules/entrust/entrustRegisterReport";
	}


	private Map<String,Map<String,String>> genateMapFromList(List<DnaExperimentStr> strList) {
		Map<String,Map<String,String>> strMapList =  new HashMap<String, Map<String,String>>();
		for(DnaExperimentStr str :strList){
			String geneLoci = str.getGeneLoci();
			String specimenCode = str.getSpecimenCode();
			String x = str.getX();
			String y = str.getY();
			if(strMapList.containsKey(geneLoci)){
				Map<String, String> map = strMapList.get(geneLoci);
				map.put(specimenCode, x+" "+(x.equals(y)?"":y));
			}else{
				Map<String, String> map =  new HashMap<String, String>();
				map.put(specimenCode, x+" "+(x.equals(y)?"":y));
				strMapList.put(geneLoci, map);
			}
		}
		return strMapList;
	}
	@RequiresPermissions("entrust:entrustRegister:edit")
	@RequestMapping(value = "entrustRegisterReceipt")
	public String entrustRegisterReceipt(EntrustRegister entrustRegister,Model model){
		List<EntrustAbstracts> entrustAbstracts= entrustAbstractsService.findAllentrustAbstracts(entrustRegister.getId());
		model.addAttribute("entrustAbstracts", entrustAbstracts);
		model.addAttribute("entrustRegister", entrustRegister);
		return "modules/entrust/entrustRegisterReceipt";
	}

	//归档 实验室记录表
	@RequiresPermissions("entrust:entrustRegister:edit")
	@RequestMapping(value = "findBoard")
	public String findBoard(EntrustRegister entrustRegister,Model model){
    
		List<EntrustAbstracts>allentrustAbstracts=entrustAbstractsService.findAllentrustAbstracts(entrustRegister.getId());
		
		List<DnaBoardJgg> dnaBoardJgg=dnaBoardJggDao.findBoard(allentrustAbstracts.get(0).getSpecimenCode());
		//委托单下的样品号
		allentrustAbstracts.get(0).getSpecimenCode();
		//电泳版
		DnaBoard board = dnaBoardService.get(dnaBoardJgg.get(0).getBoard().getId());
		model.addAttribute("board", board);
		//提取室
		List<DnaExtractRecordItem> dnaExtractRecordItems=dnaExtractRecordItemDao.findDnaExtractRecordItem(allentrustAbstracts.get(0).getSpecimenCode());
		DnaExtractRecord dnaExtractRecord=dnaExtractRecordService.get(dnaExtractRecordItems.get(0).getRecord().getId());
		model.addAttribute("dnaExtractRecord", dnaExtractRecord);
		//扩增室
		List<DnaPreparationReagentsIteam>dnaPreparationReagentsIteams=dnaPreparationReagentsIteamDao.findNumber(allentrustAbstracts.get(0).getSpecimenCode());
		DnaPreparationReagents dnaPreparationReagents=dnaPreparationReagentsService.get(dnaPreparationReagentsIteams.get(0).getDnaPreparationReagents().getId());
		model.addAttribute("dnaPreparationReagents", dnaPreparationReagents);
		
				//电泳室
		List<DnaElectrophoresisPartingIteam>dnaElectrophoresisPartingIteams= dnaElectrophoresisPartingIteamDao.findNumber(allentrustAbstracts.get(0).getSpecimenCode());
		DnaElectrophoresisParting dnaElectrophoresisParting=dnaElectrophoresisPartingService.get(dnaElectrophoresisPartingIteams.get(0).getDnaElectrophoresisParting().getId());
		model.addAttribute("dnaElectrophoresisParting", dnaElectrophoresisParting);
		 	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		
		return "modules/entrust/labrecords";
	}
	


	@RequiresPermissions("entrust:entrustRegister:edit")
	@RequestMapping(value = "save")
	public String save(EntrustRegister entrustRegister, Model model, HttpServletRequest request,RedirectAttributes redirectAttributes,String remainingq,String timeLimitReportq,String whetherq,String sendWayq,String materialDisposeq) {

		if (!beanValidator(model, entrustRegister)){
			return form(entrustRegister, model);
		}

		entrustRegister.setRemaining(remainingq);
		entrustRegister.setTimeLimitReport(timeLimitReportq);
		entrustRegister.setWhether(whetherq);
		entrustRegister.setSendWay(sendWayq);
		entrustRegister.setMaterialDispose(materialDisposeq);
		entrustRegisterService.save(entrustRegister);
		addMessage(redirectAttributes, "保存受理成功");
		return "redirect:" + adminPath + "/act/task/todo/";
		//return "redirect:"+Global.getAdminPath()+"/entrust/entrustRegister/?repage";
	}

	@RequiresPermissions("entrust:entrustRegister:edit")
	@RequestMapping(value = "delete")
	public String delete(EntrustRegister entrustRegister, RedirectAttributes redirectAttributes) {
		entrustRegisterService.delete(entrustRegister);
		addMessage(redirectAttributes, "删除保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/entrust/entrustRegister/?repage";
	}

	@RequestMapping(value="readcard")
	public String card(HttpServletRequest request, HttpServletResponse response, Model model){
		String number=null;
		ReadCard t = new ReadCard();
		for (int i = 0; i < 10000; i++) {
			number=t.ss();
			if(number!=null&&!number.equals("")){
				break;
			}
		}

		SpecimenXueka specimenXueka=new SpecimenXueka();
		specimenXueka.setXuekaId(number);
		String registerId=null;
		SpecimenXueka xueka= specimenXuekaService.get(number);
		registerId=xueka.getMaterialRegisterItemId();
		EntrustRegister entrustRegister=new EntrustRegister();
		entrustRegister.setCode(registerId);
		Page<EntrustRegister> page = entrustRegisterService.findPage(new Page<EntrustRegister>(request, response), entrustRegister); 
		model.addAttribute("page", page);
		return "modules/entrust/entrustRegisterList";
	}
	
	//图谱
	@RequestMapping(value="map")
	public String map(EntrustRegister entrustRegister,Model model,Mapping mapping){
		if(mappingDao.findEntrsut(entrustRegister.getId())!=null){
			mapping=mappingDao.findEntrsut(entrustRegister.getId());
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
		}
		return "modules/entrust/mappingForm";
	}
	//流转单
	@RequestMapping(value="sheeet")
	public String sheet(EntrustRegister entrustRegister,Model model,EntrustFlowSheet entrustFlowSheet){
		if(entrustFlowSheetDao.findEntrust(entrustRegister.getId())!=null){
			entrustFlowSheet=entrustFlowSheetDao.findEntrust(entrustRegister.getId());
		}else{
			entrustFlowSheet=new EntrustFlowSheet();
		}
		model.addAttribute("entrustId",entrustRegister.getId());
		model.addAttribute("entrustFlowSheet", entrustFlowSheet);
		return "modules/entrust_flow_sheet/entrust/entrustFlowSheetForm";
	}
	
	
	//执业证
	@RequestMapping(value="licensed")
	public String licensed(EntrustRegister entrustRegister,Model model,Licensed licensed){
		List<String> photos=new ArrayList<String>();
	    List<Licensed>licenseds=licensedDao.findEntrust (entrustRegister.getId());
	    if(licenseds!=null){
		    for (int i = 0; i <licenseds.size(); i++) {
		    	String photo= systemService.getUser(licenseds.get(0).getUser().getId()).getPhoto();
		    	String []d=photo.split("\\|");
				for (String pic : d) {
					 if(pic.equals("")){
					 }else{
						 photos.add(pic);
					 }
				}
			}
	    }
	    model.addAttribute("photos", photos);
		model.addAttribute("licenseds", licenseds);
	
		return "modules/entrust/entrustLicensed";                                                                         
	}
	
	//收费凭据
	@RequestMapping(value="chargeCredentials")
	public String chargeCredentials(EntrustRegister entrustRegister,Model model,EntrustChargeCredentials entrustChargeCredentials ){
		if(entrustChargeCredentialsDao.findEntrust(entrustRegister.getId())!=null){
			entrustChargeCredentials=entrustChargeCredentialsDao.findEntrust(entrustRegister.getId());
		}else{
			entrustChargeCredentials=new EntrustChargeCredentials();
		}
		model.addAttribute("entrustId",entrustRegister.getId());
		
		model.addAttribute("entrustChargeCredentials", entrustChargeCredentials);
		return "modules/entrust/entrustChargeCredentialsForm";
	}
	
	
	
	@RequestMapping(value="export")
	public String export(){





		return null;
	}

}