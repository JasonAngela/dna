/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;

/**
 * str导入记录Entity
 * @author zhuguli
 * @version 2017-05-11
 */
public class DnaStrImport extends ActEntity<DnaStrImport> {
	
	private static final long serialVersionUID = 1L;
	private EntrustRegister register;		// register_id
	private Integer status;		// 状态
	private List<DnaStrImportItem> dnaStrImportItemList = Lists.newArrayList();		// 子表列表
	
	public DnaStrImport() {
		super();
	}

	public DnaStrImport(String id){
		super(id);
	}

	public EntrustRegister getRegister() {
		return register;
	}

	public void setRegister(EntrustRegister register) {
		this.register = register;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public List<DnaStrImportItem> getDnaStrImportItemList() {
		return dnaStrImportItemList;
	}

	public void setDnaStrImportItemList(List<DnaStrImportItem> dnaStrImportItemList) {
		this.dnaStrImportItemList = dnaStrImportItemList;
	}
}