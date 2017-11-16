/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;

/**
 * 物证登记Entity
 * @author zhuguli
 * @version 2017-05-10
 */
public class SpecimenMaterialRegister extends ActEntity<SpecimenMaterialRegister> {
	
	private static final long serialVersionUID = 1L;
	private EntrustRegister entrustRegister;		// register_id
	private String code;		// 编码
	private String pic;		// 图片
	private Integer itemCount;		// 子项数量
	private Integer totalQty;		// 总量
	private List<SpecimenMaterialRegisterItem> specimenMaterialRegisterItemList = Lists.newArrayList();		// 子表列表
	
	public SpecimenMaterialRegister() {
		super();
	}

	public SpecimenMaterialRegister(String id){
		super(id);
	}

	public EntrustRegister getEntrustRegister() {
		return entrustRegister;
	}

	public void setEntrustRegister(EntrustRegister entrustRegister) {
		this.entrustRegister = entrustRegister;
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
	
	public List<SpecimenMaterialRegisterItem> getSpecimenMaterialRegisterItemList() {
		return specimenMaterialRegisterItemList;
	}

	public void setSpecimenMaterialRegisterItemList(List<SpecimenMaterialRegisterItem> specimenMaterialRegisterItemList) {
		this.specimenMaterialRegisterItemList = specimenMaterialRegisterItemList;
	}
}