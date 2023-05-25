
package com.logic.jaxws;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "startAudit", namespace = "http://logic.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "startAudit", namespace = "http://logic.com/")
public class StartAudit {

    @XmlElement(name = "arg0", namespace = "")
    private ArrayList arg0;

    /**
     * 
     * @return
     *     returns ArrayList
     */
    public ArrayList getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(ArrayList arg0) {
        this.arg0 = arg0;
    }

}
