package com.thinkgem.jeesite.modules.dna.entity;

public class ParentageTestingEntity {
	private String appellation;
	private DnaGeneFrequency first;
	private DnaGeneFrequency second;
	
	public ParentageTestingEntity(String appellation, DnaGeneFrequency first, DnaGeneFrequency second) {
		super();
		this.appellation = appellation;
		this.first = first;
		this.second = second;
	}
	
	public ParentageTestingEntity() {
		super();
	}
	//判断是否是纯合子
	public boolean isPure(){
		return first.getValue().equals(second.getValue());
	}
	public String getAppellation() {
		return appellation;
	}
	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}
	public DnaGeneFrequency getFirst() {
		return first;
	}
	public void setFirst(DnaGeneFrequency first) {
		this.first = first;
	}
	public DnaGeneFrequency getSecond() {
		return second;
	}
	public void setSecond(DnaGeneFrequency second) {
		this.second = second;
	}
	public double getFirstValue(){
		return Double.valueOf(first.getValue());
	}
	public double getSecondValue(){
		return Double.valueOf(second.getValue());
	}
	public Double getFirstProbability(){
		return first.getProbability();
	}
	public Double getSecondProbability(){
		return second.getProbability();
	}

	public double getMinValue(){
		return getFirstValue()<getSecondValue()?getFirstValue():getSecondValue();
	}
	public double getMaxValue(){
		return getFirstValue()>getSecondValue()?getFirstValue():getSecondValue();
	}
	public Double getMinProbability(){
		return getFirstValue()<getSecondValue()?getFirstProbability():getSecondProbability();
	}
	public Double getMaxProbability(){
		return getFirstValue()>getSecondValue()?getFirstProbability():getSecondProbability();
	}

}
