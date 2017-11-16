/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dna.entity.DnaExtractRecord;
import com.thinkgem.jeesite.modules.dna.dao.DnaExtractRecordDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaExtractRecordItem;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipment;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipmentUsageRecord;
import com.thinkgem.jeesite.modules.synth.service.SynthEquipmentUsageRecordService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.dna.dao.DnaExtractRecordItemDao;

/**
 * 提取室记录Service
 * @author yunyun
 * @version 2017-08-19
 */
@Service
@Transactional(readOnly = true)
public class DnaExtractRecordService extends CrudService<DnaExtractRecordDao, DnaExtractRecord> {

	@Autowired
	private DnaExtractRecordItemDao dnaExtractRecordItemDao;
	@Autowired
	private SynthEquipmentUsageRecordService synthEquipmentUsageRecordService;
	
	public DnaExtractRecord get(String id) {
		DnaExtractRecord dnaExtractRecord = super.get(id);
		dnaExtractRecord.setDnaExtractRecordItemList(dnaExtractRecordItemDao.findList(new DnaExtractRecordItem(dnaExtractRecord)));
		return dnaExtractRecord;
	}
	
	public List<DnaExtractRecord> findList(DnaExtractRecord dnaExtractRecord) {
		return super.findList(dnaExtractRecord);
	}
	
	public Page<DnaExtractRecord> findPage(Page<DnaExtractRecord> page, DnaExtractRecord dnaExtractRecord) {
		return super.findPage(page, dnaExtractRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(DnaExtractRecord dnaExtractRecord) {
		super.save(dnaExtractRecord);
		User user=UserUtils.getUser();
		for (SynthEquipment iterable_element : dnaExtractRecord.getSynthEquipments()) {
			SynthEquipmentUsageRecord synthEquipmentUsageRecord=new SynthEquipmentUsageRecord();
			SynthEquipment equipment=new SynthEquipment();
			equipment.setId(iterable_element.getId());
			synthEquipmentUsageRecord.setEquipment(equipment);
			synthEquipmentUsageRecord.setOperator(user);
			System.out.println(synthEquipmentUsageRecord.getEquipment().getId());
			synthEquipmentUsageRecordService.save(synthEquipmentUsageRecord);
		}
		
		for (DnaExtractRecordItem dnaExtractRecordItem : dnaExtractRecord.getDnaExtractRecordItemList()){
			if (dnaExtractRecordItem.getId() == null){
				continue;
			}
			if (DnaExtractRecordItem.DEL_FLAG_NORMAL.equals(dnaExtractRecordItem.getDelFlag())){
				if (StringUtils.isBlank(dnaExtractRecordItem.getId())){
					dnaExtractRecordItem.setRecord(dnaExtractRecord);
					dnaExtractRecordItem.preInsert();
					dnaExtractRecordItemDao.insert(dnaExtractRecordItem);
				}else{
					dnaExtractRecordItem.preUpdate();
					dnaExtractRecordItemDao.update(dnaExtractRecordItem);
				}
			}else{
				dnaExtractRecordItemDao.delete(dnaExtractRecordItem);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DnaExtractRecord dnaExtractRecord) {
		super.delete(dnaExtractRecord);
		dnaExtractRecordItemDao.delete(new DnaExtractRecordItem(dnaExtractRecord));
	}
	
}