package com.yuanxd.wx.wechat4j.oauth.protocol.valid_access_token;

/**
 * ��Ӧ��������Ȩƾ֤��access_token���Ƿ���Ч
 * Created by xuwen on 2015-12-11.
 */
public class ValidAccessTokenResponse {

    private String errcode;
    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public boolean ok(){
        return this.errmsg != null && "ok".equals(this.errmsg);
    }

    @Override
    public String toString() {
        return "ValidAccessTokenResponse{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
