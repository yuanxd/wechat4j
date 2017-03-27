/**
 * ��Ӧ�ӿ��ĵ���ַ
 * http://mp.weixin.qq.com/wiki/17/304c1885ea66dbedf7dc170d84999a9d.html
 */
package com.yuanxd.wx.wechat4j.message;


import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.yuanxd.wx.wechat4j.lang.HttpUtils;
import com.yuanxd.wx.wechat4j.message.template.TemplateMsgBody;
import com.yuanxd.wx.wechat4j.message.template.TemplateMsgData;
import com.yuanxd.wx.wechat4j.token.TokenProxy;

/**
 * ģ����Ϣ�ӿ�
 * @author ChengNing
 * @date   2014��12��24��
 */
public class TemplateMsg {
	
	private static Logger logger = Logger.getLogger(TemplateMsg.class);
	
	//����������ҵ�ӿڵ�ַ
	public static final String SET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=";
	//���ģ��id�ӿڵ�ַ
	public static final String GET_TEMPLATE_ID_URL = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=";
	//����ģ����Ϣ�ӿڵ�ַ
	public static final String SEND_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	
	private String accessToken;
	
	public TemplateMsg(){
		this.accessToken = TokenProxy.accessToken();
	}
	
	/**
	 * ����������ҵ
	 * �ӿ�˵����û�и���
	 */
	public void setIndustry(String...industrys){
		String url = SET_INDUSTRY_URL + this.accessToken;
		JSONObject json = new JSONObject();
		for(int i=0;i<industrys.length;i++){
			json.put("industry_id" + i, industrys[i]);
		}
		String data = json.toJSONString();
		HttpUtils.post(url, data);
	}

	/**
	 * ���ģ��ID
	 * @param templateIdShort    template_id_short
	 * @return                   template_id
	 */
	public String getTemplateId(String templateIdShort){
		logger.info("get template id,short template id is:" + templateIdShort);
		//������������
		String url = GET_TEMPLATE_ID_URL + this.accessToken;
		JSONObject json = new JSONObject();
		json.put("template_id_short", templateIdShort);
		String data = json.toJSONString();
		String result = HttpUtils.post(url, data);
		logger.info("post result:" + result);
		//������������
		JSONObject resultJson = JSONObject.parseObject(result);
		if(resultJson.getString("errcode").equals("0"))
			return resultJson.getString("template_id");
		logger.error("get template id error:" + resultJson.getString("errmsg"));
		return null;
	}
	
	/**
	 * ����ģ����Ϣ
	 */
	public String send(TemplateMsgBody postData){
		logger.info("send template message");
		//������������
		String url = SEND_MSG_URL + this.accessToken;
		JSONObject json = new JSONObject();
		json.put("touser", postData.getTouser());
		json.put("template_id", postData.getTemplateId());
		json.put("url", postData.getUrl());
		json.put("topcolor", postData.getTopcolor());
		JSONObject jsonData = new JSONObject();
		for (TemplateMsgData data : postData.getData()) {
			JSONObject keynote = new JSONObject();
			keynote.put("value", data.getValue());
			keynote.put("color", data.getColor());
			jsonData.put(data.getName(), keynote);
		}
		json.put("data", jsonData);
		
		String data = json.toJSONString();
		String result = HttpUtils.post(url, data);
		logger.info("post result:" + result);
		//������������
		JSONObject resultJson = JSONObject.parseObject(result);
		if(resultJson.getString("errcode").equals("0"))
			return resultJson.getString("msgid");
		
		logger.error("send template message error:" + resultJson.getString("errmsg"));
		return null;
	}
	
	
}
