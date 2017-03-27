/**
 * 
 */
package com.yuanxd.wx.wechat4j.token.server;

import com.yuanxd.wx.wechat4j.token.Token;

/**
 * @author ChengNing
 * @date   2015��1��30��
 */
public abstract class CustomerServer implements IServer {
	
	public String token(){
		return find();
	}
	
	/**
	 * ������߸���accesstoken�����ݿ�
	 * �ɿͻ��Լ�ʵ�����ݿ������߸��²���
	 * @param token   �õ���token����ticket����Ҫ����
	 * @return
	 */
	public abstract boolean save(Token token);
	/**
	 * �����ݿ�õ�accessToken
	 * �ɿͻ��Լ�ʵ�����ݿ�Ĳ�ѯ����
	 * @return
	 */
	protected abstract String find();

}
