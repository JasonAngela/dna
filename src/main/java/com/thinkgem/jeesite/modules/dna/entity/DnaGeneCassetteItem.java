/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 基因盒Entity
 * @author zhuguli
 * @version 2017-06-01
 */
public class DnaGeneCassetteItem extends DataEntity<DnaGeneCassetteItem> {
	
	private static final long serialVersionUID = 1L;
	private DnaGeneCassette cassette;		// cassette_id 父类
	private DnaGeneLoci loci;		// 基因座
	private Integer seq;	// 排序
	public DnaGeneCassetteItem() {
		super();
	}

	public DnaGeneCassetteItem(String id){
		super(id);
	}

	public DnaGeneCassetteItem(DnaGeneCassette cassette){
		this.cassette = cassette;
	}

	@Length(min=1, max=64, message="cassette_id长度必须介于 1 和 64 之间")
	public DnaGeneCassette getCassette() {
		return cassette;
	}

	public void setCassette(DnaGeneCassette cassette) {
		this.cassette = cassette;
	}
	
	@NotNull(message="基因座不能为空")
	public DnaGeneLoci getLoci() {
		return loci;
	}

	public void setLoci(DnaGeneLoci loci) {
		this.loci = loci;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
}