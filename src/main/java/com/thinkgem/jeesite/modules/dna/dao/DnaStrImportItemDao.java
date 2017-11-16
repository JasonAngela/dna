/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaStrImportItem;

/**
 * str导入记录DAO接口
 * @author zhuguli
 * @version 2017-05-11
 */
@MyBatisDao
public interface DnaStrImportItemDao extends CrudDao<DnaStrImportItem> {
	
}