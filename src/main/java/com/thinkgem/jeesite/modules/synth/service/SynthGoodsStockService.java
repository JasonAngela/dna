/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.synth.entity.SynthGoodsStock;
import com.thinkgem.jeesite.modules.synth.dao.SynthGoodsStockDao;

/**
 * 药品库存Service
 * @author zhuguli
 * @version 2017-05-13
 */
@Service
@Transactional(readOnly = true)
public class SynthGoodsStockService extends CrudService<SynthGoodsStockDao, SynthGoodsStock> {

	public SynthGoodsStock get(String id) {
		return super.get(id);
	}
	
	public List<SynthGoodsStock> findList(SynthGoodsStock synthGoodsStock) {
		return super.findList(synthGoodsStock);
	}
	
	public Page<SynthGoodsStock> findPage(Page<SynthGoodsStock> page, SynthGoodsStock synthGoodsStock) {
		return super.findPage(page, synthGoodsStock);
	}
	
	@Transactional(readOnly = false)
	public void save(SynthGoodsStock synthGoodsStock) {
		super.save(synthGoodsStock);
	}
	
	@Transactional(readOnly = false)
	public void delete(SynthGoodsStock synthGoodsStock) {
		super.delete(synthGoodsStock);
	}
	
}