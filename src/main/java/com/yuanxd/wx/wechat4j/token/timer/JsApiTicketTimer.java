/**
 * 
 */
package com.yuanxd.wx.wechat4j.token.timer;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.yuanxd.wx.wechat4j.token.Ticket;
import com.yuanxd.wx.wechat4j.token.TicketType;
import com.yuanxd.wx.wechat4j.token.server.CustomerServer;
import com.yuanxd.wx.wechat4j.token.server.JsApiTicketServer;

/**
 * @author ChengNing
 * @date 2015��1��29��
 */
public class JsApiTicketTimer extends TimerTask {

	private static Logger logger = Logger.getLogger(JsApiTicketTimer.class);
	// jsapi_ticket��Ч��7200��,��ǰ200�������µ�token
	public static final long PERIOD = 7000 * 1000;
	public static final long DELAY = 0; // ��������ӳ�ʱ��Ϊ0��������ִ��

	@Override
	public void run() {
		logger.info("jsapi_ticket ��ʱ������������ȡ�µ�jsapi_ticket");
		// �õ��µ�access token
		Ticket jsapiTicket = new Ticket(TicketType.jsapi);
		// �ֶ���ȡ�ɹ�֮��־û�accessToken
		if (jsapiTicket.request()) {
			JsApiTicketServer jsapiTicketServer = new JsApiTicketServer();
			CustomerServer customerServer = (CustomerServer) jsapiTicketServer
					.customerServer();
			customerServer.save(jsapiTicket);
		}
	}

}
