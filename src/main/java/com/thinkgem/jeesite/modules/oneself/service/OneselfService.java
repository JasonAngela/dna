/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oneself.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oneself.entity.Oneself;
import com.thinkgem.jeesite.modules.oneself.dao.OneselfDao;
import com.thinkgem.jeesite.modules.oneself.entity.OneselExperimental;
import com.thinkgem.jeesite.modules.oneself.dao.OneselExperimentalDao;

/**
 * 单独实验Service
 * @author fuyun
 * @version 2017-10-18
 */
@Service
@Transactional(readOnly = true)
public class OneselfService extends CrudService<OneselfDao, Oneself> {

	@Autowired
	private OneselExperimentalDao oneselExperimentalDao;
	
	public Oneself get(String id) {
		Oneself oneself = super.get(id);
		oneself.setOneselExperimentalList(oneselExperimentalDao.findList(new OneselExperimental(oneself)));
		return oneself;
	}
	
	public List<Oneself> findList(Oneself oneself) {
		return super.findList(oneself);
	}
	
	public Page<Oneself> findPage(Page<Oneself> page, Oneself oneself) {
		return super.findPage(page, oneself);
	}
	
	@Transactional(readOnly = false)
	public void save(Oneself oneself) {
		super.save(oneself);
		for (OneselExperimental oneselExperimental : oneself.getOneselExperimentalList()){
			if (oneselExperimental.getId() == null){
				continue;
			}
			if (OneselExperimental.DEL_FLAG_NORMAL.equals(oneselExperimental.getDelFlag())){
				if (StringUtils.isBlank(oneselExperimental.getId())){
					oneselExperimental.setOneself(oneself);
					oneselExperimental.preInsert();
					oneselExperimentalDao.insert(oneselExperimental);
				}else{
					oneselExperimental.preUpdate();
					oneselExperimentalDao.update(oneselExperimental);
				}
			}else{
				oneselExperimentalDao.delete(oneselExperimental);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Oneself oneself) {
		super.delete(oneself);
		oneselExperimentalDao.delete(new OneselExperimental(oneself));
	}
	
}