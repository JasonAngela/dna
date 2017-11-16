/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust_flow_sheet.entity.entrust;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 流转单Entity
 * @author fu
 * @version 2017-09-14
 */
public class EntrustFlowSheet extends DataEntity<EntrustFlowSheet> {
	
	private static final long serialVersionUID = 1L;
	private String pic;		// pic
	private String entrustId;		// entrust_id
	
	public EntrustFlowSheet() {
		super();
	}

	public EntrustFlowSheet(String id){
		super(id);
	}

	@Length(min=0, max=255, message="pic长度必须介于 0 和 255 之间")
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	@Length(min=0, max=255, message="entrust_id长度必须介于 0 和 255 之间")
	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	
}