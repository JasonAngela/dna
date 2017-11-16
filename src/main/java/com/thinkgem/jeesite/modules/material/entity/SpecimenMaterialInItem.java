/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustAbstracts;

/**
 * 样品入库Entity
 * @author doonly
 * @version 2017-06-01
 */
public class SpecimenMaterialInItem extends DataEntity<SpecimenMaterialInItem> {
	
	private static final long serialVersionUID = 1L;
	private SpecimenMaterialIn materialIn;		// material_in_id 父类
	private EntrustAbstracts abstracts;		// 摘要id
	private String code;		// 编码
	private String clientCode;		// client_code
	private String materialType;		// material_type
	private Integer qty;		// qty
	private String measure;		// 度量
	
	public SpecimenMaterialInItem() {
		super();
	}

	public SpecimenMaterialInItem(String id){
		super(id);
	}

	public SpecimenMaterialInItem(SpecimenMaterialIn materialIn){
		this.materialIn = materialIn;
	}

	@Length(min=1, max=64, message="material_in_id长度必须介于 1 和 64 之间")
	public SpecimenMaterialIn getMaterialIn() {
		return materialIn;
	}

	public void setMaterialIn(SpecimenMaterialIn materialIn) {
		this.materialIn = materialIn;
	}
	
	@NotNull(message="摘要id不能为空")
	public EntrustAbstracts getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(EntrustAbstracts abstracts) {
		this.abstracts = abstracts;
	}
	
	@Length(min=0, max=255, message="编码长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=255, message="client_code长度必须介于 0 和 255 之间")
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	
	@Length(min=0, max=1, message="material_type长度必须介于 0 和 1 之间")
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