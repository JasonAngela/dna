/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品管理Entity
 * @author zhuguli
 * @version 2017-05-13
 */
public class SynthGoods extends DataEntity<SynthGoods> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 编码
	private String name;		// 名称
	private String specification;		// 规格
	private SynthGoodsCategory category;		// category_id
	
	public SynthGoods() {
		super();
	}

	public SynthGoods(String id){
		super(id);
	}

	@Length(min=0, max=255, message="编码长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="规格长度必须介于 0 和 255 之间")
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	public SynthGoodsCategory getCategory() {
		return category;
	}

	public void setCategory(SynthGoodsCategory category) {
		this.category = category;
	}
	
}