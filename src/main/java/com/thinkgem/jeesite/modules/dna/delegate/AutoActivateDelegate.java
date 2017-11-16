package com.thinkgem.jeesite.modules.dna.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.modules.dna.entity.DnaExperiment;
import com.thinkgem.jeesite.modules.dna.service.DnaExperimentService;
@Component
public class AutoActivateDelegate implements JavaDelegate{
	@Autowired
	private DnaExperimentService dnaExperimentService;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String businessKey = execution.getProcessBusinessKey();
		DnaExperiment dnaExperiment =  dnaExperimentService.get(businessKey.replace("dna_experiment:", ""));
		dnaExperimentService.autoActivate(dnaExperiment);
	}

}
