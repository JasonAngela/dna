/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.entrust.entity.Mapping;
import com.thinkgem.jeesite.modules.entrust.dao.MappingDao;

/**
 * 图谱Service
 * @author 好好
 * @version 2017-09-08
 */
@Service
@Transactional(readOnly = true)
public class MappingService extends CrudService<MappingDao, Mapping> {

	public Mapping get(String id) {
		return super.get(id);
	}
	
	public List<Mapping> findList(Mapping mapping) {
		return super.findList(mapping);
	}
	
	public Page<Mapping> findPage(Page<Mapping> page, Mapping mapping) {
		return super.findPage(page, mapping);
	}
	
	@Transactional(readOnly = false)
	public void save(Mapping mapping) {
		super.save(mapping);
	}
	
	@Transactional(readOnly = false)
	public void delete(Mapping mapping) {
		super.delete(mapping);
	}
	
}