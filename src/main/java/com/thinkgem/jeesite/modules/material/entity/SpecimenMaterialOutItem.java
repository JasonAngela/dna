/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 物料领取Entity
 * @author zhuguli
 * @version 2017-05-10
 */
public class SpecimenMaterialOutItem extends DataEntity<SpecimenMaterialOutItem> {
	
	private static final long serialVersionUID = 1L;
	private SpecimenMaterialOut materialOut;		// material_out_id 父类
	private String abstractsId;		// 摘要id
	private String code;		// 编码
	private String clientCode;		// 样品编码
	private String materialType;		// 样品类型
	private Integer qty;		// 数量
	private String measure;		// 度量
	
	public SpecimenMaterialOutItem() {
		super();
	}

	public SpecimenMaterialOutItem(String id){
		super(id);
	}

	public SpecimenMaterialOutItem(SpecimenMaterialOut materialOut){
		this.materialOut = materialOut;
	}

	@Length(min=1, max=64, message="material_out_id长度必须介于 1 和 64 之间")
	public SpecimenMaterialOut getMaterialOut() {
		return materialOut;
	}

	public void setMaterialOut(SpecimenMaterialOut materialOut) {
		this.materialOut = materialOut;
	}
	
	@Length(min=0, max=64, message="摘要id长度必须介于 0 和 64 之间")
	public String getAbstractsId() {
		return abstractsId;
	}

	public void setAbstractsId(String abstractsId) {
		this.abstractsId = abstractsId;
	}
	
	@Length(min=0, max=255, message="编码长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=255, message="样品编码长度必须介于 0 和 255 之间")
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	
	@Length(min=0, max=1, message="样品类型长度必须介于 0 和 1 之间")
	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	
	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	@Length(min=0, max=255, message="度量长度必须介于 0 和 255 之间")
	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}
	
}