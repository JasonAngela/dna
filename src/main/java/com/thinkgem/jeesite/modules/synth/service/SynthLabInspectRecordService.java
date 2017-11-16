/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.synth.entity.SynthLabInspectRecord;
import com.thinkgem.jeesite.modules.synth.dao.SynthLabInspectRecordDao;

/**
 * 实验室检查记录Service
 * @author zhuguli
 * @version 2017-05-13
 */
@Service
@Transactional(readOnly = true)
public class SynthLabInspectRecordService extends CrudService<SynthLabInspectRecordDao, SynthLabInspectRecord> {

	public SynthLabInspectRecord get(String id) {
		return super.get(id);
	}
	
	public List<SynthLabInspectRecord> findList(SynthLabInspectRecord synthLabInspectRecord) {
		return super.findList(synthLabInspectRecord);
	}
	
	public Page<SynthLabInspectRecord> findPage(Page<SynthLabInspectRecord> page, SynthLabInspectRecord synthLabInspectRecord) {
		return super.findPage(page, synthLabInspectRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(SynthLabInspectRecord synthLabInspectRecord) {
		super.save(synthLabInspectRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(SynthLabInspectRecord synthLabInspectRecord) {
		super.delete(synthLabInspectRecord);
	}
	
}