package com.demo.test.functions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.demo.test.utils.Log;

/**
 * �ļ�����Ϊ��URLAvailability.java �ļ����ܼ����� ����һ��URL��ַ�Ƿ���Ч
 * 
 */
public class URLAvailability {
	private static URL url;
	private static HttpURLConnection con;
	private static HttpsURLConnection cons;
	private static int state = -1;

	/**
	 * ���ܣ���⵱ǰURL�Ƿ�����ӻ��Ƿ���Ч, ����������������� 5 ��, ��� 5 �ζ����ɹ�����Ϊ�õ�ַ������
	 * 
	 * @param urlStr  ָ��URL�����ַ
	 * @return URL
	 */
	public static synchronized boolean isConnect(List<String> urlStr) {
		boolean flag = true;
		int counts = 0;
		if (urlStr == null || urlStr.size() <= 0) {
			flag = false;
			Log.logError("��������Ϊ��");
		}

		for (int i = 0; i < urlStr.size(); i++) {

			if (urlStr.get(i).equalsIgnoreCase(null)) {
				Log.logError(urlStr.get(i) + "Ϊ������");
				flag = false;
			}
			while (counts < 3) {
				try {

					url = new URL(urlStr.get(i));
					con = (HttpURLConnection) url.openConnection();

					state = con.getResponseCode();
					// System.out.println("��" + counts + "������" + urlStr.get(i) +
					// "Ϊ"+state);
					if (state == 200) {
						Log.logInfo("URLL����"+urlStr.get(i)+"�ɹ���");
						// System.out.println("URL���ã�");
					}
					break;
				} catch (Exception e) {
					counts++;
					Log.logError("URL�����ã���" + counts + "������" + urlStr.get(i) + "Ϊ" + state);
					// urlStr = null;
					flag = false;
					continue;
				}
			}

		}
		return flag;
	}

	public static synchronized boolean isConnect(String[] urlStr) {
		boolean flag = true;
		int counts = 0;
		if (urlStr == null || urlStr.length <= 0) {
			flag = false;
			Log.logError("��������Ϊ��");
		}

		for (int i = 0; i < urlStr.length; i++) {
			if (urlStr[i].equalsIgnoreCase(null)) {
				Log.logError(urlStr[i] + "Ϊ������");
				flag = false;
			}
			while (counts < 5) {
				try {

					url = new URL(urlStr[i]);
					con = (HttpURLConnection) url.openConnection();
					state = con.getResponseCode();
					// System.out.println("��" + counts + "������" + urlStr.get(i) +
					// "Ϊ"+state);
					if (state == 200) {
						;
						// System.out.println("URL���ã�");
					}
					break;
				} catch (Exception e) {
					counts++;
					Log.logError("URL�����ã���" + counts + "������" + urlStr[i] + "Ϊ" + state);
					// urlStr = null;
					flag = false;
					continue;
				}
			}

		}
		return flag;
	}

	public static synchronized boolean httpsIsConnect(List<String> urlStr) {
		boolean flag = true;
		int counts = 0;
		if (urlStr == null || urlStr.size() <= 0) {
			flag = false;
			Log.logError("��������Ϊ��");
		}

		for (int i = 0; i < urlStr.size(); i++) {

			if (urlStr.get(i).equalsIgnoreCase(null)) {
				Log.logError(urlStr.get(i) + "Ϊ������");
				flag = false;
			}
			while (counts < 3) {
				try {

					url = new URL(urlStr.get(i));
					cons = (HttpsURLConnection) url.openConnection();

					state = cons.getResponseCode();
					// System.out.println("��" + counts + "������" + urlStr.get(i) +
					// "Ϊ"+state);
					if (state == 200) {
						;
						// System.out.println("URL���ã�");
					}
					break;
				} catch (Exception e) {
					counts++;
					Log.logError("URL�����ã���" + counts + "������" + urlStr.get(i) + "Ϊ" + state);
					// urlStr = null;
					flag = false;
					continue;
				}
			}

		}
		return flag;
	}

	public static synchronized boolean httpsIsConnect(String[] urlStr) {
		boolean flag = true;
		int counts = 0;
		if (urlStr == null || urlStr.length <= 0) {
			flag = false;
			Log.logError("��������Ϊ��");
		}

		for (int i = 0; i < urlStr.length; i++) {
			if (urlStr[i].equalsIgnoreCase(null)) {
				Log.logError(urlStr[i] + "Ϊ������");
				flag = false;
			}
			while (counts < 5) {
				try {

					url = new URL(urlStr[i]);
					cons = (HttpsURLConnection) url.openConnection();
					state = cons.getResponseCode();
					// System.out.println("��" + counts + "������" + urlStr.get(i) +
					// "Ϊ"+state);
					if (state == 200) {
						;
						// System.out.println("URL���ã�");
					}
					break;
				} catch (Exception e) {
					counts++;
					Log.logError("URL�����ã���" + counts + "������" + urlStr[i] + "Ϊ" + state);
					// urlStr = null;
					flag = false;
					continue;
				}
			}

		}
		return flag;
	}

}
