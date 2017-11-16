/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;

/**
 * 委托登记DAO接口
 * @author zhuguli
 * @version 2017-05-12
 */
@MyBatisDao
public interface EntrustRegisterDao extends CrudDao<EntrustRegister> {
	
}