/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.xueka.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 血卡Entity
 * @author fuyunyou
 * @version 2017-06-06
 */
public class SpecimenXueka extends DataEntity<SpecimenXueka> {
	
	private static final long serialVersionUID = 1L;
	private String xuekaId;		// xueka_id
	private String materialRegisterItemId;		// material_register_item_id
	
	public SpecimenXueka() {
		super();
	}

	public SpecimenXueka(String id){
		super(id);
	}

	@Length(min=0, max=255, message="xueka_id长度必须介于 0 和 255 之间")
	public String getXuekaId() {
		return xuekaId;
	}

	public void setXuekaId(String xuekaId) {
		this.xuekaId = xuekaId;
	}
	
	@Length(min=0, max=64, message="material_register_item_id长度必须介于 0 和 64 之间")
	public String getMaterialRegisterItemId() {
		return materialRegisterItemId;
	}

	public void setMaterialRegisterItemId(String materialRegisterItemId) {
		this.materialRegisterItemId = materialRegisterItemId;
	}
	
}