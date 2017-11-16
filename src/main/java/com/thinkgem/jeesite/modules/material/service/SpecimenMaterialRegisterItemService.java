/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialRegisterItemDao;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegisterItem;

/**
 * 物证登记明细Service
 * @author zhuguli
 * @version 2017-05-10
 */
@Service
@Transactional(readOnly = true)
public class SpecimenMaterialRegisterItemService extends CrudService<SpecimenMaterialRegisterItemDao, SpecimenMaterialRegisterItem> {

	@SuppressWarnings("unused")
	@Autowired
	private SpecimenMaterialRegisterItemDao specimenMaterialRegisterItemDao;
	 
 
	public Page<SpecimenMaterialRegisterItem> findPage(Page<SpecimenMaterialRegisterItem> page, SpecimenMaterialRegisterItem specimenMaterialRegisterItem) {
		return super.findPage(page, specimenMaterialRegisterItem);
	}

	 

}