/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * dna试验Entity
 * @author zhuguli
 * @version 2017-06-11
 */
public class DnaExperimentSpecimen extends DataEntity<DnaExperimentSpecimen> {
	
	private static final long serialVersionUID = 1L;
	private DnaExperiment experiment;		// experiment_id 父类
	private String specimenCode;		// 检样编码
	private DnaBoard board;		// board_id
	private Integer hang;		// hang
	private Integer lie;		// lie
	private String geneValue;		// 基因值
	
	public DnaExperimentSpecimen() {
		super();
	}

	public DnaExperimentSpecimen(String id){
		super(id);
	}

	public DnaExperimentSpecimen(DnaExperiment experiment){
		this.experiment = experiment;
	}

	@Length(min=1, max=64, message="experiment_id长度必须介于 1 和 64 之间")
	public DnaExperiment getExperiment() {
		return experiment;
	}

	public void setExperiment(DnaExperiment experiment) {
		this.experiment = experiment;
	}
	
	@Length(min=0, max=255, message="检样编码长度必须介于 0 和 255 之间")
	public String getSpecimenCode() {
		return specimenCode;
	}

	public void setSpecimenCode(String specimenCode) {
		this.specimenCode = specimenCode;
	}
	
	public DnaBoard getBoard() {
		return board;
	}

	public void setBoard(DnaBoard board) {
		this.board = board;
	}
	
	public Integer getHang() {
		return hang;
	}

	public void setHang(Integer hang) {
		this.hang = hang;
	}
	
	public Integer getLie() {
		return lie;
	}

	public void setLie(Integer lie) {
		this.lie = lie;
	}
	
	@Length(min=0, max=255, message="基因值长度必须介于 0 和 255 之间")
	public String getGeneValue() {
		return geneValue;
	}

	public void setGeneValue(String geneValue) {
		this.geneValue = geneValue;
	}
	
}