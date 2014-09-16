
package com.royalcyber.monitor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.royalcyber.monitor package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CallingApplicationCode_QNAME = new QName("http://www.royalcyber.com/monitor", "CallingApplicationCode");
    private final static QName _ClientRequestDateTime_QNAME = new QName("http://www.royalcyber.com/monitor", "ClientRequestDateTime");
    private final static QName _OutputPayload_QNAME = new QName("http://www.royalcyber.com/monitor", "OutputPayload");
    private final static QName _MonitorDebugPayload_QNAME = new QName("http://www.royalcyber.com/monitor", "MonitorDebugPayload");
    private final static QName _SLStepName_QNAME = new QName("http://www.royalcyber.com/monitor", "SL_StepName");
    private final static QName _MonitorDebugDetail_QNAME = new QName("http://www.royalcyber.com/monitor", "MonitorDebugDetail");
    private final static QName _ClientUserID_QNAME = new QName("http://www.royalcyber.com/monitor", "ClientUserID");
    private final static QName _SLCode_QNAME = new QName("http://www.royalcyber.com/monitor", "SL_Code");
    private final static QName _LobCode_QNAME = new QName("http://www.royalcyber.com/monitor", "LobCode");
    private final static QName _ErrorCode_QNAME = new QName("http://www.royalcyber.com/monitor", "ErrorCode");
    private final static QName _CorrelationID_QNAME = new QName("http://www.royalcyber.com/monitor", "CorrelationID");
    private final static QName _SourceSystemErrorDesc_QNAME = new QName("http://www.royalcyber.com/monitor", "SourceSystemErrorDesc");
    private final static QName _ExecutionSeq_QNAME = new QName("http://www.royalcyber.com/monitor", "ExecutionSeq");
    private final static QName _OutcomeStatus_QNAME = new QName("http://www.royalcyber.com/monitor", "OutcomeStatus");
    private final static QName _ErroredApplicationCode_QNAME = new QName("http://www.royalcyber.com/monitor", "ErroredApplicationCode");
    private final static QName _ParentCorrelationID_QNAME = new QName("http://www.royalcyber.com/monitor", "ParentCorrelationID");
    private final static QName _ErrorType_QNAME = new QName("http://www.royalcyber.com/monitor", "ErrorType");
    private final static QName _InputPayload_QNAME = new QName("http://www.royalcyber.com/monitor", "InputPayload");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.royalcyber.monitor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MonitorDebugPayloadType }
     * 
     */
    public MonitorDebugPayloadType createMonitorDebugPayloadType() {
        return new MonitorDebugPayloadType();
    }

    /**
     * Create an instance of {@link MonitorDebugDetailType }
     * 
     */
    public MonitorDebugDetailType createMonitorDebugDetailType() {
        return new MonitorDebugDetailType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "CallingApplicationCode")
    public JAXBElement<String> createCallingApplicationCode(String value) {
        return new JAXBElement<String>(_CallingApplicationCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "ClientRequestDateTime")
    public JAXBElement<String> createClientRequestDateTime(String value) {
        return new JAXBElement<String>(_ClientRequestDateTime_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "OutputPayload")
    public JAXBElement<String> createOutputPayload(String value) {
        return new JAXBElement<String>(_OutputPayload_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MonitorDebugPayloadType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "MonitorDebugPayload")
    public JAXBElement<MonitorDebugPayloadType> createMonitorDebugPayload(MonitorDebugPayloadType value) {
        return new JAXBElement<MonitorDebugPayloadType>(_MonitorDebugPayload_QNAME, MonitorDebugPayloadType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "SL_StepName")
    public JAXBElement<String> createSLStepName(String value) {
        return new JAXBElement<String>(_SLStepName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MonitorDebugDetailType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "MonitorDebugDetail")
    public JAXBElement<MonitorDebugDetailType> createMonitorDebugDetail(MonitorDebugDetailType value) {
        return new JAXBElement<MonitorDebugDetailType>(_MonitorDebugDetail_QNAME, MonitorDebugDetailType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "ClientUserID")
    public JAXBElement<String> createClientUserID(String value) {
        return new JAXBElement<String>(_ClientUserID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "SL_Code")
    public JAXBElement<String> createSLCode(String value) {
        return new JAXBElement<String>(_SLCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "LobCode")
    public JAXBElement<String> createLobCode(String value) {
        return new JAXBElement<String>(_LobCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "ErrorCode")
    public JAXBElement<String> createErrorCode(String value) {
        return new JAXBElement<String>(_ErrorCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "CorrelationID")
    public JAXBElement<String> createCorrelationID(String value) {
        return new JAXBElement<String>(_CorrelationID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "SourceSystemErrorDesc")
    public JAXBElement<String> createSourceSystemErrorDesc(String value) {
        return new JAXBElement<String>(_SourceSystemErrorDesc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "ExecutionSeq")
    public JAXBElement<String> createExecutionSeq(String value) {
        return new JAXBElement<String>(_ExecutionSeq_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "OutcomeStatus")
    public JAXBElement<String> createOutcomeStatus(String value) {
        return new JAXBElement<String>(_OutcomeStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "ErroredApplicationCode")
    public JAXBElement<String> createErroredApplicationCode(String value) {
        return new JAXBElement<String>(_ErroredApplicationCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "ParentCorrelationID")
    public JAXBElement<String> createParentCorrelationID(String value) {
        return new JAXBElement<String>(_ParentCorrelationID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "ErrorType")
    public JAXBElement<String> createErrorType(String value) {
        return new JAXBElement<String>(_ErrorType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.royalcyber.com/monitor", name = "InputPayload")
    public JAXBElement<String> createInputPayload(String value) {
        return new JAXBElement<String>(_InputPayload_QNAME, String.class, null, value);
    }

}
