/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.synth.entity.SynthLabUsageRecord;

/**
 * 实验室使用记录DAO接口
 * @author zhuguli
 * @version 2017-05-13
 */
@MyBatisDao
public interface SynthLabUsageRecordDao extends CrudDao<SynthLabUsageRecord> {
	
}