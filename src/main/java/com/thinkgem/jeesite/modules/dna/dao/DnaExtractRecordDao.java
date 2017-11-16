/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaExtractRecord;

/**
 * 提取室记录DAO接口
 * @author yunyun
 * @version 2017-08-19
 */
@MyBatisDao
public interface DnaExtractRecordDao extends CrudDao<DnaExtractRecord> {
	
}