/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * str值导入Entity
 * @author zhuguli
 * @version 2017-06-17
 */
public class DnaExperimentStr extends DataEntity<DnaExperimentStr> {

	private static final long serialVersionUID = 1L;
	private DnaExperiment experiment;		// experiment_id
	private String specimenCode;		// 检样编码
	private String geneLoci;		// gene_loci
	private String x;		// x
	private String y;		// y
	private String abstracts;//摘要id
	public DnaExperimentStr() {
		super();
	}

	public DnaExperimentStr(String id){
		super(id);
	}
	public DnaExperimentStr(DnaExperiment dnaExperiment){
		this.experiment = dnaExperiment;
	}
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

	@Length(min=0, max=255, message="gene_loci长度必须介于 0 和 255 之间")
	public String getGeneLoci() {
		return geneLoci;
	}

	public void setGeneLoci(String geneLoci) {
		this.geneLoci = geneLoci;
	}

	@Length(min=0, max=255, message="x长度必须介于 0 和 255 之间")
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	@Length(min=0, max=255, message="y长度必须介于 0 和 255 之间")
	public String getY() {
		if(StringUtils.isEmpty(y)){
			return x;
		}
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	public String getXValue(){
		Double value =  Double.parseDouble(x);
	if(	isIntegerForDouble(value)==true){
		return value.intValue()+"";
	}else{
		return value.doubleValue()+"";
	}
	}
	public String getYValue(){
		Double value =  Double.parseDouble(getY());
		if(	isIntegerForDouble(value)==true){
			return value.intValue()+"";
		}else{
			return value.doubleValue()+"";
		}
	}
	public  static  boolean isIntegerForDouble(double obj) {  
	    double eps = 1e-10;  // 精度范围  
	    return obj-Math.floor(obj) < eps;  
	}  
}