package com.thinkgem.jeesite.modules.entrust.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.modules.material.service.SpecimenMaterialInService;
/**
 * 自动生成入库单
 * @author zhuguli
 */
@Component
public class AutoMakeMaterialInDelegate implements JavaDelegate {
	@Autowired
	private SpecimenMaterialInService specimenMaterialInService;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("========自动生成入库单==============");
		String businessKey = execution.getProcessBusinessKey();
		specimenMaterialInService.autoMakeMaterialIn(businessKey);
		
	}

}
