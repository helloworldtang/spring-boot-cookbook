package com.tangcheng.learning.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // 必须要标明这个元素
@XmlAccessorType(XmlAccessType.FIELD)
public class Boy {
    String name = "CY";
}  