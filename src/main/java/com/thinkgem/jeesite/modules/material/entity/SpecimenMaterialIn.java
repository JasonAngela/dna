/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 样品入库Entity
 * @author doonly
 * @version 2017-06-01
 */
public class SpecimenMaterialIn extends DataEntity<SpecimenMaterialIn> {
	
	private static final long serialVersionUID = 1L;
	private SpecimenMaterialRegister materialRegister;		// 物证登记
	private String code;		// 编码
	private String pic;		// 图片
	private Integer itemCount;		// 子项数量
	private Integer totalQty;		// 总量
	private String status;		// 状态
	private List<SpecimenMaterialInItem> specimenMaterialInItemList = Lists.newArrayList();		// 子表列表
	
	public SpecimenMaterialIn() {
		super();
	}

	public SpecimenMaterialIn(String id){
		super(id);
	}

	@NotNull(message="物证登记不能为空")
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
	
	public List<SpecimenMaterialInItem> getSpecimenMaterialInItemList() {
		return specimenMaterialInItemList;
	}

	public void setSpecimenMaterialInItemList(List<SpecimenMaterialInItem> specimenMaterialInItemList) {
		this.specimenMaterialInItemList = specimenMaterialInItemList;
	}
}