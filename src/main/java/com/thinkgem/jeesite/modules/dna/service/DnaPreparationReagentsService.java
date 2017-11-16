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
import com.thinkgem.jeesite.modules.dna.entity.DnaPreparationReagents;
import com.thinkgem.jeesite.modules.dna.dao.DnaPreparationReagentsDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaPreparationReagentsIteam;
import com.thinkgem.jeesite.modules.dna.dao.DnaPreparationReagentsIteamDao;

/**
 * 试剂配制记录表Service
 * @author fyun
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class DnaPreparationReagentsService extends CrudService<DnaPreparationReagentsDao, DnaPreparationReagents> {

	@Autowired
	private DnaPreparationReagentsIteamDao dnaPreparationReagentsIteamDao;
	
	public DnaPreparationReagents get(String id) {
		DnaPreparationReagents dnaPreparationReagents = super.get(id);
		dnaPreparationReagents.setDnaPreparationReagentsIteamList(dnaPreparationReagentsIteamDao.findList(new DnaPreparationReagentsIteam(dnaPreparationReagents)));
		return dnaPreparationReagents;
	}
	
	public List<DnaPreparationReagents> findList(DnaPreparationReagents dnaPreparationReagents) {
		return super.findList(dnaPreparationReagents);
	}
	
	public Page<DnaPreparationReagents> findPage(Page<DnaPreparationReagents> page, DnaPreparationReagents dnaPreparationReagents) {
		return super.findPage(page, dnaPreparationReagents);
	}
	
	@Transactional(readOnly = false)
	public void save(DnaPreparationReagents dnaPreparationReagents) {
		super.save(dnaPreparationReagents);
		for (DnaPreparationReagentsIteam dnaPreparationReagentsIteam : dnaPreparationReagents.getDnaPreparationReagentsIteamList()){
			if (dnaPreparationReagentsIteam.getId() == null){
				continue;
			}
			if (DnaPreparationReagentsIteam.DEL_FLAG_NORMAL.equals(dnaPreparationReagentsIteam.getDelFlag())){
				if (StringUtils.isBlank(dnaPreparationReagentsIteam.getId())){
					dnaPreparationReagentsIteam.setDnaPreparationReagents(dnaPreparationReagents);
					dnaPreparationReagentsIteam.preInsert();
					dnaPreparationReagentsIteamDao.insert(dnaPreparationReagentsIteam);
				}else{
					dnaPreparationReagentsIteam.preUpdate();
					dnaPreparationReagentsIteamDao.update(dnaPreparationReagentsIteam);
				}
			}else{
				dnaPreparationReagentsIteamDao.delete(dnaPreparationReagentsIteam);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DnaPreparationReagents dnaPreparationReagents) {
		super.delete(dnaPreparationReagents);
		dnaPreparationReagentsIteamDao.delete(new DnaPreparationReagentsIteam(dnaPreparationReagents));
	}
	
}