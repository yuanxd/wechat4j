/**
 * 
 */
package com.yuanxd.wx.wechat4j.token;

import org.apache.log4j.Logger;


/**
 * ΢��ticket������
 * ticket��token���߼�����Ѷ�ǲ��ģ����Լ̳г�����token
 * @author ChengNing
 * @date   2015��1��29��
 */
public class Ticket extends Token {

	private static Logger logger = Logger.getLogger(Ticket.class);
	
	private static final String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";
	private static final String TICKET_NAME = "ticket";
	private static final String EXPIRESIN_NAME = "expires_in";
	
	private String type;
	
	public Ticket(TicketType ticketType){
		super();
		this.type = ticketType.name();
	}

	/* (non-Javadoc)
	 * @see org.sword.wechat4j.token.Token#accessTokenUrl()
	 */
	@Override
	protected String accessTokenUrl() {
		String access_token = TokenProxy.accessToken();
		String url = TICKET_URL + "access_token=" + access_token + "&type=" + this.type;
		logger.info("��ȡticket,ticket����" + this.type);
		return url;
	}

	/* (non-Javadoc)
	 * @see org.sword.wechat4j.token.Token#tokenName()
	 */
	@Override
	protected String tokenName() {
		return TICKET_NAME;
	}

	/* (non-Javadoc)
	 * @see org.sword.wechat4j.token.Token#expiresInName()
	 */
	@Override
	protected String expiresInName() {
		return EXPIRESIN_NAME;
	}


}
