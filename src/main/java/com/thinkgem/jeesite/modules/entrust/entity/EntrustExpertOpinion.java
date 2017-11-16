/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.ActEntity;

/**
 * 鉴定意见Entity
 * @author zhuguli
 * @version 2017-07-07
 */
public class EntrustExpertOpinion extends ActEntity<EntrustExpertOpinion> {
	
	private static final long serialVersionUID = 1L;
	private EntrustRegister register;		// register_id
	private String drafter;		// 第一鉴定人
	private Date draftTime;		// 初稿
	private String version;		// 版本
	private String draft;		// draft
	private String draftRemark;		// 初稿说明
	private String seconder;		// 第二鉴定人
	private String secondRemark;		// second_remark
	private Date secondTime;		// second_time
	private String director;		// 签字授权人
	private String directorRemark;		// director_remark
	private Date directorTime;		// director_time
	private String finaler;		// finaler
	private String finalVersion;		// 终版
	private Date finalTime;		// final_time
	private String explain;
	private String explainRemark;
	private Mapping mapping;
	
	
	public Mapping getMapping() {
		return mapping;
	}

	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}

	public EntrustExpertOpinion() {
		super();
	}

	public EntrustExpertOpinion(String id){
		super(id);
	}
	public EntrustExpertOpinion(EntrustRegister register){
		super();
		this.register = register;
	}
	public EntrustRegister getRegister() {
		return register;
	}

	public void setRegister(EntrustRegister register) {
		this.register = register;
	}
	
	@Length(min=0, max=255, message="第一鉴定人长度必须介于 0 和 255 之间")
	public String getDrafter() {
		return drafter;
	}

	public void setDrafter(String drafter) {
		this.drafter = drafter;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDraftTime() {
		return draftTime;
	}

	public void setDraftTime(Date draftTime) {
		this.draftTime = draftTime;
	}
	
	@Length(min=0, max=255, message="版本长度必须介于 0 和 255 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getDraft() {
		return draft;
	}

	public void setDraft(String draft) {
		this.draft = draft;
	}
	
	public String getDraftRemark() {
		return draftRemark;
	}

	public void setDraftRemark(String draftRemark) {
		this.draftRemark = draftRemark;
	}
	
	@Length(min=0, max=255, message="第二鉴定人长度必须介于 0 和 255 之间")
	public String getSeconder() {
		return seconder;
	}

	public void setSeconder(String seconder) {
		this.seconder = seconder;
	}
	
	public String getSecondRemark() {
		return secondRemark;
	}

	public void setSecondRemark(String secondRemark) {
		this.secondRemark = secondRemark;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSecondTime() {
		return secondTime;
	}

	public void setSecondTime(Date secondTime) {
		this.secondTime = secondTime;
	}
	
	@Length(min=0, max=255, message="签字授权人长度必须介于 0 和 255 之间")
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getDirectorRemark() {
		return directorRemark;
	}

	public void setDirectorRemark(String directorRemark) {
		this.directorRemark = directorRemark;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDirectorTime() {
		return directorTime;
	}

	public void setDirectorTime(Date directorTime) {
		this.directorTime = directorTime;
	}
	
	@Length(min=0, max=255, message="finaler长度必须介于 0 和 255 之间")
	public String getFinaler() {
		return finaler;
	}

	public void setFinaler(String finaler) {
		this.finaler = finaler;
	}
	
	public String getFinalVersion() {
		return finalVersion;
	}

	public void setFinalVersion(String finalVersion) {
		this.finalVersion = finalVersion;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(Date finalTime) {
		this.finalTime = finalTime;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getExplainRemark() {
		return explainRemark;
	}

	public void setExplainRemark(String explainRemark) {
		this.explainRemark = explainRemark;
	}

	 

	 
	
}