package com.yuanxd.wx.wechat4j.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * ָ���ͷ�
 * @author Zhangxs
 * @version 2015-7-7
 */

public class TransInfoResponse {
	private String KfAccount;//ָ���Ự����Ŀͷ��˺�
	
	public TransInfoResponse() {
		super();
	}

	public TransInfoResponse(String kfAccount) {
		super();
		KfAccount = kfAccount;
	}

	@XmlElement(name="KfAccount")
	public String getKfAccount() {
		return KfAccount;
	}

	public void setKfAccount(String kfAccount) {
		KfAccount = kfAccount;
	}
	
}
