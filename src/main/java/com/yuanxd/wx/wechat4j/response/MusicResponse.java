/**
 * 
 */
package com.yuanxd.wx.wechat4j.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * ������Ϣ����
 * @author ChengNing
 * @date   2014-12-4
 */
public class MusicResponse {

	private String Title;        //���ֱ���
	private String Description;  //��������
	private String MusicURL;     //��������
	private String HQMusicUrl;   //�������������ӣ�WIFI��������ʹ�ø����Ӳ�������
	private String ThumbMediaId; //����ͼ��ý��id��ͨ���ϴ���ý���ļ����õ���id
	

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
	@XmlElement(name="MusicURL")
	public String getMusicURL() {
		return MusicURL;
	}
	public void setMusicURL(String musicURL) {
		MusicURL = musicURL;
	}
	@XmlElement(name="HQMusicUrl")
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
	@XmlElement(name="ThumbMediaId")
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	
}
