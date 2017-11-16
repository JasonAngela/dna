/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 编码规则Entity
 * @author zhuguli
 * @version 2017-05-12
 */
public class SysCodeRule extends DataEntity<SysCodeRule> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 编码
	private String name;		// 名称
	private List<SysCodeRuleItem> sysCodeRuleItemList = Lists.newArrayList();		// 子表列表
	
	public SysCodeRule() {
		super();
	}

	public SysCodeRule(String id){
		super(id);
	}

	@Length(min=0, max=255, message="编码长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<SysCodeRuleItem> getSysCodeRuleItemList() {
		return sysCodeRuleItemList;
	}

	public void setSysCodeRuleItemList(List<SysCodeRuleItem> sysCodeRuleItemList) {
		this.sysCodeRuleItemList = sysCodeRuleItemList;
	}
}