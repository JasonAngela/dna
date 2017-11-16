/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 电泳分型板Entity
 * @author zhuguli
 * @version 2017-06-08
 */
public class DnaBoardJgg extends DataEntity<DnaBoardJgg> {
	
	private static final long serialVersionUID = 1L;
	private DnaBoard board;		// 关联板子 父类
	private Integer hang;		// 行
	private Integer lie;		// 列
	private String specimenCode;		// 检样编码
	
	public DnaBoardJgg() {
		super();
	}
	public String getHangLabel(){
		String[]labels ={"A","B","C","D","E","F","G","H"};
		return labels[hang-1];
	}
	public DnaBoardJgg(String id){
		super(id);
	}

	public DnaBoardJgg(DnaBoard board){
		this.board = board;
	}

	@Length(min=1, max=64, message="关联板子长度必须介于 1 和 64 之间")
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
	
	@Length(min=0, max=255, message="检样编码长度必须介于 0 和 255 之间")
	public String getSpecimenCode() {
		return specimenCode;
	}

	public void setSpecimenCode(String specimenCode) {
		this.specimenCode = specimenCode;
	}
	
}