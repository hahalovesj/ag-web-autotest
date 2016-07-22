package com.demo.test.utils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;


public class UpdateHosts {
		
	 public synchronized static  boolean updateHostName(String ip,String hostName) throws Exception {

	        String splitter = " ";
	        String fileName = null;

	        // �ж�ϵͳ
	        if ("linux".equalsIgnoreCase(System.getProperty("os.name"))) {
	            fileName = "/etc/hosts";
	        } else {
	            fileName = "C://WINDOWS//system32//drivers//etc//hosts";
	        }

	        // �����趨�ļ�
	        List < ? > lines = FileUtils.readLines(new File(fileName));
	        List <String> newLines = new ArrayList <String>();
	        boolean findFlag = false;
	        boolean updateFlag = false;
	        for (Object line : lines) {
	            String strLine = (String) line;
	            if (strLine.trim().length()>0 && !strLine.startsWith("#")) {
	                strLine = strLine.replaceAll("/t", splitter);
	                int index = strLine.toLowerCase().indexOf(hostName.toLowerCase());
	                if (index != -1) {
	                    String[] array = strLine.trim().split(splitter);
	                    for (String name : array) {
	                        if (hostName.equalsIgnoreCase(name)) {
	                            findFlag = true;
	                            if (array[0].equals(ip)) {
	                                // ���IP��ͬ���򲻸���
	                                newLines.add(strLine);
	                                break;
	                            }
	                            // ���³��趨�õ�IP��ַ
	                            StringBuilder sb = new StringBuilder();
	                            sb.append(ip);
	                            for (int i = 1; i < array.length; i++) {
	                                sb.append(splitter).append(array[i]);
	                            }
	                            newLines.add(sb.toString());
	                            updateFlag = true;
	                            break;
	                        }
	                    }

	                    if (findFlag) {
	                        break;
	                    }
	                }
	            }
	            newLines.add(strLine);
	        }
	        // ���û��Host������׷��
	        if (!findFlag) {
	            newLines.add(new StringBuilder(ip).append(splitter).append(hostName).toString());
	        }

	        if (updateFlag || !findFlag) {
	            // д�趨�ļ�
	            FileUtils.writeLines(new File(fileName), newLines);

	            // ȷ���趨���
	            String formatIp = formatIpv6IP(ip);
	            for (int i = 0; i < 20; i++) {
	                try {
	                    boolean breakFlg = false;
	                    InetAddress[] addressArr = InetAddress.getAllByName(hostName);

	                    for (InetAddress address : addressArr) {
	                        if (formatIp.equals(address.getHostAddress())) {
	                            breakFlg = true;
	                            break;
	                        }
	                    }

	                    if (breakFlg) {
	                        break;
	                    }
	                } catch (Exception e) {
	                   // logger.warn(e.getMessage());
	                }
	                Thread.sleep(3000);
	            }
	        }

	        return updateFlag;
	    }

	    private static String formatIpv6IP(String ipV6Addr) {
	        String strRet = ipV6Addr;
	        StringBuffer replaceStr;
	        int iCount = 0;
	        char ch = ':';	        
	        if (ipV6Addr.trim().length()>0 && ipV6Addr.indexOf("::") > -1) {
	            for (int i = 0; i < ipV6Addr.length(); i++) {
	                if (ch == ipV6Addr.charAt(i)) {
	                    iCount++;
	                }
	            }

	            if (ipV6Addr.startsWith("::")) {
	                replaceStr = new StringBuffer("0:0:");
	                for (int i = iCount; i < 7; i++) {
	                    replaceStr.append("0:");
	                }
	            } else if (ipV6Addr.endsWith("::")) {
	                replaceStr = new StringBuffer(":0:0");
	                for (int i = iCount; i < 7; i++) {
	                    replaceStr.append(":0");
	                }
	            } else {
	                replaceStr = new StringBuffer(":0:");
	                for (int i = iCount; i < 7; i++) {
	                    replaceStr.append("0:");
	                }
	            }
	            strRet = ipV6Addr.trim().replaceAll("::", replaceStr.toString());
	        }
	        
	        return strRet;
	    }
	    
	    /**
	     * �Ƴ�IP��ת**/
	    public static void removeHosts(){
	    	File file=new File("C://WINDOWS//system32//drivers//etc//hosts");
	    	List<?> lines;
			try {
				lines = FileUtils.readLines(file);
				int len=lines.size();
				for(int i=len-1;i>=0;i--){
					String lineString=((String)lines.get(i)).trim();
					if(!lineString.startsWith("#")&&!lineString.trim().equals("")){
						lines.remove(i);
					}
				}
				FileUtils.writeLines(file, lines);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public static void refresh() throws IOException{
	    Runtime runtime=Runtime.getRuntime();
		runtime.exec("cmd.exe /k start ipconfig /flushdns");
	    }
}
