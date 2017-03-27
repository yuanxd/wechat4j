/**
 * 
 */
package com.yuanxd.wx.wechat4j.token.server;

import com.yuanxd.wx.wechat4j.common.Config;

/**
 * ÊÊÅäÆ÷
 * @author ChengNing
 * @date   2015Äê1ÔÂ30ÈÕ
 */
public class AccessTokenServer extends AbsServer implements TokenServer {
	

	/**
	 * 
	 */
	public String token(){
		return super.token();
	}

	@Override
	protected String getCustomerServerClass() {
		return Config.instance().getAccessTokenServer();
	}

	@Override
	public IServer defaultServer() {
		return AccessTokenMemServer.instance();
	}

}
