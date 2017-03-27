/**
 * 
 */
package com.yuanxd.wx.wechat4j.token;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.yuanxd.wx.wechat4j.lang.HttpUtils;

/**
 * @author ChengNing
 * @date   2015��1��29��
 */
public abstract class Token {
	private static Logger logger = Logger.getLogger(Token.class);
	
	private String token;   //token
	private long expires;         //token��Чʱ��
	
	private long tokenTime;       //token����ʱ��
	private int redundance = 10*1000;  //����ʱ�䣬��ǰ10���ȥ�����µ�token
	
	/**
	 * �õ�access token
	 */
	public String getToken(){
		return this.token;
	}
	
	/**
	 * �õ���Чʱ��
	 */
	public long getExpires() {
		return expires;
	}
	
	/**
	 * �����ŵ�access token
	 * http����ʽ: GET
		https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
		{"access_token":"ACCESS_TOKEN","expires_in":7200}
		{"errcode":40013,"errmsg":"invalid appid"}
	 */
	public boolean request(){
		String url = accessTokenUrl();
		String result = HttpUtils.get(url);
		if(StringUtils.isBlank(result))
			return false;
		if(!parseData(result)){
			return false;
		}
		logger.info("token��ȡ�ɹ�");
		return true;
	}
	
	/**
	 * ����token����
	 * @param data
	 * @return
	 */
	private boolean parseData(String data){
		JSONObject jsonObject = JSONObject.parseObject(data);
		String tokenName = tokenName();
		String expiresInName = expiresInName();
		try {
			String token = jsonObject.get(tokenName).toString();
			if(StringUtils.isBlank(token)){
				logger.error("token��ȡʧ��,��ȡ���" + data);
				return false;
			}
			this.token = token;
			this.tokenTime = (new Date()).getTime();
			String expiresIn = jsonObject.get(expiresInName).toString();
			if(StringUtils.isBlank(expiresIn)){
				logger.error("token��ȡʧ��,��ȡ���" + expiresIn);
				return false;
			}
			else{
				this.expires = Long.valueOf(expiresIn);
			}
		} catch (Exception e) {
			logger.error("token �������ʧ�ܣ�token��������: " + tokenName 
					+ "��Ч�ڲ�������:" + expiresInName
					+ "token������:" + data);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * token�Ĳ�������
	 * @return
	 */
	protected abstract String tokenName(); 
	/**
	 * expireIn�Ĳ�������
	 * @return
	 */
	protected abstract String expiresInName(); 

	/**
	 * ��֯accesstoken������utl
	 * @return
	 */
	protected abstract String accessTokenUrl();
	
	/**
	 * accessToken �Ƿ���Ч
	 * @return true:��Ч��false: ��Ч
	 */
	public boolean isValid(){
		//�������ж���
		if(StringUtils.isBlank(this.token))
			return false;
		if(this.expires <= 0)
			return false;
		//����
		if(isExpire())
			return false;
		return true;
	}
	
	/**
	 * �Ƿ����
	 * @return true ���� false����Ч
	 */
	private boolean isExpire(){
		Date currentDate = new Date();
		long currentTime = currentDate.getTime();
		long expiresTime = expires * 1000 - redundance;
		//�ж��Ƿ����
		if((tokenTime + expiresTime) > currentTime)
			return false;
		return true;
	}
}
