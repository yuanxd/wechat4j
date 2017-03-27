package com.yuanxd.wx.wechat4j.util;

import com.alibaba.fastjson.JSONObject;
import com.yuanxd.wx.wechat4j.exception.WeChatException;
import com.yuanxd.wx.wechat4j.exception.WeChatReturnCode;
/**
 * ������
 * @author Zhangxs
 * @version 2015-7-4
 */
public class WeChatUtil {
	/**
	 * �ж��Ƿ�����ɹ�
	 * @param resultStr
	 * @throws WeChatException
	 */
	public static void isSuccess(String resultStr) throws WeChatException{		
		JSONObject jsonObject = JSONObject.parseObject(resultStr);
		Integer errCode =jsonObject.getIntValue("errcode");
		if (errCode!=null && errCode!=0) {
			String errMsg = WeChatReturnCode.getMsg(errCode);
			if (errMsg.equals("")) {
				errMsg = jsonObject.getString("errmsg");
			}
			throw new WeChatException("�쳣��:"+errCode+";�쳣˵��:"+errMsg);
		}
	}
}
