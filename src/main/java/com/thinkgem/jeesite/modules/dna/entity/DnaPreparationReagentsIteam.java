/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 试剂配制记录表Entity
 * @author fyun
 * @version 2017-09-06
 */
public class DnaPreparationReagentsIteam extends DataEntity<DnaPreparationReagentsIteam> {
	
	private static final long serialVersionUID = 1L;
	private DnaPreparationReagents dnaPreparationReagents;		// dna_preparation_reagents_id 父类
	private String sampleNumber;		// 样品编号
	
	public DnaPreparationReagentsIteam() {
		super();
	}

	public DnaPreparationReagentsIteam(String id){
		super(id);
	}

	public DnaPreparationReagentsIteam(DnaPreparationReagents dnaPreparationReagents){
		this.dnaPreparationReagents = dnaPreparationReagents;
	}

	@Length(min=1, max=64, message="dna_preparation_reagents_id长度必须介于 1 和 64 之间")
	public DnaPreparationReagents getDnaPreparationReagents() {
		return dnaPreparationReagents;
	}

	public void setDnaPreparationReagents(DnaPreparationReagents dnaPreparationReagents) {
		this.dnaPreparationReagents = dnaPreparationReagents;
	}
	
	@Length(min=0, max=255, message="样品编号长度必须介于 0 和 255 之间")
	public String getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}
	
}