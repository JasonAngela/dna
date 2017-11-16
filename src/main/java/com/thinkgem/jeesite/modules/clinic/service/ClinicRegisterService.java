/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.clinic.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.clinic.entity.ClinicRegister;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.clinic.dao.ClinicRegisterDao;

/**
 * 临床登记Service
 * @author zhuguli
 * @version 2017-10-15
 */
@Service
@Transactional(readOnly = true)
public class ClinicRegisterService extends CrudService<ClinicRegisterDao, ClinicRegister> {
	@Autowired
	private ActTaskService actTaskService;
	public ClinicRegister get(String id) {
		return super.get(id);
	}
	
	public List<ClinicRegister> findList(ClinicRegister clinicRegister) {
		return super.findList(clinicRegister);
	}
	
	public Page<ClinicRegister> findPage(Page<ClinicRegister> page, ClinicRegister clinicRegister) {
		return super.findPage(page, clinicRegister);
	}
	
	@Transactional(readOnly = false)
	public void save(ClinicRegister clinicRegister) {
		super.save(clinicRegister);
		actTaskService.startProcess(ActUtils.PD_CLINIC[0], ActUtils.PD_CLINIC[1], clinicRegister.getId(), clinicRegister.getCode());
	}
	
	@Transactional(readOnly = false)
	public void delete(ClinicRegister clinicRegister) {
		super.delete(clinicRegister);
	}

	public void saveMaterial(ClinicRegister clinicRegister) {
		// 完成流程任务
		Map<String, Object> vars = Maps.newHashMap();
		actTaskService.complete(clinicRegister.getAct().getTaskId(), clinicRegister.getAct().getProcInsId(), clinicRegister.getAct().getComment(), clinicRegister.getCode(), vars);
	
	}
	
}