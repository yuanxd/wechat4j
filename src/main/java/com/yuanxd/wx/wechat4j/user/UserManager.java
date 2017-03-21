package com.yuanxd.wx.wechat4j.user;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuanxd.wx.wechat4j.exception.WeChatException;
import com.yuanxd.wx.wechat4j.lang.HttpUtils;
import com.yuanxd.wx.wechat4j.token.TokenProxy;
import com.yuanxd.wx.wechat4j.util.WeChatUtil;
/**
 * �û�����
 * @author Zhangxs
 * @version 2015-7-5
 */
public class UserManager {

	Logger logger = Logger.getLogger(UserManager.class);
	//��ȡ�û��б�
	private static final String USRE_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=";
	//�����û���ע��
	private static final String USER_UPDATE_REMARK_POST_URL="https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=";
	//��ȡ�û�������Ϣ
	private static final String USER_INFO_GET_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
	//��������
	private static final String GROUP_CREATE_POST_URL="https://api.weixin.qq.com/cgi-bin/groups/create?access_token=";
	//��ѯ���з���
	private static final String GROUP_GET_POST_URL="https://api.weixin.qq.com/cgi-bin/groups/get?access_token=";
	//��ѯ�û����ڷ���
	private static final String GROUP_GETID_POST_URL="https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=";
	//�޸ķ�����
	private static final String GROUP_UPDATE_POST_URL="https://api.weixin.qq.com/cgi-bin/groups/update?access_token=";
	//�ƶ��û�����
	private static final String GROUP_MEMBERS_UPDATE_POST_URL="https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=";
	//�����ƶ��û�����
	private static final String GROUP_MEMBERS_DATCHUPDATE_POST_URL="https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=";
	//ɾ������
	private static final String GROUP_DELETE_POST_URL="https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=";
	
	/**
	 * ��ȡ���еĹ�ע���б�
	 * @return
	 */
	public List<String> allSubscriber(){
		Follwers follwers = subscriberList();
		String nextOpenId = follwers.getNextOpenid();
		while (StringUtils.isNotBlank(nextOpenId)) {
			Follwers f = subscriberList(nextOpenId);
			nextOpenId = f.getNextOpenid();
			if (f.getData()!=null) {
				follwers.getData().getOpenid().addAll(f.getData().getOpenid());				
			}
		}
		return follwers.getData().getOpenid();
	}
	/**
	 * ��ȡ�ʺŵĹ�ע���б�ǰ10000��
	 * @return
	 */
	public Follwers subscriberList(){
		return subscriberList(null);
	}
	/**
	 * ��ȡ�ʺŵĹ�ע���б�
	 * @param nextOpenId
	 * @return
	 */
	public Follwers subscriberList(String nextOpenId){
		String url = USRE_GET_URL + TokenProxy.accessToken();
		if(StringUtils.isNotBlank(nextOpenId)){
			url += "&next_openid=" + nextOpenId;
		}
		String resultStr = HttpUtils.get(url);
		logger.info("return data "+resultStr);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return JSONObject.parseObject(resultStr, Follwers.class);
	}
	/**
	 * �����û���ע��
	 * @param openid �û�openid
	 * @param remark �µı�ע�������ȱ���С��30�ַ�
	 * @return
	 * @throws WeChatException 
	 */
	public void updateRemark(String openId,String remark) throws WeChatException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("openid", openId);
		jsonObject.put("remark", remark);
		String requestData = jsonObject.toString();
		logger.info("request data "+requestData);
		String resultStr = HttpUtils.post(USER_UPDATE_REMARK_POST_URL+TokenProxy.accessToken(),requestData);
		logger.info("return data "+resultStr);
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * ��ȡ�û�������Ϣ
	 * @param openid ��ͨ�û��ı�ʶ���Ե�ǰ���ں�Ψһ
	 * @return
	 */
	public User getUserInfo(String openId){
		return getUserInfo(openId, null);
	}
	/**
	 * ��ȡ�û�������Ϣ
	 * @param openid ��ͨ�û��ı�ʶ���Ե�ǰ���ں�Ψһ
	 * @param lang ���ع��ҵ������԰汾��zh_CN ���壬zh_TW ���壬en Ӣ��
	 * @return
	 */
	public User getUserInfo(String openId,LanguageType lang){
		String url = USER_INFO_GET_URL+TokenProxy.accessToken()+"&openid="+openId;
		if (lang!=null) {
			url+="&lang="+lang.name();
		}
		String resultStr = HttpUtils.get(url);
		logger.info("return data "+resultStr);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		User user = JSONObject.parseObject(resultStr, User.class);
		return user;
	}
	
	/**
	 * ��������
	 * @param name  �������֣�30���ַ����ڣ�
	 * @return 
	 * @throws WeChatException 
	 */
	public Group createGroup(String name) throws WeChatException{
		JSONObject nameJson =new JSONObject();
		JSONObject groupJson =new JSONObject();
		nameJson.put("name", name);
		groupJson.put("group", nameJson);
		String requestData=groupJson.toString();
		logger.info("request data "+requestData);
		String resultStr = HttpUtils.post(GROUP_CREATE_POST_URL+TokenProxy.accessToken(), requestData);
		logger.info("return data "+resultStr);
		WeChatUtil.isSuccess(resultStr);
		return JSONObject.parseObject(resultStr).getObject("group", Group.class);
	}
	/**
	 * ��ѯ���з���
	 * @return
	 */
	public List<Group> getGroup(){
		String resultStr = HttpUtils.post(GROUP_GET_POST_URL+TokenProxy.accessToken());
		logger.info("return data "+resultStr);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		JSONObject jsonObject = JSONObject.parseObject(resultStr);
		List<Group> groups = JSON.parseArray(jsonObject.getString("groups"), Group.class);
		return groups;
	}
	/**
	 *  ��ѯ�û����ڷ���
	 * @param openId �û���OpenID
	 * @return �û�������groupid
	 */
	public Integer getIdGroup(String openId){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("openid", openId);

		String requestData = jsonObject.toString();
		logger.info("request data "+requestData);
		String resultStr = HttpUtils.post(GROUP_GETID_POST_URL+TokenProxy.accessToken(), requestData);
		logger.info("return data "+resultStr);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		JSONObject resultJson = JSONObject.parseObject(resultStr);
		int groupId = resultJson.getIntValue("groupid");
		return groupId;
	}
	/**
	 * �޸ķ�����
	 * @param groupId ����id
	 * @param name ��������
	 * @throws WeChatException 
	 */
	public void updateGroup(int groupId,String name) throws WeChatException{
		JSONObject nameJson =new JSONObject();
		JSONObject groupJson =new JSONObject();
		nameJson.put("id", groupId);
		nameJson.put("name", name);
		groupJson.put("group", nameJson);
		String requestData = groupJson.toString();
		logger.info("request data "+requestData);
		String resultStr = HttpUtils.post(GROUP_UPDATE_POST_URL+TokenProxy.accessToken(),requestData);
		logger.info("return data "+resultStr);
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * �ƶ��û�����
	 * @param openid �û���OpenID
	 * @param groupId ����id
	 * @throws WeChatException 
	 */
	public void membersUpdateGroup(String openId,int groupId) throws WeChatException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("openid", openId);
		jsonObject.put("to_groupid", groupId);
		String requestData = jsonObject.toString();
		logger.info("request data "+requestData);
		String resultStr = HttpUtils.post(GROUP_MEMBERS_UPDATE_POST_URL+TokenProxy.accessToken(),requestData);
		logger.info("return data "+resultStr);
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 *  �����ƶ��û�����
	 * @param openids �û�Ψһ��ʶ��openid���б�size���ܳ���50��
	 * @param toGroupid ����id
	 * @return �Ƿ��޸ĳɹ�
	 * @throws WeChatException 
	 */
	public void membersDatchUpdateGroup(String [] openIds,int groupId) throws WeChatException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("openid_list", openIds);
		jsonObject.put("to_groupid", groupId);
		String requestData = jsonObject.toString();
		logger.info("request data "+requestData);
		String resultStr = HttpUtils.post(GROUP_MEMBERS_DATCHUPDATE_POST_URL+TokenProxy.accessToken(),requestData);
		logger.info("return data "+resultStr);
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * ɾ������
	 * @param groupId
	 * @throws WeChatException 
	 */
	public void deleteGroup(int groupId) throws WeChatException{
		JSONObject idJson = new JSONObject();
		idJson.put("id", groupId);
		JSONObject groupJson = new JSONObject();
		groupJson.put("group", idJson);
		String requestData = groupJson.toJSONString();
		logger.info("request data "+requestData);
		String resultStr = HttpUtils.post(GROUP_DELETE_POST_URL+TokenProxy.accessToken(),requestData);
		logger.info("return data "+resultStr);
		WeChatUtil.isSuccess(resultStr);
	}
}
