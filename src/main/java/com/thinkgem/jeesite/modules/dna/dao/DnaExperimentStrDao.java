/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperimentStr;

/**
 * str值导入DAO接口
 * @author zhuguli
 * @version 2017-06-17
 */
@MyBatisDao
public interface DnaExperimentStrDao extends CrudDao<DnaExperimentStr> {

	List<DnaExperimentStr> getByExperimentIdAndAbstractsId(@Param("dnaExperimentId")String dnaExperimentId, @Param("abstractsId")String abstractsId);
	/**
	 * @param id
	 * @param specimenCode
	 * @return
	 */
	List<DnaExperimentStr> getByExperimentIdAndCode(@Param("dnaExperimentId")String experimentId,@Param("specimenCode") String specimenCode);
	List<DnaExperimentStr> getByRegisterId(@Param("registerId")String registerId);
	List<DnaExperimentStr> getById(String id);
	void delect();
	
}