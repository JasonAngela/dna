/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 提取室记录Entity
 * @author yunyun
 * @version 2017-08-19
 */
public class DnaExtractRecordItem extends DataEntity<DnaExtractRecordItem> {
	
	private static final long serialVersionUID = 1L;
	private DnaExtractRecord record;		// record_id 父类
	private String extractRounds;		// 提取轮次
	private String sampleNumber;		// 样品编号
	private String checkTypes;		// 检材类型
	private String basedAmount;		// 取材量
	private String basedAmountOther;		// 取材量其他
	private String extractWay;		// 提取方式
	private String extractWayother;		// 提取方式其他
	private String reagentBatches;		// 试剂批号
	private String extractionMethod;		// 核酸抽提方法
	
	public DnaExtractRecordItem() {
		super();
	}

	public DnaExtractRecordItem(String id){
		super(id);
	}

	public DnaExtractRecordItem(DnaExtractRecord record){
		this.record = record;
	}

	@Length(min=1, max=64, message="record_id长度必须介于 1 和 64 之间")
	public DnaExtractRecord getRecord() {
		return record;
	}

	public void setRecord(DnaExtractRecord record) {
		this.record = record;
	}
	
	@Length(min=0, max=255, message="提取轮次长度必须介于 0 和 255 之间")
	public String getExtractRounds() {
		return extractRounds;
	}

	public void setExtractRounds(String extractRounds) {
		this.extractRounds = extractRounds;
	}
	
	@Length(min=0, max=255, message="样品编号长度必须介于 0 和 255 之间")
	public String getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}
	
	@Length(min=0, max=255, message="检材类型长度必须介于 0 和 255 之间")
	public String getCheckTypes() {
		return checkTypes;
	}

	public void setCheckTypes(String checkTypes) {
		this.checkTypes = checkTypes;
	}
	
	@Length(min=0, max=255, message="取材量长度必须介于 0 和 255 之间")
	public String getBasedAmount() {
		return basedAmount;
	}

	public void setBasedAmount(String basedAmount) {
		this.basedAmount = basedAmount;
	}
	
	@Length(min=0, max=255, message="取材量其他长度必须介于 0 和 255 之间")
	public String getBasedAmountOther() {
		return basedAmountOther;
	}

	public void setBasedAmountOther(String basedAmountOther) {
		this.basedAmountOther = basedAmountOther;
	}
	
	@Length(min=0, max=255, message="提取方式长度必须介于 0 和 255 之间")
	public String getExtractWay() {
		return extractWay;
	}

	public void setExtractWay(String extractWay) {
		this.extractWay = extractWay;
	}
	
	@Length(min=0, max=255, message="提取方式其他长度必须介于 0 和 255 之间")
	public String getExtractWayother() {
		return extractWayother;
	}

	public void setExtractWayother(String extractWayother) {
		this.extractWayother = extractWayother;
	}
	
	@Length(min=0, max=255, message="试剂批号长度必须介于 0 和 255 之间")
	public String getReagentBatches() {
		return reagentBatches;
	}

	public void setReagentBatches(String reagentBatches) {
		this.reagentBatches = reagentBatches;
	}
	
	@Length(min=0, max=255, message="核酸抽提方法长度必须介于 0 和 255 之间")
	public String getExtractionMethod() {
		return extractionMethod;
	}

	public void setExtractionMethod(String extractionMethod) {
		this.extractionMethod = extractionMethod;
	}
	
}