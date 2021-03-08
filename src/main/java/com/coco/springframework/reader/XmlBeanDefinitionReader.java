package com.coco.springframework.reader;

import com.coco.springframework.entity.BeanDefinition;
import com.coco.springframework.entity.BeanReference;
import com.coco.springframework.entity.PropertyValue;
import com.coco.springframework.entity.PropertyValues;
import com.coco.springframework.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 14:07
 * @Description:
 **/
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinition(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        // 解析xml document并注册bean
        registerBeanDefinitions(document);
        inputStream.close();
    }

    public void registerBeanDefinitions(Document document) {
        Element root = document.getDocumentElement();
        // 从文件根递归解析
        parseBeanDefinitions(root);
    }

    protected void parseBeanDefinitions(Element root) {
        NodeList nodeList = root.getChildNodes();
        for(int i = 0; i < nodeList.getLength(); i ++) {
            Node node = nodeList.item(i);
            if(node instanceof Element) {
                processBeanDefinition((Element) node);
            }
        }
    }

    protected void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        boolean singleton = true;
        if(ele.hasAttribute("scope") && "prototype".equals(ele.getAttribute("scope"))) {
            singleton = false;
        }
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele, beanDefinition);
        beanDefinition.setClassName(className);
        beanDefinition.setSingleton(singleton);
        getRegistry().put(name, beanDefinition);
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for(int i = 0; i < propertyNode.getLength(); i ++) {
            Node node = propertyNode.item(i);
            if(node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if(value != null && value.length() > 0) {
                    // 优先进行值注入
                    PropertyValues propertyValues = beanDefinition.getPropertyValues();
                    PropertyValue propertyValue = new PropertyValue(name, value);
                    propertyValues.addPropertyValue(propertyValue);
                } else {
                    String ref = propertyEle.getAttribute("ref");
                    if(ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '" + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    PropertyValues propertyValues = beanDefinition.getPropertyValues();
                    PropertyValue propertyValue = new PropertyValue(name, beanReference);
                    propertyValues.addPropertyValue(propertyValue);
                }
            }
        }
    }


}