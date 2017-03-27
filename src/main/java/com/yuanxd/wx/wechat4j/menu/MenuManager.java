package com.yuanxd.wx.wechat4j.menu;




import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuanxd.wx.wechat4j.exception.WeChatException;
import com.yuanxd.wx.wechat4j.lang.HttpUtils;
import com.yuanxd.wx.wechat4j.token.TokenProxy;
import com.yuanxd.wx.wechat4j.util.WeChatUtil;
/**
 * ΢�Ų˵�����
 * @author Zhangxs
 * @version 2015-7-4
 */
public class MenuManager {
	private static Logger logger = Logger.getLogger(MenuManager.class);
	
	private static final String MENU_CREATE_POST_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	private static final String MENU_GET_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
	private static final String MENU_DEL_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";

	private String accessToken;
	public MenuManager() {
		this.accessToken = TokenProxy.accessToken();
	}
	/**
	 * �����˵�
	 * @throws WeChatException 
	 */
	public void create(Menu menu) throws WeChatException{
		logger.info("�����˵�");
		String resultStr = HttpUtils.post(MENU_CREATE_POST_URL+this.accessToken, JSON.toJSONString(menu));
		WeChatUtil.isSuccess(resultStr);
	}
	
	/**
	 * ��ѯ�˵�
	 */
	public Menu getMenu() {	
		logger.info("��ѯ�˵�");
		String resultStr = HttpUtils.get(MENU_GET_GET_URL+this.accessToken);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			e.printStackTrace();
			return null;
		}
		JSONObject menuObject = JSONObject.parseObject(resultStr);
		Menu menu = menuObject.getObject("menu", Menu.class);
		return menu;
	}
	/**
	 * ɾ���˵�
	 * @throws WeChatException 
	 */
	public void delete() throws WeChatException{
		logger.info("ɾ���˵�");
		String resultStr = HttpUtils.get(MENU_DEL_GET_URL+this.accessToken);
		WeChatUtil.isSuccess(resultStr);
	}
	
}
