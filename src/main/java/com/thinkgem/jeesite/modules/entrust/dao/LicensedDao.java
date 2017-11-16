/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.entrust.entity.Licensed;

/**
 * 执业证DAO接口
 * @author 浮云
 * @version 2017-09-13
 */
@MyBatisDao
public interface LicensedDao extends CrudDao<Licensed> {
	List<Licensed>  findEntrust(String entrustId);
}