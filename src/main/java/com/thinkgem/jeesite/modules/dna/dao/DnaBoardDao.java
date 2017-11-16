/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaBoard;

/**
 * 电泳分型板DAO接口
 * @author zhuguli
 * @version 2017-06-08
 */
@MyBatisDao
public interface DnaBoardDao extends CrudDao<DnaBoard> {

	DnaBoard getByCode(@Param("code")String code);

	
}