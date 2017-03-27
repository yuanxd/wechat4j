package com.yuanxd.wx.wechat4j.pay;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yuanxd.wx.wechat4j.common.Config;
import com.yuanxd.wx.wechat4j.lang.JaxbParser;
import com.yuanxd.wx.wechat4j.pay.exception.PayApiException;
import com.yuanxd.wx.wechat4j.pay.exception.PayBusinessException;
import com.yuanxd.wx.wechat4j.pay.exception.SignatureException;
import com.yuanxd.wx.wechat4j.pay.protocol.closeorder.CloseorderRequest;
import com.yuanxd.wx.wechat4j.pay.protocol.closeorder.CloseorderResponse;
import com.yuanxd.wx.wechat4j.pay.protocol.downloadbill.DownloadbillRequest;
import com.yuanxd.wx.wechat4j.pay.protocol.orderquery.OrderqueryRequest;
import com.yuanxd.wx.wechat4j.pay.protocol.orderquery.OrderqueryResponse;
import com.yuanxd.wx.wechat4j.pay.protocol.pay_result_notify.PayResultNotifyResponse;
import com.yuanxd.wx.wechat4j.pay.protocol.refund.RefundRequest;
import com.yuanxd.wx.wechat4j.pay.protocol.refund.RefundResponse;
import com.yuanxd.wx.wechat4j.pay.protocol.refundquery.RefundqueryRequest;
import com.yuanxd.wx.wechat4j.pay.protocol.refundquery.RefundqueryResponse;
import com.yuanxd.wx.wechat4j.pay.protocol.report.ReportRequest;
import com.yuanxd.wx.wechat4j.pay.protocol.report.ReportResponse;
import com.yuanxd.wx.wechat4j.pay.protocol.unifiedorder.UnifiedorderRequest;
import com.yuanxd.wx.wechat4j.pay.protocol.unifiedorder.UnifiedorderResponse;

/**
 * <h2>֧������</h2>
 * <p>�ο�<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_3">�����ĵ�</a></p>
 * <p/>
 * Created by xuwen on 2015-12-10.
 */
public class PayManager {

    private static Logger logger = Logger.getLogger(PayManager.class);

    /**
     * ͳһ�µ�
     */
    private static final String HTTPS_API_MCH_WEIXIN_QQ_COM_PAY_UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * ��ѯ����
     */
    private static final String HTTPS_API_MCH_WEIXIN_QQ_COM_PAY_ORDERQUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
    /**
     * �رն���
     */
    private static final String HTTPS_API_MCH_WEIXIN_QQ_COM_PAY_CLOSEORDER = "https://api.mch.weixin.qq.com/pay/closeorder";
    /**
     * �����˿�
     */
    private static final String HTTPS_API_MCH_WEIXIN_QQ_COM_SECAPI_PAY_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    /**
     * ��ѯ�˿�
     */
    private static final String HTTPS_API_MCH_WEIXIN_QQ_COM_PAY_REFUNDQUERY = "https://api.mch.weixin.qq.com/pay/refundquery";
    /**
     * ���ض��˵�
     */
    private static final String HTTPS_API_MCH_WEIXIN_QQ_COM_PAY_DOWNLOADBILL = "https://api.mch.weixin.qq.com/pay/downloadbill";
    /**
     * �����ϱ�
     */
    private static final String HTTPS_API_MCH_WEIXIN_QQ_COM_PAYITIL_REPORT = "https://api.mch.weixin.qq.com/payitil/report";

    /**
     * ͳһ�µ�
     * <p>�ο�<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1">�����ĵ�</p>
     *
     * @param request
     * @return
     * @throws SignatureException
     * @throws PayApiException
     * @throws PayBusinessException
     */
    public static UnifiedorderResponse unifiedorder(UnifiedorderRequest request) throws SignatureException, PayApiException, PayBusinessException {
        JaxbParser requestParser = buildJAXBParser(UnifiedorderRequest.class);
        JaxbParser responseParser = buildJAXBParser(UnifiedorderResponse.class);
        request.setSign(signature(request));
        String postData = requestParser.toXML(request);
        logger.info("post data \n" + postData);
        String postResult = post(HTTPS_API_MCH_WEIXIN_QQ_COM_PAY_UNIFIEDORDER, postData);
        logger.info("post result \n" + postResult);
        checkAccess(postResult);
        checkBusiness(postResult);
        validResponseSign(postResult);
        UnifiedorderResponse response = (UnifiedorderResponse) responseParser.toObj(postResult);
        return response;
    }

    /**
     * ��ѯ����
     * <p>�ο�<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2">�����ĵ�</p>
     *
     * @param request
     * @return
     * @throws SignatureException
     * @throws PayApiException
     * @throws PayBusinessException
     */
    public static OrderqueryResponse orderquery(OrderqueryRequest request) throws SignatureException, PayApiException, PayBusinessException {
        JaxbParser requestParser = buildJAXBParser(OrderqueryRequest.class);
        JaxbParser responseParser = buildJAXBParser(OrderqueryResponse.class);
        request.setSign(signature(request));
        String postData = requestParser.toXML(request);
        logger.info("post data \n" + postData);
        String postResult = post(HTTPS_API_MCH_WEIXIN_QQ_COM_PAY_ORDERQUERY, postData);
        logger.info("post result \n" + postResult);
        checkAccess(postResult);
        checkBusiness(postResult);
        validResponseSign(postResult);
        OrderqueryResponse response = (OrderqueryResponse) responseParser.toObj(postResult);
        try {
            parseCouponsForOrderquery(postResult, response);
        } catch (Exception e) {
            logger.error("��������ȯ�������Ż�ʧ��", e);
            PayApiException exception = new PayApiException(PayCode.FAIL, "��������ȯ�������Ż�ʧ��");
            throw exception;
        }
        return response;
    }

    /**
     * �رն���
     * <p>�ο�<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2">�����ĵ�</p>
     *
     * @param request
     * @return
     * @throws SignatureException
     * @throws PayApiException
     * @throws PayBusinessException
     */
    public static CloseorderResponse closeorder(CloseorderRequest request) throws SignatureException, PayApiException, PayBusinessException {
        JaxbParser requestParser = buildJAXBParser(CloseorderRequest.class);
        JaxbParser responseParser = buildJAXBParser(CloseorderResponse.class);
        request.setSign(signature(request));
        String postData = requestParser.toXML(request);
        logger.info("post data \n" + postData);
        String postResult = post(HTTPS_API_MCH_WEIXIN_QQ_COM_PAY_CLOSEORDER, postData);
        logger.info("post result \n" + postResult);
        checkAccess(postResult);
        checkBusiness(postResult);
        validResponseSign(postResult);
        CloseorderResponse response = (CloseorderResponse) responseParser.toObj(postResult);
        return response;
    }

    /**
     * �����˿�
     * <p>�ο�<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4">�����ĵ�</p>
     *
     * @param request
     * @return
     * @throws SignatureException
     * @throws PayApiException
     * @throws PayBusinessException
     */
    public static RefundResponse refund(RefundRequest request) throws SignatureException, PayApiException, PayBusinessException {
        JaxbParser requestParser = buildJAXBParser(RefundRequest.class);
        JaxbParser responseParser = buildJAXBParser(RefundResponse.class);
        request.setSign(signature(request));
        String postData = requestParser.toXML(request);
        logger.info("post data \n" + postData);
        String postResult = post(HTTPS_API_MCH_WEIXIN_QQ_COM_SECAPI_PAY_REFUND, postData);
        logger.info("post result \n" + postResult);
        checkAccess(postResult);
        checkBusiness(postResult);
        validResponseSign(postResult);
        RefundResponse response = (RefundResponse) responseParser.toObj(postResult);
        return response;
    }

    /**
     * ��ѯ�˿�
     * <p>�ο�<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4">�����ĵ�</p>
     *
     * @param request
     * @return
     * @throws SignatureException
     * @throws PayApiException
     * @throws PayBusinessException
     */
    public static RefundqueryResponse refundquery(RefundqueryRequest request) throws SignatureException, PayApiException, PayBusinessException {
        JaxbParser requestParser = buildJAXBParser(RefundqueryRequest.class);
        JaxbParser responseParser = buildJAXBParser(RefundqueryResponse.class);
        request.setSign(signature(request));
        String postData = requestParser.toXML(request);
        logger.info("post data \n" + postData);
        String postResult = post(HTTPS_API_MCH_WEIXIN_QQ_COM_PAY_REFUNDQUERY, postData);
        logger.info("post result \n" + postResult);
        checkAccess(postResult);
        checkBusiness(postResult);
        validResponseSign(postResult);
        RefundqueryResponse response = (RefundqueryResponse) responseParser.toObj(postResult);
        try {
            parseCouponsForRefundquery(postResult, response);
        } catch (Exception e) {
            logger.error("��������ȯ�������Ż�ʧ��", e);
            PayApiException exception = new PayApiException(PayCode.FAIL, "��������ȯ�������Ż�ʧ��");
            throw exception;
        }
        return response;
    }

    /**
     * ���ض��˵�
     * <p>�ο�<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">�����ĵ�</p>
     *
     * @param request
     * @return
     * @throws PayApiException
     */
    public static String downloadbill(DownloadbillRequest request) throws PayApiException {
        JaxbParser requestParser = buildJAXBParser(DownloadbillRequest.class);
        JaxbParser responseParser = buildJAXBParser(PayApiException.class);
        request.setSign(signature(request));
        String postData = requestParser.toXML(request);
        logger.info("post data \n" + postData);
        String postResult = post(HTTPS_API_MCH_WEIXIN_QQ_COM_PAY_DOWNLOADBILL, postData);
        logger.info("post result \n" + postResult);
        PayApiException exception = null;
        try {
            Map<String, Object> mapFromXMLString = getMapFromXMLString(postResult);
            exception = new PayApiException(mapFromXMLString.get("return_code").toString(), mapFromXMLString.get("return_msg").toString());
        } catch (Exception e) {
            // �������XML��˵�����˵����سɹ�
        }
        if (exception != null) {
            throw exception;
        } else {
            return postResult;
        }
    }

    /**
     * �����ϱ�
     * <p>�ο�<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_8">�����ĵ�</p>
     *
     * @param request
     * @return
     * @throws SignatureException
     * @throws PayApiException
     * @throws PayBusinessException
     */
    public static ReportResponse report(ReportRequest request) throws SignatureException, PayApiException, PayBusinessException {
        JaxbParser requestParser = buildJAXBParser(ReportRequest.class);
        JaxbParser responseParser = buildJAXBParser(ReportResponse.class);
        request.setSign(signature(request));
        String postData = requestParser.toXML(request);
        logger.info("post data \n" + postData);
        String postResult = post(HTTPS_API_MCH_WEIXIN_QQ_COM_PAYITIL_REPORT, postData);
        logger.info("post result \n" + postResult);
        checkAccess(postResult);
        checkBusiness(postResult);
        // �����ϱ����᷵��ǩ��
//        validResponseSign(postResult);
        ReportResponse response = (ReportResponse) responseParser.toObj(postResult);
        return response;
    }

    /**
     * ��װ֧�����֪ͨ
     * <p/>
     * <b>ע�⣺ͬ����֪ͨ���ܻ��η��͸��̻�ϵͳ���̻�ϵͳ�����ܹ���ȷ�����ظ���֪ͨ�� </b>
     * <p><a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7">�����ĵ�</p>
     *
     * @param servletRequest
     * @return
     * @throws SignatureException
     * @throws PayApiException
     * @throws PayBusinessException
     */
    public static PayResultNotifyResponse parsePayResultNotify(ServletRequest servletRequest, ServletResponse servletResponse) throws SignatureException, PayApiException, PayBusinessException {
        JaxbParser responseParser = buildJAXBParser(PayResultNotifyResponse.class);
        JaxbParser exceptionParser = buildJAXBParser(PayApiException.class);
        PayApiException exception = new PayApiException(PayCode.SUCCESS, "OK");
        String postResult;
        try {
            int len;
            byte[] b = new byte[1024];
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            InputStream servletInputStream = servletRequest.getInputStream();
            while ((len = servletInputStream.read(b)) != -1) {
                stream.write(b, 0, len);
            }
            postResult = stream.toString(Consts.UTF_8.name());
        } catch (IOException e) {
            logger.error("֧�����֪ͨ���ݽ���ʧ��", e);
            exception = new PayApiException(PayCode.FAIL, "֧�����֪ͨ���ݽ���ʧ��");
            responseToWechat(servletResponse, exceptionParser.toXML(exception));
            throw exception;
        }
        logger.info("result data \n" + postResult);
        checkAccess(postResult);
        try {
            validResponseSign(postResult);
        } catch (SignatureException e) {
            exception = new PayApiException(PayCode.FAIL, "ǩ��У��ʧ��");
            responseToWechat(servletResponse, exceptionParser.toXML(exception));
            throw e;
        }
        checkBusiness(postResult);
        PayResultNotifyResponse response = (PayResultNotifyResponse) responseParser.toObj(postResult);
        try {
            parseCouponsForPayResultNotify(postResult, response);
        } catch (Exception e) {
            logger.error("��������ȯ�������Ż�ʧ��", e);
            exception = new PayApiException(PayCode.FAIL, "��������ȯ�������Ż�ʧ��");
            responseToWechat(servletResponse, exceptionParser.toXML(exception));
            throw exception;
        }
        responseToWechat(servletResponse, exceptionParser.toXML(exception));
        return response;
    }

    /**
     * �̻�����֧�����֪ͨ��ͬ�����ظ�΢�Ų���
     *
     * @param servletResponse
     * @param postData
     * @throws PayApiException
     */
    private static void responseToWechat(ServletResponse servletResponse, String postData) throws PayApiException {
        try {
            servletResponse.getOutputStream().write(postData.getBytes(Consts.UTF_8));
            servletResponse.getOutputStream().flush();
            servletResponse.getOutputStream().close();
        } catch (IOException e) {
            throw new PayApiException(PayCode.FAIL, "֧�����֪ͨͬ������ʧ��");
        }
    }

    /**
     * ����֧�����֪ͨ�Ĵ���ȯ�������Ż�
     *
     * @param postResult
     * @param payResultNotifyResponse
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    private static void parseCouponsForPayResultNotify(String postResult, PayResultNotifyResponse payResultNotifyResponse) throws ParserConfigurationException, IOException, SAXException {
        List<String> coupon_id_$n = new ArrayList<String>();
        List<Integer> coupon_fee_$n = new ArrayList<Integer>();
        Map<String, Object> mapFromPayResultNotifyXML = getMapFromXMLString(postResult);
        Iterator<String> iterator = mapFromPayResultNotifyXML.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            // ��������ȯ�������Żݣ�$nΪ�±꣬��0��ʼ���
            if (key.matches("^coupon_id_[0-9]+$")) { // coupon_id_$n
                coupon_id_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^coupon_fee_[0-9]+$")) { // coupon_fee_$n
                coupon_fee_$n.add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            }
        }
        payResultNotifyResponse.setCoupon_id_$n(coupon_id_$n.toArray(new String[coupon_id_$n.size()]));
        payResultNotifyResponse.setCoupon_fee_$n(coupon_fee_$n.toArray(new Integer[coupon_fee_$n.size()]));
    }

    /**
     * ������ѯ�����Ĵ���ȯ�������Ż�
     *
     * @param postResult
     * @param orderqueryResponse
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    private static void parseCouponsForOrderquery(String postResult, OrderqueryResponse orderqueryResponse) throws ParserConfigurationException, IOException, SAXException {
        List<String> coupon_batch_id_$n = new ArrayList<String>();
        List<String> coupon_id_$n = new ArrayList<String>();
        List<Integer> coupon_fee_$n = new ArrayList<Integer>();
        Map<String, Object> mapFromPayResultNotifyXML = getMapFromXMLString(postResult);
        Iterator<String> iterator = mapFromPayResultNotifyXML.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            // ��������ȯ�������Żݣ�$nΪ�±꣬��0��ʼ���
            if (key.matches("^coupon_batch_id_[0-9]+$")) { // coupon_batch_id_$n
                coupon_batch_id_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^coupon_id_[0-9]+$")) { // coupon_id_$n
                coupon_id_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^coupon_fee_[0-9]+$")) { // coupon_fee_$n
                coupon_fee_$n.add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            }
        }
        orderqueryResponse.setCoupon_batch_id_$n(coupon_batch_id_$n.toArray(new String[coupon_id_$n.size()]));
        orderqueryResponse.setCoupon_id_$n(coupon_id_$n.toArray(new String[coupon_id_$n.size()]));
        orderqueryResponse.setCoupon_fee_$n(coupon_fee_$n.toArray(new Integer[coupon_fee_$n.size()]));
    }

    /**
     * ������ѯ�˿�Ĵ���ȯ�������Ż�
     *
     * @param postResult
     * @param refundqueryResponse
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    private static void parseCouponsForRefundquery(String postResult, RefundqueryResponse refundqueryResponse) throws ParserConfigurationException, IOException, SAXException {
        List<String> out_refund_no_$n = new ArrayList<String>();
        List<String> refund_id_$n = new ArrayList<String>();
        List<String> refund_channel_$n = new ArrayList<String>();
        List<Integer> refund_fee_$n = new ArrayList<Integer>();
        List<Integer> coupon_refund_fee_$n = new ArrayList<Integer>();
        List<Integer> coupon_refund_count_$n = new ArrayList<Integer>();
        List<List<String>> coupon_refund_batch_id_$n_$m = new ArrayList<List<String>>();
        List<List<String>> coupon_refund_id_$n_$m = new ArrayList<List<String>>();
        List<List<Integer>> coupon_refund_fee_$n_$m = new ArrayList<List<Integer>>();
        List<String> refund_status_$n = new ArrayList<String>();
        Map<String, Object> mapFromPayResultNotifyXML = getMapFromXMLString(postResult);
        Iterator<String> iterator = mapFromPayResultNotifyXML.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            // ��������ȯ�������Żݣ�$nΪ�±꣬$nΪ�±꣬��0��ʼ���
            if (key.matches("^out_refund_no_[0-9]+$")) { // out_refund_no_$n
                out_refund_no_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^refund_id_[0-9]+$")) { // refund_id_$n
                refund_id_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^refund_channel_[0-9]+$")) { // refund_channel_$n
                refund_channel_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^refund_fee_[0-9]+$")) { // refund_fee_$n
                refund_fee_$n.add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            } else if (key.matches("^coupon_refund_fee_[0-9]+$")) { // coupon_refund_fee_$n
                coupon_refund_fee_$n.add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            } else if (key.matches("^coupon_refund_fee_[0-9]+$")) { // coupon_refund_fee_$n
                coupon_refund_fee_$n.add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            } else if (key.matches("^coupon_refund_count_[0-9]+$")) { // coupon_refund_count_$n
                coupon_refund_count_$n.add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            } else if (key.matches("^coupon_refund_batch_id_[0-9]+_[0-9]+$")) { // coupon_refund_batch_id_$n_$m
                String[] indexs = key.replace("coupon_refund_batch_id_", "").split("_");
                int index0 = Integer.valueOf(indexs[0]);
                if (coupon_refund_batch_id_$n_$m.size() <= index0) {
                    coupon_refund_batch_id_$n_$m.add(new ArrayList<String>());
                }
                coupon_refund_batch_id_$n_$m.get(index0).add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^coupon_refund_id_[0-9]+_[0-9]+$")) { // coupon_refund_id_$n_$m
                String[] indexs = key.replace("coupon_refund_id_", "").split("_");
                int index0 = Integer.valueOf(indexs[0]);
                if (coupon_refund_id_$n_$m.size() <= index0) {
                    coupon_refund_id_$n_$m.add(new ArrayList<String>());
                }
                coupon_refund_id_$n_$m.get(index0).add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^coupon_refund_fee_[0-9]+_[0-9]+$")) { // coupon_refund_fee_$n_$m
                String[] indexs = key.replace("coupon_refund_fee_", "").split("_");
                int index0 = Integer.valueOf(indexs[0]);
                if (coupon_refund_fee_$n_$m.size() <= index0) {
                    coupon_refund_fee_$n_$m.add(new ArrayList<Integer>());
                }
                coupon_refund_fee_$n_$m.get(index0).add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            } else if (key.matches("^refund_status_[0-9]+$")) { // refund_status_$n
                refund_status_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            }
        }
        refundqueryResponse.setOut_refund_no_$n(out_refund_no_$n.toArray(new String[out_refund_no_$n.size()]));
        refundqueryResponse.setRefund_id_$n(refund_id_$n.toArray(new String[refund_id_$n.size()]));
        refundqueryResponse.setRefund_channel_$n(refund_channel_$n.toArray(new String[refund_channel_$n.size()]));
        refundqueryResponse.setRefund_fee_$n(refund_fee_$n.toArray(new Integer[refund_fee_$n.size()]));
        refundqueryResponse.setCoupon_refund_fee_$n(coupon_refund_fee_$n.toArray(new Integer[coupon_refund_fee_$n.size()]));
        refundqueryResponse.setCoupon_refund_count_$n(coupon_refund_count_$n.toArray(new Integer[coupon_refund_count_$n.size()]));
        String[][] coupon_refund_batch_id_$n_$m_array = new String[coupon_refund_batch_id_$n_$m.size()][];
        for (int i = 0; i < coupon_refund_batch_id_$n_$m.size(); i++) {
            List<String> item = coupon_refund_batch_id_$n_$m.get(i);
            coupon_refund_batch_id_$n_$m_array[i] = item.toArray(new String[item.size()]);
        }
        refundqueryResponse.setCoupon_refund_batch_id_$n_$m(coupon_refund_batch_id_$n_$m_array);
        String[][] coupon_refund_id_$n_$m_array = new String[coupon_refund_id_$n_$m.size()][];
        for (int i = 0; i < coupon_refund_id_$n_$m.size(); i++) {
            List<String> item = coupon_refund_id_$n_$m.get(i);
            coupon_refund_id_$n_$m_array[i] = item.toArray(new String[item.size()]);
        }
        refundqueryResponse.setCoupon_refund_id_$n_$m(coupon_refund_id_$n_$m_array);
        Integer[][] coupon_refund_fee_$n_$m_array = new Integer[coupon_refund_fee_$n_$m.size()][];
        for (int i = 0; i < coupon_refund_fee_$n_$m.size(); i++) {
            List<Integer> item = coupon_refund_fee_$n_$m.get(i);
            coupon_refund_fee_$n_$m_array[i] = item.toArray(new Integer[item.size()]);
        }
        refundqueryResponse.setCoupon_refund_fee_$n_$m(coupon_refund_fee_$n_$m_array);
        refundqueryResponse.setRefund_status_$n(refund_status_$n.toArray(new String[refund_status_$n.size()]));
    }

    /**
     * ����H5����֧���Ĳ�������
     *
     * @param timeStamp
     * @param nonceStr
     * @param prepayId
     * @return
     */
    public static H5PayParam buildH5PayConfig(String timeStamp, String nonceStr, String prepayId) {
        H5PayParam config = new H5PayParam();
        config.setTimeStamp(timeStamp);
        config.setNonceStr(nonceStr);
        config.setPackageWithPrepayId("prepay_id=" + prepayId);
        config.setPaySign(signature(config));
        return config;
    }

    /**
     * �����Ӧ����Ƿ���ȷ
     *
     * @param postResult
     */
    private static void checkAccess(String postResult) throws PayApiException {
        PayApiException exception = null;
        try {
            Map<String, Object> map = getMapFromXMLString(postResult);
            if (PayCode.FAIL.equals(map.get("return_code").toString())) {
                exception = new PayApiException(PayCode.FAIL, map.get("return_msg").toString());
            }
        } catch (Exception e) {
            logger.error("�ذ����ݽ���ʧ��", e);
            exception = new PayApiException(PayCode.FAIL, "�ذ����ݽ���ʧ��");
        }
        if (exception != null) {
            throw exception;
        }
    }

    /**
     * ���ҵ�����Ƿ���ȷ
     *
     * @param postResult
     */
    private static void checkBusiness(String postResult) throws PayBusinessException {
        PayBusinessException exception = null;
        try {
            Map<String, Object> map = getMapFromXMLString(postResult);
            if (PayCode.FAIL.equals(map.get("result_code").toString())) {
                exception = new PayBusinessException(PayCode.FAIL, map.get("err_code").toString(), map.get("err_code_des").toString());
            }
        } catch (Exception e) {
            logger.error("�ذ����ݽ���ʧ��", e);
            exception = new PayBusinessException(PayCode.FAIL, "RESPONSE_PARSE_ERROR", "�ذ����ݽ���ʧ��");
        }
        if (exception != null) {
            throw exception;
        }
    }

    /**
     * У����Ӧ���ݵ�ǩ��
     *
     * @param xmlStr
     */
    private static void validResponseSign(String xmlStr) throws SignatureException {
        try {
            Map<String, Object> map = getMapFromXMLString(xmlStr);
            String orignalSign = map.get("sign").toString();
            if (!orignalSign.equals(signature(map))) {
                throw new SignatureException();
            }
            logger.debug("�������ݵ�ǩ��У��ɹ�");
        } catch (Exception e) {
            throw new SignatureException();
        }
    }

    /**
     * ֧��ǩ���㷨
     * <p><a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3">�����ĵ�</p>
     *
     * @param object ��ǩ������
     * @return
     */
    private static String signature(Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = object.getClass().getDeclaredFields();
        // �ֵ���
        Arrays.sort(fields, new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (Field field : fields) {
            // sign������ǩ��
            if ("sign".equals(field.getName())) {
                continue;
            }
            XmlElement xmlElement = field.getAnnotation(XmlElement.class);
            if (xmlElement == null) {
                try {
                    Method getMethod = object.getClass().getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
                    xmlElement = getMethod.getAnnotation(XmlElement.class);
                } catch (NoSuchMethodException e) {
                    logger.warn("get method not found : " + field.getName());
                    // skip this
                }
            }
            field.setAccessible(true);
            try {
                map.put(xmlElement != null ? xmlElement.name() : field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                // never throws...
            }
        }
        return signature(map);
    }

    /**
     * ֧��ǩ���㷨
     * <p><a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3">�����ĵ�</p>
     *
     * @param map ��ǩ������
     * @return
     */
    private static String signature(Map<String, Object> map) {
        map.put("sign", ""); // sign������ǩ��
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null && !"".equals(entry.getValue())) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder signatureTemp = new StringBuilder();
        for (int i = 0; i < size; i++) {
            signatureTemp.append(arrayToSort[i]);
        }
        signatureTemp.append("key=").append(Config.instance().getMchKey());
        String signatureSource = signatureTemp.toString();
        logger.debug("sign source : " + signatureSource);
        String signature = DigestUtils.md5Hex(signatureSource).toUpperCase();
        logger.debug("sign result : " + signature);
        return signature;
    }

    /**
     * XML��ת����Map
     *
     * @param xmlString
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    private static Map<String, Object> getMapFromXMLString(String xmlString) throws ParserConfigurationException, IOException, SAXException {
//        xmlString = xmlString.replaceAll("\n","");
//        xmlString = xmlString.replaceAll("<!\\[CDATA\\[(.*?)\\]\\]","$1");
        //������Dom�ķ�ʽ�����ذ�������ҪĿ���Ƿ�ֹAPI�����ذ��ֶ�
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        ByteArrayInputStream is = null;
        if (xmlString != null && !"".equals(xmlString.trim())) {
            is = new ByteArrayInputStream(xmlString.getBytes());
        }
        Document document = builder.parse(is);
        //��ȡ��document�����ȫ�����
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof Element) {
                map.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }
        return map;

    }

    /**
     * ����XML������JAXBPaser
     *
     * @param clazz
     * @return
     */
    private static JaxbParser buildJAXBParser(Class clazz) {
        JaxbParser parser = new JaxbParser(clazz);
        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        parser.setCdataNode(fieldNames);
        return parser;
    }

    /**
     * post ����
     *
     * @param url
     * @param xml
     * @return
     */
    private static String post(String url, String xml) {
        try {
            HttpEntity entity = Request.Post(url)
                    .bodyString(xml, ContentType.create("text/xml", Consts.UTF_8.name()))
                    .execute().returnResponse().getEntity();
            if (entity != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                entity.writeTo(byteArrayOutputStream);
                return byteArrayOutputStream.toString(Consts.UTF_8.name());
            }
            return null;
        } catch (Exception e) {
            logger.error("post�����쳣��" + e.getMessage() + "\npost url:" + url);
            e.printStackTrace();
        }
        return null;
    }

}
