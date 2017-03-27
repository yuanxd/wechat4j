package com.yuanxd.wx.wechat4j.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * ΢��ȫ�ַ�����
 * @author Zhangxs
 * @date 2015-7-8 
 * @version
 */
public class WeChatReturnCode {

    /**
     * ȫ�ַ�����˵��
     */
    private final static Map<Integer, String> returnCodeMap = new HashMap<Integer, String>();

    static {
        returnCodeMap.put(-1, "ϵͳ��æ����ʱ�뿪�����Ժ�����");
        returnCodeMap.put(0, "����ɹ�");
        returnCodeMap.put(40001, "��ȡaccess_tokenʱAppSecret���󣬻���access_token��Ч���뿪��������ȶ�AppSecret����ȷ�ԣ���鿴�Ƿ�����Ϊǡ���Ĺ��ںŵ��ýӿ�");
        returnCodeMap.put(40002, "���Ϸ���ƾ֤����");
        returnCodeMap.put(40003, "���Ϸ���OpenID���뿪����ȷ��OpenID�����û����Ƿ��ѹ�ע���ںţ����Ƿ����������ںŵ�OpenID");
        returnCodeMap.put(40004, "���Ϸ���ý���ļ�����");
        returnCodeMap.put(40005, "���Ϸ����ļ�����");
        returnCodeMap.put(40006, "���Ϸ����ļ���С");
        returnCodeMap.put(40007, "���Ϸ���ý���ļ�id");
        returnCodeMap.put(40008, "���Ϸ�����Ϣ����");
        returnCodeMap.put(40009, "���Ϸ���ͼƬ�ļ���С");
        returnCodeMap.put(40010, "���Ϸ��������ļ���С");
        returnCodeMap.put(40011, "���Ϸ�����Ƶ�ļ���С");
        returnCodeMap.put(40012, "���Ϸ�������ͼ�ļ���С");
        returnCodeMap.put(40013, "���Ϸ���AppID���뿪���߼��AppID����ȷ�ԣ������쳣�ַ���ע���Сд");
        returnCodeMap.put(40014, "���Ϸ���access_token���뿪��������ȶ�access_token����Ч�ԣ����Ƿ���ڣ�����鿴�Ƿ�����Ϊǡ���Ĺ��ںŵ��ýӿ�");
        returnCodeMap.put(40015, "���Ϸ��Ĳ˵�����");
        returnCodeMap.put(40016, "���Ϸ��İ�ť����");
        returnCodeMap.put(40017, "���Ϸ��İ�ť����");
        returnCodeMap.put(40018, "���Ϸ��İ�ť���ֳ���");
        returnCodeMap.put(40019, "���Ϸ��İ�ťKEY����");
        returnCodeMap.put(40020, "���Ϸ��İ�ťURL����");
        returnCodeMap.put(40021, "���Ϸ��Ĳ˵��汾��");
        returnCodeMap.put(40022, "���Ϸ����Ӳ˵�����");
        returnCodeMap.put(40023, "���Ϸ����Ӳ˵���ť����");
        returnCodeMap.put(40024, "���Ϸ����Ӳ˵���ť����");
        returnCodeMap.put(40025, "���Ϸ����Ӳ˵���ť���ֳ���");
        returnCodeMap.put(40026, "���Ϸ����Ӳ˵���ťKEY����");
        returnCodeMap.put(40027, "���Ϸ����Ӳ˵���ťURL����");
        returnCodeMap.put(40028, "���Ϸ����Զ���˵�ʹ���û�");
        returnCodeMap.put(40029, "���Ϸ���oauth_code");
        returnCodeMap.put(40030, "���Ϸ���refresh_token");
        returnCodeMap.put(40031, "���Ϸ���openid�б�");
        returnCodeMap.put(40032, "���Ϸ���openid�б���");
        returnCodeMap.put(40033, "���Ϸ��������ַ������ܰ���\\uxxxx��ʽ���ַ�");
        returnCodeMap.put(40035, "���Ϸ��Ĳ���");
        returnCodeMap.put(40038, "���Ϸ��������ʽ");
        returnCodeMap.put(40039, "���Ϸ���URL����");
        returnCodeMap.put(40050, "���Ϸ��ķ���id");
        returnCodeMap.put(40051, "�������ֲ��Ϸ�");
        returnCodeMap.put(40053, "��Ч�Ĳ�����Ϣ,�����ĵ�");
        returnCodeMap.put(40054, "��Ч��url����");
        returnCodeMap.put(40117, "�������ֲ��Ϸ�");
        returnCodeMap.put(40118, "media_id��С���Ϸ�");
        returnCodeMap.put(40119, "button���ʹ���");
		returnCodeMap.put(40120, "button���ʹ���");
		returnCodeMap.put(40121, "���Ϸ���media_id����");
        returnCodeMap.put(40132, "΢�źŲ��Ϸ�");
        returnCodeMap.put(41001, "ȱ��access_token����");
        returnCodeMap.put(41002, "ȱ��appid����");
        returnCodeMap.put(41003, "ȱ��refresh_token����");
        returnCodeMap.put(41004, "ȱ��secret����");
        returnCodeMap.put(41005, "ȱ�ٶ�ý���ļ�����");
        returnCodeMap.put(41006, "ȱ��media_id����");
        returnCodeMap.put(41007, "ȱ���Ӳ˵�����");
        returnCodeMap.put(41008, "ȱ��oauthcode");
        returnCodeMap.put(41009, "ȱ��openid");
        returnCodeMap.put(42001, "access_token��ʱ������access_token����Ч�ڣ���ο�����֧��-��ȡaccess_token�У���access_token����ϸ����˵��");
        returnCodeMap.put(42002, "refresh_token��ʱ");
        returnCodeMap.put(42003, "oauth_code��ʱ");
        returnCodeMap.put(43001, "��ҪGET����");
        returnCodeMap.put(43002, "��ҪPOST����");
        returnCodeMap.put(43003, "��ҪHTTPS����");
        returnCodeMap.put(43004, "��Ҫ�����߹�ע");
        returnCodeMap.put(43005, "��Ҫ���ѹ�ϵ");
        returnCodeMap.put(44001, "��ý���ļ�Ϊ��");
        returnCodeMap.put(44002, "POST�����ݰ�Ϊ��");
        returnCodeMap.put(44003, "ͼ����Ϣ����Ϊ��");
        returnCodeMap.put(44004, "�ı���Ϣ����Ϊ��");
        returnCodeMap.put(45001, "��ý���ļ���С��������");
        returnCodeMap.put(45002, "��Ϣ���ݳ�������");
        returnCodeMap.put(45003, "�����ֶγ�������");
        returnCodeMap.put(45004, "�����ֶγ�������");
        returnCodeMap.put(45005, "�����ֶγ�������");
        returnCodeMap.put(45006, "ͼƬ�����ֶγ�������");
        returnCodeMap.put(45007, "��������ʱ�䳬������");
        returnCodeMap.put(45008, "ͼ����Ϣ��������");
        returnCodeMap.put(45009, "�ӿڵ��ó�������");
        returnCodeMap.put(45010, "�����˵�������������");
        returnCodeMap.put(45015, "�ظ�ʱ�䳬������");
        returnCodeMap.put(45016, "ϵͳ���飬�������޸�");
        returnCodeMap.put(45017, "�������ֹ���");
        returnCodeMap.put(45018, "����������������");
        returnCodeMap.put(46001, "������ý������");
        returnCodeMap.put(46002, "�����ڵĲ˵��汾");
        returnCodeMap.put(46003, "�����ڵĲ˵�����");
        returnCodeMap.put(46004, "�����ڵ��û�");
        returnCodeMap.put(47001, "����JSON/XML���ݴ���");
        returnCodeMap.put(48001, "api����δ��Ȩ����ȷ�Ϲ��ں��ѻ�øýӿڣ������ڹ���ƽ̨����-����������ҳ�в鿴�ӿ�Ȩ��");
        returnCodeMap.put(50001, "�û�δ��Ȩ��api");
        returnCodeMap.put(50002, "�û����ޣ�������Υ���ӿڱ����");
        returnCodeMap.put(61451, "��������(invalidparameter)");
        returnCodeMap.put(61452, "��Ч�ͷ��˺�(invalidkf_account)");
        returnCodeMap.put(61453, "�ͷ��ʺ��Ѵ���(kf_accountexsited)");
        returnCodeMap.put(61454, "�ͷ��ʺ������ȳ�������(������10��Ӣ���ַ���������@��@��Ĺ��ںŵ�΢�ź�)(invalidkf_acountlength)");
        returnCodeMap.put(61455, "�ͷ��ʺ��������Ƿ��ַ�(������Ӣ��+����)(illegalcharacterinkf_account)");
        returnCodeMap.put(61456, "�ͷ��ʺŸ�����������(10���ͷ��˺�)(kf_accountcountexceeded)");
        returnCodeMap.put(61457, "��Чͷ���ļ�����(invalidfiletype)");
        returnCodeMap.put(61458, "�ͻ����ڱ������ͷ��Ӵ�(customer accepted by xxx@xxxx)");
        returnCodeMap.put(61459, "�ͷ�������(kf offline)");
        returnCodeMap.put(61450, "ϵͳ����(systemerror)");
        returnCodeMap.put(61500, "���ڸ�ʽ����");
        returnCodeMap.put(61501, "���ڷ�Χ����");
        returnCodeMap.put(9001001, "POST���ݲ������Ϸ�");
        returnCodeMap.put(9001002, "Զ�˷��񲻿���");
        returnCodeMap.put(9001003, "Ticket���Ϸ�");
        returnCodeMap.put(9001004, "��ȡҡ�ܱ��û���Ϣʧ��");
        returnCodeMap.put(9001005, "��ȡ�̻���Ϣʧ��");
        returnCodeMap.put(9001006, "��ȡOpenIDʧ��");
        returnCodeMap.put(9001007, "�ϴ��ļ�ȱʧ");
        returnCodeMap.put(9001008, "�ϴ��زĵ��ļ����Ͳ��Ϸ�");
        returnCodeMap.put(9001009, "�ϴ��زĵ��ļ��ߴ粻�Ϸ�");
        returnCodeMap.put(9001010, "�ϴ�ʧ��");
        returnCodeMap.put(9001020, "�ʺŲ��Ϸ�");
        returnCodeMap.put(9001021, "�����豸�����ʵ���50%�����������豸");
        returnCodeMap.put(9001022, "�豸���������Ϸ�������Ϊ����0������");
        returnCodeMap.put(9001023, "�Ѵ�������е��豸ID����");
        returnCodeMap.put(9001024, "һ�β�ѯ�豸ID�������ܳ���50");
        returnCodeMap.put(9001025, "�豸ID���Ϸ�");
        returnCodeMap.put(9001026, "ҳ��ID���Ϸ�");
        returnCodeMap.put(9001027, "ҳ��������Ϸ�");
        returnCodeMap.put(9001028, "һ��ɾ��ҳ��ID�������ܳ���10");
        returnCodeMap.put(9001029, "ҳ����Ӧ�����豸�У����Ƚ��Ӧ�ù�ϵ��ɾ��");
        returnCodeMap.put(9001030, "һ�β�ѯҳ��ID�������ܳ���50");
        returnCodeMap.put(9001031, "ʱ�����䲻�Ϸ�");
        returnCodeMap.put(9001032, "�����豸��ҳ��İ󶨹�ϵ��������");
        returnCodeMap.put(9001033, "�ŵ�ID���Ϸ�");
        returnCodeMap.put(9001034, "�豸��ע��Ϣ����");
        returnCodeMap.put(9001035, "�豸����������Ϸ�");
        returnCodeMap.put(9001036, "��ѯ��ʼֵbegin���Ϸ�");
    }

    /**
     * �����쳣��Ϣ
     * @param returnCode
     * @return
     */
    public static String getMsg(int returnCode) {
        if (returnCodeMap.containsKey(returnCode)) {
            return returnCodeMap.get(returnCode);
        }
        return "";
    }
}
