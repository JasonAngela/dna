/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialOutDao;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialOutItemDao;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialRegisterItemDao;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialOut;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialOutItem;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegisterItem;
import com.thinkgem.jeesite.modules.sys.service.SysCodeRuleService;

/**
 * 物料领取Service
 * @author zhuguli
 * @version 2017-05-10
 */
@Service
@Transactional(readOnly = true)
public class SpecimenMaterialOutService extends CrudService<SpecimenMaterialOutDao, SpecimenMaterialOut> {

	@Autowired
	private SpecimenMaterialOutItemDao specimenMaterialOutItemDao;
	@Autowired
	private SpecimenMaterialRegisterItemDao specimenMaterialRegisterItemDao;
	@Autowired
	private SysCodeRuleService sysCodeRuleService;
	public SpecimenMaterialOut get(String id) {
		SpecimenMaterialOut specimenMaterialOut = super.get(id);
		specimenMaterialOut.setSpecimenMaterialOutItemList(specimenMaterialOutItemDao.findList(new SpecimenMaterialOutItem(specimenMaterialOut)));
		return specimenMaterialOut;
	}

	public List<SpecimenMaterialOut> findList(SpecimenMaterialOut specimenMaterialOut) {
		return super.findList(specimenMaterialOut);
	}

	public Page<SpecimenMaterialOut> findPage(Page<SpecimenMaterialOut> page, SpecimenMaterialOut specimenMaterialOut) {
		return super.findPage(page, specimenMaterialOut);
	}

	@Transactional(readOnly = false)
	public void save(SpecimenMaterialOut specimenMaterialOut) {
		super.save(specimenMaterialOut);
		for (SpecimenMaterialOutItem specimenMaterialOutItem : specimenMaterialOut.getSpecimenMaterialOutItemList()){
			if (SpecimenMaterialOutItem.DEL_FLAG_NORMAL.equals(specimenMaterialOutItem.getDelFlag())){
				if (StringUtils.isBlank(specimenMaterialOutItem.getId())){
					specimenMaterialOutItem.setMaterialOut(specimenMaterialOut);
					specimenMaterialOutItem.preInsert();
					specimenMaterialOutItemDao.insert(specimenMaterialOutItem);
				}else{
					specimenMaterialOutItem.preUpdate();
					specimenMaterialOutItemDao.update(specimenMaterialOutItem);
				}
			}else{
				specimenMaterialOutItemDao.delete(specimenMaterialOutItem);
			}
		}
	}

	@Transactional(readOnly = false)
	public void delete(SpecimenMaterialOut specimenMaterialOut) {
		super.delete(specimenMaterialOut);
		specimenMaterialOutItemDao.delete(new SpecimenMaterialOutItem(specimenMaterialOut));
	}
	@Transactional(readOnly = false)
	public void save(String[] ids) {
		SpecimenMaterialOut specimenMaterialOut = new SpecimenMaterialOut();
		String code = sysCodeRuleService.generateCode("material_out_code");
		specimenMaterialOut.setCode(code);
		List<SpecimenMaterialOutItem>itemList = new ArrayList<SpecimenMaterialOutItem>();
		for(String id:ids){
			SpecimenMaterialRegisterItem registerItem = specimenMaterialRegisterItemDao.get(id);
			SpecimenMaterialOutItem outItem = new SpecimenMaterialOutItem();
			outItem.setCode(registerItem.getCode());
			outItem.setClientCode(registerItem.getClientCode());
			outItem.setMaterialType(registerItem.getMaterialType());
			outItem.setQty(1);
			itemList.add(outItem);
		}
		specimenMaterialOut.setSpecimenMaterialOutItemList(itemList);
		this.save(specimenMaterialOut );
	}

}