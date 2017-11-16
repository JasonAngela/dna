/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 物证登记Entity
 * @author zhuguli
 * @version 2017-05-10
 */
public class SpecimenMaterialRegisterItem extends DataEntity<SpecimenMaterialRegisterItem> {
	
	private static final long serialVersionUID = 1L;
	private SpecimenMaterialRegister materialRegister;		// material_register_id 父类
	private String abstracts;		// abstracts
	private String code;		// 编码
	private String clientCode;		// client_code
	private String materialType;		// 样品类型
	private Integer qty;		// 数量
	private String measure;		// 度量
	private String pic;		// 图片
	private Integer inQty;		// 入库数量
	private Integer outQty;		// 出库数量
	private Integer leftQty;		// 剩余数量
	
	public SpecimenMaterialRegisterItem(){
		super();
	}

	public SpecimenMaterialRegisterItem(String id){
		super(id);
	}

	public SpecimenMaterialRegisterItem(SpecimenMaterialRegister materialRegister){
		this.materialRegister = materialRegister;
	}

	@Length(min=1, max=64, message="material_register_id长度必须介于 1 和 64 之间")
	public SpecimenMaterialRegister getMaterialRegister() {
		return materialRegister;
	}

	public void setMaterialRegister(SpecimenMaterialRegister materialRegister) {
		this.materialRegister = materialRegister;
	}
	
	@Length(min=1, max=64, message="abstracts长度必须介于 0 和 64 之间")
	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
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
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public Integer getInQty() {
		return inQty;
	}

	public void setInQty(Integer inQty) {
		this.inQty = inQty;
	}
	
	public Integer getOutQty() {
		return outQty;
	}

	public void setOutQty(Integer outQty) {
		this.outQty = outQty;
	}
	
	public Integer getLeftQty() {
		return leftQty;
	}

	public void setLeftQty(Integer leftQty) {
		this.leftQty = leftQty;
	}
	
}