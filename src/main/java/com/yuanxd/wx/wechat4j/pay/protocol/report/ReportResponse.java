package com.yuanxd.wx.wechat4j.pay.protocol.report;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * �����ϱ��������
 * <p>�ο�<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_8">�����ĵ�</p>
 * <p/>
 * Created by xuwen on 2015-12-13.
 */
@XmlRootElement(name = "xml")
public class ReportResponse {

    private String result_code;

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    @Override
    public String toString() {
        return "ReportResponse{" +
                "result_code='" + result_code + '\'' +
                '}';
    }
}
