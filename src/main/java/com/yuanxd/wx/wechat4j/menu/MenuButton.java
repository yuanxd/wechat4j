package com.yuanxd.wx.wechat4j.menu;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.yuanxd.wx.wechat4j.event.EventType;
/**
 * �˵���ť
 * @author Zhangxs
 * @version 2015-7-4
 */
public class MenuButton {
	private EventType type;//�˵�����Ӧ��������
	private String name;//�˵����⣬������16���ֽڣ��Ӳ˵�������40���ֽ�
	private String key;//click�ȵ�����ͱ���	�˵�KEYֵ��������Ϣ�ӿ����ͣ�������128�ֽ�
	private String url;//view���ͱ���	��ҳ���ӣ��û�����˵��ɴ����ӣ�������256�ֽ�
	private String mediaId;//media_id���ͺ�view_limited���ͱ���	�������������زĽӿڷ��صĺϷ�media_id
	private List<MenuButton> subButton;//�Ӳ˵�,ÿ��һ���˵�������5�������˵�
	
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@JSONField(name="media_id")
	public String getMediaId() {
		return mediaId;
	}
	@JSONField(name="media_id")
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	@JSONField(name="sub_button")
	public List<MenuButton> getSubButton() {
		return subButton;
	}
	@JSONField(name="sub_button")
	public void setSubButton(List<MenuButton> subButton) {
		this.subButton = subButton;
	}
	
}
