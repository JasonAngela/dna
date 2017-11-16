/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.synth.entity.SynthGoodsCategory;
import com.thinkgem.jeesite.modules.synth.dao.SynthGoodsCategoryDao;

/**
 * 药品分类Service
 * @author zhuguli
 * @version 2017-05-13
 */
@Service
@Transactional(readOnly = true)
public class SynthGoodsCategoryService extends CrudService<SynthGoodsCategoryDao, SynthGoodsCategory> {

	public SynthGoodsCategory get(String id) {
		return super.get(id);
	}
	
	public List<SynthGoodsCategory> findList(SynthGoodsCategory synthGoodsCategory) {
		return super.findList(synthGoodsCategory);
	}
	
	public Page<SynthGoodsCategory> findPage(Page<SynthGoodsCategory> page, SynthGoodsCategory synthGoodsCategory) {
		return super.findPage(page, synthGoodsCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(SynthGoodsCategory synthGoodsCategory) {
		super.save(synthGoodsCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(SynthGoodsCategory synthGoodsCategory) {
		super.delete(synthGoodsCategory);
	}
	
}