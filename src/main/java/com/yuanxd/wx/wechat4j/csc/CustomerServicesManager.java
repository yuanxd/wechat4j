
package com.yuanxd.wx.wechat4j.csc;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuanxd.wx.wechat4j.exception.WeChatException;
import com.yuanxd.wx.wechat4j.lang.HttpUtils;
import com.yuanxd.wx.wechat4j.token.TokenProxy;
import com.yuanxd.wx.wechat4j.util.WeChatUtil;
/**
 * �ͷ�����
 * @author Zhangxs
 * @date 2015-7-7
 * @version
 */
public class CustomerServicesManager {
	private static Logger logger = Logger.getLogger(CustomerServicesManager.class);
	
	/* �����Ự*/
	private static final String CUSTOMSERVICE_KFSESSION_CREATE_POST_URL = "https://api.weixin.qq.com/customservice/kfsession/create?access_token=";
	/* �رջỰ*/
	private static final String CUSTOMSERVICE_KFSESSION_CLOSE_POST_URL = "https://api.weixin.qq.com/customservice/kfsession/close?access_token=";
	/* ��ȡ�ͻ��ĻỰ״̬*/
	private static final String CUSTOMSERVICE_KFSESSION_GETSESSION_GET_URL = "https://api.weixin.qq.com/customservice/kfsession/getsession?access_token=";
	/* ��ȡ�ͷ��ĻỰ�б�*/
	private static final String CUSTOMSERVICE_KFSESSION_GETSESSIONLIST_GET_URL = "https://api.weixin.qq.com/customservice/kfsession/getsessionlist?access_token=";
	/* ��ȡδ����Ự�б�*/
	private static final String CUSTOMSERVICE_KFSESSION_GETWAITCASE_GET_URL = "https://api.weixin.qq.com/customservice/kfsession/getwaitcase?access_token=";
	/* ��ȡ�ͷ�������Ϣ*/
	private static final String CUSTOMSERVICE_GETKFLIST_GET_URL = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=";
	/* ��ȡ���߿ͷ��Ӵ���Ϣ*/
	private static final String CUSTOMSERVICE_GETONLIEKFLIST_GET_URL = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=";
	/* ��ӿͷ��˺�*/
	private static final String CUSTOMSERVICE_KFACCOUNT_ADD_POST_URL = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=";
	/* ���ÿͷ���Ϣ*/
	private static final String CUSTOMSERVICE_KFACCOUNT_UPDATE_POST_URL = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=";
	/* �ϴ��ͷ�ͷ��*/
	private static final String CUSTOMSERVICE_KFACCOUNT_UPLOADHEADIMG_POST_URL = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=";
	/* ɾ���ͷ��˺�*/
	private static final String CUSTOMSERVICE_KFACCOUNT_DEL_POST_URL = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=";
	// ��ȡ�ͷ������¼�ӿ�
	private static final String CUSTOMSERVICE_MSGRECORD_GETRECORD_POST_URL="https://api.weixin.qq.com/customservice/msgrecord/getrecord?access_token=";
	private static final String PARAM_FILE = "media";
	private String accessToken;
	public CustomerServicesManager() {
		this.accessToken = TokenProxy.accessToken();
	}
	/**
	 * �����Ự
	 * @param openid �ͻ�openid
	 * @param kf_account �����ͷ��˺ţ���ʽΪ���˺�ǰ׺@���ں�΢�ź�
	 * @return
	 * @throws WeChatException 
	 */
	public void kfSessionCreate(String openId,String kfAccount) throws WeChatException{
		kfSessionCreate(openId, kfAccount, null);
	}
	/**
	 * �����Ự
	 * @param openid �ͻ�openid
	 * @param kf_account �����ͷ��˺ţ���ʽΪ���˺�ǰ׺@���ں�΢�ź�
	 * @param text ������Ϣ���ı���չʾ�ڿͷ���Ա�Ķ�ͷ��ͻ���
	 * @return
	 * @throws WeChatException 
	 */
	public void kfSessionCreate(String openId,String kfAccount,String text) throws WeChatException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("openid", openId);
		jsonObject.put("kf_account", kfAccount);
		if (text!=null){
			jsonObject.put("text", text);			
		}
		String resultStr = HttpUtils.post(CUSTOMSERVICE_KFSESSION_CREATE_POST_URL+this.accessToken,jsonObject.toJSONString());
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * �رջỰ
	 * @param openid �ͻ�openid
	 * @param kf_account �����ͷ��˺ţ���ʽΪ���˺�ǰ׺@���ں�΢�ź�
	 * @return
	 * @throws WeChatException 
	 */
	public void kfSessionClose(String openId,String kfAccount) throws WeChatException{
		kfSessionClose(openId, kfAccount, null);
	}
	/**
	 * �رջỰ
	 * @param openid �ͻ�openid
	 * @param kf_account �����ͷ��˺ţ���ʽΪ���˺�ǰ׺@���ں�΢�ź�
	 * @param text �����ͷ��˺ţ���ʽΪ���˺�ǰ׺@���ں�΢�ź�
	 * @return
	 * @throws WeChatException 
	 */
	public void kfSessionClose(String openId,String kfAccount,String text) throws WeChatException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("openid", openId);
		jsonObject.put("kf_account", kfAccount);
		if (text!=null){
			jsonObject.put("text", text);			
		}
		String resultStr = HttpUtils.post(CUSTOMSERVICE_KFSESSION_CLOSE_POST_URL+this.accessToken,jsonObject.toJSONString());
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * ��ȡ�ͻ��ĻỰ״̬ 
	 * @param openId
	 * @return
	 */
	public CustomerServicesSession getSession(String openId) {
		String resultStr = HttpUtils.get(CUSTOMSERVICE_KFSESSION_GETSESSION_GET_URL+this.accessToken+"&openid="+openId);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		CustomerServicesSession customerServicesSession = JSON.parseObject(resultStr, CustomerServicesSession.class);
		return customerServicesSession;
	}
	/**
	 * ��ȡ�ͷ��ĻỰ�б�
	 * @param KfAccount
	 * @return
	 */
	public List<CustomerServicesSession> getSessionList(String kfAccount) {
		String resultStr = HttpUtils.get(CUSTOMSERVICE_KFSESSION_GETSESSIONLIST_GET_URL+this.accessToken+"&kf_account="+kfAccount);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		String sessionlist = JSON.parseObject(resultStr).getString("sessionlist");
		List<CustomerServicesSession> customerServicesSessions = JSON.parseArray(sessionlist, CustomerServicesSession.class);
		return customerServicesSessions;
	}
	/**
	 * ��ȡδ����Ự�б�
	 * @return
	 */
	public List<CustomerServicesSession> getWaitCaseList() {
		String resultStr = HttpUtils.get(CUSTOMSERVICE_KFSESSION_GETWAITCASE_GET_URL+this.accessToken);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		String waitcaselist = JSON.parseObject(resultStr).getString("waitcaselist");
		List<CustomerServicesSession> customerServicesSessions = JSON.parseArray(waitcaselist, CustomerServicesSession.class);
		return customerServicesSessions;
	}
	/**
	 * ��ȡ���пͷ��˺�	
	 * @return
	 */
	public List<CustomerServices> getKfList() {
		String resultStr = HttpUtils.get(CUSTOMSERVICE_GETKFLIST_GET_URL+this.accessToken);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		String kf_list = JSONObject.parseObject(resultStr).getString("kf_list");
		List<CustomerServices> list = JSON.parseArray(kf_list,  CustomerServices.class);
		return list;
	}
	 
	/**
	 * ��ȡ���߿ͷ��Ӵ���Ϣ
	 * @return
	 */
	 
	public List<CustomerServices> getOnlieKfList() {
		String resultStr = HttpUtils.get(CUSTOMSERVICE_GETONLIEKFLIST_GET_URL+this.accessToken);		
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		JSONObject jsonObject = JSONObject.parseObject(resultStr);
		List<CustomerServices> list = JSON.parseArray(jsonObject.getString("kf_online_list"),  CustomerServices.class);
		return list;
	}
	
	/**
	 * ��ӿͷ��˺� 
	 * @param kfAccount �����ͷ��˺ţ���ʽΪ���˺�ǰ׺@���ں�΢�źţ��˺�ǰ׺���10���ַ���������Ӣ�Ļ��������ַ������û�й��ں�΢�źţ���ǰ��΢�Ź���ƽ̨���á�
	 * @param nickName �ͷ��ǳƣ��6�����ֻ�12��Ӣ���ַ�
	 * @param password �ͷ��˺ŵ�¼���룬��ʽΪ�������ĵ�32λ����MD5ֵ
	 * @return
	 * @throws WeChatException 
	 */
	public void kfAddAccount(String kfAccount,String nickName,String password) throws WeChatException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("kf_account", kfAccount);
		jsonObject.put("nickname", nickName);
		jsonObject.put("password", password);
		String resultStr = HttpUtils.post(CUSTOMSERVICE_KFACCOUNT_ADD_POST_URL+this.accessToken,jsonObject.toJSONString());
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * ���ÿͷ���Ϣ
	 * @param kf_account �����ͷ��˺ţ���ʽΪ���˺�ǰ׺@���ں�΢�ź�
	 * @param nickname	�ͷ��ǳƣ��6�����ֻ�12��Ӣ���ַ�
	 * @param password	�ͷ��˺ŵ�¼���룬��ʽΪ�������ĵ�32λ����MD5ֵ
	 * @return
	 * @throws WeChatException 
	 */
	public void kfUpdateAccount(String kfAccount,String nickName,String password) throws WeChatException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("kf_account", kfAccount);
		jsonObject.put("nickname", nickName);
		jsonObject.put("password", password);
		String resultStr =HttpUtils.post(CUSTOMSERVICE_KFACCOUNT_UPDATE_POST_URL+this.accessToken, jsonObject.toJSONString());
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * �ϴ��ͷ�ͷ��
	 * ͷ��ͼƬ�ļ�������jpg��ʽ���Ƽ�ʹ��640*640��С��ͼƬ�Դﵽ���Ч��
	 * @param kfAccount �����ͷ��˺ţ���ʽΪ���˺�ǰ׺@���ں�΢�ź�
	 * @param file	�ͷ�ͷ��
	 * @return
	 * @throws WeChatException 
	 */
	public void kfUploadHeadImg(String kfAccount,File file) throws WeChatException{
		String resultStr = HttpUtils.postFile(CUSTOMSERVICE_KFACCOUNT_UPLOADHEADIMG_POST_URL+this.accessToken+"&kf_account="+kfAccount,PARAM_FILE, file);
		WeChatUtil.isSuccess(resultStr);
	}

	/**
	 * ɾ���ͷ��˺�
	 * @param kfAccount	�����ͷ��˺ţ���ʽΪ���˺�ǰ׺@���ں�΢�ź�
	 * @return
	 * @throws WeChatException 
	 */
	public void kfDelAccount(String kfAccount) throws WeChatException{
		String resultStr = HttpUtils.post(CUSTOMSERVICE_KFACCOUNT_DEL_POST_URL+this.accessToken+"&kf_account="+kfAccount);
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * ��ȡ�ͷ������¼
	 * @param starttime ��ѯ��ʼʱ�䣬UNIXʱ���
	 * @param endtime ��ѯ����ʱ�䣬UNIXʱ�����ÿ�β�ѯ���ܿ��ղ�ѯ
	 * @param pageindex ��ѯ�ڼ�ҳ����1��ʼ
	 * @param pagesize ÿҳ��С��ÿҳ�����ȡ50��
	 * @return
	 */
	public List<Record> getRecord(long starttime,long endtime, int pageindex,int pagesize) {
		JSONObject data = new JSONObject();
		data.put("endtime", endtime);
		data.put("pageindex", pageindex);
		data.put("pagesize", pagesize);
		data.put("starttime", starttime);
		String resultStr = HttpUtils.post(CUSTOMSERVICE_MSGRECORD_GETRECORD_POST_URL+this.accessToken, data.toJSONString());
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		String recordlist = JSON.parseObject(resultStr).getString("recordlist");
		List<Record> records = JSON.parseArray(recordlist, Record.class);
		return records;
	}
}
