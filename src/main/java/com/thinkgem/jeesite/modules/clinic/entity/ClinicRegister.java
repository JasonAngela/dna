/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.clinic.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.ActEntity;

/**
 * 临床登记Entity
 * @author zhuguli
 * @version 2017-10-15
 */
public class ClinicRegister extends ActEntity<ClinicRegister> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 编码
	private String clientName;		// 委托人
	private String clientTel;		// 委托人电话
	private String clientReceiver;		// 委托收件人
	private String clientEmail;		// 委托人邮箱
	private String clientFax;		// 委托人传真
	private String clientZipcode;		// 委托人邮编
	private String clientArea;		// 委托人区域
	private String clientAddress;		// 委托人地址
	private String agentName;		// 送检人(机构)
	private String agentTel;		// 送检人电话
	private String serverName;		// 受理人
	private String serverOrgId;		// 受理机构
	private String sendMode;		// 报告传递方式
	private String specialty;		// 专业
	private String type;		// 类型
	private String material;		// 鉴定材料
	private String materialDispose;		// 检材处理
	private String timeLimitResult;		// 时限结果
	private String timeLimitReport;		// 时间报告
	private Double standardFee;		// 标准费用
	private Double specialFee;		// 特殊费用
	private Double totalFee;		// 合计费用
	private String appraisalItem;		// 鉴定项
	private Boolean clientAvoid;		// 鉴定人回避
	private Boolean authorizeNotification;		// 授权客服人员通知
	private String status;		// status
	private String processInstanceId;		// 流程id
	
	public ClinicRegister() {
		super();
	}

	public ClinicRegister(String id){
		super(id);
	}

	@Length(min=0, max=255, message="编码长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=255, message="委托人长度必须介于 0 和 255 之间")
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	@Length(min=0, max=255, message="委托人电话长度必须介于 0 和 255 之间")
	public String getClientTel() {
		return clientTel;
	}

	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}
	
	@Length(min=0, max=255, message="委托收件人长度必须介于 0 和 255 之间")
	public String getClientReceiver() {
		return clientReceiver;
	}

	public void setClientReceiver(String clientReceiver) {
		this.clientReceiver = clientReceiver;
	}
	
	@Length(min=0, max=255, message="委托人邮箱长度必须介于 0 和 255 之间")
	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	
	@Length(min=0, max=255, message="委托人传真长度必须介于 0 和 255 之间")
	public String getClientFax() {
		return clientFax;
	}

	public void setClientFax(String clientFax) {
		this.clientFax = clientFax;
	}
	
	@Length(min=0, max=255, message="委托人邮编长度必须介于 0 和 255 之间")
	public String getClientZipcode() {
		return clientZipcode;
	}

	public void setClientZipcode(String clientZipcode) {
		this.clientZipcode = clientZipcode;
	}
	
	@Length(min=0, max=1000, message="委托人区域长度必须介于 0 和 1000 之间")
	public String getClientArea() {
		return clientArea;
	}

	public void setClientArea(String clientArea) {
		this.clientArea = clientArea;
	}
	
	@Length(min=0, max=1000, message="委托人地址长度必须介于 0 和 1000 之间")
	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	
	@Length(min=0, max=255, message="送检人(机构)长度必须介于 0 和 255 之间")
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	@Length(min=0, max=255, message="送检人电话长度必须介于 0 和 255 之间")
	public String getAgentTel() {
		return agentTel;
	}

	public void setAgentTel(String agentTel) {
		this.agentTel = agentTel;
	}
	
	@Length(min=0, max=255, message="受理人长度必须介于 0 和 255 之间")
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	@Length(min=0, max=255, message="受理机构长度必须介于 0 和 255 之间")
	public String getServerOrgId() {
		return serverOrgId;
	}

	public void setServerOrgId(String serverOrgId) {
		this.serverOrgId = serverOrgId;
	}
	
	@Length(min=0, max=1, message="报告传递方式长度必须介于 0 和 1 之间")
	public String getSendMode() {
		return sendMode;
	}

	public void setSendMode(String sendMode) {
		this.sendMode = sendMode;
	}
	
	@Length(min=0, max=1, message="专业长度必须介于 0 和 1 之间")
	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="鉴定材料长度必须介于 0 和 255 之间")
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
	@Length(min=0, max=255, message="检材处理长度必须介于 0 和 255 之间")
	public String getMaterialDispose() {
		return materialDispose;
	}

	public void setMaterialDispose(String materialDispose) {
		this.materialDispose = materialDispose;
	}
	
	@Length(min=0, max=1, message="时限结果长度必须介于 0 和 1 之间")
	public String getTimeLimitResult() {
		return timeLimitResult;
	}

	public void setTimeLimitResult(String timeLimitResult) {
		this.timeLimitResult = timeLimitResult;
	}
	
	@Length(min=0, max=1, message="时间报告长度必须介于 0 和 1 之间")
	public String getTimeLimitReport() {
		return timeLimitReport;
	}

	public void setTimeLimitReport(String timeLimitReport) {
		this.timeLimitReport = timeLimitReport;
	}
	
	public Double getStandardFee() {
		return standardFee;
	}

	public void setStandardFee(Double standardFee) {
		this.standardFee = standardFee;
	}
	
	public Double getSpecialFee() {
		return specialFee;
	}

	public void setSpecialFee(Double specialFee) {
		this.specialFee = specialFee;
	}
	
	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	
	@Length(min=0, max=255, message="鉴定项长度必须介于 0 和 255 之间")
	public String getAppraisalItem() {
		return appraisalItem;
	}

	public void setAppraisalItem(String appraisalItem) {
		this.appraisalItem = appraisalItem;
	}
	
	public Boolean getClientAvoid() {
		return clientAvoid;
	}

	public void setClientAvoid(Boolean clientAvoid) {
		this.clientAvoid = clientAvoid;
	}
	
	public Boolean getAuthorizeNotification() {
		return authorizeNotification;
	}

	public void setAuthorizeNotification(Boolean authorizeNotification) {
		this.authorizeNotification = authorizeNotification;
	}
	
	@Length(min=0, max=1, message="status长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="流程id长度必须介于 0 和 255 之间")
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
}