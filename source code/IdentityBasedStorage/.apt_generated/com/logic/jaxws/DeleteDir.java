
package com.logic.jaxws;

import java.io.File;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "deleteDir", namespace = "http://logic.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteDir", namespace = "http://logic.com/")
public class DeleteDir {

    @XmlElement(name = "arg0", namespace = "")
    private File arg0;

    /**
     * 
     * @return
     *     returns File
     */
    public File getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(File arg0) {
        this.arg0 = arg0;
    }

}
