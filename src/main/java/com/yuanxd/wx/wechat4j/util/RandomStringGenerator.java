package com.yuanxd.wx.wechat4j.util;

import java.util.Random;

/**
 * ����ַ���������
 */
public class RandomStringGenerator {

    private static final int defaultLength = 32;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * ��ȡһ�����ȵ�����ַ���
     *
     * @param length ָ���ַ�������
     * @return һ�����ȵ��ַ���
     */
    public static String generate(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(number));
        }
        return sb.toString();
    }

    /**
     * ��ȡĬ�ϳ��ȵ�����ַ���
     *
     * @return Ĭ�ϳ��ȵ��ַ���
     */
    public static String generate() {
        return generate(defaultLength);
    }

}
