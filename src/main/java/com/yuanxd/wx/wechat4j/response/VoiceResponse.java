/**
 * 
 */
package com.yuanxd.wx.wechat4j.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author ChengNing
 * @date   2014Äê12ÔÂ7ÈÕ
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
