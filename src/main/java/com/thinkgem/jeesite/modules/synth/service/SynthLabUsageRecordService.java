/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.synth.entity.SynthLabUsageRecord;
import com.thinkgem.jeesite.modules.synth.dao.SynthLabUsageRecordDao;

/**
 * 实验室使用记录Service
 * @author zhuguli
 * @version 2017-05-13
 */
@Service
@Transactional(readOnly = true)
public class SynthLabUsageRecordService extends CrudService<SynthLabUsageRecordDao, SynthLabUsageRecord> {

	public SynthLabUsageRecord get(String id) {
		return super.get(id);
	}
	
	public List<SynthLabUsageRecord> findList(SynthLabUsageRecord synthLabUsageRecord) {
		return super.findList(synthLabUsageRecord);
	}
	
	public Page<SynthLabUsageRecord> findPage(Page<SynthLabUsageRecord> page, SynthLabUsageRecord synthLabUsageRecord) {
		return super.findPage(page, synthLabUsageRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(SynthLabUsageRecord synthLabUsageRecord) {
		super.save(synthLabUsageRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(SynthLabUsageRecord synthLabUsageRecord) {
		super.delete(synthLabUsageRecord);
	}
	
}