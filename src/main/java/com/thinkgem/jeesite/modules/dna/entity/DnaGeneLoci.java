/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 基因座Entity
 * @author zhuguli
 * @version 2017-06-01
 */
public class DnaGeneLoci extends DataEntity<DnaGeneLoci> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private Integer seq;		// 排序
	
	public DnaGeneLoci() {
		super();
	}

	public DnaGeneLoci(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
}