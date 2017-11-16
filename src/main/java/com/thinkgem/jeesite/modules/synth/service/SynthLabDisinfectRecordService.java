/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.synth.entity.SynthLabDisinfectRecord;
import com.thinkgem.jeesite.modules.synth.dao.SynthLabDisinfectRecordDao;

/**
 * 实验室消毒记录Service
 * @author zhuguli
 * @version 2017-05-13
 */
@Service
@Transactional(readOnly = true)
public class SynthLabDisinfectRecordService extends CrudService<SynthLabDisinfectRecordDao, SynthLabDisinfectRecord> {

	public SynthLabDisinfectRecord get(String id) {
		return super.get(id);
	}
	
	public List<SynthLabDisinfectRecord> findList(SynthLabDisinfectRecord synthLabDisinfectRecord) {
		return super.findList(synthLabDisinfectRecord);
	}
	
	public Page<SynthLabDisinfectRecord> findPage(Page<SynthLabDisinfectRecord> page, SynthLabDisinfectRecord synthLabDisinfectRecord) {
		return super.findPage(page, synthLabDisinfectRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(SynthLabDisinfectRecord synthLabDisinfectRecord) {
		super.save(synthLabDisinfectRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(SynthLabDisinfectRecord synthLabDisinfectRecord) {
		super.delete(synthLabDisinfectRecord);
	}
	
}