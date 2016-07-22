
package com.demo.test.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: ParseTxt 
 * @Description: �����ܴ�txt�ĵ�������������
 * @date 2015��7��17�� ����11:45:11  
 */
public class ParseTxt {

	String filePath = null;
	BufferedReader br = null;
	BufferedWriter bw = null;
	
	public ParseTxt(String filePath){
		this.filePath = filePath;
	}
	
	public List<String> readByLines()  {
		
		List<String> result = new ArrayList<String>();
		
		try {
			br = new BufferedReader(new FileReader(filePath));	
			String temp = null;
			
			while((temp=br.readLine()) != null){
				result.add(temp);
			}
							
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
		
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			    
		return result;	
	}
	
	public void writeByLines(List<String> texts){
		
		try {
			bw = new BufferedWriter(new FileWriter(filePath));
			int size = texts.size();
			for(int i=0;i<size-1;i++){
				bw.write(texts.get(i));
				bw.write("\n");
			}
			bw.write(texts.get(size-1));	
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			if(bw !=null){
				try {
					bw.flush();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(bw !=null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
