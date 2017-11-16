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
import com.thinkgem.jeesite.modules.entrust.entity.EntrustAbstracts;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialInDao;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialInItemDao;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialRegisterDao;
import com.thinkgem.jeesite.modules.material.dao.SpecimenMaterialRegisterItemDao;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialIn;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialInItem;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegister;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegisterItem;

/**
 * 样品入库Service
 * @author doonly
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class SpecimenMaterialInService extends CrudService<SpecimenMaterialInDao, SpecimenMaterialIn> {

	@Autowired
	private SpecimenMaterialInItemDao specimenMaterialInItemDao;
	@Autowired
	private SpecimenMaterialRegisterDao specimenMaterialRegisterDao;
	@Autowired
	private SpecimenMaterialRegisterItemDao specimenMaterialRegisterItemDao;
	@Autowired
	private  SpecimenMaterialInDao specimenMaterialInDao;
	
	
	public SpecimenMaterialIn specimenMaterialInEntrust(String id){
		return specimenMaterialInDao.specimenMaterialInEntrust(id);
	}
	
	public SpecimenMaterialIn get(String id) {
		SpecimenMaterialIn specimenMaterialIn = super.get(id);
		specimenMaterialIn.setSpecimenMaterialInItemList(specimenMaterialInItemDao.findList(new SpecimenMaterialInItem(specimenMaterialIn)));
		return specimenMaterialIn;
	}
	
	public List<SpecimenMaterialIn> findList(SpecimenMaterialIn specimenMaterialIn) {
		return super.findList(specimenMaterialIn);
	}
	
	public Page<SpecimenMaterialIn> findPage(Page<SpecimenMaterialIn> page, SpecimenMaterialIn specimenMaterialIn) {
		return super.findPage(page, specimenMaterialIn);
	}
	
	@Transactional(readOnly = false)
	public void save(SpecimenMaterialIn specimenMaterialIn) {
		super.save(specimenMaterialIn);
		for (SpecimenMaterialInItem specimenMaterialInItem : specimenMaterialIn.getSpecimenMaterialInItemList()){
			if (SpecimenMaterialInItem.DEL_FLAG_NORMAL.equals(specimenMaterialInItem.getDelFlag())){
				if (StringUtils.isBlank(specimenMaterialInItem.getId())){
					specimenMaterialInItem.setMaterialIn(specimenMaterialIn);
					specimenMaterialInItem.preInsert();
					specimenMaterialInItemDao.insert(specimenMaterialInItem);
				}else{
					specimenMaterialInItem.preUpdate();
					specimenMaterialInItemDao.update(specimenMaterialInItem);
				}
			}else{
				specimenMaterialInItemDao.delete(specimenMaterialInItem);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SpecimenMaterialIn specimenMaterialIn) {
		super.delete(specimenMaterialIn);
		specimenMaterialInItemDao.delete(new SpecimenMaterialInItem(specimenMaterialIn));
	}
	@Transactional(readOnly = false)
	public void autoMakeMaterialIn(String businessKey) {
		
		SpecimenMaterialRegister materialRegister =  specimenMaterialRegisterDao.getByEntrustRegisterId(businessKey.replace("entrust_register:", ""));
		SpecimenMaterialIn specimenMaterialIn = new SpecimenMaterialIn();
		specimenMaterialIn.setMaterialRegister(materialRegister);
		specimenMaterialIn.setCode(materialRegister.getCode());
		specimenMaterialIn.setRemarks("自动生成入库单");
		
		Integer totalQty = 0;
		List<SpecimenMaterialInItem> inItemList = new ArrayList<SpecimenMaterialInItem>();
		List<SpecimenMaterialRegisterItem> specimenMaterialRegisterItemlist = specimenMaterialRegisterItemDao.listByParentId(materialRegister.getId());
		for(SpecimenMaterialRegisterItem registerItem:specimenMaterialRegisterItemlist){
			SpecimenMaterialInItem inItem = new SpecimenMaterialInItem();
			inItem.setCode(registerItem.getCode());
			inItem.setClientCode(registerItem.getClientCode());
			inItem.setQty(registerItem.getQty());
			inItem.setMaterialType(registerItem.getMaterialType());
			EntrustAbstracts abstracts = new EntrustAbstracts(registerItem.getAbstracts());
			inItem.setAbstracts(abstracts);
			inItemList.add(inItem);
			totalQty+=inItem.getQty();
		}
		specimenMaterialIn.setSpecimenMaterialInItemList(inItemList);
		specimenMaterialIn.setTotalQty(totalQty);
		specimenMaterialIn.setItemCount(inItemList.size());
		this.save(specimenMaterialIn);
	}
	
}