/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaElectrophoresisPartingIteam;

/**
 * 电泳室DAO接口
 * @author fuyun
 * @version 2017-09-06
 */
@MyBatisDao
public interface DnaElectrophoresisPartingIteamDao extends CrudDao<DnaElectrophoresisPartingIteam> {
	List<DnaElectrophoresisPartingIteam> findNumber(String sampleNumber); 
}