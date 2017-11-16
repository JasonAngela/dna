/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品库存Entity
 * @author zhuguli
 * @version 2017-05-13
 */
public class SynthGoodsStock extends DataEntity<SynthGoodsStock> {
	
	private static final long serialVersionUID = 1L;
	private SynthGoods  goods;		// goods_id
	private String batchNumber;		// batch_number
	private String manufacturer;		// manufacturer
	private Double qty;		// qty
	
	public SynthGoodsStock() {
		super();
	}

	public SynthGoodsStock(String id){
		super(id);
	}

	public SynthGoods getGoods() {
		return goods;
	}

	public void setGoods(SynthGoods goods) {
		this.goods = goods;
	}
	
	@Length(min=0, max=255, message="batch_number长度必须介于 0 和 255 之间")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	@Length(min=0, max=255, message="manufacturer长度必须介于 0 和 255 之间")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}
	
}