/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneLoci;
import com.thinkgem.jeesite.modules.dna.dao.DnaGeneLociDao;

/**
 * 基因座Service
 * @author zhuguli
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class DnaGeneLociService extends CrudService<DnaGeneLociDao, DnaGeneLoci> {

	public DnaGeneLoci get(String id) {
		return super.get(id);
	}
	
	public List<DnaGeneLoci> findList(DnaGeneLoci dnaGeneLoci){
		return super.findList(dnaGeneLoci);
	}
	
	public Page<DnaGeneLoci> findPage(Page<DnaGeneLoci> page, DnaGeneLoci dnaGeneLoci) {
		return super.findPage(page, dnaGeneLoci);
	}
	
	@Transactional(readOnly = false)
	public void save(DnaGeneLoci dnaGeneLoci) {
		super.save(dnaGeneLoci);
	}
	
	@Transactional(readOnly = false)
	public void delete(DnaGeneLoci dnaGeneLoci) {
		super.delete(dnaGeneLoci);
	}
	
}