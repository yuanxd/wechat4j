package com.yuanxd.wx.wechat4j.user;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ��ע�߼���
 * @author Zhangxs
 * @version 2015-7-5
 */
public class Follwers {
	private int total;//	��ע�ù����˺ŵ����û���
	private int count;//	��ȡ��OPENID���������ֵΪ10000
	private Data data;//	�б����ݣ�OPENID���б�
	private String nextOpenid;//��ȡ�б�ĺ�һ���û���OPENID
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	@JSONField(name="next_openid")
	public String getNextOpenid() {
		return nextOpenid;
	}
	@JSONField(name="next_openid")
	public void setNextOpenid(String nextOpenid) {
		this.nextOpenid = nextOpenid;
	}
	
}

