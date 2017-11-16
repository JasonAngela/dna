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
import com.thinkgem.jeesite.modules.dna.entity.DnaDaily;
import com.thinkgem.jeesite.modules.dna.dao.DnaDailyDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaDailyItem;
import com.thinkgem.jeesite.modules.dna.dao.DnaDailyItemDao;

/**
 * dna日常记录Service
 * @author fuyun
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class DnaDailyService extends CrudService<DnaDailyDao, DnaDaily> {

	@Autowired
	private DnaDailyItemDao dnaDailyItemDao;
	
	public DnaDaily get(String id) {
		DnaDaily dnaDaily = super.get(id);
		dnaDaily.setDnaDailyItemList(dnaDailyItemDao.findList(new DnaDailyItem(dnaDaily)));
		return dnaDaily;
	}
	
	public List<DnaDaily> findList(DnaDaily dnaDaily) {
		return super.findList(dnaDaily);
	}
	
	public Page<DnaDaily> findPage(Page<DnaDaily> page, DnaDaily dnaDaily) {
		return super.findPage(page, dnaDaily);
	}
	
	@Transactional(readOnly = false)
	public void save(DnaDaily dnaDaily) {
		super.save(dnaDaily);
		
		for (DnaDailyItem dnaDailyItem : dnaDaily.getDnaDailyItemList()){
			if (dnaDailyItem.getId() == null){
				continue;
			}
			if (DnaDailyItem.DEL_FLAG_NORMAL.equals(dnaDailyItem.getDelFlag())){
				if (StringUtils.isBlank(dnaDailyItem.getId())){
					dnaDailyItem.setDaily(dnaDaily);
					dnaDailyItem.preInsert();
					dnaDailyItemDao.insert(dnaDailyItem);
				}else{
					dnaDailyItem.preUpdate();
					dnaDailyItemDao.update(dnaDailyItem);
				}
			}else{
				dnaDailyItemDao.delete(dnaDailyItem);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DnaDaily dnaDaily) {
		super.delete(dnaDaily);
		dnaDailyItemDao.delete(new DnaDailyItem(dnaDaily));
	}
	
}