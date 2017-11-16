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
import com.thinkgem.jeesite.modules.dna.entity.DnaElectrophoresisParting;
import com.thinkgem.jeesite.modules.dna.dao.DnaElectrophoresisPartingDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaElectrophoresisPartingIteam;
import com.thinkgem.jeesite.modules.dna.dao.DnaElectrophoresisPartingIteamDao;

/**
 * 电泳室Service
 * @author fuyun
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class DnaElectrophoresisPartingService extends CrudService<DnaElectrophoresisPartingDao, DnaElectrophoresisParting> {

	@Autowired
	private DnaElectrophoresisPartingIteamDao dnaElectrophoresisPartingIteamDao;
	
	public DnaElectrophoresisParting get(String id) {
		DnaElectrophoresisParting dnaElectrophoresisParting = super.get(id);
		dnaElectrophoresisParting.setDnaElectrophoresisPartingIteamList(dnaElectrophoresisPartingIteamDao.findList(new DnaElectrophoresisPartingIteam(dnaElectrophoresisParting)));
		return dnaElectrophoresisParting;
	}
	
	public List<DnaElectrophoresisParting> findList(DnaElectrophoresisParting dnaElectrophoresisParting) {
		return super.findList(dnaElectrophoresisParting);
	}
	
	public Page<DnaElectrophoresisParting> findPage(Page<DnaElectrophoresisParting> page, DnaElectrophoresisParting dnaElectrophoresisParting) {
		return super.findPage(page, dnaElectrophoresisParting);
	}
	
	@Transactional(readOnly = false)
	public void save(DnaElectrophoresisParting dnaElectrophoresisParting) {
		super.save(dnaElectrophoresisParting);
		for (DnaElectrophoresisPartingIteam dnaElectrophoresisPartingIteam : dnaElectrophoresisParting.getDnaElectrophoresisPartingIteamList()){
			if (dnaElectrophoresisPartingIteam.getId() == null){
				continue;
			}
			if (DnaElectrophoresisPartingIteam.DEL_FLAG_NORMAL.equals(dnaElectrophoresisPartingIteam.getDelFlag())){
				if (StringUtils.isBlank(dnaElectrophoresisPartingIteam.getId())){
					dnaElectrophoresisPartingIteam.setDnaElectrophoresisParting(dnaElectrophoresisParting);
					dnaElectrophoresisPartingIteam.preInsert();
					dnaElectrophoresisPartingIteamDao.insert(dnaElectrophoresisPartingIteam);
				}else{
					dnaElectrophoresisPartingIteam.preUpdate();
					dnaElectrophoresisPartingIteamDao.update(dnaElectrophoresisPartingIteam);
				}
			}else{
				dnaElectrophoresisPartingIteamDao.delete(dnaElectrophoresisPartingIteam);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DnaElectrophoresisParting dnaElectrophoresisParting) {
		super.delete(dnaElectrophoresisParting);
		dnaElectrophoresisPartingIteamDao.delete(new DnaElectrophoresisPartingIteam(dnaElectrophoresisParting));
	}
	
}