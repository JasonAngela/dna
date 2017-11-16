/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * dna日常记录Entity
 * @author fuyun
 * @version 2017-08-31
 */
public class DnaDaily extends DataEntity<DnaDaily> {
	
	private static final long serialVersionUID = 1L;
	private User operator;		// operator
	private List<DnaDailyItem> dnaDailyItemList = Lists.newArrayList();		// 子表列表
	
	public DnaDaily() {
		super();
	}

	public DnaDaily(String id){
		super(id);
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}
	
	public List<DnaDailyItem> getDnaDailyItemList() {
		return dnaDailyItemList;
	}

	public void setDnaDailyItemList(List<DnaDailyItem> dnaDailyItemList) {
		this.dnaDailyItemList = dnaDailyItemList;
	}
}