/**
 * 
 */
package com.yuanxd.wx.wechat4j.common;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.yuanxd.wx.wechat4j.lang.HttpUtils;
import com.yuanxd.wx.wechat4j.token.TokenProxy;

/**
 * �ϴ����ض�ý���ļ�
 * @author ChengNing
 * @date   2015��1��6��
 */
public class MediaFile {
	
	private static final String UPLOAD = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=";
	private static final String DOWNLOAD = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="; 
	private static final String PARAM_FILE = "media";
	private static final String PARAM_MEDIA_ID = "media_id";
	private static final String PARAM_TYPE = "type";
	private static final String PARAM_CREATE_TIME = "created_at";
	
	private MediaType type;
	private File file;
	private String mediaId;  //3��ʧЧ
	private String createdTimestamp;//�ļ�����ʱ������ϴ�֮�󷵻�
	
	
	/**
	 * �ļ��ϴ�url
	 * @return
	 */
	private String uploadUrl(){
		String url = UPLOAD + TokenProxy.accessToken() + "&" 
	              + PARAM_TYPE + "=" + this.type.name();
		return url;
	}
	
	/**
	 * �ļ�����url
	 * @return
	 */
	private String downloadUrl(){
		String url = DOWNLOAD + TokenProxy.accessToken() + "&" 
	         + PARAM_MEDIA_ID + "=" + this.mediaId;
		return url;
	}
	
	/**
	 * �ļ��ϴ�
	 * success: {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
	 * error:   {"errcode":40004,"errmsg":"invalid media type"}
	 * @return  media_id  �ɹ����� media_id, ʧ�ܷ���null
	 */
	public String upload(File file,MediaType type){
		this.file = file;
		this.type = type;
		String url = uploadUrl();
		String result = HttpUtils.postFile(url,PARAM_FILE, file);
		parseUploadResult(result);
		if(StringUtils.isNotBlank(this.mediaId))
			return this.mediaId;
		return null;
	}
	
	private void parseUploadResult(String result){
		JSONObject jsonObject = JSONObject.parseObject(result);
		if(jsonObject.containsKey(PARAM_MEDIA_ID)){
			this.mediaId = jsonObject.getString(PARAM_MEDIA_ID);
			this.createdTimestamp = jsonObject.getString(PARAM_CREATE_TIME);
		}
		else{
			this.mediaId = null;
			this.createdTimestamp = null;
		}
	}
	
	/**
	 * �ļ�����
	 * @return  byte[]
	 */
	public byte[] download(String mediaId){
		this.mediaId = mediaId;
		String url = downloadUrl();
		byte[] fb = HttpUtils.getFile(url);
		if(fb == null || fb.length == 0)
			return null;
		return fb;
	}

	public MediaType getType() {
		return type;
	}

	public String getMediaId() {
		return mediaId;
	}

	public String getCreatedTimestamp() {
		return createdTimestamp;
	}
	
	
}
