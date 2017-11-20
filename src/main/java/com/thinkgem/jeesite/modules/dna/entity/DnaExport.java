package com.thinkgem.jeesite.modules.dna.entity;

public class DnaExport {
	private String jyz;
	private String bh;
	private String jyzhi;


	private String geneLoci;//基因座
	private String xyValue;//xy值
	private String formula;//公式
	private String pcValue;
	private String pdValue;
	private String nValue;


	public String getXyValue() {
		return xyValue;
	}

	public void setXyValue(String xyValue) {
		this.xyValue = xyValue;
	}

	public String getGeneLoci() {
		return geneLoci;
	}

	public void setGeneLoci(String geneLoci) {
		this.geneLoci = geneLoci;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getPcValue() {
		return pcValue;
	}

	public void setPcValue(String pcValue) {
		this.pcValue = pcValue;
	}

	public String getPdValue() {
		return pdValue;
	}

	public void setPdValue(String pdValue) {
		this.pdValue = pdValue;
	}

	public String getnValue() {
		return nValue;
	}

	public void setnValue(String nValue) {
		this.nValue = nValue;
	}

	public String getJyz() {
		return jyz;
	}
	public void setJyz(String jyz) {
		this.jyz = jyz;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getJyzhi() {
		return jyzhi;
	}
	public void setJyzhi(String jyzhi) {
		this.jyzhi = jyzhi;
	}
}