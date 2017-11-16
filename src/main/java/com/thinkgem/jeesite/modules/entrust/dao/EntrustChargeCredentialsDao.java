/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.entrust.entity.EntrustChargeCredentials;

/**
 * 收费凭据DAO接口
 * @author 浮云
 * @version 2017-09-14
 */
@MyBatisDao
public interface EntrustChargeCredentialsDao extends CrudDao<EntrustChargeCredentials> {
		EntrustChargeCredentials findEntrust(String entrustId);
}