/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.SysCodeRuleItem;

/**
 * 编码规则DAO接口
 * @author zhuguli
 * @version 2017-05-12
 */
@MyBatisDao
public interface SysCodeRuleItemDao extends CrudDao<SysCodeRuleItem> {
	
}