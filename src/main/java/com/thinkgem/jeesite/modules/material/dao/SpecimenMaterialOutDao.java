/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialOut;

/**
 * 物料领取DAO接口
 * @author zhuguli
 * @version 2017-05-10
 */
@MyBatisDao
public interface SpecimenMaterialOutDao extends CrudDao<SpecimenMaterialOut> {
	
}