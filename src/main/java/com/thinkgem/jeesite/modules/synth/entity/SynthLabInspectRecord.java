/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 实验室检查记录Entity
 * @author zhuguli
 * @version 2017-05-13
 */
public class SynthLabInspectRecord extends DataEntity<SynthLabInspectRecord> {
	
	private static final long serialVersionUID = 1L;
	private SynthLab lab;		// lab_id
	private String temperature;		// 温度
	private String wetness;		// 湿度
	private Date inspectTime;		// 检查时间
	
	public SynthLabInspectRecord() {
		super();
	}

	public SynthLabInspectRecord(String id){
		super(id);
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
	
	@Length(min=0, max=255, message="湿度长度必须介于 0 和 255 之间")
	public String getWetness() {
		return wetness;
	}

	public void setWetness(String wetness) {
		this.wetness = wetness;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInspectTime() {
		return inspectTime;
	}

	public void setInspectTime(Date inspectTime) {
		this.inspectTime = inspectTime;
	}
	
}