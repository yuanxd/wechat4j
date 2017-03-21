/**
 * 
 */
package com.yuanxd.wx.wechat4j.common;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * ����΢�ŵ�ǰ����֤
 * @author ChengNing
 * @date   2014-12-4
 */
public class ValidateSignature {
	
	private String signature;
	private String timestamp;
	private String nonce;
	private String token;
	
	/**
	 * ǰ����֤����
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param token
	 */
	public ValidateSignature(String signature,String timestamp,String nonce,String token){
		this.signature = signature;
		this.timestamp = timestamp;
		this.nonce = nonce;
		this.token = token;
	}
	
	/**
	 * ��֤
	 * @param token
	 * @return true ��֤ͨ����false ��֤ʧ��
	 */
	public boolean check(){
		String sha1 = encode();
		return sha1.equals(this.signature);
	}
	
	/**
	 * �õ����ܺ������
	 * @return
	 */
	private String encode(){
		String[] sa = {this.token,this.timestamp, this.nonce};
		Arrays.sort(sa);
		String sortStr = sa[0] + sa[1] + sa[2];
		return DigestUtils.sha1Hex(sortStr);
	}
}
