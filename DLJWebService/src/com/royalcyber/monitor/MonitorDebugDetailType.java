
package com.royalcyber.monitor;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MonitorDebugDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MonitorDebugDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}ExecutionSeq"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}SL_StepName"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}InputPayload"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}OutputPayload"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}ErrorType"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}ErroredApplicationCode"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}SourceSystemErrorDesc"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MonitorDebugDetailType", propOrder = {
    "executionSeq",
    "slStepName",
    "inputPayload",
    "outputPayload",
    "errorType",
    "erroredApplicationCode",
    "sourceSystemErrorDesc"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
public class MonitorDebugDetailType {

    @XmlElement(name = "ExecutionSeq", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String executionSeq;
    @XmlElement(name = "SL_StepName", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String slStepName;
    @XmlElement(name = "InputPayload", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String inputPayload;
    @XmlElement(name = "OutputPayload", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String outputPayload;
    @XmlElement(name = "ErrorType", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String errorType;
    @XmlElement(name = "ErroredApplicationCode", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String erroredApplicationCode;
    @XmlElement(name = "SourceSystemErrorDesc", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String sourceSystemErrorDesc;

    /**
     * Gets the value of the executionSeq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getExecutionSeq() {
        return executionSeq;
    }

    /**
     * Sets the value of the executionSeq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setExecutionSeq(String value) {
        this.executionSeq = value;
    }

    /**
     * Gets the value of the slStepName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getSLStepName() {
        return slStepName;
    }

    /**
     * Sets the value of the slStepName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setSLStepName(String value) {
        this.slStepName = value;
    }

    /**
     * Gets the value of the inputPayload property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getInputPayload() {
        return inputPayload;
    }

    /**
     * Sets the value of the inputPayload property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setInputPayload(String value) {
        this.inputPayload = value;
    }

    /**
     * Gets the value of the outputPayload property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getOutputPayload() {
        return outputPayload;
    }

    /**
     * Sets the value of the outputPayload property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setOutputPayload(String value) {
        this.outputPayload = value;
    }

    /**
     * Gets the value of the errorType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getErrorType() {
        return errorType;
    }

    /**
     * Sets the value of the errorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setErrorType(String value) {
        this.errorType = value;
    }

    /**
     * Gets the value of the erroredApplicationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getErroredApplicationCode() {
        return erroredApplicationCode;
    }

    /**
     * Sets the value of the erroredApplicationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setErroredApplicationCode(String value) {
        this.erroredApplicationCode = value;
    }

    /**
     * Gets the value of the sourceSystemErrorDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getSourceSystemErrorDesc() {
        return sourceSystemErrorDesc;
    }

    /**
     * Sets the value of the sourceSystemErrorDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setSourceSystemErrorDesc(String value) {
        this.sourceSystemErrorDesc = value;
    }

}
