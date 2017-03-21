/**
 * 
 */
package com.yuanxd.wx.wechat4j.token.server;

import com.yuanxd.wx.wechat4j.common.Config;


/**
 * Ticket server������
 * @author ChengNing
 * @date   2015��1��29��
 */
public class JsApiTicketServer extends AbsServer implements TicketServer  {


	/**
	 * 
	 */
	public String ticket() {
		return super.token();
	}

	/**
	 * 
	 */
	@Override
	protected String getCustomerServerClass() {
		return Config.instance().getJsApiTicketServer();
	}

	/**
	 * 
	 */
	@Override
	public IServer defaultServer() {
		return JsApiTicketMemServer.instance();
	}

}
