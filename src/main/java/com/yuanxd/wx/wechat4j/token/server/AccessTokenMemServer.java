/**
 * 
 */
package com.yuanxd.wx.wechat4j.token.server;

import com.yuanxd.wx.wechat4j.token.AccessToken;
import com.yuanxd.wx.wechat4j.token.Ticket;
import com.yuanxd.wx.wechat4j.token.TicketType;

/**
 * �ڴ��пط�����
 * access_token �пط�����
 * access_token�������ڴ���,�������Զ�ˢ��
 * ���пط��������õ���ģʽ���ṩ��һ�ķ��ʵ㣬���ҳ���ȫ��Ψһ��accessToken����
 * ��������ģʽ������AccessToken�ṩȫ��Ψһ������
 * ��ΪAccessToken��ҪΪ�������͵��пط������ṩ����
 * �����Ƕ�ʱ��ˢ�´����ݿ�����ļ�֮��ľͲ���Ҫ�ṩȫ��Ψһ
 * @author ChengNing
 * @date   2015��1��8��
 */
public class AccessTokenMemServer implements IServer{

	
	private static AccessTokenMemServer tokenServer = new AccessTokenMemServer();
	
	private AccessToken accessToken = new AccessToken();
	
	private int requestTimes = 1;//token����ʧ�ܺ���������Ĵ���
	
	/**
	 * ˽�й���
	 */
	private AccessTokenMemServer(){
		//��ȡ�µ�token
		refresh();
	}
	
	/**
	 * token�пط�����ʵ��
	 * @return �пط�����ʵ��
	 */
	public static AccessTokenMemServer instance(){
		return tokenServer;
	}
	
	/**
	 * ͨ���пط������õ�token
	 * @return
	 */
	private AccessToken accessToken(){
		//û�п��õ�token����ȥˢ��
		if(!this.accessToken.isValid()){
			refresh();
		}
		return this.accessToken;
	}
	
	/**
	 * ͨ���пط������õ�accessToken
	 * @return
	 */
	public String token(){
		return accessToken().getToken();
	}
	
	/**
	 * ������ˢ��token
	 */
	private void refresh(){
		for(int i=0;i<requestTimes;i++){
			//����ɹ����˳�
			if(this.accessToken.request())
				break;
		}
	}
}
