/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 编码规则Entity
 * @author zhuguli
 * @version 2017-05-12
 */
public class SysCodeRuleItem extends DataEntity<SysCodeRuleItem> {
	
	private static final long serialVersionUID = 1L;
	
	public static final int CODE_TYPE_STRING = 1;
	public static final int CODE_TYPE_DATE = 2;
	public static final int CODE_TYPE_SEQ =3;
	
	private SysCodeRule rule;		// rule_id 父类
	private Integer seq;		// 序号
	private Integer codeType;		// 类型
	private String pattern;		// 模式
	private Integer digitalLength;		// 长度
	private SysSequence sequence;		// 序列id
	
	public SysCodeRuleItem() {
		super();
	}

	public SysCodeRuleItem(String id){
		super(id);
	}

	public SysCodeRuleItem(SysCodeRule rule){
		this.rule = rule;
	}

	@Length(min=1, max=64, message="rule_id长度必须介于 1 和 64 之间")
	public SysCodeRule getRule() {
		return rule;
	}

	public void setRule(SysCodeRule rule) {
		this.rule = rule;
	}
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	public Integer getCodeType() {
		return codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}
	
	@Length(min=0, max=255, message="模式长度必须介于 0 和 255 之间")
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public Integer getDigitalLength() {
		return digitalLength;
	}

	public void setDigitalLength(Integer digitalLength) {
		this.digitalLength = digitalLength;
	}
	
	public SysSequence getSequence() {
		return sequence;
	}

	public void setSequence(SysSequence sequence) {
		this.sequence = sequence;
	}
	
}