/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust_flow_sheet.service.entrust;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.entrust_flow_sheet.entity.entrust.EntrustFlowSheet;
import com.thinkgem.jeesite.modules.entrust_flow_sheet.dao.entrust.EntrustFlowSheetDao;

/**
 * 流转单Service
 * @author fu
 * @version 2017-09-14
 */
@Service
@Transactional(readOnly = true)
public class EntrustFlowSheetService extends CrudService<EntrustFlowSheetDao, EntrustFlowSheet> {

	public EntrustFlowSheet get(String id) {
		return super.get(id);
	}
	
	public List<EntrustFlowSheet> findList(EntrustFlowSheet entrustFlowSheet) {
		return super.findList(entrustFlowSheet);
	}
	
	public Page<EntrustFlowSheet> findPage(Page<EntrustFlowSheet> page, EntrustFlowSheet entrustFlowSheet) {
		return super.findPage(page, entrustFlowSheet);
	}
	
	@Transactional(readOnly = false)
	public void save(EntrustFlowSheet entrustFlowSheet) {
		super.save(entrustFlowSheet);
	}
	
	@Transactional(readOnly = false)
	public void delete(EntrustFlowSheet entrustFlowSheet) {
		super.delete(entrustFlowSheet);
	}
	
}