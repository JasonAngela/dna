/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 收费凭据Entity
 * @author 浮云
 * @version 2017-09-14
 */
public class EntrustChargeCredentials extends DataEntity<EntrustChargeCredentials> {
	
	private static final long serialVersionUID = 1L;
	private String entrustId;		// entrust_id
	private String pic;		// pic
	
	public EntrustChargeCredentials() {
		super();
	}

	public EntrustChargeCredentials(String id){
		super(id);
	}

	@Length(min=0, max=255, message="entrust_id长度必须介于 0 和 255 之间")
	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	
	@Length(min=0, max=255, message="pic长度必须介于 0 和 255 之间")
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
}