/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 执业证Entity
 * @author 浮云
 * @version 2017-09-13
 */
public class Licensed extends DataEntity<Licensed> {
	
	private static final long serialVersionUID = 1L;
	private String entrustId;		// entrust_id
	private User user;		// user
	
	public Licensed() {
		super();
	}

	public Licensed(String id){
		super(id);
	}

	@Length(min=0, max=255, message="entrust_id长度必须介于 0 和 255 之间")
	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}