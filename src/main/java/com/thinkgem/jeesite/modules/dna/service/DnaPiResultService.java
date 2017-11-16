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
import com.thinkgem.jeesite.modules.dna.entity.DnaPiResult;
import com.thinkgem.jeesite.modules.dna.dao.DnaPiResultDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaPiResultItem;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;
import com.thinkgem.jeesite.modules.dna.dao.DnaPiResultItemDao;

/**
 * 亲权指数Service
 * @author zhuguli
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class DnaPiResultService extends CrudService<DnaPiResultDao, DnaPiResult> {
  
	@Autowired
	private DnaPiResultItemDao dnaPiResultItemDao;

	public DnaPiResult get(String id) {
		DnaPiResult dnaPiResult = super.get(id);
		dnaPiResult.setDnaPiResultItemList(dnaPiResultItemDao.findList(new DnaPiResultItem(dnaPiResult)));
		return dnaPiResult;
	}

	public List<DnaPiResult> findList(DnaPiResult dnaPiResult) {
		return super.findList(dnaPiResult);
	}

	public Page<DnaPiResult> findPage(Page<DnaPiResult> page, DnaPiResult dnaPiResult) {
		return super.findPage(page, dnaPiResult);
	}

	@Transactional(readOnly = false)
	public void save(DnaPiResult dnaPiResult) {
		super.save(dnaPiResult);
		for (DnaPiResultItem dnaPiResultItem : dnaPiResult.getDnaPiResultItemList()){
			if (DnaPiResultItem.DEL_FLAG_NORMAL.equals(dnaPiResultItem.getDelFlag())){
				if (StringUtils.isBlank(dnaPiResultItem.getId())){
					dnaPiResultItem.setResult(dnaPiResult);
					dnaPiResultItem.preInsert();
					dnaPiResultItemDao.insert(dnaPiResultItem);
				}else{
					dnaPiResultItem.preUpdate();
					dnaPiResultItemDao.update(dnaPiResultItem);
				}
			}else{
				dnaPiResultItemDao.delete(dnaPiResultItem);
			}
		}
	}

	@Transactional(readOnly = false)
	public void delete(DnaPiResult dnaPiResult) {
		super.delete(dnaPiResult);
		dnaPiResultItemDao.delete(new DnaPiResultItem(dnaPiResult));
	}

	public List<DnaPiResult> selectByRegisterId(String registerId) {
		DnaPiResult entity = new DnaPiResult();
		entity.setRegister(new EntrustRegister(registerId));
		List<DnaPiResult>list =  findList(entity);
		for(DnaPiResult dnaPiResult:list){
			dnaPiResult.setDnaPiResultItemList(dnaPiResultItemDao.findList(new DnaPiResultItem(dnaPiResult)));
		}
		return list;
	}

}