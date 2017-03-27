/**
 *
 */
package com.yuanxd.wx.wechat4j.user;


import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.yuanxd.wx.wechat4j.exception.WeChatException;
import com.yuanxd.wx.wechat4j.lang.HttpUtils;
import com.yuanxd.wx.wechat4j.token.TokenProxy;
import com.yuanxd.wx.wechat4j.util.WeChatUtil;

/**
 * �˻�����
 *
 * @author Zhangxs
 * @version 2015-7-5
 */
public class AccountManager {
    Logger logger = Logger.getLogger(AccountManager.class);

    //������ת�����ӽӿ�
    private static final String SHORTURL_POST_URL = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=";
    //���ɴ������Ķ�ά��
    private static final String QRCODE_POST_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

    /**
     * ������ת�����ӽӿ�
     *
     * @param longUrl ��Ҫת���ĳ�����
     * @return
     */
    public String shortUrl(String longUrl) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", "long2short");
        jsonObject.put("long_url", longUrl);
        String requestData = jsonObject.toString();
        logger.info("request data " + requestData);
        String resultStr = HttpUtils.post(SHORTURL_POST_URL + TokenProxy.accessToken(), requestData);
        logger.info("return data " + resultStr);
        try {
            WeChatUtil.isSuccess(resultStr);
        } catch (WeChatException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
        JSONObject resultJson = JSONObject.parseObject(resultStr);
        return resultJson.getString("short_url");
    }

    /**
     * �������ö�ά��
     *
     * @param sceneId ����ֵID,Ŀǰ����ֻ֧��1--100000
     * @return
     */
    public Qrcode createQrcodePerpetual(long sceneId) {
        return createQrcodeTicket(QrcodeType.QR_LIMIT_SCENE, null, sceneId, null);
    }

    /**
     * �������ö�ά��
     *
     * @param sceneStr ����ֵID,��������Ϊ1��64
     * @return
     */
    public Qrcode createQrcodePerpetualstr(String sceneStr) {
        return createQrcodeTicket(QrcodeType.QR_LIMIT_STR_SCENE, null, null, sceneStr);
    }

    /**
     * ������ʱ��ά��
     *
     * @param sceneId       ����ֵID
     * @param expireSeconds ��ά����Чʱ�䣬����Ϊ��λ,��󲻳���604800����7�죩��
     * @return
     */
    public Qrcode createQrcodeTemporary(long sceneId, int expireSeconds) {
        return createQrcodeTicket(QrcodeType.QR_SCENE, expireSeconds, sceneId, null);
    }

    private Qrcode createQrcodeTicket(QrcodeType qrcodeType, Integer expireSeconds, Long sceneId, String sceneStr) {
        JSONObject ticketJson = new JSONObject();
        ticketJson.put("action_name", qrcodeType);
        JSONObject sceneJson = new JSONObject();
        switch (qrcodeType) {
            case QR_SCENE:
                ticketJson.put("expire_seconds", expireSeconds);
                sceneJson.put("scene_id", sceneId);
                break;
            case QR_LIMIT_SCENE:
                sceneJson.put("scene_id", sceneId);
                break;
            case QR_LIMIT_STR_SCENE:
                sceneJson.put("scene_str", sceneStr);
                break;
        }
        JSONObject actionInfoJson = new JSONObject();
        actionInfoJson.put("scene", sceneJson);
        ticketJson.put("action_info", actionInfoJson);
        String requestData = ticketJson.toString();
        logger.info("request data " + requestData);
        String resultStr = HttpUtils.post(QRCODE_POST_URL + TokenProxy.accessToken(), requestData);
        logger.info("return data " + resultStr);
        try {
            WeChatUtil.isSuccess(resultStr);
        } catch (WeChatException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
        Qrcode qrcode = JSONObject.parseObject(resultStr, Qrcode.class);
        return qrcode;
    }

}
