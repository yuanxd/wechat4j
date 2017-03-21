package com.yuanxd.wx.wechat4j.csc;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * �ͷ�������Ϣ
 * @author Zhangxs
 * @date 2015-7-8 
 * @version
 */
public class CustomerServices {
	private String kfAccount;//�����ͷ��˺ţ���ʽΪ���˺�ǰ׺@���ں�΢�ź�
	private String kfId;//	�ͷ�����
	private String kfHeadimgurl;//�ͷ�ͷ��url
	private String kfNick;//	�ͷ��ǳ�
	private Integer status;//	�ͷ�����״̬
	private Integer autoAccept;//	�ͷ����õ�����Զ�������
	private Integer acceptedCase;//	�ͷ���ǰ���ڽӴ��ĻỰ��
	
	/**
	 * �����ͷ��˺�<br/>
	 * ��ʽΪ���˺�ǰ׺@���ں�΢�ź�
	 * @return
	 */
	@JSONField(name="kf_account")
	public String getKfAccount() {
		return kfAccount;
	}
	@JSONField(name="kf_account")
	public void setKfAccount(String kfAccount) {
		this.kfAccount = kfAccount;
	}
	/**
	 * �ͷ�����
	 * @return
	 */
	@JSONField(name="kf_id")
	public String getKfId() {
		return kfId;
	}
	@JSONField(name="kf_id")
	public void setKfId(String kfId) {
		this.kfId = kfId;
	}
	/**
	 * �ͷ�ͷ��url
	 * @return
	 */
	@JSONField(name="kf_headimgurl")
	public String getKfHeadimgurl() {
		return kfHeadimgurl;
	}
	@JSONField(name="kf_headimgurl")
	public void setKfHeadimgurl(String kfHeadimgurl) {
		this.kfHeadimgurl = kfHeadimgurl;
	}
	/**
	 * �ͷ��ǳ�
	 * @return
	 */
	@JSONField(name="kf_nick")
	public String getKfNick() {
		return kfNick;
	}
	@JSONField(name="kf_nick")
	public void setKfNick(String kfNick) {
		this.kfNick = kfNick;
	}
	/**
	 * �ͷ�����״̬</br>
	 *  1:pc����</br>
	 *  2:�ֻ�����</br>
	 *  3:pc���ֻ�ͬʱ����
	 * @return
	 */
	@JSONField(name="status")
	public Integer getStatus() {
		return status;
	}
	@JSONField(name="status")
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * �ͷ����õ�����Զ�������
	 * @return
	 */
	@JSONField(name="auto_accept")
	public Integer getAutoAccept() {
		return autoAccept;
	}
	@JSONField(name="auto_accept")
	public void setAutoAccept(Integer autoAccept) {
		this.autoAccept = autoAccept;
	}
	/**
	 * �ͷ���ǰ���ڽӴ��ĻỰ��
	 * @return
	 */
	@JSONField(name="accepted_case")
	public Integer getAcceptedCase() {
		return acceptedCase;
	}
	@JSONField(name="accepted_case")
	public void setAcceptedCase(Integer acceptedCase) {
		this.acceptedCase = acceptedCase;
	}
	
	
}
