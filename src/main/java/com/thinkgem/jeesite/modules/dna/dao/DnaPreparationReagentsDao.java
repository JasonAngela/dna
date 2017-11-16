/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaPreparationReagents;

/**
 * 试剂配制记录表DAO接口
 * @author fyun
 * @version 2017-09-06
 */
@MyBatisDao
public interface DnaPreparationReagentsDao extends CrudDao<DnaPreparationReagents> {
	
}