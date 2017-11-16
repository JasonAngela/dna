/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 亲权指数Entity
 * @author zhuguli
 * @version 2017-07-04
 */
public class DnaPiResultItem extends DataEntity<DnaPiResultItem> {
	
	private static final long serialVersionUID = 1L;
	private String geneLoci;		// 基因座
	private Integer pValue;		// P
	private Integer qValue;		// Q
	private Double pProb;		// p
	private Double qProb;		// q
	private Double pi;		// pi
	private String formula; //公式
	private Double min;
	private DnaPiResult result;		// result_id 父类
	
	
	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public DnaPiResultItem() {
		super();
	}

	public DnaPiResultItem(String id){
		super(id);
	}

	public DnaPiResultItem(DnaPiResult result){
		this.result = result;
	}

	@Length(min=0, max=255, message="基因座长度必须介于 0 和 255 之间")
	public String getGeneLoci() {
		return geneLoci;
	}

	public void setGeneLoci(String geneLoci) {
		this.geneLoci = geneLoci;
	}
	
	public Integer getPValue() {
		return pValue;
	}

	public void setPValue(Integer pValue) {
		this.pValue = pValue;
	}
	
	public Integer getQValue() {
		return qValue;
	}

	public void setQValue(Integer qValue) {
		this.qValue = qValue;
	}

	public Integer getpValue() {
		return pValue;
	}

	public void setpValue(Integer pValue) {
		this.pValue = pValue;
	}

	public Integer getqValue() {
		return qValue;
	}

	public void setqValue(Integer qValue) {
		this.qValue = qValue;
	}

	public Double getpProb() {
		return pProb;
	}

	public void setpProb(Double pProb) {
		this.pProb = pProb;
	}

	public Double getqProb() {
		return qProb;
	}

	public void setqProb(Double qProb) {
		this.qProb = qProb;
	}

	public Double getPi() {
		return pi;
	}

	public void setPi(Double pi) {
		this.pi = pi;
	}

	public DnaPiResult getResult() {
		return result;
	}

	public void setResult(DnaPiResult result) {
		this.result = result;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
}