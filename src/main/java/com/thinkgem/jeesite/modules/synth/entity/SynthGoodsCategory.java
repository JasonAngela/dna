/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品分类Entity
 * @author zhuguli
 * @version 2017-05-13
 */
public class SynthGoodsCategory extends DataEntity<SynthGoodsCategory> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	
	public SynthGoodsCategory() {
		super();
	}

	public SynthGoodsCategory(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}