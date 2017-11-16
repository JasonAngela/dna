/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.synth.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.synth.entity.SynthLab;

/**
 * 实验室DAO接口
 * @author zhuguli
 * @version 2017-05-13
 */
@MyBatisDao
public interface SynthLabDao extends CrudDao<SynthLab> {
	List<SynthLab> findAll();
}