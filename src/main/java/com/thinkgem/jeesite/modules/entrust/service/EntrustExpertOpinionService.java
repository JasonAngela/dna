/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustExpertOpinion;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.entrust.dao.EntrustExpertOpinionDao;

/**
 * 鉴定意见Service
 * @author zhuguli
 * @version 2017-05-11
 */
@Service
@Transactional(readOnly = true)
public class EntrustExpertOpinionService extends CrudService<EntrustExpertOpinionDao, EntrustExpertOpinion> {
	@Autowired
	private ActTaskService actTaskService;
	public EntrustExpertOpinion get(String id) {
		return super.get(id);
	}

	public List<EntrustExpertOpinion> findList(EntrustExpertOpinion entrustExpertOpinion) {
		return super.findList(entrustExpertOpinion);
	}

	public Page<EntrustExpertOpinion> findPage(Page<EntrustExpertOpinion> page, EntrustExpertOpinion entrustExpertOpinion) {
		return super.findPage(page, entrustExpertOpinion);
	}

	@Transactional(readOnly = false)
	
	public void save(EntrustExpertOpinion entrustExpertOpinion) {
		if(!StringUtils.isEmpty(entrustExpertOpinion.getFinalVersion())){
		entrustExpertOpinion.setFinalVersion(StringEscapeUtils.unescapeHtml4(entrustExpertOpinion.getFinalVersion().trim()));
		}
		entrustExpertOpinion.setExplain(StringEscapeUtils.unescapeHtml4(entrustExpertOpinion.getExplain()).trim());
		if(entrustExpertOpinion.getIsNewRecord()){
			entrustExpertOpinion.setExplainRemark(StringEscapeUtils.unescapeHtml4(entrustExpertOpinion.getExplain()).trim());
			entrustExpertOpinion.setDraftRemark(StringEscapeUtils.unescapeHtml4(entrustExpertOpinion.getDraft()).trim());
		}
		super.save(entrustExpertOpinion);
		// 完成流程任务
		Map<String, Object> vars = Maps.newHashMap();
		
		if(!(entrustExpertOpinion.getAct().getTaskDefKey().equals("reportInit")||entrustExpertOpinion.getAct().getTaskDefKey().equals("requestEnd"))){
			vars.put("flag", entrustExpertOpinion.getAct().getFlag().equals("true")?1:0);
		}
		actTaskService.complete(entrustExpertOpinion.getAct().getTaskId(), entrustExpertOpinion.getAct().getProcInsId(), entrustExpertOpinion.getAct().getComment(), entrustExpertOpinion.getRemarks(), vars);
	}
	
	

	@Transactional(readOnly = false)
	public void delete(EntrustExpertOpinion entrustExpertOpinion) {
		super.delete(entrustExpertOpinion);
	}

	public List<EntrustExpertOpinion> getByRegisterId(String registerId) {
		return this.dao.getByRegisterId(registerId);
	}
	@Transactional(readOnly = false)
	public void saveAnalyze(EntrustExpertOpinion entrustExpertOpinion) {
		Map<String, Object> vars = Maps.newHashMap();
		
		actTaskService.complete(entrustExpertOpinion.getAct().getTaskId(), entrustExpertOpinion.getAct().getProcInsId(), entrustExpertOpinion.getAct().getComment(), entrustExpertOpinion.getRemarks(), vars);
		
	}

}