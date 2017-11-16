/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.entity;

import javax.validation.constraints.NotNull;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 实验室消毒记录Entity
 * @author zhuguli
 * @version 2017-05-13
 */
public class SynthLabDisinfectRecord extends DataEntity<SynthLabDisinfectRecord> {
	
	private static final long serialVersionUID = 1L;
	private SynthLab lab;		// lab_id
	private User operator;		// 使用人
	private Date operateDate;		// 使用时间
	
	public SynthLabDisinfectRecord() {
		super();
	}

	public SynthLabDisinfectRecord(String id){
		super(id);
	}

	public SynthLab getLab() {
		return lab;
	}

	public void setLab(SynthLab lab) {
		this.lab = lab;
	}
	
	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	
}