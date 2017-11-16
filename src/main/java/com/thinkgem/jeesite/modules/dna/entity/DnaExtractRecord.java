/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipment;
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 提取室记录Entity
 * @author yunyun
 * @version 2017-08-19
 */
public class DnaExtractRecord extends DataEntity<DnaExtractRecord> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 编码
	private SynthLab lab;		// 实验室id
	private SynthEquipment equipment;	// 设备ID
	private String temperature;		// 温度
	private String humidity;		// 相对湿度
	private String refrigeratorTemperature;		// 冰箱温度
	private String beforeTrial;		// 实验前
	private String instrumentUse;		// 仪器设备使用
	private String biosafetyCabinet;		// 生物安全柜/超净台
	private String finalTemplate;		// 最后模版溶液体积
	private String extractedTemplate;		// 提取的模板存放处
	private String heatingProcess;		// 加热过程
	private String storageTemperature;		// 存放温度
	private String extractors;		// 提取者
	private String reviewer;		// 复核人
	private String transferTemplate;		// 模板交接
	private String recipient;		// 接收人
	private String templateHandoverDate;		// 模板交接日期
	private String other;		// 其他
	private String otherone;		// 其他1
	private String othertow;		// 其他2
	private String othertr;		// 其它3
	private String otherfour;		// 其它4
	private String otherfive;		// 其它5
	private User operator;
	private List<DnaExtractRecordItem> dnaExtractRecordItemList = Lists.newArrayList();		// 子表列表
	private List<SynthEquipment>synthEquipments= Lists.newArrayList();
	
	
	
	
	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public List<SynthEquipment> getSynthEquipments() {
		return synthEquipments;
	}

	public void setSynthEquipments(List<SynthEquipment> synthEquipments) {
		this.synthEquipments = synthEquipments;
	}

	public DnaExtractRecord() {
		super();
	}

	public DnaExtractRecord(String id){
		super(id);
	}

	@Length(min=0, max=255, message="编码长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	@Length(min=0, max=255, message="温度长度必须介于 0 和 255 之间")
	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	@Length(min=0, max=255, message="相对湿度长度必须介于 0 和 255 之间")
	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	
	@Length(min=0, max=255, message="冰箱温度长度必须介于 0 和 255 之间")
	public String getRefrigeratorTemperature() {
		return refrigeratorTemperature;
	}

	public void setRefrigeratorTemperature(String refrigeratorTemperature) {
		this.refrigeratorTemperature = refrigeratorTemperature;
	}
	
	@Length(min=0, max=255, message="实验前长度必须介于 0 和 255 之间")
	public String getBeforeTrial() {
		return beforeTrial;
	}

	public void setBeforeTrial(String beforeTrial) {
		this.beforeTrial = beforeTrial;
	}
	
	@Length(min=0, max=255, message="仪器设备使用长度必须介于 0 和 255 之间")
	public String getInstrumentUse() {
		return instrumentUse;
	}

	public void setInstrumentUse(String instrumentUse) {
		this.instrumentUse = instrumentUse;
	}
	
	@Length(min=0, max=255, message="生物安全柜/超净台长度必须介于 0 和 255 之间")
	public String getBiosafetyCabinet() {
		return biosafetyCabinet;
	}

	public void setBiosafetyCabinet(String biosafetyCabinet) {
		this.biosafetyCabinet = biosafetyCabinet;
	}
	
	@Length(min=0, max=255, message="最后模版溶液体积长度必须介于 0 和 255 之间")
	public String getFinalTemplate() {
		return finalTemplate;
	}

	public void setFinalTemplate(String finalTemplate) {
		this.finalTemplate = finalTemplate;
	}
	
	@Length(min=0, max=255, message="提取的模板存放处长度必须介于 0 和 255 之间")
	public String getExtractedTemplate() {
		return extractedTemplate;
	}

	public void setExtractedTemplate(String extractedTemplate) {
		this.extractedTemplate = extractedTemplate;
	}
	
	@Length(min=0, max=255, message="加热过程长度必须介于 0 和 255 之间")
	public String getHeatingProcess() {
		return heatingProcess;
	}

	public void setHeatingProcess(String heatingProcess) {
		this.heatingProcess = heatingProcess;
	}
	
	@Length(min=0, max=255, message="存放温度长度必须介于 0 和 255 之间")
	public String getStorageTemperature() {
		return storageTemperature;
	}

	public void setStorageTemperature(String storageTemperature) {
		this.storageTemperature = storageTemperature;
	}
	
	@Length(min=0, max=255, message="提取者长度必须介于 0 和 255 之间")
	public String getExtractors() {
		return extractors;
	}

	public void setExtractors(String extractors) {
		this.extractors = extractors;
	}
	
	@Length(min=0, max=255, message="复核人长度必须介于 0 和 255 之间")
	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	
	@Length(min=0, max=255, message="模板交接长度必须介于 0 和 255 之间")
	public String getTransferTemplate() {
		return transferTemplate;
	}

	public void setTransferTemplate(String transferTemplate) {
		this.transferTemplate = transferTemplate;
	}
	
	@Length(min=0, max=255, message="接收人长度必须介于 0 和 255 之间")
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	@Length(min=0, max=255, message="模板交接日期长度必须介于 0 和 255 之间")
	public String getTemplateHandoverDate() {
		return templateHandoverDate;
	}

	public void setTemplateHandoverDate(String templateHandoverDate) {
		this.templateHandoverDate = templateHandoverDate;
	}
	
	@Length(min=0, max=255, message="其他长度必须介于 0 和 255 之间")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	@Length(min=0, max=255, message="其他1长度必须介于 0 和 255 之间")
	public String getOtherone() {
		return otherone;
	}

	public void setOtherone(String otherone) {
		this.otherone = otherone;
	}
	
	@Length(min=0, max=255, message="其他2长度必须介于 0 和 255 之间")
	public String getOthertow() {
		return othertow;
	}

	public void setOthertow(String othertow) {
		this.othertow = othertow;
	}
	
	@Length(min=0, max=255, message="其它3长度必须介于 0 和 255 之间")
	public String getOthertr() {
		return othertr;
	}

	public void setOthertr(String othertr) {
		this.othertr = othertr;
	}
	
	@Length(min=0, max=255, message="其它4长度必须介于 0 和 255 之间")
	public String getOtherfour() {
		return otherfour;
	}

	public void setOtherfour(String otherfour) {
		this.otherfour = otherfour;
	}
	
	@Length(min=0, max=255, message="其它5长度必须介于 0 和 255 之间")
	public String getOtherfive() {
		return otherfive;
	}

	public void setOtherfive(String otherfive) {
		this.otherfive = otherfive;
	}
	
	public List<DnaExtractRecordItem> getDnaExtractRecordItemList() {
		return dnaExtractRecordItemList;
	}

	public void setDnaExtractRecordItemList(List<DnaExtractRecordItem> dnaExtractRecordItemList) {
		this.dnaExtractRecordItemList = dnaExtractRecordItemList;
	}

	public SynthLab getLab() {
		return lab;
	}

	public void setLab(SynthLab lab) {
		this.lab = lab;
	}

	public SynthEquipment getEquipment() {
		return equipment;
	}

	public void setEquipment(SynthEquipment equipment) {
		this.equipment = equipment;
	}
	
	
}