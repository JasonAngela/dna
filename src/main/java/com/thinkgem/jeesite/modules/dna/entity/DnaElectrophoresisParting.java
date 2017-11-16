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
 * 电泳室Entity
 * @author fuyun
 * @version 2017-08-25
 */
public class DnaElectrophoresisParting extends DataEntity<DnaElectrophoresisParting> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 编码
	private SynthLab lab;		// 实验室id
	private String temperature;		// 温度
	private String humidity;		// 相对湿度
	private String refrigeratorTemperature;		// 冰箱温度
	private String formamideBatchNumber;		// 甲酰胺批号
	private String validated;		// 是否已验证有效
	private String validated1;		// 是否已验证有效1
	private String internalStandard; // 内标
	private String product;		// 产物取量和处理
	private String positiveControl;		// 阳性对照
	private String negativeControl;		// 阴性对照
	private List<SynthEquipment>synthEquipments= Lists.newArrayList();
	private List<DnaElectrophoresisPartingIteam> dnaElectrophoresisPartingIteamList = Lists.newArrayList();	
	private User operator;
	
	
	
	public List<DnaElectrophoresisPartingIteam> getDnaElectrophoresisPartingIteamList() {
		return dnaElectrophoresisPartingIteamList;
	}
	public void setDnaElectrophoresisPartingIteamList(
			List<DnaElectrophoresisPartingIteam> dnaElectrophoresisPartingIteamList) {
		this.dnaElectrophoresisPartingIteamList = dnaElectrophoresisPartingIteamList;
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

	public DnaElectrophoresisParting() {
		super();
	}

	public DnaElectrophoresisParting(String id){
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
	
	@Length(min=0, max=255, message="甲酰胺批号长度必须介于 0 和 255 之间")
	public String getFormamideBatchNumber() {
		return formamideBatchNumber;
	}

	public void setFormamideBatchNumber(String formamideBatchNumber) {
		this.formamideBatchNumber = formamideBatchNumber;
	}
	
	@Length(min=0, max=1, message="是否已验证有效长度必须介于 0 和 1 之间")
	public String getValidated() {
		return validated;
	}

	public void setValidated(String validated) {
		this.validated = validated;
	}
	
	@Length(min=0, max=1, message="是否已验证有效1长度必须介于 0 和 1 之间")
	public String getValidated1() {
		return validated1;
	}

	public void setValidated1(String validated1) {
		this.validated1 = validated1;
	}
	
	@Length(min=0, max=255, message="内标长度必须介于 0 和 255 之间")
	public String getInternalStandard() {
		return internalStandard;
	}

	public void setInternalStandard(String internalStandard) {
		this.internalStandard = internalStandard;
	}
	
	@Length(min=0, max=255, message="产物取量和处理长度必须介于 0 和 255 之间")
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	@Length(min=0, max=255, message="阳性对照长度必须介于 0 和 255 之间")
	public String getPositiveControl() {
		return positiveControl;
	}

	public void setPositiveControl(String positiveControl) {
		this.positiveControl = positiveControl;
	}
	
	@Length(min=0, max=255, message="阴性对照长度必须介于 0 和 255 之间")
	public String getNegativeControl() {
		return negativeControl;
	}

	public void setNegativeControl(String negativeControl) {
		this.negativeControl = negativeControl;
	}
	
}