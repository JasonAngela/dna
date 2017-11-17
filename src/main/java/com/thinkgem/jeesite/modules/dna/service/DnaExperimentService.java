/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.service;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DnaDataParser;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.dna.dao.DnaBoardDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaBoardJggDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaExperimentBoardDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaExperimentDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaExperimentImportDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaExperimentSpecimenDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaExperimentStrDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaPiResultDao;
import com.thinkgem.jeesite.modules.dna.dao.DnaPiResultItemDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaBoard;
import com.thinkgem.jeesite.modules.dna.entity.DnaBoardJgg;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperiment;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperimentBoard;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperimentImport;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperimentSpecimen;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperimentStr;
import com.thinkgem.jeesite.modules.dna.entity.DnaExport;
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneFrequency;
import com.thinkgem.jeesite.modules.dna.entity.DnaPiResult;
import com.thinkgem.jeesite.modules.dna.entity.DnaPiResultItem;
import com.thinkgem.jeesite.modules.dna.entity.DnaSpeIteam;
import com.thinkgem.jeesite.modules.dna.entity.ParentageTestingEntity;
import com.thinkgem.jeesite.modules.entrust.dao.EntrustAbstractsDao;
import com.thinkgem.jeesite.modules.entrust.dao.EntrustRegisterDao;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustAbstracts;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;
import com.thinkgem.jeesite.modules.entrust.service.EntrustRegisterService;
import com.thinkgem.jeesite.modules.entrust.service.GeneFrequencyStorage;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialRegisterItemDao;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegisterItem;
import com.thinkgem.jeesite.modules.sys.service.SysCodeRuleService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.util.CollectionUtils;

/**
 * dna试验Service
 * @author zhuguli
 * @version 2017-06-11
 */
@Service
@Transactional(readOnly = true)
public class DnaExperimentService extends CrudService<DnaExperimentDao, DnaExperiment> {
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private SysCodeRuleService codeRuleService;
	@Autowired
	private DnaExperimentImportDao dnaExperimentImportDao;
	@Autowired
	private DnaExperimentSpecimenDao dnaExperimentSpecimenDao;
	@Autowired
	private DnaExperimentBoardDao dnaExperimentBoardDao;
	@Autowired
	private DnaBoardDao dnaBoardDao;
	@Autowired
	private DnaBoardJggDao boardJggDao;
	@Autowired
	private EntrustAbstractsDao entrustAbstractsDao;
	@Autowired
	private DnaExperimentStrDao dnaExperimentStrDao;
	@Autowired
	private SpecimenMaterialRegisterItemDao specimenMaterialRegisterItemDao;
	@Autowired
	private EntrustRegisterDao entrustRegisterDao;
	@Autowired
	private EntrustRegisterService entrustRegisterService;
	@Autowired
	private GeneFrequencyStorage frequencyStorage;
	@Autowired
	private DnaPiResultService dnaPiResultService;
	@Autowired
	private DnaPiResultDao dnaPiResultDao;
	@Autowired
	private DnaPiResultItemDao dnaPiResultItemDao;
	public DnaExperiment get(String id) {
		DnaExperiment dnaExperiment = super.get(id);
		if(dnaExperiment!=null){
			dnaExperiment.setDnaExperimentImportList(dnaExperimentImportDao.findList(new DnaExperimentImport(dnaExperiment)));
			dnaExperiment.setDnaExperimentSpecimenList(dnaExperimentSpecimenDao.findList(new DnaExperimentSpecimen(dnaExperiment)));
		}
		return dnaExperiment;
	}

	public List<DnaExperiment> findList(DnaExperiment dnaExperiment) {
		return super.findList(dnaExperiment);
	}

	public Page<DnaExperiment> findPage(Page<DnaExperiment> page, DnaExperiment dnaExperiment) {
		return super.findPage(page, dnaExperiment);
	}

	@Transactional(readOnly = false)
	public void save(DnaExperiment dnaExperiment) {
		String code = codeRuleService.generateCode("dna_experiment_code");
		dnaExperiment.setCode(code);
		super.save(dnaExperiment);
		for (DnaExperimentSpecimen dnaExperimentSpecimen : dnaExperiment.getDnaExperimentSpecimenList()){
			if (DnaExperimentSpecimen.DEL_FLAG_NORMAL.equals(dnaExperimentSpecimen.getDelFlag())){
				if (StringUtils.isBlank(dnaExperimentSpecimen.getId())){
					dnaExperimentSpecimen.setExperiment(dnaExperiment);
					dnaExperimentSpecimen.preInsert();
					dnaExperimentSpecimenDao.insert(dnaExperimentSpecimen);
				}else{
					dnaExperimentSpecimen.preUpdate();
					dnaExperimentSpecimenDao.update(dnaExperimentSpecimen);
				}
			}else{
				dnaExperimentSpecimenDao.delete(dnaExperimentSpecimen);
			}
		}
		//auto relation board
		if(StringUtils.isNotEmpty(dnaExperiment.getBoardId())){
			DnaBoard board = dnaBoardDao.get(dnaExperiment.getBoardId());
			DnaExperimentBoard experimentBoard = new DnaExperimentBoard();
			experimentBoard.preInsert();
			experimentBoard.setBoardCode(board.getCode());
			experimentBoard.setExperiment(dnaExperiment);
			dnaExperimentBoardDao.insert(experimentBoard);
		}
		// 启动流程
		actTaskService.startProcess(ActUtils.PD_DNA_APPRAISAL[0], ActUtils.PD_DNA_APPRAISAL[1], dnaExperiment.getId(), dnaExperiment.getCode());
	}

	@Transactional(readOnly = false)
	public void delete(DnaExperiment dnaExperiment) {
		super.delete(dnaExperiment);
		dnaExperimentImportDao.delete(new DnaExperimentImport(dnaExperiment));
		dnaExperimentSpecimenDao.delete(new DnaExperimentSpecimen(dnaExperiment));
	}
	/**
	 * select board by experimentId
	 * @param id
	 * @return
	 */
	public DnaBoard getLastBoard(String id) {
		List<DnaExperimentBoard> ebList = dnaExperimentBoardDao.findByParentId(id);
		if(ebList.isEmpty()){
			return null;
		}
		DnaBoard board = dnaBoardDao.getByCode(ebList.get(0).getBoardCode());
		board.setDnaBoardJggList(boardJggDao.findList(new DnaBoardJgg(board)));
		return board;
	}

	@Transactional(readOnly = false)
	public void saveBoard(DnaExperiment dnaExperiment) {
		DnaBoard board = dnaExperiment.getBoard();
		List<DnaExperimentSpecimen> specimens = dnaExperiment.getDnaExperimentSpecimenList();
		Map<String,DnaExperimentSpecimen>specimensMap = new HashMap<String, DnaExperimentSpecimen>();
		for(DnaExperimentSpecimen specimen:specimens){
			specimensMap.put(specimen.getSpecimenCode(), specimen);
		}
		//dna_experiment_specimen
		for(DnaBoardJgg jgg:board.getDnaBoardJggList()){
			if(StringUtils.isNotBlank(jgg.getSpecimenCode())){
				DnaExperimentSpecimen dnaExperimentSpecimen =  specimensMap.get(jgg.getSpecimenCode());
				dnaExperimentSpecimen.setBoard(board);
				dnaExperimentSpecimen.setExperiment(dnaExperiment);
				dnaExperimentSpecimen.setHang(jgg.getHang());
				dnaExperimentSpecimen.setLie(jgg.getLie());
				dnaExperimentSpecimen.setSpecimenCode(jgg.getSpecimenCode());
				dnaExperimentSpecimenDao.update(dnaExperimentSpecimen);
				DnaBoardJgg entity = boardJggDao.get(jgg.getId());
				entity.setSpecimenCode(jgg.getSpecimenCode());
				entity.setUpdateDate( new Date());
				boardJggDao.update(entity);
			}
		}
		// 完成流程任务
		Map<String, Object> vars = Maps.newHashMap();
		actTaskService.complete(dnaExperiment.getAct().getTaskId(), dnaExperiment.getAct().getProcInsId(), dnaExperiment.getAct().getComment(), dnaExperiment.getCode(), vars);

	}
	@Transactional(readOnly = false)
	public void importData(DnaExperiment dnaExperiment) {
		List<DnaExperimentSpecimen> dnaExperimentSpecimenList = dnaExperiment.getDnaExperimentSpecimenList();
		List<Map<String,String>>data = DnaDataParser.parse(dnaExperiment.getImportDataAddress());
		//删除所有数据
		dnaExperimentStrDao.delete(new DnaExperimentStr(dnaExperiment));
		//添加数据
		for(Map<String,String>item:data){
			String code = item.get("Sample Name");//样品编码
			if(isExist(dnaExperimentSpecimenList,code)){
				DnaExperimentStr str = new DnaExperimentStr();
				str.setSpecimenCode(code);
				str.setX(item.get("Allele 1"));
				str.setY(item.get("Allele 2"));
				str.setExperiment(dnaExperiment);
				str.setGeneLoci(item.get("Marker"));
				str.preInsert();
				dnaExperimentStrDao.insert(str);
			}
		} 
		// 完成流程任务
		Map<String, Object> vars = Maps.newHashMap();
		actTaskService.complete(dnaExperiment.getAct().getTaskId(), dnaExperiment.getAct().getProcInsId(), dnaExperiment.getAct().getComment(), dnaExperiment.getCode(), vars);

	}

	private boolean isExist(List<DnaExperimentSpecimen> dnaExperimentSpecimenList, String code) {
		for(DnaExperimentSpecimen specimen:dnaExperimentSpecimenList){
			if(specimen.getSpecimenCode().equals(code)){
				return true;
			}
		}
		return false;
	}
	@Transactional(readOnly = false)
	public void autoActivate(DnaExperiment dnaExperiment) {
		Set<String>registerIdSet = new HashSet<String>();
		for(DnaExperimentSpecimen specimen:dnaExperiment.getDnaExperimentSpecimenList()){
			if(hasResult(specimen)){
				//修改物证摘要id
				String registerId = updateAbstractsExperimentId(dnaExperiment.getId(), specimen.getSpecimenCode());
				registerIdSet.add(registerId);
			}
		}
		for(String registerId:registerIdSet){
			//激活相应的委托单流程
			EntrustRegister register = entrustRegisterDao.get(registerId);
			if(isCompleted(register)){
				singal(register);
			}
		}
	}
	private boolean hasResult(DnaExperimentSpecimen specimen) {
		List<DnaExperimentStr> list = dnaExperimentStrDao.getByExperimentIdAndCode(specimen.getExperiment().getId(), specimen.getSpecimenCode());
		if(list.isEmpty()){
			return false;
		}
		return true;
	}

	//判断是否完成了检验
	private boolean isCompleted(EntrustRegister register) {
		List<EntrustAbstracts>notExperimentList = entrustAbstractsDao.listNotExperiment(register.getId());
		if(notExperimentList.isEmpty()){
			return true;
		}
		return false;
	}

	private String updateAbstractsExperimentId(String dnaExperimentId, String  specimenCode) {

		EntrustAbstracts abstracts=null;
		List<SpecimenMaterialRegisterItem> materialRegisterItems =  specimenMaterialRegisterItemDao.selectByCode(specimenCode);
		if(materialRegisterItems == null){
			throw new RuntimeException("不存在的样品编码:"+specimenCode);
		}

		abstracts = entrustAbstractsDao.get(materialRegisterItems.get(0).getAbstracts());
		abstracts.setDnaExperimentId(dnaExperimentId);
		abstracts.setSpecimenCode(materialRegisterItems.get(0).getCode());
		entrustAbstractsDao.update(abstracts);



		return abstracts.getRegister().getId();
	}
	@Transactional(readOnly = false)
	private void singal(EntrustRegister entrustRegister) {

		Execution execution1 = actTaskService.getProcessEngine().getRuntimeService()  
				.createExecutionQuery()  
				.processInstanceId(entrustRegister.getProcInsId())//流程实例ID  
				.activityId("waitImport")//当前活动的名称  
				.singleResult(); 
		if(execution1!=null){
			actTaskService.getProcessEngine().getRuntimeService().signal(execution1.getId());
		}
		//自动计算kpi
		entrustRegisterService.autoCalculatePi(entrustRegister);
	}
	@Transactional(readOnly = false)
	public void batchImportData(DnaExperiment dnaExperiment) {
		save(dnaExperiment);
		List<Map<String,String>>data = DnaDataParser.parse(dnaExperiment.getImportDataAddress());
		Set<String>specimenCodeList = new TreeSet<String>();
		//添加数据
		for(Map<String,String>item:data){
			String code = item.get("Sample Name");//样品编码
			DnaExperimentStr str = new DnaExperimentStr();
			str.setSpecimenCode(code);
			str.setX(item.get("Allele 1"));
			str.setY(item.get("Allele 2"));
			str.setExperiment(dnaExperiment);
			str.setGeneLoci(item.get("Marker"));
			str.preInsert();
			dnaExperimentStrDao.insert(str);
			specimenCodeList.add(code);
		}
		for(String code:specimenCodeList){
			DnaExperimentSpecimen scpecimen = new DnaExperimentSpecimen();
			scpecimen.setSpecimenCode(code);
			scpecimen.setExperiment(dnaExperiment);
			scpecimen.preInsert();
			dnaExperimentSpecimenDao.insert(scpecimen);
		}
	}
	@Transactional(readOnly = false)
	public void  calculate(List<DnaSpeIteam>dnaSpeIteams,String experimentId ) {
		// TODO Auto-generated method stub
		dnaPiResultItemDao.delete();
		dnaPiResultDao.delete();
		List<String>parentList = new ArrayList<String>();
		List<String>matherList=new ArrayList<String>();
		List<String>childrenList = new ArrayList<String>();
		String one=null;
		
		for (DnaSpeIteam dnaSpeIteam : dnaSpeIteams) {
			one=dnaSpeIteam.getParrens();
			if(StringUtils.isNotEmpty(one)){
				if(one.contains("F")){
					parentList.add(dnaSpeIteam.getSpecimen());
				}
				if(one.contains("M")){
					matherList.add(dnaSpeIteam.getSpecimen());
				}
				if(one.contains("C")){
					childrenList.add(dnaSpeIteam.getSpecimen());
				}
			}
		}
		//交叉生成结果
		for(String parent:parentList){
			List<DnaExperimentStr> parentStrList = dnaExperimentStrDao.getByExperimentIdAndCode(experimentId,parent);
			BigDecimal cpi = new BigDecimal(1);
			for(String child:childrenList){
				DnaPiResult dnaPiResult = new DnaPiResult();
				dnaPiResult.setParentCode(parent);
				dnaPiResult.setChildCode(child);
				List<DnaPiResultItem> dnaPiResultItemList = new ArrayList<DnaPiResultItem>();
				List<DnaExperimentStr> childStrList = dnaExperimentStrDao.getByExperimentIdAndCode(experimentId,child);
				//保存明细
          				for(DnaExperimentStr parentStr:parentStrList ){
							if(parentStr.getGeneLoci().toUpperCase().equals("AMEL")){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
								continue;
							}
							DnaExperimentStr childStr = getChildStr(parentStr,childStrList);
							if(childStr == null){
								continue;
							}
					ParentageTestingEntity p = new ParentageTestingEntity("父亲", 
							new DnaGeneFrequency(parentStr.getXValue(),frequencyStorage.getProb(parentStr.getGeneLoci()+"_"+parentStr.getXValue())),
							new DnaGeneFrequency(parentStr.getYValue(),frequencyStorage.getProb(parentStr.getGeneLoci()+"_"+parentStr.getYValue())));

  					ParentageTestingEntity c = new ParentageTestingEntity("小孩", 
							new DnaGeneFrequency(childStr.getXValue(),frequencyStorage.getProb(childStr.getGeneLoci()+"_"+childStr.getXValue())),
							new DnaGeneFrequency(childStr.getYValue(),frequencyStorage.getProb(childStr.getGeneLoci()+"_"+childStr.getYValue())));

					Object[] result = ParentageTesting.duos(c, p);//0 公式
					DnaPiResultItem dnaPiResultItem = new DnaPiResultItem();
					if(!parentStr.getGeneLoci().toUpperCase().equals("AMEL")){
						dnaPiResultItem.setGeneLoci(parentStr.getGeneLoci());
					}
					dnaPiResultItem.setPi((Double) result[1]);
					dnaPiResultItem.setFormula(result[0]+"    ");
					dnaPiResultItem.setpProb((Double)result[3]);//p值
					dnaPiResultItem.setqProb((Double)result[4]);//q值
					dnaPiResultItem.setMin((Double)result[5]);//n值
 					dnaPiResultItemList.add(dnaPiResultItem);
                    cpi = cpi.multiply(new BigDecimal(dnaPiResultItem.getPi()));
				}
				dnaPiResult.setCpi(cpi);
				dnaPiResult.setRcp(cpi.divide(cpi.add(new BigDecimal(1)),10,BigDecimal.ROUND_DOWN));
				dnaPiResult.setDnaPiResultItemList(dnaPiResultItemList);
				dnaPiResultService.save(dnaPiResult);

			}
		}                                                                                                                                                                                                                      
		for(String parent:matherList){
			List<DnaExperimentStr> parentStrList = dnaExperimentStrDao.getByExperimentIdAndCode(experimentId,parent);
			BigDecimal cpi = new BigDecimal(1);
			for(String child:childrenList){
				DnaPiResult dnaPiResult = new DnaPiResult();
				dnaPiResult.setParentCode(parent);
				dnaPiResult.setChildCode(child);
				List<DnaPiResultItem> dnaPiResultItemList = new ArrayList<DnaPiResultItem>();
				List<DnaExperimentStr> childStrList = dnaExperimentStrDao.getByExperimentIdAndCode(experimentId,child);
				//保存明细
				for(DnaExperimentStr parentStr:parentStrList ){
					
					if(parentStr.getGeneLoci().toUpperCase().equals("AMEL")){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
						continue;
					}
					DnaExperimentStr childStr = getChildStr(parentStr,childStrList);
					if(childStr == null){
						continue;
					}
					
					ParentageTestingEntity p = new ParentageTestingEntity("母亲", 
							new DnaGeneFrequency(parentStr.getXValue(),frequencyStorage.getProb(parentStr.getGeneLoci()+"_"+parentStr.getXValue())),
							new DnaGeneFrequency(parentStr.getYValue(),frequencyStorage.getProb(parentStr.getGeneLoci()+"_"+parentStr.getYValue())));

					ParentageTestingEntity c = new ParentageTestingEntity("小孩", 
							new DnaGeneFrequency(childStr.getXValue(),frequencyStorage.getProb(childStr.getGeneLoci()+"_"+childStr.getXValue())),
							new DnaGeneFrequency(childStr.getYValue(),frequencyStorage.getProb(childStr.getGeneLoci()+"_"+childStr.getYValue())));

					Object[] result = ParentageTesting.duos(c, p);//0 公式
					DnaPiResultItem dnaPiResultItem = new DnaPiResultItem();
					dnaPiResultItem.setGeneLoci(parentStr.getGeneLoci());
					dnaPiResultItem.setPi((Double) result[1]);
					dnaPiResultItem.setFormula(result[0]+"    ");
					dnaPiResultItem.setpProb((Double)result[3]);//p值
					dnaPiResultItem.setqProb((Double)result[4]);//q值
					dnaPiResultItem.setMin((Double)result[5]);//n值
					dnaPiResultItemList.add(dnaPiResultItem);
					cpi = cpi.multiply(new BigDecimal(dnaPiResultItem.getPi()));
				}
				dnaPiResult.setCpi(cpi);
				dnaPiResult.setRcp(cpi.divide(cpi.add(new BigDecimal(1)),10,BigDecimal.ROUND_DOWN));
				dnaPiResult.setDnaPiResultItemList(dnaPiResultItemList);
				dnaPiResultService.save(dnaPiResult);
			}
		}
	}
	//获得对应的子值
	private DnaExperimentStr getChildStr(DnaExperimentStr parentStr, List<DnaExperimentStr> childStrList) {
		for(DnaExperimentStr child:childStrList){
			if(child.getGeneLoci().equals(parentStr.getGeneLoci())){
   				return child;
			}
		}
		return null;
	}
	
	
	
	
	public void export(List<Map<String,Map<String,String>>> strMapList,List<DnaExperimentStr> daExperimentStrs,HttpServletResponse response,List<DnaSpeIteam> dnaSpeIteams) {
		WritableWorkbook  book;
        try {
        	if(CollectionUtils.isEmpty(dnaSpeIteams))
        	{
				//啥也没选
				throw new Exception("未选择任何编码数据");
			}

        	OutputStream os = response.getOutputStream();// 取得输出流     
        	response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(daExperimentStrs.get(0).getSpecimenCode().getBytes("GB2312"),
					"iso8859_1") + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			book=Workbook.createWorkbook(os);
			WritableSheet sheet = book.createSheet("受理", 0);
			List<String> bn = new ArrayList<String>();
			bn.add("基因座");
			for(DnaSpeIteam dnaItem:dnaSpeIteams){
				bn.add("案件编号-"+dnaItem.getSpecimen());
			}
			bn.add("基因值");
			bn.add("使用公式");
			bn.add("pc值");
			bn.add("pd值");
			bn.add("n值");
            String[] columns =  bn.toArray(new String[bn.size()]);
            for (int i = 0; i < columns.length; i++) {
            	sheet.addCell(new Label(i, 0, columns[i]));
				sheet.setColumnView(i,20);
            }

          /*  List<String>keys=new ArrayList<String>();
            Map<String, String>map1=new HashMap<String, String>();
			for(Map<String,Map<String,String>> strMap: strMapList){
					for (String key : strMap.keySet()) {
						keys.add(key);
						map1=strMap.get(key);
				}
			}

			for (String key1 : keys) {
               	keys.add(key1);
               	keys.add(map1.get(key1));
             }

         List<DnaExport>keys1=new ArrayList<DnaExport>();
			for(Map<String,Map<String,String>> strMap: strMapList){
				for (String key : strMap.keySet()){
					DnaExport dnaExport=new DnaExport();
					dnaExport.setJyz(key);
					keys1.add(dnaExport);
				}
			}

            for (int i = 0; i <keys1.size(); i++) {
            	 sheet.addCell(new Label(0, i+1,keys1.get(i).getJyz()));
			}*/

            // 写入数据并关闭文件
            book.write();
            book.close();
            os.flush();  
            os.close();  
            os=null;  
            response.flushBuffer();  
            PrintWriter out = response.getWriter();
            out.flush();
            out.close(); 
            
        } catch (Exception e) {
        	e.printStackTrace();            
        }
}
}

