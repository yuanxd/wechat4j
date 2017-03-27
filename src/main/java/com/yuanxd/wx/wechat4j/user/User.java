package com.yuanxd.wx.wechat4j.user;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * �û�ʵ����
 * @author Zhangxs
 * @version 2015-7-5
 */
public class User {
	private int subscribe;//	�û��Ƿ��ĸù��ںű�ʶ��ֵΪ0ʱ��������û�û�й�ע�ù��ںţ���ȡ����������Ϣ��
	private String openId;//	�û��ı�ʶ���Ե�ǰ���ں�Ψһ
	private String nickName;//	�û����ǳ�
	private int sex;//	�û����Ա�ֵΪ1ʱ�����ԣ�ֵΪ2ʱ��Ů�ԣ�ֵΪ0ʱ��δ֪
	private String city;//	�û����ڳ���
	private String country;//	�û����ڹ���
	private String province;//	�û�����ʡ��
	private LanguageType language;//	�û������ԣ���������Ϊzh_CN
	private String headimgUrl;//	�û�ͷ�����һ����ֵ����������ͷ���С����0��46��64��96��132��ֵ��ѡ��0����640*640������ͷ�񣩣��û�û��ͷ��ʱ����Ϊ�ա����û�����ͷ��ԭ��ͷ��URL��ʧЧ��
	private String subscribeTime;//	�û���עʱ�䣬Ϊʱ���������û�����ι�ע����ȡ����עʱ��
	private String unionId;//	ֻ�����û������ںŰ󶨵�΢�ſ���ƽ̨�ʺź󣬲Ż���ָ��ֶΡ��������ȡ�û�������Ϣ��UnionID���ƣ�
	private String remark;//	���ں���Ӫ�߶Է�˿�ı�ע�����ں���Ӫ�߿���΢�Ź���ƽ̨�û��������Է�˿��ӱ�ע
	private int groupId;//	�û����ڵķ���ID
	
	/**
	 * �û��Ƿ��ĸù��ںű�ʶ
	 * ֵΪ0ʱ��������û�û�й�ע�ù��ںţ���ȡ����������Ϣ
	 */
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	/**
	 * �û��ı�ʶ���Ե�ǰ���ں�Ψһ
	 */
	@JSONField(name="openid")
	public String getOpenId() {
		return openId;
	}
	
	@JSONField(name="openid")
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	/**
	 * �û����ǳ�
	 */
	@JSONField(name="nickname")
	public String getNickName() {
		return nickName;
	}
	@JSONField(name="nickname")
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * �û����Ա�
	 * ֵΪ1ʱ�����ԣ�
	 * ֵΪ2ʱ��Ů�ԣ�
	 * ֵΪ0ʱ��δ֪
	 */
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	/**
	 * �û����ڳ���
	 */
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * �û����ڹ���
	 */
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * �û�����ʡ��
	 */
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * �û�������
	 */
	public void setLanguage(LanguageType language) {
		this.language = language;
	}
	public LanguageType getLanguage() {
		return language;
	}
	/**
	 * �û�ͷ��
	 * ���һ����ֵ����������ͷ���С����0��46��64��96��132��ֵ��ѡ��0����640*640������ͷ�񣩣�
	 * �û�û��ͷ��ʱ����Ϊ�ա�
	 * ���û�����ͷ��ԭ��ͷ��URL��ʧЧ��
	 */
	@JSONField(name="headimgurl")
	public String getHeadimgUrl() {
		return headimgUrl;
	}
	@JSONField(name="headimgurl")
	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}
	/**
	 * �û���עʱ�䣬Ϊʱ�����
	 * ����û�����ι�ע����ȡ����עʱ��
	 */
	
	@JSONField(name="subscribe_time")
	public String getSubscribeTime() {
		return subscribeTime;
	}
	@JSONField(name="subscribe_time")
	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	/**
	 * ֻ�����û������ںŰ󶨵�΢�ſ���ƽ̨�ʺź󣬲Ż���ָ��ֶΡ�
	 * �������ȡ�û�������Ϣ��UnionID���ƣ�
	 */
	
	@JSONField(name="unionid")
	public String getUnionId() {
		return unionId;
	}
	@JSONField(name="unionid")
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	/**
	 * ���ں���Ӫ�߶Է�˿�ı�ע��
	 * ���ں���Ӫ�߿���΢�Ź���ƽ̨�û��������Է�˿��ӱ�ע
	 */
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * �û����ڵķ���ID
	 */
	@JSONField(name="groupid")	
	public int getGroupId() {
		return groupId;
	}
	@JSONField(name="groupid")
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	@Override
	public String toString() {
		return "User [subscribe=" + subscribe + ", openId=" + openId
				+ ", nickName=" + nickName + ", sex=" + sex + ", city=" + city
				+ ", country=" + country + ", province=" + province
				+ ", language=" + language + ", headimgUrl=" + headimgUrl
				+ ", subscribeTime=" + subscribeTime + ", unionId=" + unionId
				+ ", remark=" + remark + ", groupId=" + groupId + "]";
	}
	
	
	
}
