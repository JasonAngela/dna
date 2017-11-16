/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * dna试验Entity
 * @author zhuguli
 * @version 2017-06-11
 */
public class DnaExperiment extends ActEntity<DnaExperiment> {
	private static final long serialVersionUID = 1L;
	private String code;		// 编码
	private DnaGeneCassette cassette;		// 基因盒
	private SynthLab lab;		// 实验室
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private Integer status;		// 状态
	private String procInsId;		// proc_ins_id
	private String boardId;
	private List<DnaExperimentImport> dnaExperimentImportList = Lists.newArrayList();		// 子表列表
	private List<DnaExperimentSpecimen> dnaExperimentSpecimenList = Lists.newArrayList();		// 子表列表
	private List<DnaExperimentStr> dnaExperimentStrList = Lists.newArrayList();//子列表
	private DnaBoard board;
	private String importDataAddress;//导入数据地址
	private String extractionChambers;		// 提取室
	private String expansionChambers;		// 扩增室
	private String elctrophoresisChambers;		// 电泳室
	private User operator;		// 其他

	
	public String getExtractionChambers() {
		return extractionChambers;
	}

	public void setExtractionChambers(String extractionChambers) {
		this.extractionChambers = extractionChambers;
	}

	public String getExpansionChambers() {
		return expansionChambers;
	}

	public void setExpansionChambers(String expansionChambers) {
		this.expansionChambers = expansionChambers;
	}

	public String getElctrophoresisChambers() {
		return elctrophoresisChambers;
	}

	public void setElctrophoresisChambers(String elctrophoresisChambers) {
		this.elctrophoresisChambers = elctrophoresisChambers;
	}



	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public DnaExperiment() {
		super();
	}

	public DnaExperiment(String id){
		super(id);
	}

	@Length(min=0, max=255, message="编码长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public DnaGeneCassette getCassette() {
		return cassette;
	}

	public void setCassette(DnaGeneCassette cassette) {
		this.cassette = cassette;
	}
	
	public SynthLab getLab() {
		return lab;
	}

	public void setLab(SynthLab lab) {
		this.lab = lab;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=0, max=64, message="proc_ins_id长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	public List<DnaExperimentImport> getDnaExperimentImportList() {
		return dnaExperimentImportList;
	}

	public void setDnaExperimentImportList(List<DnaExperimentImport> dnaExperimentImportList) {
		this.dnaExperimentImportList = dnaExperimentImportList;
	}
	public List<DnaExperimentSpecimen> getDnaExperimentSpecimenList() {
		return dnaExperimentSpecimenList;
	}

	public void setDnaExperimentSpecimenList(List<DnaExperimentSpecimen> dnaExperimentSpecimenList) {
		this.dnaExperimentSpecimenList = dnaExperimentSpecimenList;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public DnaBoard getBoard() {
		return board;
	}

	public void setBoard(DnaBoard board) {
		this.board = board;
	}

	public String getImportDataAddress() {
		return importDataAddress;
	}

	public void setImportDataAddress(String importDataAddress) {
		this.importDataAddress = importDataAddress;
	}

	public List<DnaExperimentStr> getDnaExperimentStrList() {
		return dnaExperimentStrList;
	}

	public void setDnaExperimentStrList(List<DnaExperimentStr> dnaExperimentStrList) {
		this.dnaExperimentStrList = dnaExperimentStrList;
	}
		
}