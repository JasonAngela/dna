/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oneself.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oneself.entity.Oneself;

/**
 * 单独实验DAO接口
 * @author fuyun
 * @version 2017-10-18
 */
@MyBatisDao
public interface OneselfDao extends CrudDao<Oneself> {
	
}