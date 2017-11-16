/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaDaily;

/**
 * dna日常记录DAO接口
 * @author fuyun
 * @version 2017-08-22
 */
@MyBatisDao
public interface DnaDailyDao extends CrudDao<DnaDaily> {
	
}