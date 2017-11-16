/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipment;
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 试剂配制记录表Entity
 * @author fyun
 * @version 2017-08-22
 */
public class DnaPreparationReagents extends DataEntity<DnaPreparationReagents> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 编码
	private SynthLab lab;		// 实验室id
	private String temperature;		// 温度
	private String humidity;		// 相对湿度
	private String refrigeratorTemperature;		// 冰箱温度
	private String other;		// 其他
	private String otherone;		// 其他1
	private String othertow;		// 其他2
	private String othertr;		// 其它3
	private String otherfour;		// 其它4
	private String otherfive;		// 其它5
	private String pcrReagentSource;		// PCR试剂来源
	private String first;		// first
	private String second;		// second
	private String third;		// third
	private String fourth;		// fourth
	private String fifth;		// fifth
	private String nAddOne;		// n+1
	private String totalOne;		// 总数1
	private String totalTwo;		// 总数2
	private String totalThree;		// 总数3
	private String totalFour;		// 总数4
	private String totalFive;		// 总数5
	private String instrumentUse;		// 仪器设备使用
	private String userDate;		// 日期
	private String surveyor;		// 检验人
	private String reviewer;		// 复核人
	private String beforeTrial;		// 实验前
	private User operator;
	private List<DnaPreparationReagentsIteam> dnaPreparationReagentsIteamList = Lists.newArrayList();		// 子表列表
	private List<SynthEquipment>synthEquipments= Lists.newArrayList();
	
	
	public List<DnaPreparationReagentsIteam> getDnaPreparationReagentsIteamList() {
		return dnaPreparationReagentsIteamList;
	}

	public void setDnaPreparationReagentsIteamList(List<DnaPreparationReagentsIteam> dnaPreparationReagentsIteamList) {
		this.dnaPreparationReagentsIteamList = dnaPreparationReagentsIteamList;
	}

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

	public DnaPreparationReagents() {
		super();
	}

	public DnaPreparationReagents(String id){
		super(id);
	}

	@Length(min=0, max=255, message="编码长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	public SynthLab getLab() {
		return lab;
	}

	public void setLab(SynthLab lab) {
		this.lab = lab;
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
	
	@Length(min=0, max=255, message="PCR试剂来源长度必须介于 0 和 255 之间")
	public String getPcrReagentSource() {
		return pcrReagentSource;
	}

	public void setPcrReagentSource(String pcrReagentSource) {
		this.pcrReagentSource = pcrReagentSource;
	}
	
	@Length(min=0, max=255, message="first长度必须介于 0 和 255 之间")
	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}
	
	@Length(min=0, max=255, message="second长度必须介于 0 和 255 之间")
	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}
	
	@Length(min=0, max=255, message="third长度必须介于 0 和 255 之间")
	public String getThird() {
		return third;
	}

	public void setThird(String third) {
		this.third = third;
	}
	
	@Length(min=0, max=255, message="fourth长度必须介于 0 和 255 之间")
	public String getFourth() {
		return fourth;
	}

	public void setFourth(String fourth) {
		this.fourth = fourth;
	}
	
	@Length(min=0, max=255, message="fifth长度必须介于 0 和 255 之间")
	public String getFifth() {
		return fifth;
	}

	public void setFifth(String fifth) {
		this.fifth = fifth;
	}
	
	@Length(min=0, max=255, message="n+1长度必须介于 0 和 255 之间")
	public String getNAddOne() {
		return nAddOne;
	}

	public void setNAddOne(String nAddOne) {
		this.nAddOne = nAddOne;
	}
	
	@Length(min=0, max=255, message="总数1长度必须介于 0 和 255 之间")
	public String getTotalOne() {
		return totalOne;
	}

	public void setTotalOne(String totalOne) {
		this.totalOne = totalOne;
	}
	
	@Length(min=0, max=255, message="总数2长度必须介于 0 和 255 之间")
	public String getTotalTwo() {
		return totalTwo;
	}

	public void setTotalTwo(String totalTwo) {
		this.totalTwo = totalTwo;
	}
	
	@Length(min=0, max=255, message="总数3长度必须介于 0 和 255 之间")
	public String getTotalThree() {
		return totalThree;
	}

	public void setTotalThree(String totalThree) {
		this.totalThree = totalThree;
	}
	
	@Length(min=0, max=255, message="总数4长度必须介于 0 和 255 之间")
	public String getTotalFour() {
		return totalFour;
	}

	public void setTotalFour(String totalFour) {
		this.totalFour = totalFour;
	}
	
	@Length(min=0, max=255, message="总数5长度必须介于 0 和 255 之间")
	public String getTotalFive() {
		return totalFive;
	}

	public void setTotalFive(String totalFive) {
		this.totalFive = totalFive;
	}
	
	@Length(min=0, max=255, message="仪器设备使用长度必须介于 0 和 255 之间")
	public String getInstrumentUse() {
		return instrumentUse;
	}

	public void setInstrumentUse(String instrumentUse) {
		this.instrumentUse = instrumentUse;
	}
	
	@Length(min=0, max=255, message="日期长度必须介于 0 和 255 之间")
	public String getUserDate() {
		return userDate;
	}

	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}
	
	@Length(min=0, max=255, message="检验人长度必须介于 0 和 255 之间")
	public String getSurveyor() {
		return surveyor;
	}

	public void setSurveyor(String surveyor) {
		this.surveyor = surveyor;
	}
	
	@Length(min=0, max=255, message="复核人长度必须介于 0 和 255 之间")
	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	
	@Length(min=0, max=255, message="实验前长度必须介于 0 和 255 之间")
	public String getBeforeTrial() {
		return beforeTrial;
	}

	public void setBeforeTrial(String beforeTrial) {
		this.beforeTrial = beforeTrial;
	}
	
}