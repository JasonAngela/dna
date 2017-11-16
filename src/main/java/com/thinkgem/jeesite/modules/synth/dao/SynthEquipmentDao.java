/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.synth.entity.SynthEquipment;

/**
 * 设备管理DAO接口
 * @author zhuguli
 * @version 2017-05-13
 */
@MyBatisDao
public interface SynthEquipmentDao extends CrudDao<SynthEquipment> {
	List<SynthEquipment>  findAll(String id);
}