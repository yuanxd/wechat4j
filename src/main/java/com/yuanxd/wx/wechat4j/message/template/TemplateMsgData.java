/**
 * 
 */
package com.yuanxd.wx.wechat4j.message.template;

/**
 * @author ChengNing
 * @date   2014��12��24��
 */
public class TemplateMsgData {
	private String name; //json�е���������
	private String value; //keynote value
	private String color; //data keynote color
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
