/**
 * 
 */
package com.yuanxd.wx.wechat4j.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author ChengNing
 * @date   2014��12��7��
 */
public class VoiceResponse {

	private String MediaId;

	@XmlElement(name="MediaId")
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	
}
