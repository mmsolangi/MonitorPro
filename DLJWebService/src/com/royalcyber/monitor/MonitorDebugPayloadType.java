
package com.royalcyber.monitor;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MonitorDebugPayloadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MonitorDebugPayloadType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}SL_Code"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}CallingApplicationCode"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}ParentCorrelationID"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}CorrelationID"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}ClientUserID"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}ClientRequestDateTime"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}OutcomeStatus"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}LobCode"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}ErrorCode"/>
 *         &lt;element ref="{http://www.royalcyber.com/monitor}MonitorDebugDetail" maxOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MonitorDebugPayloadType", propOrder = {
    "slCode",
    "callingApplicationCode",
    "parentCorrelationID",
    "correlationID",
    "clientUserID",
    "clientRequestDateTime",
    "outcomeStatus",
    "lobCode",
    "errorCode",
    "monitorDebugDetail"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
public class MonitorDebugPayloadType {

    @XmlElement(name = "SL_Code", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String slCode;
    @XmlElement(name = "CallingApplicationCode", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String callingApplicationCode;
    @XmlElement(name = "ParentCorrelationID", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String parentCorrelationID;
    @XmlElement(name = "CorrelationID", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String correlationID;
    @XmlElement(name = "ClientUserID", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String clientUserID;
    @XmlElement(name = "ClientRequestDateTime", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String clientRequestDateTime;
    @XmlElement(name = "OutcomeStatus", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String outcomeStatus;
    @XmlElement(name = "LobCode", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String lobCode;
    @XmlElement(name = "ErrorCode", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected String errorCode;
    @XmlElement(name = "MonitorDebugDetail", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    protected List<MonitorDebugDetailType> monitorDebugDetail;

    /**
     * Gets the value of the slCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getSLCode() {
        return slCode;
    }

    /**
     * Sets the value of the slCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setSLCode(String value) {
        this.slCode = value;
    }

    /**
     * Gets the value of the callingApplicationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getCallingApplicationCode() {
        return callingApplicationCode;
    }

    /**
     * Sets the value of the callingApplicationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setCallingApplicationCode(String value) {
        this.callingApplicationCode = value;
    }

    /**
     * Gets the value of the parentCorrelationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getParentCorrelationID() {
        return parentCorrelationID;
    }

    /**
     * Sets the value of the parentCorrelationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setParentCorrelationID(String value) {
        this.parentCorrelationID = value;
    }

    /**
     * Gets the value of the correlationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getCorrelationID() {
        return correlationID;
    }

    /**
     * Sets the value of the correlationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setCorrelationID(String value) {
        this.correlationID = value;
    }

    /**
     * Gets the value of the clientUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getClientUserID() {
        return clientUserID;
    }

    /**
     * Sets the value of the clientUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setClientUserID(String value) {
        this.clientUserID = value;
    }

    /**
     * Gets the value of the clientRequestDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getClientRequestDateTime() {
        return clientRequestDateTime;
    }

    /**
     * Sets the value of the clientRequestDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setClientRequestDateTime(String value) {
        this.clientRequestDateTime = value;
    }

    /**
     * Gets the value of the outcomeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getOutcomeStatus() {
        return outcomeStatus;
    }

    /**
     * Sets the value of the outcomeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setOutcomeStatus(String value) {
        this.outcomeStatus = value;
    }

    /**
     * Gets the value of the lobCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getLobCode() {
        return lobCode;
    }

    /**
     * Sets the value of the lobCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setLobCode(String value) {
        this.lobCode = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the monitorDebugDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the monitorDebugDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMonitorDebugDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MonitorDebugDetailType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2014-09-09T10:57:42+10:00", comments = "JAXB RI v2.2.10-b140310.1920")
    public List<MonitorDebugDetailType> getMonitorDebugDetail() {
        if (monitorDebugDetail == null) {
            monitorDebugDetail = new ArrayList<MonitorDebugDetailType>();
        }
        return this.monitorDebugDetail;
    }

}
