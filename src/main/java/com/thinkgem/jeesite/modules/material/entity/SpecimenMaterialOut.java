/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 物料领取Entity
 * @author zhuguli
 * @version 2017-05-10
 */
public class SpecimenMaterialOut extends DataEntity<SpecimenMaterialOut> {
	
	private static final long serialVersionUID = 1L;
	private SpecimenMaterialRegister materialRegister;		// material_register_id
	private String code;		// 编码
	private String pic;		// 图片
	private Integer itemCount;		// 子项数量
	private Integer totalQty;		// 总量
	private String status;		// 状态
	private List<SpecimenMaterialOutItem> specimenMaterialOutItemList = Lists.newArrayList();		// 子表列表
	
	public SpecimenMaterialOut() {
		super();
	}

	public SpecimenMaterialOut(String id){
		super(id);
	}

	public SpecimenMaterialRegister getMaterialRegister() {
		return materialRegister;
	}

	public void setMaterialRegister(SpecimenMaterialRegister materialRegister) {
		this.materialRegister = materialRegister;
	}
	
	@Length(min=0, max=255, message="编码长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	
	public Integer getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<SpecimenMaterialOutItem> getSpecimenMaterialOutItemList() {
		return specimenMaterialOutItemList;
	}

	public void setSpecimenMaterialOutItemList(List<SpecimenMaterialOutItem> specimenMaterialOutItemList) {
		this.specimenMaterialOutItemList = specimenMaterialOutItemList;
	}
}