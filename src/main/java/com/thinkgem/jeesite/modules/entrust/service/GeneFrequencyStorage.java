package com.thinkgem.jeesite.modules.entrust.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.dna.dao.DnaGeneFrequencyDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaGeneFrequency;

@Service
public class GeneFrequencyStorage {
	@Autowired
	private DnaGeneFrequencyDao geneFrequencyDao;
	private Map<String, Double> storage;
	@PostConstruct
	public void init(){
		storage = new HashMap<String, Double>();
		List<DnaGeneFrequency> list = geneFrequencyDao.findAllList(new DnaGeneFrequency());
		for(DnaGeneFrequency frequency:list){
			storage.put(frequency.getLoci().getName()+"_"+frequency.getValue(),frequency.getProbability());
		}
	}
	
	public Double getProb(String key){
		return storage.get(key);
	}
}
 