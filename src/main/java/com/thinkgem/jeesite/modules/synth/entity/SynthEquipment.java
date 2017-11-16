/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设备管理Entity
 * @author zhuguli
 * @version 2017-05-13
 */
public class SynthEquipment extends DataEntity<SynthEquipment> {
	
	private static final long serialVersionUID = 1L;
	private SynthLab lab;		// lab_id
	private String code;		// 编码
	private String name;		// 名称
	private Date purchaseTime;		// 采购时间
	private String manufacturer;		// 生产厂家
	private String dealer;		// 经销商
	
	
	public SynthEquipment() {
		super();
	}
	public SynthEquipment(String id){
		super(id);
	}
	public SynthLab getLab() {
		return lab;
	}

	public void setLab(SynthLab lab) {
		this.lab = lab;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	
	@Length(min=0, max=255, message="生产厂家长度必须介于 0 和 255 之间")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@Length(min=0, max=255, message="经销商长度必须介于 0 和 255 之间")
	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	
}