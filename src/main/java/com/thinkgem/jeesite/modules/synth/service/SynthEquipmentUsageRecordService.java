/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipmentUsageRecord;
import com.thinkgem.jeesite.modules.synth.dao.SynthEquipmentUsageRecordDao;

/**
 * 设备使用记录Service
 * @author zhuguli
 * @version 2017-05-13
 */
@Service
@Transactional(readOnly = true)
public class SynthEquipmentUsageRecordService extends CrudService<SynthEquipmentUsageRecordDao, SynthEquipmentUsageRecord> {

	public SynthEquipmentUsageRecord get(String id) {
		return super.get(id);
	}
	
	public List<SynthEquipmentUsageRecord> findList(SynthEquipmentUsageRecord synthEquipmentUsageRecord) {
		return super.findList(synthEquipmentUsageRecord);
	}
	
	public Page<SynthEquipmentUsageRecord> findPage(Page<SynthEquipmentUsageRecord> page, SynthEquipmentUsageRecord synthEquipmentUsageRecord) {
		return super.findPage(page, synthEquipmentUsageRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(SynthEquipmentUsageRecord synthEquipmentUsageRecord) {
		super.save(synthEquipmentUsageRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(SynthEquipmentUsageRecord synthEquipmentUsageRecord) {
		super.delete(synthEquipmentUsageRecord);
	}
	
}