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
public class DnaExperimentImport extends DataEntity<DnaExperimentImport> {
	
	private static final long serialVersionUID = 1L;
	private DnaExperiment experiment;		// experiment_id 父类
	
	public DnaExperimentImport() {
		super();
	}

	public DnaExperimentImport(String id){
		super(id);
	}

	public DnaExperimentImport(DnaExperiment experiment){
		this.experiment = experiment;
	}

	@Length(min=1, max=64, message="experiment_id长度必须介于 1 和 64 之间")
	public DnaExperiment getExperiment() {
		return experiment;
	}

	public void setExperiment(DnaExperiment experiment) {
		this.experiment = experiment;
	}
	
}