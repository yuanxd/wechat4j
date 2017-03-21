package com.yuanxd.wx.wechat4j.csc;

import java.util.HashMap;
import java.util.Map;
/**
 * ����ID(�Ự״̬)˵��
 * @author Zhangxs
 * @date 2015-7-8 
 * @version
 */
public class RecordOperCode {
    private final static Map<Integer, String> operCodeMap = new HashMap<Integer, String>();

	static {
		 operCodeMap.put(1000,"����δ����Ự");
		 operCodeMap.put(1001,"����Ự");
		 operCodeMap.put(1002,"��������Ự");
		 operCodeMap.put(1004,"�رջỰ");
		 operCodeMap.put(1005,"���ӻỰ");
		 operCodeMap.put(2001,"���ں��յ���Ϣ");
		 operCodeMap.put(2002,"�ͷ�������Ϣ");
		 operCodeMap.put(2003,"�ͷ��յ���Ϣ");
	}
	/**
	 * ����opercode���ػỰ״̬
	 * @param opercode
	 * @return
	 */
	public static String getSessionState(int opercode){
		if (operCodeMap.containsKey(opercode)) {
            return operCodeMap.get(opercode);
        }
        return "";
	}
}
