/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 委托登记Entity
 * @author zhuguli
 * @version 2017-05-12
 */
public class EntrustAbstracts extends DataEntity<EntrustAbstracts> {
	
	private static final long serialVersionUID = 1L;
	private EntrustRegister register;		// register_id 父类
	private String clientCode;		// 编码
	private String clientName;		// 名称
	private String gender;		// 性别
	private String appellation;		// 称谓
	private String birthday;		// 出生日期
	private String idType;		// 证件类型
	private String idNo;		// 证件号
	private String idPic;		// 证件图片
	private String clientPic;		// 图片
	private String dnaExperimentId;//关联试验id
	private String specimenCode;//关联的物证编码
	public EntrustAbstracts() {
		super();
	}

	public EntrustAbstracts(String id){
		super(id);
	}

	public EntrustAbstracts(EntrustRegister register){
		this.register = register;
	}

	@Length(min=1, max=64, message="register_id长度必须介于 1 和 64 之间")
	public EntrustRegister getRegister() {
		return register;
	}

	public void setRegister(EntrustRegister register) {
		this.register = register;
	}
	
	@Length(min=0, max=255, message="编码长度必须介于 0 和 255 之间")
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	
	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=1, message="称谓长度必须介于 0 和 1 之间")
	public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}
	
	@Length(min=0, max=255, message="出生日期长度必须介于 0 和 255 之间")
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=1, message="证件类型长度必须介于 0 和 1 之间")
	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}
	
	@Length(min=0, max=255, message="证件号长度必须介于 0 和 255 之间")
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	@Length(min=0, max=255, message="证件图片长度必须介于 0 和 255 之间")
	public String getIdPic() {
		return idPic;
	}

	public void setIdPic(String idPic) {
		this.idPic = idPic;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getClientPic() {
		return clientPic;
	}

	public void setClientPic(String clientPic) {
		this.clientPic = clientPic;
	}

	public String getDnaExperimentId() {
		return dnaExperimentId;
	}

	public void setDnaExperimentId(String dnaExperimentId) {
		this.dnaExperimentId = dnaExperimentId;
	}

	public String getSpecimenCode() {
		return specimenCode;
	}

	public void setSpecimenCode(String specimenCode) {
		this.specimenCode = specimenCode;
	}
	
}