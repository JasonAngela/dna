package com.thinkgem.jeesite.modules.dna.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class DnaSpe {
	
 		private List<DnaSpeIteam>dnaSpeIteams=Lists.newArrayList();

		public List<DnaSpeIteam> getDnaSpeIteams() {
			return dnaSpeIteams;
		}

		public void setDnaSpeIteams(List<DnaSpeIteam> dnaSpeIteams) {
			this.dnaSpeIteams = dnaSpeIteams;
		} 
	

}
