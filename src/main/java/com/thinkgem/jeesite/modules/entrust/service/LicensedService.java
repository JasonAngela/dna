/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.entrust.entity.Licensed;
import com.thinkgem.jeesite.modules.entrust.dao.LicensedDao;

/**
 * 执业证Service
 * @author 浮云
 * @version 2017-09-13
 */
@Service
@Transactional(readOnly = true)
public class LicensedService extends CrudService<LicensedDao, Licensed> {

	public Licensed get(String id) {
		return super.get(id);
	}
	
	public List<Licensed> findList(Licensed licensed) {
		return super.findList(licensed);
	}
	
	public Page<Licensed> findPage(Page<Licensed> page, Licensed licensed) {
		return super.findPage(page, licensed);
	}
	
	@Transactional(readOnly = false)
	public void save(Licensed licensed) {
		super.save(licensed);
	}
	
	@Transactional(readOnly = false)
	public void delete(Licensed licensed) {
		super.delete(licensed);
	}
	
}