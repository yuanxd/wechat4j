package com.yuanxd.wx.wechat4j.pay;

/**
 * ֧���Ľ��
 * ��Ӧ����2�������
 * return_code
 * result_code
 * <p/>
 * Created by xuwen on 2015-12-13.
 */
public enum PayCode {

    SUCCESS,
    FAIL;

    public boolean equals(String payCode) {
        return this.toString().equals(payCode);
    }

}
