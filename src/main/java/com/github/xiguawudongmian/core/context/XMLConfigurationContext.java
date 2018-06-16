package com.github.xiguawudongmian.core.context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class XMLConfigurationContext implements BoxContext {

    private Map<Class,Class> mappers = new HashMap<>();
    private Map<String,Object> config = new HashMap<>();

    /**
     * 解析映射文件的容器
     */
    public XMLConfigurationContext(String applicationPath){
        parse(applicationPath);
    }


    //解析映射文件
    private void parse(String applicationPath) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(XMLConfigurationContext.class.getClassLoader().getResourceAsStream(applicationPath));
            Node boxContext = document.getDocumentElement();
            NodeList childNodes = boxContext.getChildNodes();
            for(int i=0;i<childNodes.getLength();i++){
                Node item = childNodes.item(i);
                String nodeName = item.getNodeName();
                switch (nodeName){
                    case "config":
                        parseConfig(item);
                        break;
                    case "mappers":
                        parseMappers(item);
                        break;
                    default:
                         break;
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析mappers
     * @param mappersNode
     */
    private void parseMappers(Node mappersNode) throws ClassNotFoundException {
        NodeList childNodes = mappersNode.getChildNodes();
        for (int i=0;i<childNodes.getLength();i++){
            Node item = childNodes.item(i);
            switch (item.getNodeName()){
                case "mapper":
                    String keyClassName = item.getAttributes().getNamedItem("name").getNodeValue();
                    System.out.println("注册到容器里面的key为:"+keyClassName);
                    String valueClassName = item.getTextContent();
                    System.out.println("注册到容器里面的value为:"+valueClassName);
                    mappers.put(Class.forName(keyClassName),Class.forName(valueClassName));
                    break;
            }
        }
    }

    private void parseConfig(Node item) {
    }
    @Override
    public <T> Class<? extends T> get(Class<T> boxType, Object... args) {
        Class targetClass = mappers.get(boxType);
            return targetClass;
    }

    @Override
    public void put(Class<? extends Collection> boxType, Class<?> implType) {

    }
}
