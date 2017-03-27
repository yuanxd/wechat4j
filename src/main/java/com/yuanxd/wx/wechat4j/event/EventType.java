/**
 * 
 */
package com.yuanxd.wx.wechat4j.event;

/**
 * ΢���¼����ͣ�event�ֶε�ö��<br/>
 * <b>scancode_push��scancode_waitmsg��pic_sysphoto��pic_photo_or_album��pic_weixin��location_select</b><br/>
 * ��֧��΢��iPhone5.4.1���ϰ汾,��Android5.4���ϰ汾��΢���û�<br/>
 * <b>media_id,view_limited</b><br/>
 * ���޵�����ƽ̨����δ΢����֤��������ԣ���������֤δͨ�����Ķ��ĺ�׼�����¼�����,û���¼�����
 * @author ChengNing
 * @author Zhangxs
 * @date 2015-7-6
 * 
 */
public enum EventType {
	subscribe,             //��ע
	unsubscribe,           //ȡ����ע
	/** �����˵�ʹ�� */
	click,				   
	CLICK,                 //���
	/** �����˵�ʹ��  */
	view,				   
	VIEW,                  //��ת����
	SCAN,                  //ɨ��
	LOCATION,              //�ϱ�����λ��
	TEMPLATESENDJOBFINISH, //ģ����Ϣ���ͳɹ�֮���¼�
	scancode_push,         //ɨ�����¼�
	scancode_waitmsg,      //ɨ�����¼��ҵ�������Ϣ�����С���ʾ����¼�
	pic_sysphoto,          //����ϵͳ���շ�ͼ���¼�
	pic_photo_or_album,    //�������ջ�����ᷢͼ���¼�
	pic_weixin,            //����΢����ᷢͼ�����¼�
	location_select,       //��������λ��ѡ�������¼�
	media_id,			   //�·���Ϣ(���ı���Ϣ)
	view_limited,		   //��תͼ����ϢURL 
	kf_create_session,	   //����Ự
	kf_close_session,	   //�رջỰ
	kf_switch_session,	   //ת�ӻỰ
}
