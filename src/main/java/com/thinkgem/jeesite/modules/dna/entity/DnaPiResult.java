/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustAbstracts;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;

/**
 * 亲权指数Entity
 * @author zhuguli
 * @version 2017-07-04
 */
public class DnaPiResult extends DataEntity<DnaPiResult> {
	
	private static final long serialVersionUID = 1L;
	private String parentCode;		// 父编码
	private String childCode;		// 子编码
	private EntrustRegister register;		// 委托id
	private List<DnaPiResultItem> dnaPiResultItemList = Lists.newArrayList();		// 子表列表
	private BigDecimal cpi;
	private BigDecimal rcp;
	private EntrustAbstracts parentAbstracts;
	private EntrustAbstracts childAbstracts;
	
	public DnaPiResult() {
		super();
	}

	public DnaPiResult(String id){
		super(id);
	}

	@Length(min=0, max=255, message="父编码长度必须介于 0 和 255 之间")
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	@Length(min=0, max=255, message="子编码长度必须介于 0 和 255 之间")
	public String getChildCode() {
		return childCode;
	}

	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}
	
	public EntrustRegister getRegister() {
		return register;
	}

	public void setRegister(EntrustRegister register) {
		this.register = register;
	}
	
	public List<DnaPiResultItem> getDnaPiResultItemList() {
		return dnaPiResultItemList;
	}

	public void setDnaPiResultItemList(List<DnaPiResultItem> dnaPiResultItemList) {
		this.dnaPiResultItemList = dnaPiResultItemList;
	}

	public BigDecimal getCpi() {
		return cpi;
	}

	public void setCpi(BigDecimal cpi) {
		this.cpi = cpi;
	}

	public BigDecimal getRcp() {
		return rcp;
	}

	public void setRcp(BigDecimal rcp) {
		this.rcp = rcp;
	}

	public EntrustAbstracts getParentAbstracts() {
		return parentAbstracts;
	}

	public void setParentAbstracts(EntrustAbstracts parentAbstracts) {
		this.parentAbstracts = parentAbstracts;
	}

	public EntrustAbstracts getChildAbstracts() {
		return childAbstracts;
	}

	public void setChildAbstracts(EntrustAbstracts childAbstracts) {
		this.childAbstracts = childAbstracts;
	}
	
}