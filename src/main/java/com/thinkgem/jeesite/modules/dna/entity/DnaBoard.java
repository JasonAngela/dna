/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 电泳分型板Entity
 * @author zhuguli
 * @version 2017-06-08
 */
public class DnaBoard extends DataEntity<DnaBoard> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 编码
	private List<DnaBoardJgg> dnaBoardJggList = Lists.newArrayList();		// 子表列表
	
	public DnaBoard() {
		super();
	}

	public DnaBoard(String id){
		super(id);
	}

	@Length(min=0, max=255, message="编码长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public List<DnaBoardJgg> getDnaBoardJggList() {
		return dnaBoardJggList;
	}

	public void setDnaBoardJggList(List<DnaBoardJgg> dnaBoardJggList) {
		this.dnaBoardJggList = dnaBoardJggList;
	}
}