/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.material.entity.SpecimenMaterialRegisterItem;

/**
 * 物证登记DAO接口
 * @author zhuguli
 * @version 2017-05-10
 */
@MyBatisDao
public interface SpecimenMaterialRegisterItemDao extends CrudDao<SpecimenMaterialRegisterItem> {

	List<SpecimenMaterialRegisterItem> listByParentId(@Param("registerId")String registerId);

	List<SpecimenMaterialRegisterItem> selectByCode(@Param("specimenCode") String specimenCode);
	List<SpecimenMaterialRegisterItem> findall(String id);
	List<SpecimenMaterialRegisterItem> findac(String id);

	List<SpecimenMaterialRegisterItem> getNotExperiment();
}