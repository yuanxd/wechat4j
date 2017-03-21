/**
 * 
 */
package com.yuanxd.wx.wechat4j.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * ��Ƶ��Ϣ
 * @author ChengNing
 * @date   2014��12��7��
 */
public class VideoResponse {

	private String MediaId;     //ͨ���ϴ���ý���ļ����õ���id
	private String Title;       //��Ƶ��Ϣ�ı���
	private String Description; //��Ƶ��Ϣ������
	private String ThumbMediaId;

	@XmlElement(name="MediaId")
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	@XmlElement(name="Title")
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	@XmlElement(name="Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@XmlElement(name="ThumbMediaId")
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	
	
	
}
