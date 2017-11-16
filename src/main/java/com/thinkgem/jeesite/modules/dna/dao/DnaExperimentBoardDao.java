/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaExperimentBoard;

/**
 * 板子DAO接口
 * @author zhuguli
 * @version 2017-06-11
 */
@MyBatisDao
public interface DnaExperimentBoardDao extends CrudDao<DnaExperimentBoard> {

	List<DnaExperimentBoard> findByParentId(@Param("parentId")String parentId);
	
}