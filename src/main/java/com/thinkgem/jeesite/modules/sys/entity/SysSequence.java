/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 序列Entity
 * @author zhuguli
 * @version 2017-05-12
 */
public class SysSequence extends DataEntity<SysSequence> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private Integer start;		// 开始
	private Long current;		// 当前值
	
	public SysSequence() {
		super();
	}

	public SysSequence(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}
	
	public Long getCurrent() {
		return current;
	}

	public void setCurrent(Long current) {
		this.current = current;
	}
	
}