/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.SysSequence;
import com.thinkgem.jeesite.modules.sys.dao.SysSequenceDao;

/**
 * 序列Service
 * @author zhuguli
 * @version 2017-05-12
 */
@Service
@Transactional(readOnly = true)
public class SysSequenceService extends CrudService<SysSequenceDao, SysSequence> {

	public SysSequence get(String id) {
		return super.get(id);
	}
	
	public List<SysSequence> findList(SysSequence sysSequence) {
		return super.findList(sysSequence);
	}
	
	public Page<SysSequence> findPage(Page<SysSequence> page, SysSequence sysSequence) {
		return super.findPage(page, sysSequence);
	}
	
	@Transactional(readOnly = false)
	public void save(SysSequence sysSequence) {
		super.save(sysSequence);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysSequence sysSequence) {
		super.delete(sysSequence);
	}
	@Transactional(readOnly = false)
	public synchronized Long getCurrentValue(String id) {
		SysSequence sysSequence = get(id);
		Long current = sysSequence.getCurrent();
		sysSequence.setCurrent(current+1);
		super.save(sysSequence);
		return current;
	}
	
}