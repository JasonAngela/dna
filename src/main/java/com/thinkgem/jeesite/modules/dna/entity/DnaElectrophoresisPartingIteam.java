/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 电泳室Entity
 * @author fuyun
 * @version 2017-09-06
 */
public class DnaElectrophoresisPartingIteam extends DataEntity<DnaElectrophoresisPartingIteam> {
	
	private static final long serialVersionUID = 1L;
	private DnaElectrophoresisParting dnaElectrophoresisParting;		// dna_electrophoresis_parting_id 父类
	private String sampleNumber;		// 样品编号
	
	public DnaElectrophoresisPartingIteam() {
		super();
	}

	public DnaElectrophoresisPartingIteam(String id){
		super(id);
	}

	public DnaElectrophoresisPartingIteam(DnaElectrophoresisParting dnaElectrophoresisParting){
		this.dnaElectrophoresisParting = dnaElectrophoresisParting;
	}

	@Length(min=1, max=64, message="dna_electrophoresis_parting_id长度必须介于 1 和 64 之间")
	public DnaElectrophoresisParting getDnaElectrophoresisParting() {
		return dnaElectrophoresisParting;
	}

	public void setDnaElectrophoresisParting(DnaElectrophoresisParting dnaElectrophoresisParting) {
		this.dnaElectrophoresisParting = dnaElectrophoresisParting;
	}
	
	@Length(min=0, max=255, message="样品编号长度必须介于 0 和 255 之间")
	public String getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}
	
}