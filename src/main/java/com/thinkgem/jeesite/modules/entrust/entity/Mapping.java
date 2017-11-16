/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 图谱Entity
 * @author 好好
 * @version 2017-09-08
 */
public class Mapping extends DataEntity<Mapping> {
	
	private static final long serialVersionUID = 1L;
	private String entrustId;		// entrust_id
	private String pic;		// pic
	
	public Mapping() {
		super();
	}

	public Mapping(String id){
		super(id);
	}

	@Length(min=0, max=255, message="entrust_id长度必须介于 0 和 255 之间")
	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	
	
	
	
}