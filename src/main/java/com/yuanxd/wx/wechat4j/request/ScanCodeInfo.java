/**
 * 
 */
package com.yuanxd.wx.wechat4j.request;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author ChengNing
 * @date   2015��1��7��
 */
public class ScanCodeInfo {
	private String ScanType;   //ɨ�����ͣ�һ����qrcode
	private String ScanResult; //ɨ����������ά���Ӧ���ַ�����Ϣ
	
	@XmlElement(name="ScanType")
	public String getScanType() {
		return ScanType;
	}
	public void setScanType(String scanType) {
		ScanType = scanType;
	}
	@XmlElement(name="ScanResult")
	public String getScanResult() {
		return ScanResult;
	}
	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}
	
	
}
