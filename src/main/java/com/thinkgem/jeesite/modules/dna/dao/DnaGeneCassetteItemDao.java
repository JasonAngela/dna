/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneCassetteItem;

/**
 * 基因盒DAO接口
 * @author zhuguli
 * @version 2017-06-01
 */
@MyBatisDao
public interface DnaGeneCassetteItemDao extends CrudDao<DnaGeneCassetteItem> {
	
}