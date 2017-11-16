/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneFrequency;
import com.thinkgem.jeesite.modules.dna.dao.DnaGeneFrequencyDao;

/**
 * 等位基因频率Service
 * @author zhuguli
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class DnaGeneFrequencyService extends CrudService<DnaGeneFrequencyDao, DnaGeneFrequency> {

	public DnaGeneFrequency get(String id) {
		return super.get(id);
	}
	
	public List<DnaGeneFrequency> findList(DnaGeneFrequency dnaGeneFrequency) {
		return super.findList(dnaGeneFrequency);
	}
	
	public Page<DnaGeneFrequency> findPage(Page<DnaGeneFrequency> page, DnaGeneFrequency dnaGeneFrequency) {
		return super.findPage(page, dnaGeneFrequency);
	}
	
	@Transactional(readOnly = false)
	public void save(DnaGeneFrequency dnaGeneFrequency) {
		super.save(dnaGeneFrequency);
	}
	
	@Transactional(readOnly = false)
	public void delete(DnaGeneFrequency dnaGeneFrequency) {
		super.delete(dnaGeneFrequency);
	}
	
}