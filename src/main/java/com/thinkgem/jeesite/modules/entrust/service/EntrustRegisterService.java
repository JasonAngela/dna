/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.dna.dao.DnaExperimentStrDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperimentStr;
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneFrequency;
import com.thinkgem.jeesite.modules.dna.entity.DnaPiResult;
import com.thinkgem.jeesite.modules.dna.entity.DnaPiResultItem;
import com.thinkgem.jeesite.modules.dna.entity.ParentageTestingEntity;
import com.thinkgem.jeesite.modules.dna.service.DnaPiResultService;
import com.thinkgem.jeesite.modules.dna.service.ParentageTesting;
import com.thinkgem.jeesite.modules.entrust.dao.EntrustAbstractsDao;
import com.thinkgem.jeesite.modules.entrust.dao.EntrustRegisterDao;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustAbstracts;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;
import com.thinkgem.jeesite.modules.sys.service.SysCodeRuleService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 委托登记Service
 * @author zhuguli
 * @version 2017-05-12
 */
@Service
@Transactional(readOnly = true)
public class EntrustRegisterService extends CrudService<EntrustRegisterDao, EntrustRegister> {
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private EntrustAbstractsDao entrustAbstractsDao;
	@Autowired
	private SysCodeRuleService codeRuleService;
	@Autowired
	private DnaPiResultService dnaPiResultService;
	@Autowired
	private DnaExperimentStrDao dnaExperimentStrDao;
	@Autowired
	private GeneFrequencyStorage frequencyStorage;
	public EntrustRegister get(String id) {
		EntrustRegister entrustRegister = super.get(id);
		entrustRegister.setEntrustAbstractsList(entrustAbstractsDao.findList(new EntrustAbstracts(entrustRegister)));
		return entrustRegister;
	}

	public List<EntrustRegister> findList(EntrustRegister entrustRegister) {
		return super.findList(entrustRegister);
	}

	public Page<EntrustRegister> findPage(Page<EntrustRegister> page, EntrustRegister entrustRegister) {
		return super.findPage(page, entrustRegister);
	}

	@Transactional(readOnly = false)
	public void save(EntrustRegister entrustRegister) {
		boolean isNew =  entrustRegister.getIsNewRecord();
		if(isNew){
			entrustRegister.setStatus("2");
			String code = codeRuleService.generateCode("entrust_register_code");
			entrustRegister.setCode(code);
			String caseCode = codeRuleService.generateCode("case_dna_code");
			entrustRegister.setCaseCode(caseCode);
		}
		super.save(entrustRegister);
		for (EntrustAbstracts entrustAbstracts : entrustRegister.getEntrustAbstractsList()){
			if (entrustAbstracts.getId() == null){
				continue;
			}
			if (EntrustAbstracts.DEL_FLAG_NORMAL.equals(entrustAbstracts.getDelFlag())){
				if (StringUtils.isBlank(entrustAbstracts.getId())){
					entrustAbstracts.setRegister(entrustRegister);
					entrustAbstracts.preInsert();
					entrustAbstractsDao.insert(entrustAbstracts);
				}else{
					entrustAbstracts.preUpdate();
					entrustAbstractsDao.update(entrustAbstracts);
				}
			}else{
				entrustAbstractsDao.delete(entrustAbstracts);
			}
		}
		if(isNew){
			// 启动流程
			actTaskService.startProcess(ActUtils.PD_APPRAISAL[0], ActUtils.PD_APPRAISAL[1], entrustRegister.getId(), entrustRegister.getCode());
		}
	}

	@Transactional(readOnly = false)
	public void delete(EntrustRegister entrustRegister) {
		super.delete(entrustRegister);
		entrustAbstractsDao.delete(new EntrustAbstracts(entrustRegister));
	}

	@Transactional(readOnly = false)
	public void autoCalculatePi(EntrustRegister entrustRegister) {
		List<EntrustAbstracts>parentList = new ArrayList<EntrustAbstracts>();
		List<EntrustAbstracts>childrenList = new ArrayList<EntrustAbstracts>();
		List<EntrustAbstracts> abstractsList = entrustAbstractsDao.findList(new EntrustAbstracts(entrustRegister));
		for(EntrustAbstracts abstracts:abstractsList){
			switch (Integer.parseInt(abstracts.getAppellation())) {
			case 1:
				parentList.add(abstracts);
				break;
			case 2:
				parentList.add(abstracts);
				break;
			case 4:
				parentList.add(abstracts);
				break;
			case 3:
				childrenList.add(abstracts);
				break;
			default:
				break;
			}
		}

		//交叉生成结果
		for(EntrustAbstracts parent:parentList){
			List<DnaExperimentStr> parentStrList = dnaExperimentStrDao.getByExperimentIdAndAbstractsId(parent.getDnaExperimentId(),parent.getId());
			BigDecimal cpi = new BigDecimal(1);
			for(EntrustAbstracts child:childrenList){
				DnaPiResult dnaPiResult = new DnaPiResult();
				dnaPiResult.setParentCode("样本"+parent.getSpecimenCode()+"("+parent.getClientName()+")");
				dnaPiResult.setChildCode("样本"+child.getSpecimenCode()+"("+child.getClientName()+")");
				dnaPiResult.setRegister(entrustRegister );
				List<DnaPiResultItem> dnaPiResultItemList = new ArrayList<DnaPiResultItem>();

				List<DnaExperimentStr> childStrList = dnaExperimentStrDao.getByExperimentIdAndAbstractsId(child.getDnaExperimentId(),child.getId());
				//保存明细
 				for(DnaExperimentStr parentStr:parentStrList ){
 					if(parentStr.getGeneLoci().toUpperCase().equals("AMEL")){
						continue;
					}
 					DnaExperimentStr childStr = getChildStr(parentStr,childStrList);
					if(childStr == null){
						continue;
					}
  					ParentageTestingEntity p = new ParentageTestingEntity(parent.getAppellation(), 
 							new DnaGeneFrequency(parentStr.getXValue(),frequencyStorage.getProb(parentStr.getGeneLoci()+"_"+parentStr.getXValue())),
 							new DnaGeneFrequency(parentStr.getYValue(),frequencyStorage.getProb(parentStr.getGeneLoci()+"_"+parentStr.getYValue())));

  					ParentageTestingEntity c = new ParentageTestingEntity(child.getAppellation(), 
  							new DnaGeneFrequency(childStr.getXValue(),frequencyStorage.getProb(childStr.getGeneLoci()+"_"+childStr.getXValue())),
   							new DnaGeneFrequency(childStr.getYValue(),frequencyStorage.getProb(childStr.getGeneLoci()+"_"+childStr.getYValue())));

  					Object[] result = ParentageTesting.duos(c, p);//0 公式

   					DnaPiResultItem dnaPiResultItem = new DnaPiResultItem();
      				dnaPiResultItem.setGeneLoci(parentStr.getGeneLoci());
  					dnaPiResultItem.setPi((Double) result[1]);
 					dnaPiResultItem.setFormula(result[0]+"    "+result[2]);
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
/*	}
	*
	 * 自动归档可以做的事情
	 * @param entrustRegister*/
	public void autoFile(EntrustRegister entrustRegister)throws Exception {
			System.out.println("自动归档..............");
			
			//entrustRegister=entrustRegisterService.get("5ed0f569f1804a01a607bf8b0a08f0ac");
			try {
				File  folder  = new File("D:\\huyun\\"+entrustRegister.getCode());
				if(!folder.exists()){
					folder.mkdir();
				}
			//String ur	request.getContextPath();
				List<EntrustAbstracts> entrustAbstractsList = entrustRegister.getEntrustAbstractsList();
				for (EntrustAbstracts entrustAbstracts : entrustAbstractsList){
					entrustAbstracts.getAppellation();
				}
				exportWord("D:\\huyun\\"+entrustRegister.getCode()+"\\"+entrustRegister.getClientName()+".doc", entrustRegister);
				/*System.out.println("ClientPic:"+entrustAbstracts.getClientPic());
					
					File srcFile = new File("D:\\huyun\\Desert.jpg");
					File destFile = new File(folder.getAbsolutePath()+"\\"+entrustAbstracts.getClientName()+".jpg");
					FileUtils.copyFile(srcFile, destFile );*/
				//jeesite/userfiles/1/files/entrust/entrustRegister/2017/08/Desert.jpg
			} catch (IOException e) {
				e.printStackTrace();
			}
		}   


	//委托列表
			public void exportWord( String path,EntrustRegister entrustRegister)
					throws Exception {
				List<EntrustAbstracts> entrustAbstractsList = entrustRegister.getEntrustAbstractsList();
		//entrustRegister=entrustRegisterService.get("5ed0f569f1804a01a607bf8b0a08f0ac");
				
				String type=null;	
					if(entrustRegister.getType().equals("1")){
						type="DNA亲子鉴定";
					}else if(entrustRegister.getType().equals("2")){
						type="DNA胎儿亲子鉴定";
					}else if(entrustRegister.getType().equals("3")){
						type="DNA亲缘鉴定";
					}else if(entrustRegister.getType().equals("4")){
						type="DNA个体识别";
					}
					
					
					
				String authorizeNotification=null;
				if(entrustRegister.getAuthorizeNotification()!=null){
					if(entrustRegister.getAuthorizeNotification()==true){
						authorizeNotification="☑是  □否";
					}else{
						authorizeNotification="□是  ☑否";
					}
				}else{
					authorizeNotification="□是  □否";
				}
				
			    String whether=null;
			    if(entrustRegister.getWhether()!=null){ 
			    if(entrustRegister.getWhether().equals("1")){
			    	  whether="个人鉴定";
			      }else if(entrustRegister.getWhether().equals("2")){
			    	  whether="司法鉴定";
			      }else if(entrustRegister.getWhether().equals("3")){
			    	  whether="个体识别";
			      }else if(entrustRegister.getWhether().equals("4")){
			    	  whether="亲缘鉴定";
			      }else if(entrustRegister.getWhether().equals("5")){
			    	  whether="其他";
			      }
			    }else{
			    	  whether="个人鉴定";
			    }
			    String aboutMaterials=null;
			    if(entrustRegister.getAboutMaterials()!=null){
			     if(entrustRegister.getAboutMaterials().equals("1")){
			    	 aboutMaterials="血痕";
			     }
			    }  
				    String chargeway=null;
				    if(entrustRegister.getChargeway()!=null){
					    if(entrustRegister.getChargeway().equals("1")){
					    	chargeway="☑现金  □转账";
					    }else{
					    	chargeway="□现金  ☑转账";
					    }
				    }else{
				    	chargeway="□现金  □转账";
				    }
				/*+"<input type='checkbox'  name='sendWayq' value='1' /> 自取		<br/>"
				+"<input type='checkbox'  name='sendWayq' value='2' /> 邮递    &nbsp;&nbsp;地址:<br/>"
				+"<input type='checkbox'  name='sendWayq' value='3' /> 其他方式（说明） 	&nbsp;&nbsp;<br/>"*/
			    String sendWay=null;
			    if(entrustRegister.getSendWay()!=null){
				    if(entrustRegister.getSendWay().equals("1")){
				    		sendWay="☑自取   <br/>   □邮递     地址<br/>   □其他方式（说明） <br/>" ;
				    }else if(entrustRegister.getSendWay().equals("2")){
				    	    sendWay="□自取   <br/>   ☑邮递     地址<br/>   □其他方式（说明） <br/>";
				    }else if(entrustRegister.getSendWay().equals("3")){
				    	    sendWay="□自取   <br/>  □邮递     地址<br/>   ☑其他方式（说明） <br/>";
				    }
			    }else{
			    	 sendWay="□自取   <br/>    □邮递     地址<br/>   □其他方式（说明）<br/>";
			    }
			  /*  +"<input type='checkbox'  name='materialDisposeq' value='1' /> 所有鉴定材料无须退换。	<br/>"
				+"<input type='checkbox'  name='materialDisposeq' value='2' /> 鉴定材料须完整，无损坏地退还委托人。	<br/>"
				+"<input type='checkbox'  name='materialDisposeq' value='3' /> 因鉴定需要，鉴定材料可能损坏、耗尽，导致无法完整退还。	<br/>"
				+"<input type='checkbox'  name='materialDisposeq' value='4' /> 对保管和使用鉴定材料的特殊要求:	 <br/>	"*/
			    String materialDisposeq=null;
			    if(entrustRegister.getMaterialDispose()!=null){
				    if(entrustRegister.getMaterialDispose().equals("1")){
				    	materialDisposeq="☑所有鉴定材料无须退换。	<br/> □鉴定材料须完整，无损坏地退还委托人。	<br/> □因鉴定需要，鉴定材料可能损坏、耗尽，导致无法完整退还。  <br/>□对保管和使用鉴定材料的特殊要求:	 <br/>";
				    }else if(entrustRegister.getMaterialDispose().equals("2")){
				    	materialDisposeq="□所有鉴定材料无须退换。	<br/> ☑鉴定材料须完整，无损坏地退还委托人。	<br/> □因鉴定需要，鉴定材料可能损坏、耗尽，导致无法完整退还。  <br/>□对保管和使用鉴定材料的特殊要求:	 <br/>";
				    }else if(entrustRegister.getMaterialDispose().equals("3")){
				    	materialDisposeq="□所有鉴定材料无须退换。	<br/> □鉴定材料须完整，无损坏地退还委托人。	<br/> ☑因鉴定需要，鉴定材料可能损坏、耗尽，导致无法完整退还。  <br/>□对保管和使用鉴定材料的特殊要求:	 <br/>";
				    }else if(entrustRegister.getMaterialDispose().equals("4")){
				    	materialDisposeq="□所有鉴定材料无须退换。	<br/> □鉴定材料须完整，无损坏地退还委托人。	<br/> □因鉴定需要，鉴定材料可能损坏、耗尽，导致无法完整退还。  <br/>☑对保管和使用鉴定材料的特殊要求:	 <br/>";
				    }
			    }else{
			    	materialDisposeq="□所有鉴定材料无须退换。	<br/> □鉴定材料须完整，无损坏地退还委托人。	<br/> □因鉴定需要，鉴定材料可能损坏、耗尽，导致无法完整退还。  <br/>□对保管和使用鉴定材料的特殊要求:	 <br/>";
			    }
				String remainingq=null;
				if(entrustRegister.getRemaining()!=null){
					if(entrustRegister.getRemaining().equals("1")){
						remainingq="☑委托人于周内自行取回。委托人未按时取回的，鉴定机构有权自行处理。<br/>□鉴定机构自行处理，如需发生处理费的，按有关收费标准或协商收取元处理费。<br/>   □其他方式:<br/>";
					}else if(entrustRegister.getRemaining().equals("2")){
						remainingq="□委托人于周内自行取回。委托人未按时取回的，鉴定机构有权自行处理。<br/>☑ 鉴定机构自行处理，如需发生处理费的，按有关收费标准或协商收取元处理费。<br/>   □其他方式:<br/>";
					}else if(entrustRegister.getRemaining().equals("3")){
						remainingq="□委托人于周内自行取回。委托人未按时取回的，鉴定机构有权自行处理。<br/>□鉴定机构自行处理，如需发生处理费的，按有关收费标准或协商收取元处理费。<br/>   ☑其他方式:<br/>";
					}
				}else{
					remainingq="□委托人于周内自行取回。委托人未按时取回的，鉴定机构有权自行处理。<br/>□鉴定机构自行处理，如需发生处理费的，按有关收费标准或协商收取元处理费。<br/>   □其他方式:<br/>";
				}
				String standardFee=null;;
				if(entrustRegister.getStandardFee()==null){
					standardFee="    ";
				}else{
					standardFee=String.valueOf(entrustRegister.getStandardFee());
				}
				
				
				String capital=null;
				if( entrustRegister.getCapital()==null){
					capital="   ";
				}else{
					capital=entrustRegister.getCapital();
				}
				
				String timelimit=null;
				
				if(entrustRegister.getTimeLimitReport()!=null){
					if(entrustRegister.getTimeLimitReport().equals("1")){
						timelimit="☑ "+entrustRegister.getClientName()+"之前完成鉴定，提交司法鉴定意见书。<br/>" +"□从该委托书生效之日起 "+ entrustRegister.getTimeLimitResult() +"个工作日内完成鉴定，提交司法鉴定意见书。<br/>";
					}else if(entrustRegister.getTimeLimitReport().equals("2")){
						timelimit="□"+entrustRegister.getClientName()+"之前完成鉴定，提交司法鉴定意见书。<br/>" +"☑从该委托书生效之日起 "+ entrustRegister.getTimeLimitResult() +"个工作日内完成鉴定，提交司法鉴定意见书。<br/>";
					}
				}else{
					timelimit="□"+entrustRegister.getClientName()+"之前完成鉴定，提交司法鉴定意见书。<br/>" +"□从该委托书生效之日起 "+ entrustRegister.getTimeLimitResult() +"个工作日内完成鉴定，提交司法鉴定意见书。<br/>";
				}
				try {
					//word内容
					String foreach ="";
					String gender="";
					String appellation="";
					String idType="";
					
					 
					for (EntrustAbstracts entrustAbstracts : entrustAbstractsList){
						if(entrustAbstracts.getGender()!=null){
							if(entrustAbstracts.getGender().equals("1")){
								gender="男";
							}else if(entrustAbstracts.getGender().equals("2")){
								gender="女";
							}else if(entrustAbstracts.getGender().equals("3")){
								gender="其它";
							}else if(entrustAbstracts.getGender().equals("")||entrustAbstracts.getGender()==null){
								gender="男";
							}
						}
						else{
							gender="无";
						}
						if(entrustAbstracts.getAppellation()!=null){
							if(entrustAbstracts.getAppellation().equals("1")){
								appellation="父亲";
							}else if(entrustAbstracts.getAppellation().equals("2")){
								appellation="母亲";
							}else if(entrustAbstracts.getAppellation().equals("3")){
								appellation="小孩";
							}else if(entrustAbstracts.getAppellation().equals("4")){
								appellation="其它";
							}
						}
							else{
							appellation="无";
						}
						
						if(entrustAbstracts.getIdType()!=null){
							if(entrustAbstracts.getIdType().equals("1")){
								idType="身份证";
							}else if(entrustAbstracts.getIdType().equals("2")){
								idType="户口";
							}else if(entrustAbstracts.getIdType().equals("3")){
								idType="驾照";
							}else if(entrustAbstracts.getIdType().equals("4")){
								idType="护照";
							}else if(entrustAbstracts.getIdType().equals("5")){
								idType="其它";
							}else if(entrustAbstracts.getIdType().equals("6")){
								idType="无";
							}
						}else{
							idType="无";
						}
						
						foreach+="<tr>"
							+"<td>"+entrustAbstracts.getClientName()+
								"</td>"
							+"<td>"+
							gender
							+"</td>"
							+"<td>"+
							appellation
							+"</td>"
							+"<td>"+
							entrustAbstracts.getBirthday()
							+"</td>"
							+"<td>"+
							idType
							+"						</td>"
							+"<td  colspan='2' style='width: 276px;'>"+
							entrustAbstracts.getIdNo()
							+"						</td>"
							+"				</tr>"
							;
					}
					String content="<html><body>"
							+"<div id='pagecontent' style='width: 649;'>"
							+"	"
							+"	<div id='a' class='o' style='display: none;'>"
							+"		<h1 align='center'>江西开元司法鉴定中心<br/>"
							+"		司法鉴定委托书</h1>"
							+"		<h6 style='margin-left: 730px;' id='lover1' >赣(景)[2017]物鉴字第800099号</h6>	"
							+"	</div>"
							+"		<table  style='width: 830px;'  width='830' align='center' height='730' class='inputTable'>"
							+"			<tr> "
							+"				<th colspan='2' style='width: 276px;'>"+"委托人"
							+ "</th>"
							+"				<td colspan='2' style='width: 276px;'>"+
							entrustRegister.getClientName()+ 
							"</td>"
							+"				<th colspan='2' style='width: 276px;'>委托人电话</th>"
							+"				<td colspan='2' style='width: 276px;'>"
							+entrustRegister.getClientTel()+
							"</td>"
							+"			</tr>"
							+"			<tr>"
							+"				<th colspan='2'>联系地址</th>"
							+"				<td colspan='2'>"
							+entrustRegister.getClientAddress()+
							 "</td>"
							+"				<th width='120' colspan='2'>联系电话</th>"
							+"				<td colspan='2'>"
							+entrustRegister.getClientZipcode()
							+"</td>"
							+"			</tr>"
							+"			<tr>"
							+"				<th colspan='2'>委托日期</th>"
							+"				<td colspan='2'>"
							+entrustRegister.getEntrustDate()
							+"				</td>"
							+"				<th colspan='2'>送检人(机构)</th>"
							+"				<td colspan='2'>"
							+"				"
							+"				</td>"
							+"			</tr>"
							+"			<tr style='height: 80px;'>"
							+"			   <th colspan='2'>司法鉴定"
							+"			   <br/>"
							+"			   机&nbsp;&nbsp;构"
							+"			   "
							+"			   </th>"
							+"			   <td colspan='7'>"
							+"			   机构名称: &nbsp;&nbsp;江西开元司法鉴定中心    &nbsp;&nbsp;&nbsp;&nbsp;许可证号:360010056   <br/>"
							+"			 地址: &nbsp;&nbsp;南昌市高新五路666号创立大厦五楼 <br/>"
							+"			  邮编:&nbsp;&nbsp;330096<br/>"
							+"			  联系人:"+
							entrustRegister.getServerName()
							+"			 联系电话:"+
							entrustRegister.getAgentTel()
							+"			   </td>"
							+"			</tr>"
							+"			<tr>	"
							+"				<th colspan='2'>委托鉴定事项</th>"
							+"				<td colspan='6'>"
							+type
							+"				</td>"
							+"			</tr>"
							+"			<tr height='80px'>"
							+"				<th colspan='2'>是否属于"
							+"				<br/>"
							+"				重新鉴定</th>"
							+"				<td colspan='2'>"
							+authorizeNotification
							+"				</td>"
							+"				<th>鉴定用途 "
							+"				 <td colspan='4'>"
							+whether
							+"				</td>  "
							+"			</tr>"
							+"			<tr>"
							+"				<th colspan='2'>鉴定材料:</th>"
							+"				<td colspan='7'>"
							+aboutMaterials
							+"				</td>"
							+"			</tr>"
							+"			<tr height='70px'>"
							+"				<th rowspan='2' colspan='2'>预计费用及收取方式</th>"
							+"				<td colspan='7' >"
							+"				   预计收费总金额:￥: "+
							standardFee
							+"				 大写:"+
							capital
							+"				</td>"
							+"				"
							+"			</tr>"
							+"				<tr>"
							+"					<td colspan='7'>"
							+"						<span>"
							+chargeway
							+"				</td>"
							+"				</tr>"
							+"				<tr>"
							+"					<th colspan='2'>司法鉴定意见"
							+"					<br>"
							+"					书发送方式 </th>"
							+"				<td colspan='7'>"
							+sendWay
							+"					"
							+"				</tr>"
							+"				"
							+"				"
							+"				<tr height='400px;'>"
							+"					<td colspan='8'>"
							+"					约定事项:<br/>"
							+"					1.(1)关于鉴定材料:<br/>"
							+"					"
							+ materialDisposeq
							+"				      (2)关于剩余鉴定材料:<br/>"
							+remainingq
							+"					2.鉴定时限：<br/>"
							+timelimit
							+"						注：鉴定过程中补充或者重新提取鉴定材料所需时间，不计入鉴定时限。<br/>"
							+"					3.需要回避的鉴定人"
							+ "无 ，回避事由：无。<br/>"
							+"					4.经双方协商一致，鉴定过程中可变更委托书内容。<br/>"
							+"					5.其他约定事项：无 	<br/>"
							+"					</td>	"
							+"				</tr>"
							+"				<tr>"
							+"				<th colspan='2'>"
							+"				  鉴定风险提示："
							+"				</th>"
							+"				<td colspan='7'>"
							+"				1.鉴定意见属于专家的专业意见，是否被采信取决于办案机关的审查和判断，鉴定人和鉴定机构无权干涉；<br/>"
							+"				2.由于受鉴定材料或者其他因素限制，并非所有的鉴定都能得出明确的鉴定意见；<br/>"
							+"				3.鉴定活动遵循依法独立、客观、公正的原则，只对鉴定材料和案发事实负责，不会考虑是否有利于任何一方当事人。<br/>"
							+"                                    委托人（签名）：          <br/>               "
							+"				</td>"
							+"				</tr>"
							+"				<tr>"
							+"					<th colspan='2'>其他需要说"
							+"					<br/>"
							+"					明的事项</th>"
							+"					"
							+"					<td colspan='7'>"
							+"					"
							+"					</td>"
							+"				</tr>"
							+"				<tr>"
							+"				  <th rowspan='8' colspan='2'>与鉴定有关<br/>的基本案情</th>"
							+"				</tr>"
							+"</tr>"
							+"				<tr >		  "
							+"				  <td>姓名</td>"
							+"				  <td>性别</td>"
							+"				  <td>称谓</td>"
							+"				  <td>出生日期</td>"
							+"				  <td align='center'>证件名称</td>"
							+"				  <td colspan='2' style='width: 276px;'>证件号码</td>"
							+"				</tr>"
							+"				 "
							
							+ foreach 
							
							+"		</table>"
							+"			</div>"

							+"</body></html>";

					byte b[] = content.getBytes("utf-8");  //这里是必须要设置编码的，不然导出中文就会乱码.
					
					ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中  
					/*
					 * 关键地方
					 * 生成word格式
					 */
					POIFSFileSystem poifs = new POIFSFileSystem();  
					DirectoryNode directory = poifs.getRoot();  
					DocumentEntry documentEntry = directory.createDocument("WordDocument", bais); 
					
//					FileOutputStream ostream = new FileOutputStream(path); 
//					fs.writeFilesystem(ostream); 
					
					FileOutputStream ostream = new FileOutputStream(path); 
					poifs.writeFilesystem(ostream);  
					
					//输出文件
					//String fileName="实习考核鉴定表";
//					request.setCharacterEncoding("utf-8");  
//					response.setContentType("application/msword");//导出word格式
		 //			response.addHeader("Content-Disposition", "attachment;filename=" +
//							new String( (fileName + ".doc").getBytes(),  
//									"iso-8859-1"));
//					OutputStream ostream = response.getOutputStream(); 
					
					
					bais.close();  
					ostream.close(); 
					
					
					
				}catch(Exception e){
					
					
				}finally {
					
				}
			}
	}