/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.clinic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.clinic.entity.ClinicRegister;

/**
 * 临床登记DAO接口
 * @author zhuguli
 * @version 2017-10-15
 */
@MyBatisDao
public interface ClinicRegisterDao extends CrudDao<ClinicRegister> {
	
}