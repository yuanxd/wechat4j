/**
 * 
 */
package com.yuanxd.wx.wechat4j.lang;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * @author ChengNing
 * @date 2014��12��7��
 */
public class JaxbParser {

    private static Logger logger = Logger.getLogger(JaxbParser.class);

    private Class clazz;
    private String[] cdataNode;

    /**
     * 
     * @param clazz
     */
    public JaxbParser(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * ������Ҫ����CDATA�Ľڵ�
     * 
     * @param cdataNode
     */
    public void setCdataNode(String[] cdataNode) {
        this.cdataNode = cdataNode;
    }

    /**
     * תΪxml��
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
            m.setProperty(Marshaller.JAXB_FRAGMENT, true);// ȥ������ͷ
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
     * תΪ����
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
     * XMLתΪ����
     * 
     * @param xmlStr
     * @return
     */
    public Object toObj(String xmlStr) {
        InputStream is = new ByteArrayInputStream(xmlStr.getBytes());
        return toObj(is);
    }

    /**
     * ��������
     * 
     * @param os
     * @return
     */
    private XMLSerializer getXMLSerializer(OutputStream os) {
        OutputFormat of = new OutputFormat();
        formatCDataTag();
        of.setCDataElements(cdataNode);
        of.setPreserveSpace(true);
        of.setIndenting(true);
        of.setOmitXMLDeclaration(true);
        XMLSerializer serializer = new XMLSerializer(of);
        serializer.setOutputByteStream(os);
        return serializer;
    }

    /**
     * ����cdata tag
     */
    private void formatCDataTag() {
        for (int i = 0; i < cdataNode.length; i++) {
            cdataNode[i] = "^" + cdataNode[i];
        }
    }
}
