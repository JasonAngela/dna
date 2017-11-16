/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.SysCodeRuleDao;
import com.thinkgem.jeesite.modules.sys.dao.SysCodeRuleItemDao;
import com.thinkgem.jeesite.modules.sys.entity.SysCodeRule;
import com.thinkgem.jeesite.modules.sys.entity.SysCodeRuleItem;

/**
 * 编码规则Service
 * @author zhuguli
 * @version 2017-05-12
 */
@Service
@Transactional(readOnly = true)
public class SysCodeRuleService extends CrudService<SysCodeRuleDao, SysCodeRule> {

	@Autowired
	private SysCodeRuleItemDao sysCodeRuleItemDao;
	@Autowired
	private SysSequenceService sysSequenceService;

	public SysCodeRule get(String id) {
		SysCodeRule sysCodeRule = super.get(id);
		sysCodeRule.setSysCodeRuleItemList(sysCodeRuleItemDao.findList(new SysCodeRuleItem(sysCodeRule)));
		return sysCodeRule;
	}

	public List<SysCodeRule> findList(SysCodeRule sysCodeRule) {
		return super.findList(sysCodeRule);
	}

	public Page<SysCodeRule> findPage(Page<SysCodeRule> page, SysCodeRule sysCodeRule) {
		return super.findPage(page, sysCodeRule);
	}

	@Transactional(readOnly = false)
	public void save(SysCodeRule sysCodeRule) {
		super.save(sysCodeRule);
		for (SysCodeRuleItem sysCodeRuleItem : sysCodeRule.getSysCodeRuleItemList()){
			if (sysCodeRuleItem.getId() == null){
				continue;
			}
			if (SysCodeRuleItem.DEL_FLAG_NORMAL.equals(sysCodeRuleItem.getDelFlag())){
				if (StringUtils.isBlank(sysCodeRuleItem.getId())){
					sysCodeRuleItem.setRule(sysCodeRule);
					sysCodeRuleItem.preInsert();
					sysCodeRuleItemDao.insert(sysCodeRuleItem);
				}else{
					sysCodeRuleItem.preUpdate();
					sysCodeRuleItemDao.update(sysCodeRuleItem);
				}
			}else{
				sysCodeRuleItemDao.delete(sysCodeRuleItem);
			}
		}
	}

	@Transactional(readOnly = false)
	public void delete(SysCodeRule sysCodeRule) {
		super.delete(sysCodeRule);
		sysCodeRuleItemDao.delete(new SysCodeRuleItem(sysCodeRule));
	}
	@Transactional(readOnly = false)
	public String generateCode(String code) {
		SysCodeRule sysCodeRule = dao.selectByCode(code);
		List<SysCodeRuleItem> itemList = sysCodeRuleItemDao.findList(new SysCodeRuleItem(sysCodeRule));
		StringBuffer sb = new StringBuffer();
		for (SysCodeRuleItem sysCodeRuleItem : itemList) {
			switch (sysCodeRuleItem.getCodeType().intValue()) {
			case SysCodeRuleItem.CODE_TYPE_STRING:
				sb.append(sysCodeRuleItem.getPattern());
				break;
			case SysCodeRuleItem.CODE_TYPE_DATE:
				String dateStr =DateUtils.formatDate(new Date(), sysCodeRuleItem.getPattern());
				sb.append(dateStr);
				break;
			case SysCodeRuleItem.CODE_TYPE_SEQ:
				Long currentValue = sysSequenceService.getCurrentValue(sysCodeRuleItem.getSequence().getId());
				Integer digitalLength = sysCodeRuleItem.getDigitalLength();
				String seqStr = String.format("%0"+digitalLength+"d", currentValue);
				sb.append(seqStr);
				break;
			default:
				break;
			}
		}
		return sb.toString();
	}

	 

}