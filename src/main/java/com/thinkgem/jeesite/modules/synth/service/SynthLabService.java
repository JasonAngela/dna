/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;
import com.thinkgem.jeesite.modules.synth.dao.SynthLabDao;

/**
 * 实验室Service
 * @author zhuguli
 * @version 2017-05-13
 */
@Service
@Transactional(readOnly = true)
public class SynthLabService extends CrudService<SynthLabDao, SynthLab> {

	public SynthLab get(String id) {
		return super.get(id);
	}
	
	public List<SynthLab> findList(SynthLab synthLab) {
		return super.findList(synthLab);
	}
	
	public Page<SynthLab> findPage(Page<SynthLab> page, SynthLab synthLab) {
		return super.findPage(page, synthLab);
	}
	
	@Transactional(readOnly = false)
	public void save(SynthLab synthLab) {
		super.save(synthLab);
	}
	
	@Transactional(readOnly = false)
	public void delete(SynthLab synthLab) {
		super.delete(synthLab);
	}
	
}