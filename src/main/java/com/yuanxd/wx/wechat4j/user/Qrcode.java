package com.yuanxd.wx.wechat4j.user;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;

import com.alibaba.fastjson.annotation.JSONField;
import com.yuanxd.wx.wechat4j.lang.HttpUtils;

/**
 * ��ά��
 * @author Zhangxs
 * @version 2015-7-5
 */
public class Qrcode {
    //ͨ��ticket��ȡ��ά��
    private static final String SHOWQRCODE_POST_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
	private String ticket;//	��ȡ�Ķ�ά��ticket��ƾ���ticket��������Чʱ���ڻ�ȡ��ά�롣
	private Integer expireSeconds;//	��ά�����Чʱ�䣬����Ϊ��λ����󲻳���1800��
	private String url;//	��ά��ͼƬ������ĵ�ַ�������߿ɸ��ݸõ�ַ����������Ҫ�Ķ�ά��ͼƬ
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	@JSONField(name="expire_seconds")
	public Integer getExpireSeconds() {
		return expireSeconds;
	}
	@JSONField(name="expire_seconds")
	public void setExpireSeconds(Integer expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

    /**
     * ��ȡ��ά��
     * @param qrcodeFile ��ά��洢·��
     */
    public void getQrcode(String qrcodeFile){
        try {
            byte[] b = HttpUtils.getFile(SHOWQRCODE_POST_URL + URLEncoder.encode(ticket, "UTF-8"));
            File file = new File(qrcodeFile);
            FileOutputStream fStream = new FileOutputStream(file);
            fStream.write(b);
            fStream.flush();
            fStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ͨ����ά���ȡ��ά��URL
     * @return
     */
    public String getQrcodeUrl(){
        return SHOWQRCODE_POST_URL + ticket;
    }
	
}
