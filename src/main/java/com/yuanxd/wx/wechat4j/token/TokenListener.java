/**
 * 
 */
package com.yuanxd.wx.wechat4j.token;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.yuanxd.wx.wechat4j.token.timer.AccessTokenTimer;
import com.yuanxd.wx.wechat4j.token.timer.JsApiTicketTimer;


/**
 * Access Token ������
 * @author ChengNing
 * @date   2015��1��8��
 */
public class TokenListener implements ServletContextListener{
	
    private static Logger log = Logger.getLogger(TokenListener.class);
    
	private Timer timer = null;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("accessToken����������..........");
		timer = new Timer(true);
		//ע�ᶨʱ����
		registeAccessTokenTimer();
		//ע��jsapi_ticket��ʱ��
		registeJsApiTicketTimer();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
	}
	
	/**
	 * ע��accessToken��ʱ��
	 */
	private void registeAccessTokenTimer(){
		AccessTokenTimer accessTokenTimer = new AccessTokenTimer();
		timer.schedule(accessTokenTimer, AccessTokenTimer.DELAY,AccessTokenTimer.PERIOD);
		log.info("accessToken��ʱ��ע��ɹ���ִ�м��Ϊ" + AccessTokenTimer.PERIOD);
	}
	
	/**
	 * ע��jsapi_ticket��ʱ��
	 */
	private void registeJsApiTicketTimer(){
		JsApiTicketTimer jsApiTicketTimer = new JsApiTicketTimer();
		timer.schedule(jsApiTicketTimer, JsApiTicketTimer.DELAY,JsApiTicketTimer.PERIOD);
	}
	
}
