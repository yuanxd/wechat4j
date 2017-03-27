/**
 * 
 */
package com.yuanxd.wx.wechat4j.token.server;

import com.yuanxd.wx.wechat4j.common.Config;


/**
 * Ticket serverÊÊÅäÆ÷
 * @author ChengNing
 * @date   2015Äê1ÔÂ29ÈÕ
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
