/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.entrust_flow_sheet.dao.entrust;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.entrust_flow_sheet.entity.entrust.EntrustFlowSheet;

/**
 * 流转单DAO接口
 * @author fu
 * @version 2017-09-14
 */
@MyBatisDao
public interface EntrustFlowSheetDao extends CrudDao<EntrustFlowSheet> {
	EntrustFlowSheet  findEntrust (String entrustId);
}