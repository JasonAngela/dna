/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oneself.entity;

import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单独实验Entity
 * @author fuyun
 * @version 2017-10-18
 */
public class Oneself extends DataEntity<Oneself> {
	
	private static final long serialVersionUID = 1L;
	private List<OneselExperimental> oneselExperimentalList = Lists.newArrayList();		// 子表列表
	
	public Oneself() {
		super();
	}

	public Oneself(String id){
		super(id);
	}

	public List<OneselExperimental> getOneselExperimentalList() {
		return oneselExperimentalList;
	}

	public void setOneselExperimentalList(List<OneselExperimental> oneselExperimentalList) {
		this.oneselExperimentalList = oneselExperimentalList;
	}
}