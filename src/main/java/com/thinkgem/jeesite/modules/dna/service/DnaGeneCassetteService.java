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
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneCassette;
import com.thinkgem.jeesite.modules.dna.dao.DnaGeneCassetteDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneCassetteItem;
import com.thinkgem.jeesite.modules.dna.dao.DnaGeneCassetteItemDao;

/**
 * 基因盒Service
 * @author zhuguli
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class DnaGeneCassetteService extends CrudService<DnaGeneCassetteDao, DnaGeneCassette> {

	@Autowired
	private DnaGeneCassetteItemDao dnaGeneCassetteItemDao;
	
	public DnaGeneCassette get(String id) {
		DnaGeneCassette dnaGeneCassette = super.get(id);
		dnaGeneCassette.setDnaGeneCassetteItemList(dnaGeneCassetteItemDao.findList(new DnaGeneCassetteItem(dnaGeneCassette)));
		return dnaGeneCassette;
	}
	
	public List<DnaGeneCassette> findList(DnaGeneCassette dnaGeneCassette) {
		return super.findList(dnaGeneCassette);
	}
	
	public Page<DnaGeneCassette> findPage(Page<DnaGeneCassette> page, DnaGeneCassette dnaGeneCassette) {
		return super.findPage(page, dnaGeneCassette);
	}
	
	@Transactional(readOnly = false)
	public void save(DnaGeneCassette dnaGeneCassette) {
		super.save(dnaGeneCassette);
		for (DnaGeneCassetteItem dnaGeneCassetteItem : dnaGeneCassette.getDnaGeneCassetteItemList()){
			if (dnaGeneCassetteItem.getId() == null){
				continue;
			} 
			if (DnaGeneCassetteItem.DEL_FLAG_NORMAL.equals(dnaGeneCassetteItem.getDelFlag())){
				if (StringUtils.isBlank(dnaGeneCassetteItem.getId())){
					dnaGeneCassetteItem.setCassette(dnaGeneCassette);
					dnaGeneCassetteItem.preInsert();
					dnaGeneCassetteItemDao.insert(dnaGeneCassetteItem);
				}else{
					dnaGeneCassetteItem.preUpdate();
					dnaGeneCassetteItemDao.update(dnaGeneCassetteItem);
				}
			}else{
				dnaGeneCassetteItemDao.delete(dnaGeneCassetteItem);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DnaGeneCassette dnaGeneCassette) {
		super.delete(dnaGeneCassette);
		dnaGeneCassetteItemDao.delete(new DnaGeneCassetteItem(dnaGeneCassette));
	}
	
	
}