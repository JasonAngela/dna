/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustAbstracts;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialRegisterItemDao;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegisterItem;
import com.thinkgem.jeesite.modules.entrust.dao.EntrustAbstractsDao;

/**
 * 摘要登记Service
 * @author zhuguli
 * @version 2017-05-12
 */
@Service
@Transactional(readOnly = true)
public class EntrustAbstractsService extends CrudService<EntrustAbstractsDao, EntrustAbstracts> {
@Autowired
private EntrustAbstractsDao entrustAbstractsDao;
@Autowired
private SpecimenMaterialRegisterItemDao specimenMaterialRegisterItemDao;
	
	public EntrustAbstracts get(String id) {
		return super.get(id);
	}
	
	public List<EntrustAbstracts> findAllentrustAbstracts(String id){
		return entrustAbstractsDao.findAllentrustAbstracts(id);
	}
	
	public List<EntrustAbstracts> findList(EntrustAbstracts entrustAbstracts) {
		return super.findList(entrustAbstracts);
	}
	
	public Page<EntrustAbstracts> findPage(Page<EntrustAbstracts> page, EntrustAbstracts entrustAbstracts) {
		return super.findPage(page, entrustAbstracts);
	}
	
	@Transactional(readOnly = false)
	public void save(EntrustAbstracts entrustAbstracts) {
		super.save(entrustAbstracts);
	}
	
	@Transactional(readOnly = false)
	public void delete(EntrustAbstracts entrustAbstracts) {
		super.delete(entrustAbstracts);
	}
	
	
	
}