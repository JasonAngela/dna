/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 设备使用记录Entity
 * @author zhuguli
 * @version 2017-05-13
 */
public class SynthEquipmentUsageRecord extends DataEntity<SynthEquipmentUsageRecord> {
	
	private static final long serialVersionUID = 1L;
    private SynthEquipment equipment;		// equipment_id
	private User operator;		// 使用人
	private Date operateDate;		// 使用时间
	private EntrustRegister register;		// register_id
	
	public SynthEquipmentUsageRecord() {
		super();
	}

	public SynthEquipmentUsageRecord(String id){
		super(id);
	}

	public SynthEquipment getEquipment() {
		return equipment;
	}

	public void setEquipment(SynthEquipment equipment) {
		this.equipment = equipment;
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
	
	public EntrustRegister getRegister() {
		return register;
	}

	public void setRegister(EntrustRegister register) {
		this.register = register;
	}
	
}