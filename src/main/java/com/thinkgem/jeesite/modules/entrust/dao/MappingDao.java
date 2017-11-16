/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.entrust.entity.Mapping;

/**
 * 图谱DAO接口
 * @author 好好
 * @version 2017-09-08
 */
@MyBatisDao
public interface MappingDao extends CrudDao<Mapping> {
     Mapping	findEntrsut(String entrustID);
}