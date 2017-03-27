/**
 * 
 */
package com.yuanxd.wx.wechat4j.token.server;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author ChengNing
 * @date   2015��1��29��
 */
public abstract class AbsServer implements IServer{

	private static Logger logger = Logger.getLogger(AbsServer.class);
	
	protected String customerServerClass;
	
	public AbsServer(){
		this.customerServerClass = getCustomerServerClass();
	}
	
	@Override
	public String token(){
		return server().token();
	}
	/**
	 * �õ�ϵͳ���õ��пط�����
	 * @return ����ʹ�õ��пط�����
	 */
	public IServer server(){
		if(isCustomer())
			return customerServer();
		return defaultServer();
	}
	
	/**
	 * �����Զ����пط�����
	 * @return �Զ�����пط�����
	 */
	public IServer customerServer(){
		String className = customerServerClass;
		IServer customerServer = null;
		try {
			Class clazz = Class.forName(className);
			customerServer = (IServer)clazz.newInstance();
		} catch (Exception e) {
			logger.error("ϵͳ�Ҳ���" + className);
			logger.error("�Զ���serverʵ����ʧ�ܣ�" + e.getMessage());
			e.printStackTrace();
		}
		return customerServer;
	}
	
	/**
	 * ��������ļ���������AccessTokenServer����ôʹ�ÿͻ��Զ���server
	 * @return �Ƿ��������Զ����пط�����
	 */
	public boolean isCustomer(){
		if(StringUtils.isBlank(customerServerClass))
			return false;
		return true;
	}
	
	/**
	 * ָ����Ĭ���пط�����
	 * @return Ĭ�ϵ��пط�����
	 */
	public abstract IServer defaultServer() ;

	/**
	 * �Զ������������
	 * @return
	 */
	protected abstract String getCustomerServerClass();
}
