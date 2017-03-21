/**
 * 
 */
package com.yuanxd.wx.wechat4j.token.server;

import com.yuanxd.wx.wechat4j.token.Ticket;
import com.yuanxd.wx.wechat4j.token.TicketType;

/**
 * �ڴ���Ƶ���
 * @author ChengNing
 * @date   2015��1��29��
 */
public class JsApiTicketMemServer implements IServer{

	private static JsApiTicketMemServer ticketServer = new JsApiTicketMemServer();
	
	private Ticket jsApiTicket = new Ticket(TicketType.jsapi);
	
	private int requestTimes = 1;//token����ʧ�ܺ���������Ĵ���
	
	/**
	 * ˽�й���
	 */
	private JsApiTicketMemServer(){
		//��ȡ�µ�token
		refresh();
	}
	
	/**
	 * token�пط�����ʵ��
	 * @return ticket������ʵ��
	 */
	public static JsApiTicketMemServer instance(){
		return ticketServer;
	}
	
	
	/**
	 * ͨ���пط������õ�accessToken
	 * @return
	 */
	public String token(){
		//û�п��õ�token����ȥˢ��
		if(!this.jsApiTicket.isValid()){
			refresh();
		}
		return this.jsApiTicket.getToken();
	}
	
	/**
	 * ������ˢ��token
	 */
	private void refresh(){
		for(int i=0;i<requestTimes;i++){
			//����ɹ����˳�
			if(this.jsApiTicket.request())
				break;
		}
	}

}
