package com.thinkgem.jeesite.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportTest {
	public static void main(String[] args) throws IOException {
		FileReader fileReader = new FileReader(new File("/Users/doonly/Documents/workspace_jeesite/jeesite/doc/20170206-001-wj.txt"));
		BufferedReader reader = new BufferedReader(fileReader);
		String readLine = reader.readLine();
		String[]columns = readLine.split("\t");
		List<Map<String,String>>data = new ArrayList<Map<String,String>>();
		while((readLine = reader.readLine())!=null){
			String []dataColumns = readLine.split("\t");
			Map<String,String> dataMap = new HashMap<String, String>();
			for(int i=0;i<columns.length;i++){
				dataMap.put(columns[i], dataColumns[i]);
			}
			data.add(dataMap);
		}
		reader.close();
		
		for(Map<String,String>s :data){
			if(s.get("Sample Name").equals("C001")||s.get("Sample Name").equals("C002")){
				System.out.println(s.get("Sample Name")+"-----"+s.get("Marker")+"----"+s.get("Allele 1")+"---"+s.get("Allele 2"));
			}
		}
	}
}
