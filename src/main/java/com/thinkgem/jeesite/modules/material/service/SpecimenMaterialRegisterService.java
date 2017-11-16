/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.entrust.dao.EntrustRegisterDao;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialRegisterDao;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialRegisterItemDao;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegister;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegisterItem;
import com.thinkgem.jeesite.modules.sys.service.SysCodeRuleService;
import com.thinkgem.jeesite.modules.xueka.dao.SpecimenXuekaDao;
import com.thinkgem.jeesite.modules.xueka.entity.SpecimenXueka;
import com.thinkgem.jeesite.modules.xueka.entity.SpecimenXuekaLsit;

/**
 * 物证登记Service
 * @author zhuguli
 * @version 2017-05-10
 */
@Service
@Transactional(readOnly = true)
public class SpecimenMaterialRegisterService extends CrudService<SpecimenMaterialRegisterDao, SpecimenMaterialRegister> {

	@Autowired
	private SpecimenMaterialRegisterDao specimenMaterialRegisterDao;
	@Autowired
	private SpecimenMaterialRegisterItemDao specimenMaterialRegisterItemDao;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private SysCodeRuleService sysCodeRuleService;
	@Autowired
	private EntrustRegisterDao entrustRegisterDao;
	@Autowired
	private SpecimenXuekaDao specimenXuekaDao;
 
	public SpecimenMaterialRegister get(String id) {
		SpecimenMaterialRegister specimenMaterialRegister = super.get(id);
		specimenMaterialRegister.setSpecimenMaterialRegisterItemList(specimenMaterialRegisterItemDao.findList(new SpecimenMaterialRegisterItem(specimenMaterialRegister)));
		return specimenMaterialRegister;
	}

	public List<SpecimenMaterialRegister> findList(SpecimenMaterialRegister specimenMaterialRegister) {
		return super.findList(specimenMaterialRegister);
	}

	public Page<SpecimenMaterialRegister> findPage(Page<SpecimenMaterialRegister> page, SpecimenMaterialRegister specimenMaterialRegister) {
		return super.findPage(page, specimenMaterialRegister);
	}

	@Transactional(readOnly = false)
	public void save(SpecimenMaterialRegister specimenMaterialRegister,SpecimenXuekaLsit specimenXuekaLsit) {
		
		if(specimenMaterialRegister.getIsNewRecord()){
			String code = sysCodeRuleService.generateCode("material_register_code");
			specimenMaterialRegister.setCode(code);
		}
		super.save(specimenMaterialRegister);
		 
		for (SpecimenMaterialRegisterItem specimenMaterialRegisterItem : specimenMaterialRegister.getSpecimenMaterialRegisterItemList()){
			
			if (specimenMaterialRegisterItem.getId() == null){
				continue;
			}
			if (SpecimenMaterialRegisterItem.DEL_FLAG_NORMAL.equals(specimenMaterialRegisterItem.getDelFlag())){
				if (StringUtils.isBlank(specimenMaterialRegisterItem.getId())){
					specimenMaterialRegisterItem.setMaterialRegister(specimenMaterialRegister);
					specimenMaterialRegisterItem.preInsert();
					specimenMaterialRegisterItemDao.insert(specimenMaterialRegisterItem);
				}else{
					specimenMaterialRegisterItem.preUpdate();
					specimenMaterialRegisterItemDao.update(specimenMaterialRegisterItem);
				}
			}else{
				specimenMaterialRegisterItemDao.delete(specimenMaterialRegisterItem);
			}
		}
		for (SpecimenXueka specimenXueka : specimenXuekaLsit.getSpecimenXuekas()) {
			specimenXueka.setMaterialRegisterItemId(specimenMaterialRegister.getEntrustRegister().getCode());
			specimenXuekaDao.insert(specimenXueka);
		}
		//修改状态
		EntrustRegister entrustRegister = entrustRegisterDao.get(specimenMaterialRegister.getEntrustRegister().getId());
		entrustRegister.setStatus("3");
		entrustRegisterDao.update(entrustRegister);
		
		// 完成流程任务
		Map<String, Object> vars = Maps.newHashMap();
		actTaskService.complete(specimenMaterialRegister.getAct().getTaskId(), specimenMaterialRegister.getAct().getProcInsId(), specimenMaterialRegister.getAct().getComment(), specimenMaterialRegister.getCode(), vars);
	}

	@Transactional(readOnly = false)
	public void delete(SpecimenMaterialRegister specimenMaterialRegister) {
		super.delete(specimenMaterialRegister);
		specimenMaterialRegisterItemDao.delete(new SpecimenMaterialRegisterItem(specimenMaterialRegister));
	}
	@Transactional(readOnly = false)
	public  List<SpecimenMaterialRegisterItem> findmaterial(String id){
		SpecimenMaterialRegister specimenMaterialRegister =specimenMaterialRegisterDao.findid(id);
		return specimenMaterialRegisterItemDao.findall(specimenMaterialRegister.getId());
	}

	
}