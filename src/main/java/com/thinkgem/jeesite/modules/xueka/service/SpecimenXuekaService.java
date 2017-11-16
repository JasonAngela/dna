/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.xueka.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.xueka.entity.SpecimenXueka;
import com.thinkgem.jeesite.modules.xueka.dao.SpecimenXuekaDao;

/**
 * 血卡Service
 * @author fuyunyou
 * @version 2017-06-06
 */
@Service
@Transactional(readOnly = true)
public class SpecimenXuekaService extends CrudService<SpecimenXuekaDao, SpecimenXueka> {

	public SpecimenXueka get(String id) {
		return super.get(id);
	}
	
	public List<SpecimenXueka> findList(SpecimenXueka specimenXueka) {
		return super.findList(specimenXueka);
	}
	
	
	public Page<SpecimenXueka> findPage(Page<SpecimenXueka> page, SpecimenXueka specimenXueka) {
		return super.findPage(page, specimenXueka);
	}
	
	@Transactional(readOnly = false)
	public void save(SpecimenXueka specimenXueka) {
		super.save(specimenXueka);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpecimenXueka specimenXueka) {
		super.delete(specimenXueka);
	}
	
}