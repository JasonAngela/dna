/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 等位基因频率Entity
 * @author zhuguli
 * @version 2017-06-01
 */
public class DnaGeneFrequency extends DataEntity<DnaGeneFrequency> {
	
	private static final long serialVersionUID = 1L;
	private DnaGeneLoci loci;		// 基因座
	private String value;		// 值
	private Double probability;		// 概率
	
	public DnaGeneFrequency() {
		super();
	}
	
	public DnaGeneFrequency(String value, Double probability) {
		super();
		this.value = value;
		this.probability = probability;
	}

	public DnaGeneFrequency(String id){
		super(id);
	}

	@NotNull(message="基因座不能为空")
	public DnaGeneLoci getLoci() {
		return loci;
	}

	public void setLoci(DnaGeneLoci loci) {
		this.loci = loci;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Double getProbability() {
		return probability;
	}

	public void setProbability(Double probability) {
		this.probability = probability;
	}
	
}