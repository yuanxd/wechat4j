/**
 * 
 */
package com.yuanxd.wx.wechat4j.lang;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

/**
 * @author ChengNing
 * @date 2014年12月7日
 */
public class JaxbParser {

    private static Logger logger = Logger.getLogger(JaxbParser.class);

    private Class<?> clazz;

    private String[] cdataNode;
    /**
     * 
     * @param clazz
     */
    public JaxbParser(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * 设置需要包含CDATA的节点
     * 
     * @param cdataNode
     */
    public void setCdataNode(String[] cdataNode) {
        this.cdataNode = cdataNode;
    }

    /**
     * 转为xml串
     * 
     * @param obj
     * @return
     */
    public String toXML(Object obj) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_FRAGMENT, true);// 去掉报文头
            StringWriter writer = new StringWriter();
            m.marshal(obj, writer);
            result = writer.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("response text:\n" + result);
        return result;
    }

    /**
     * 转为对象
     * 
     * @param is
     * @return
     */
    public Object toObj(InputStream is) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(clazz);
            Unmarshaller um = context.createUnmarshaller();
            Object obj = um.unmarshal(is);
            return obj;
        }
        catch (Exception e) {
            logger.error("post data parse error");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * XML转为对象
     * 
     * @param xmlStr
     * @return
     */
    public Object toObj(String xmlStr) {
        InputStream is = new ByteArrayInputStream(xmlStr.getBytes());
        return toObj(is);
    }
}
