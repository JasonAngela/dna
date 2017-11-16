/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dna.entity.DnaStrImport;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.dna.dao.DnaStrImportDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaStrImportItem;
import com.thinkgem.jeesite.modules.dna.dao.DnaStrImportItemDao;

/**
 * str导入记录Service
 * @author zhuguli
 * @version 2017-05-11
 */
@Service
@Transactional(readOnly = true)
public class DnaStrImportService extends CrudService<DnaStrImportDao, DnaStrImport> {

	@Autowired
	private DnaStrImportItemDao dnaStrImportItemDao;
	@Autowired
	private ActTaskService actTaskService;
	public DnaStrImport get(String id) {
		DnaStrImport dnaStrImport = super.get(id);
		dnaStrImport.setDnaStrImportItemList(dnaStrImportItemDao.findList(new DnaStrImportItem(dnaStrImport)));
		return dnaStrImport;
	}

	public List<DnaStrImport> findList(DnaStrImport dnaStrImport) {
		return super.findList(dnaStrImport);
	}

	public Page<DnaStrImport> findPage(Page<DnaStrImport> page, DnaStrImport dnaStrImport) {
		return super.findPage(page, dnaStrImport);
	}

	@Transactional(readOnly = false)
	public void save(DnaStrImport dnaStrImport) {
		super.save(dnaStrImport);
		for (DnaStrImportItem dnaStrImportItem : dnaStrImport.getDnaStrImportItemList()){
			if (dnaStrImportItem.getId() == null){
				continue;
			}
			if (DnaStrImportItem.DEL_FLAG_NORMAL.equals(dnaStrImportItem.getDelFlag())){
				if (StringUtils.isBlank(dnaStrImportItem.getId())){
					dnaStrImportItem.setStrImport(dnaStrImport);
					dnaStrImportItem.preInsert();
					dnaStrImportItemDao.insert(dnaStrImportItem);
				}else{
					dnaStrImportItem.preUpdate();
					dnaStrImportItemDao.update(dnaStrImportItem);
				}
			}else{
				dnaStrImportItemDao.delete(dnaStrImportItem);
			}
		}
		// 完成流程任务
		Map<String, Object> vars = Maps.newHashMap();
		actTaskService.complete(dnaStrImport.getAct().getTaskId(), dnaStrImport.getAct().getProcInsId(), dnaStrImport.getAct().getComment(), dnaStrImport.getRemarks(), vars);
	}

	@Transactional(readOnly = false)
	public void delete(DnaStrImport dnaStrImport) {
		super.delete(dnaStrImport);
		dnaStrImportItemDao.delete(new DnaStrImportItem(dnaStrImport));
	}

}