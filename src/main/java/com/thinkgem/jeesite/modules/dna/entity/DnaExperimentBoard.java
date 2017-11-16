/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 板子Entity
 * @author zhuguli
 * @version 2017-06-11
 */
public class DnaExperimentBoard extends DataEntity<DnaExperimentBoard> {
	
	private static final long serialVersionUID = 1L;
	private String boardCode;		// board_code
	private DnaExperiment experiment;		// experiment_id
	
	public DnaExperimentBoard() {
		super();
	}

	public DnaExperimentBoard(String id){
		super(id);
	}

	@Length(min=0, max=255, message="board_code长度必须介于 0 和 255 之间")
	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	
	public DnaExperiment getExperiment() {
		return experiment;
	}

	public void setExperiment(DnaExperiment experiment) {
		this.experiment = experiment;
	}
	
}