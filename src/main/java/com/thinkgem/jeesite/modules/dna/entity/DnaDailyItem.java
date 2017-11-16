/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;

/**
 * dna日常记录Entity
 * @author fuyun
 * @version 2017-08-22
 */
public class DnaDailyItem extends DataEntity<DnaDailyItem> {
	
	private static final long serialVersionUID = 1L;
	private DnaDaily daily;		// daily_id 父类
	private SynthLab lab;		// lab_id
	private String temperature;		// 温度
	private String relativeHumidity;		// 相对湿度
	private String refrigeratorTemperature;		// 冰箱温度
	private String other;		// 其它
	
	public DnaDailyItem() {
		super();
	}

	public DnaDailyItem(String id){
		super(id);
	}

	public DnaDailyItem(DnaDaily daily){
		this.daily = daily;
	}

	@Length(min=1, max=255, message="daily_id长度必须介于 1 和 255 之间")
	public DnaDaily getDaily() {
		return daily;
	}

	public void setDaily(DnaDaily daily) {
		this.daily = daily;
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
	public String getRelativeHumidity() {
		return relativeHumidity;
	}

	public void setRelativeHumidity(String relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}
	
	@Length(min=0, max=255, message="冰箱温度长度必须介于 0 和 255 之间")
	public String getRefrigeratorTemperature() {
		return refrigeratorTemperature;
	}

	public void setRefrigeratorTemperature(String refrigeratorTemperature) {
		this.refrigeratorTemperature = refrigeratorTemperature;
	}
	
	@Length(min=0, max=255, message="其它长度必须介于 0 和 255 之间")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
}