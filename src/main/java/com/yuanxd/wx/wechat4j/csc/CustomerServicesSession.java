package com.yuanxd.wx.wechat4j.csc;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * �Ự״̬
 * @author Zhangxs
 * @date 2015-7-8 
 * @version
 */
public class CustomerServicesSession {
	
	private int createTime;//�Ự�����ʱ��
    private String kfAccount;//�ͷ�
    private String openId;//�ͻ�openid
	/**
	 * �Ự�����ʱ��
	 * @return
	 */
	@JSONField(name="createtime")
	public int getCreateTime() {
		return createTime;
	}
	@JSONField(name="createtime")
	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
	/**
	 * �ͷ�
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
	 * �ͻ�openid
	 * @return
	 */
	@JSONField(name="openid")
	public String getOpenId() {
		return openId;
	}
	@JSONField(name="openid")
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
     
     

}
