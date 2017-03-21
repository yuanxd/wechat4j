/**
 * 
 */
package com.yuanxd.wx.wechat4j.lang;



import java.io.File;
import java.io.InputStream;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


/**
 * 
 * @author chengn
 * @date 2014��12��12��
 */
public class HttpUtils {
	private static Logger logger = Logger.getLogger(HttpUtils.class);

	public static final int timeout = 10;

	/**
	 * post ����
	 * 
	 * @param url
	 * @return
	 */
	public static String post(String url) {
		return post(url, "");
	}
	
	/**
	 * post����
	 * @param url
	 * @param data
	 * @return
	 */
	public static String post(String url, String data){
		return httpPost(url, data);
	}
	
	/**
	 * ����http post����
	 * @param url       url
	 * @param instream  post���ݵ��ֽ���
	 * @return
	 */
	public static String post(String url, InputStream instream){
		try {
			HttpEntity entity = Request.Post(url)
					.bodyStream(instream,ContentType.create("text/html", Consts.UTF_8))
					.execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("post�����쳣��" + e.getMessage() + "\n post url:" + url);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * get����
	 * @param url
	 * @return
	 */
	public static String get(String url){
		return httpGet(url);
	}

	/**
	 * post ����
	 * 
	 * @param url
	 * @param data
	 * @return
	 */
	private static String httpPost(String url, String data) {
		try {
			HttpEntity entity = Request.Post(url)
					.bodyString(data,ContentType.create("text/html", Consts.UTF_8))
					.execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("post�����쳣��" + e.getMessage() + "\n post url:" + url);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �ϴ��ļ�
	 * @param url    URL
	 * @param file   ��Ҫ�ϴ����ļ�
	 * @return
	 */
	public static String postFile(String url,File file){
		return postFile(url, null, file);
	}
	
	/**
	 * �ϴ��ļ�
	 * @param url    URL
	 * @param name   �ļ���post��������
	 * @param file   �ϴ����ļ�
	 * @return
	 */
	public static String postFile(String url,String name,File file){
		try {
			HttpEntity reqEntity = MultipartEntityBuilder.create().addBinaryBody(name, file).build();
			Request request = Request.Post(url);
			request.body(reqEntity);
			HttpEntity resEntity = request.execute().returnResponse().getEntity();
			return resEntity != null ? EntityUtils.toString(resEntity) : null;
		} catch (Exception e) {
			logger.error("postFile�����쳣��" + e.getMessage() + "\n post url:" + url);
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * �����ļ�
	 * @param url   URL
	 * @return      �ļ��Ķ����������ͻ���ʹ��outputStream���Ϊ�ļ�
	 */
	public static byte[] getFile(String url){
		try {
			Request request = Request.Get(url);
			HttpEntity resEntity = request.execute().returnResponse().getEntity();
			return EntityUtils.toByteArray(resEntity);
		} catch (Exception e) {
			logger.error("postFile�����쳣��" + e.getMessage() + "\n post url:" + url);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����get����
	 * 
	 * @param url
	 * @return
	 */
	private static String httpGet(String url) {
		try {
			HttpEntity entity = Request.Get(url).
					execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("get�����쳣��" + e.getMessage() + "\n get url:" + url);
			e.printStackTrace();
		}
		return null;
	}
}
