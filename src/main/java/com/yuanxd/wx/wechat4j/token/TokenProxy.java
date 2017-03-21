/**
 * 
 */
package com.yuanxd.wx.wechat4j.token;

import com.yuanxd.wx.wechat4j.token.server.AccessTokenServer;
import com.yuanxd.wx.wechat4j.token.server.JsApiTicketServer;
import com.yuanxd.wx.wechat4j.token.server.TicketServer;
import com.yuanxd.wx.wechat4j.token.server.TokenServer;


/**
 * AccessToken����
 * ���л�ȡaccessToken�ĵط���ͨ���˴�����
 * ��÷���String token = AccessTokenProxy.token()
 * @author ChengNing
 * @date   2015��1��9��
 */
public class TokenProxy {
	
	/**
	 * ͨ������õ�accessToken�Ĵ�
	 */
	public static String accessToken(){
		TokenServer accessTokenServer = new AccessTokenServer();
		return accessTokenServer.token();
	}
	
	/**
	 * ͨ������õ�jsapi_ticket
	 */
	public static String jsApiTicket(){
		TicketServer ticketServer = new JsApiTicketServer();
		return ticketServer.ticket();
	}
	

	
	
}
