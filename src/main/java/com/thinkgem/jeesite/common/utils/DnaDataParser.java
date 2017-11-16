package com.thinkgem.jeesite.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 数据导入
 * @author doonly
 *
 */
public class DnaDataParser {
	public static List<Map<String, String>> parse(String importDataAddress) {
		FileReader fileReader = null;
		BufferedReader reader = null;
		try {
			fileReader = new FileReader(new File(importDataAddress));
			reader = new BufferedReader(fileReader);
			String readLine = reader.readLine();
			String[]columns = readLine.split("\t");
			List<Map<String,String>>data = new ArrayList<Map<String,String>>();
			while((readLine = reader.readLine())!=null){
				String []dataColumns = readLine.split("\t");
				Map<String,String> dataMap = new HashMap<String, String>();
				for(int i=0;i<columns.length;i++){
					if(i<dataColumns.length){
						dataMap.put(columns[i], dataColumns[i]);
					}else{
						dataMap.put(columns[i],null);
					}
					
				}
				data.add(dataMap);
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}  finally {
			if(fileReader!=null){
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
