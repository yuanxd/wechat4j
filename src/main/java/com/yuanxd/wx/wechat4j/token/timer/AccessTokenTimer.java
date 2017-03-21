/**
 * 
 */
package com.yuanxd.wx.wechat4j.token.timer;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.yuanxd.wx.wechat4j.token.AccessToken;
import com.yuanxd.wx.wechat4j.token.server.AccessTokenServer;
import com.yuanxd.wx.wechat4j.token.server.CustomerServer;

/**
 * access token ��ʱ��
 * @author ChengNing
 * @date   2015��1��8��
 */
public class AccessTokenTimer extends TimerTask{
	
	private static Logger logger = Logger.getLogger(AccessTokenTimer.class);
	
	//accessToken��Ч��7200��,��ǰ200�������µ�token
	public static final long PERIOD = 7000 * 1000;
	public static final long DELAY = 0; //��������ӳ�ʱ��Ϊ0��������ִ��

	@Override
	public void run() {
		logger.info("accessToken ��ʱ������������ȡ�µ�accessToken");
		//�õ��µ�access token
		AccessToken accessToken = new AccessToken();
		//��ȡ�ɹ�֮��־û�accessToken
		if(accessToken.request()){
			AccessTokenServer accessTokenServer = new AccessTokenServer();
			CustomerServer customerServer = (CustomerServer)accessTokenServer.customerServer();
			customerServer.save(accessToken);
		}
	}

}
