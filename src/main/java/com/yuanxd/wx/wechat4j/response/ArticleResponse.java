/**
 * 
 */
package com.yuanxd.wx.wechat4j.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * ͼ����Ϣ��
 * @author ChengNing
 * @date   2014��12��7��
 */
public class ArticleResponse {

	private String Title;        //ͼ����Ϣ����
	private String Description;  //ͼ����Ϣ����
	private String PicUrl;       //ͼƬ���ӣ�֧��JPG��PNG��ʽ���Ϻõ�Ч��Ϊ��ͼ360*200��Сͼ200*200
	private String Url;          //���ͼ����Ϣ��ת����
	
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
	@XmlElement(name="PicUrl")
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	@XmlElement(name="Url")
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
	
	
}
