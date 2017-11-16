/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oneself.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单独实验Entity
 * @author fuyun
 * @version 2017-10-18
 */
public class OneselExperimental extends DataEntity<OneselExperimental> {
	
	private static final long serialVersionUID = 1L;
	private Oneself oneself;		// id 父类
	private String experimentalId;		// experimental_id
	private String code;		// code
	
	public OneselExperimental() {
		super();
	}

	public OneselExperimental(String id){
		super(id);
	}

	public OneselExperimental(Oneself oneself){
		this.oneself = oneself;
	}

	@Length(min=1, max=64, message="id长度必须介于 1 和 64 之间")
	public Oneself getOneself() {
		return oneself;
	}

	public void setOneself(Oneself oneself) {
		this.oneself = oneself;
	}
	
	@Length(min=1, max=64, message="experimental_id长度必须介于 1 和 64 之间")
	public String getExperimentalId() {
		return experimentalId;
	}

	public void setExperimentalId(String experimentalId) {
		this.experimentalId = experimentalId;
	}
	
	@Length(min=0, max=64, message="code长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}