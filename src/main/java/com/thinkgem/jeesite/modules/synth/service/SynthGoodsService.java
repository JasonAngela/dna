/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.synth.entity.SynthGoods;
import com.thinkgem.jeesite.modules.synth.dao.SynthGoodsDao;

/**
 * 药品管理Service
 * @author zhuguli
 * @version 2017-05-13
 */
@Service
@Transactional(readOnly = true)
public class SynthGoodsService extends CrudService<SynthGoodsDao, SynthGoods> {

	public SynthGoods get(String id) {
		return super.get(id);
	}
	
	public List<SynthGoods> findList(SynthGoods synthGoods) {
		return super.findList(synthGoods);
	}
	
	public Page<SynthGoods> findPage(Page<SynthGoods> page, SynthGoods synthGoods) {
		return super.findPage(page, synthGoods);
	}
	
	@Transactional(readOnly = false)
	public void save(SynthGoods synthGoods) {
		super.save(synthGoods);
	}
	
	@Transactional(readOnly = false)
	public void delete(SynthGoods synthGoods) {
		super.delete(synthGoods);
	}
	
}