/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * str导入记录Entity
 * @author zhuguli
 * @version 2017-05-11
 */
public class DnaStrImportItem extends DataEntity<DnaStrImportItem> {
	
	private static final long serialVersionUID = 1L;
	private DnaStrImport strImport;		// import_id 父类
	private String geneLoci;		// gene_loci
	private String materialCode;		// material_code
	private String value;		// value
	
	public DnaStrImportItem() {
		super();
	}

	public DnaStrImportItem(String id){
		super(id);
	}

	public DnaStrImportItem(DnaStrImport strImport){
		this.strImport = strImport;
	}

	@Length(min=1, max=64, message="import_id长度必须介于 1 和 64 之间")
	public DnaStrImport getStrImport() {
		return strImport;
	}

	public void setStrImport(DnaStrImport strImport) {
		this.strImport = strImport;
	}
	
	@Length(min=0, max=255, message="gene_loci长度必须介于 0 和 255 之间")
	public String getGeneLoci() {
		return geneLoci;
	}

	public void setGeneLoci(String geneLoci) {
		this.geneLoci = geneLoci;
	}
	
	@Length(min=0, max=255, message="material_code长度必须介于 0 和 255 之间")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	@Length(min=0, max=255, message="value长度必须介于 0 和 255 之间")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}