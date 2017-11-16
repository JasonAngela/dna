package com.thinkgem.jeesite.modules.entrust.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.modules.entrust.entity.EntrustRegister;
import com.thinkgem.jeesite.modules.entrust.service.EntrustRegisterService;
/**
 * 
 * 自动归档
 * @author zhuguli
 *
 */
@Component
public class AutoFileDelegate implements JavaDelegate {
	@Autowired
	private EntrustRegisterService entrustRegisterService;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		EntrustRegister entrustRegister =  entrustRegisterService.get(execution.getProcessBusinessKey().replace("entrust_register:", ""));
		entrustRegister.setStatus("6");
		entrustRegisterService.save(entrustRegister);
		//其他归档的事情
		entrustRegisterService.autoFile(entrustRegister);
	}

}
