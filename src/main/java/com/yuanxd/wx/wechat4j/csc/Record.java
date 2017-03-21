package com.yuanxd.wx.wechat4j.csc;

/**
 * �����¼
 * @author Zhangxs
 * @date 2015-7-7
 * @version
 */
public class Record {

	private String openid;//�û��ı�ʶ
	private int opercode;//����ID(�Ự״̬)
    private String text;//�����¼
    private int time;//����ʱ�䣬UNIXʱ���
    private String worker;//�ͷ��˺�
    
    public Record() {
    	super();
    }
	public Record(String openid, int opercode,
			String text, int time, String worker) {
		super();
		this.openid = openid;
		this.opercode = opercode;
		this.text = text;
		this.time = time;
		this.worker = worker;
	}
	
	/**
	 * �û��ı�ʶ
	 * @return
	 */
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * ����ID(�Ự״̬)
	 * @see RecordOperCode#getSessionState(int)
	 */
	public int getOpercode() {
		return opercode;
	}
	public void setOpercode(int opercode) {
		this.opercode = opercode;
	}
	/**
	 * �����¼
	 * @return
	 */
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * ����ʱ��,UNIXʱ���
	 * @return
	 */
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	/**
	 * �ͷ��˺�
	 * @return
	 */
	public String getWorker() {
		return worker;
	}
	public void setWorker(String worker) {
		this.worker = worker;
	}
    
}
