/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipment;
import com.thinkgem.jeesite.modules.synth.dao.SynthEquipmentDao;

/**
 * 设备管理Service
 * @author zhuguli
 * @version 2017-05-13
 */
@Service
@Transactional(readOnly = true)
public class SynthEquipmentService extends CrudService<SynthEquipmentDao, SynthEquipment> {

	public SynthEquipment get(String id) {
		return super.get(id);
	}
	
	public List<SynthEquipment> findList(SynthEquipment synthEquipment) {
		return super.findList(synthEquipment);
	}
	
	public Page<SynthEquipment> findPage(Page<SynthEquipment> page, SynthEquipment synthEquipment) {
		return super.findPage(page, synthEquipment);
	}
	
	@Transactional(readOnly = false)
	public void save(SynthEquipment synthEquipment) {
		super.save(synthEquipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(SynthEquipment synthEquipment) {
		super.delete(synthEquipment);
	}
	
}