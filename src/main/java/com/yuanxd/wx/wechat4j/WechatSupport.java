/**
 * 
 */
package com.yuanxd.wx.wechat4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.yuanxd.wx.wechat4j.common.Config;
import com.yuanxd.wx.wechat4j.common.ValidateSignature;
import com.yuanxd.wx.wechat4j.event.EventType;
import com.yuanxd.wx.wechat4j.event.MsgType;
import com.yuanxd.wx.wechat4j.lang.JaxbParser;
import com.yuanxd.wx.wechat4j.lang.StreamUtils;
import com.yuanxd.wx.wechat4j.param.SignatureParam;
import com.yuanxd.wx.wechat4j.request.WechatRequest;
import com.yuanxd.wx.wechat4j.response.ArticleResponse;
import com.yuanxd.wx.wechat4j.response.ImageResponse;
import com.yuanxd.wx.wechat4j.response.MusicResponse;
import com.yuanxd.wx.wechat4j.response.TransInfoResponse;
import com.yuanxd.wx.wechat4j.response.VideoResponse;
import com.yuanxd.wx.wechat4j.response.VoiceResponse;
import com.yuanxd.wx.wechat4j.response.WechatResponse;

/**
 * wechat֧�����
 * ���󷽷��У�on��ͷ����msgtype���¼�����on����event�¼�
 * 
 * @author ChengNing
 * @date 2014-12-4
 */
public abstract class WechatSupport {

    Logger logger = Logger.getLogger(WechatSupport.class);

    private HttpServletRequest request;

    protected WechatRequest wechatRequest;
    protected WechatResponse wechatResponse;

    /**
     * ����΢�Ŵ���
     * 
     * @param request
     *            ΢�ŷ��񷢹�����http����
     */
    public WechatSupport(HttpServletRequest request) {
        this.request = request;
        this.wechatRequest = new WechatRequest();
        this.wechatResponse = new WechatResponse();
    }

    /**
     * wechat������ڣ��������ݽ��գ��¼��ַ�
     * 
     * @return
     */
    public String execute() {
        logger.debug("WechatSupport run");
        SignatureParam param = new SignatureParam(request);
        String signature = param.getSignature();
        String timestamp = param.getTimestamp();
        String nonce = param.getNonce();
        String echostr = param.getEchostr();
        String token = Config.instance().getToken();

        ValidateSignature validateSignature = new ValidateSignature(signature, timestamp, nonce, token);
        if (!validateSignature.check()) { return "error"; }
        if (StringUtils.isNotBlank(echostr)) { return echostr; }
        //�ַ���Ϣ���õ���Ӧ
        String result = dispatch();
        logger.info("response data:" + result);
        return result;
    }

    /**
     * �ַ������õ���Ӧ
     * 
     * @return
     */
    private String dispatch() {
        String postDataStr = null;
        try {
            postDataStr = StreamUtils.streamToString(request.getInputStream());
        }
        catch (IOException e) {
            logger.error("post data deal failed!");
            e.printStackTrace();
        }
        // ��������
        setPostData(postDataStr);
        // ��Ϣ�ַ�����
        dispatchMessage();
        // ��Ӧ�¼�
        String result = response();
        return result;
    }

    /**
     * �õ�post���ݣ�����
     * 
     * @param xmlStr
     */
    private void setPostData(String xmlStr) {
        logger.info("parse post data:\n" + xmlStr);
        try {
            JaxbParser jaxbParser = new JaxbParser(WechatRequest.class);
            this.wechatRequest = (WechatRequest) jaxbParser.toObj(xmlStr);
        }
        catch (Exception e) {
            logger.error("post data parse error");
            e.printStackTrace();
        }
    }

    /**
     * ��Ϣ�¼��ַ�
     */
    private void dispatchMessage() {
        logger.info("distributeMessage start");
        if (StringUtils.isBlank(wechatRequest.getMsgType())) {
            logger.info("msgType is null");
        }
        MsgType msgType = MsgType.valueOf(wechatRequest.getMsgType());
        logger.info("msgType is " + msgType.name());
        switch (msgType) {
        case event:
            dispatchEvent();
            break;
        case text:
            onText();
            break;
        case image:
            onImage();
            break;
        case voice:
            onVoice();
            break;
        case video:
            onVideo();
            break;
        case shortvideo:
            onShortVideo();
            break;
        case location:
            onLocation();
            break;
        case link:
            onLink();
            break;
        default:
            onUnknown();
            break;
        }
    }

    /**
     * event�¼��ַ�
     */
    private void dispatchEvent() {
        EventType event = EventType.valueOf(wechatRequest.getEvent());
        logger.info("dispatch event,event is " + event.name());
        switch (event) {
        case CLICK:
            click();
            break;
        case subscribe:
            subscribe();
            break;
        case unsubscribe:
            unSubscribe();
            break;
        case SCAN:
            scan();
            break;
        case LOCATION:
            location();
            break;
        case VIEW:
            view();
            break;
        case TEMPLATESENDJOBFINISH:
            templateMsgCallback();
            break;
        case scancode_push:
            scanCodePush();
            break;
        case scancode_waitmsg:
            scanCodeWaitMsg();
            break;
        case pic_sysphoto:
            picSysPhoto();
            break;
        case pic_photo_or_album:
            picPhotoOrAlbum();
            break;
        case pic_weixin:
            picWeixin();
            break;
        case location_select:
            locationSelect();
            break;
        case kf_create_session:
            kfCreateSession();
            break;
        case kf_close_session:
            kfCloseSession();
            break;
        case kf_switch_session:
            kfSwitchSession();
            break;
        default:
            break;
        }
    }

    /**
     * ������Ӧ����
     * 
     * @return
     */
    private String response() {
        String result = null;
        try {
            JaxbParser jaxbParser = new JaxbParser(WechatResponse.class);
            //����
            jaxbParser.setCdataNode(WechatResponse.CDATA_TAG);
            result = jaxbParser.toXML(wechatResponse);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ��Ӧ���ݻ�������
     */
    private void responseBase() {
        wechatResponse.setToUserName(this.wechatRequest.getFromUserName());
        wechatResponse.setFromUserName(wechatRequest.getToUserName());
        wechatResponse.setCreateTime(wechatRequest.getCreateTime());
    }

    /**
     * �ظ��ı���Ϣ
     * 
     * @param content
     *            �ظ�����Ϣ���ݣ����У���content���ܹ����У�΢�ſͻ��˾�֧�ֻ�����ʾ��
     */
    public void responseText(String content) {
        responseBase();
        wechatResponse.setMsgType(MsgType.text.name());
        wechatResponse.setContent(content);
    }

    /**
     * �ظ�ͼƬ��Ϣ
     * 
     * @param mediaId
     *            ͨ���ϴ���ý���ļ����õ���id
     */
    public void responseImage(String mediaId) {
        responseBase();
        wechatResponse.setMsgType(MsgType.image.name());
        ImageResponse image = new ImageResponse();
        image.setMediaId(mediaId);
        wechatResponse.setImage(image);
    }

    /**
     * �ظ�������Ϣ
     * 
     * @param mediaId
     *            ͨ���ϴ���ý���ļ����õ���id
     */
    public void responseVoice(String mediaId) {
        responseBase();
        wechatResponse.setMsgType(MsgType.voice.name());
        VoiceResponse voice = new VoiceResponse();
        voice.setMediaId(mediaId);
        wechatResponse.setVoice(voice);
    }

    /**
     * �ظ���Ƶ��Ϣ
     * 
     * @param mediaId
     *            ͨ���ϴ���ý���ļ����õ���id
     * @param title
     *            ��Ƶ��Ϣ�ı���
     * @param description
     *            ��Ƶ��Ϣ������
     */
    public void responseVideo(String mediaId, String title, String description) {
        VideoResponse video = new VideoResponse();
        video.setMediaId(mediaId);
        video.setTitle(title);
        video.setDescription(description);
        responseVideo(video);
    }

    /**
     * �ظ���Ƶ��Ϣ
     * 
     * @param video
     *            ��Ƶ��Ϣ
     */
    public void responseVideo(VideoResponse video) {
        responseBase();
        wechatResponse.setMsgType(MsgType.video.name());
        wechatResponse.setVideo(video);
    }

    /**
     * �ظ�������Ϣ
     * 
     * @param title
     *            ���ֱ���
     * @param description
     *            ��������
     * @param musicURL
     *            ��������
     * @param hQMusicUrl
     *            �������������ӣ�WIFI��������ʹ�ø����Ӳ�������
     * @param thumbMediaId
     *            ����ͼ��ý��id��ͨ���ϴ���ý���ļ����õ���id
     */
    public void responseMusic(String title, String description, String musicURL, String hQMusicUrl,
            String thumbMediaId) {
        MusicResponse music = new MusicResponse();
        music.setTitle(title);
        music.setDescription(description);
        music.setMusicURL(musicURL);
        music.setHQMusicUrl(hQMusicUrl);
        music.setThumbMediaId(thumbMediaId);
        responseMusic(music);
    }

    /**
     * �ظ�������Ϣ
     * 
     * @param music
     *            ������Ϣ
     */
    public void responseMusic(MusicResponse music) {
        responseBase();
        wechatResponse.setMsgType(MsgType.music.name());
        wechatResponse.setMusic(music);
    }

    /**
     * �ظ�ͼ����Ϣ������ͼ����Ϣ
     * 
     * @param title
     *            ͼ����Ϣ����
     * @param description
     *            ͼ����Ϣ����
     * @param picUrl
     *            ͼƬ���ӣ�֧��JPG��PNG��ʽ���Ϻõ�Ч��Ϊ��ͼ360*200��Сͼ200*200
     * @param url
     *            ���ͼ����Ϣ��ת����
     */
    public void responseNew(String title, String description, String picUrl, String url) {
        ArticleResponse item = new ArticleResponse();
        item.setTitle(title);
        item.setDescription(description);
        item.setPicUrl(picUrl);
        item.setUrl(url);
        responseNews(item);
    }

    /**
     * �ظ�ͼ����Ϣ����
     * 
     * @param item
     */
    public void responseNews(ArticleResponse item) {
        List<ArticleResponse> items = new ArrayList<ArticleResponse>();
        items.add(item);
        responseNews(items);
    }

    /**
     * �ظ�ͼ����Ϣ
     * 
     * @param items
     */
    public void responseNews(List<ArticleResponse> items) {
        responseBase();
        wechatResponse.setMsgType(MsgType.news.name());
        wechatResponse.setArticleCount(String.valueOf(items.size()));
        wechatResponse.setArticle(items);

    }

    /**
     * ��Ϣת������ͷ�
     */
    public void responseCustomerService() {
        responseBase();
        wechatResponse.setMsgType(MsgType.transfer_customer_service.name());
    }

    /**
     * ��Ϣת����ָ���ͷ�
     * 
     * @param kfAccount
     *            �ͷ��˺�
     */
    public void responseCustomerService(String kfAccount) {
        responseBase();
        wechatResponse.setMsgType(MsgType.transfer_customer_service.name());
        wechatResponse.setTransInfo(new TransInfoResponse(kfAccount));

    }

    /**
     * ��Ϣת����ָ���ͷ�
     * 
     * @param kfAccount
     *            �ͷ�
     */
    public void responseCustomerService(TransInfoResponse transInfo) {
        responseBase();
        wechatResponse.setMsgType(MsgType.transfer_customer_service.name());
        wechatResponse.setTransInfo(transInfo);

    }

    /**
     * �ı���Ϣ����Msgtype=text
     */
    protected abstract void onText();

    /**
     * ͼ����ϢMsgtype=image
     */
    protected abstract void onImage();

    /**
     * ������Ϣ Msgtype=voice
     */
    protected abstract void onVoice();

    /**
     * ��Ƶ ��ϢMsgtype=video
     */
    protected abstract void onVideo();

    /**
     * С��Ƶ ��ϢMsgtype=shortvideo
     */
    protected abstract void onShortVideo();

    /**
     * ����λ����ϢMsgtype=location
     */
    protected abstract void onLocation();

    /**
     * ������ϢMsgtype=link
     */
    protected abstract void onLink();

    /**
     * δ֪��Ϣ���͵Ĵ������߼�������Ҫ������շ�������
     */
    protected abstract void onUnknown();

    /**
     * click����¼�����event=location
     */
    protected abstract void click();

    /**
     * subscribe��ע�¼�����
     */
    protected abstract void subscribe();

    /**
     * unSubscribeȡ����ע�¼�����
     */
    protected abstract void unSubscribe();

    /**
     * scan�¼�����
     */
    protected abstract void scan();

    /**
     * location�¼�����event=location
     */
    protected abstract void location();

    /**
     * view �¼�����event=location
     */
    protected abstract void view();

    /**
     * ģ����Ϣ���ͻص�
     */
    protected abstract void templateMsgCallback();

    /**
     * ɨ�����¼�
     */
    protected abstract void scanCodePush();

    /**
     * ɨ�����¼��ҵ�������Ϣ�����С���ʾ����¼�
     */
    protected abstract void scanCodeWaitMsg();

    /**
     * ����ϵͳ���շ�ͼ���¼�
     */
    protected abstract void picSysPhoto();

    /**
     * �������ջ�����ᷢͼ���¼�
     */
    protected abstract void picPhotoOrAlbum();

    /**
     * ɨ�����¼��ҵ�������Ϣ�����С���ʾ����¼�
     */
    protected abstract void picWeixin();

    /**
     * ��������λ��ѡ�������¼�
     */
    protected abstract void locationSelect();

    /**
     * �ͷ���Ա�н���Ự
     */
    protected abstract void kfCreateSession();

    /**
     * �ͷ���Ա�йرջỰ
     */
    protected abstract void kfCloseSession();

    /**
     * �ͷ���Ա��ת�ӻỰ
     */
    protected abstract void kfSwitchSession();
}
