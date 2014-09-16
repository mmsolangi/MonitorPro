// LOB JSON

var LOB = {
    "Header":
            {
                OutcomeHdrID: "ID",
                SLCode: "Solution Logic Code",
                ApplicationID: "Application Name",
                CorrelID: "CorrelID",
                ClientUserID: "User",
                ClientReqDateTime: "Request Time",
                OutcomeStatus: "Status",
                Timetaken: "Duration",
                ErrorCode: "Error Code"
            },
    "Data":
            [
                {
                    OutcomeHdrID: "1110222",
                    SLCode: "GetQuoteFLOW",
                    ApplicationID: "NA",
                    CorrelID: "177aaa-2-110333",
                    ClientUserID: "NA",
                    ClientReqDateTime: "05-JUN-2013 12.10.10",
                    OutcomeStatus: "SUCCESS",
                    Timetaken: "",
                    ErrorCode: ""
                },
                {
                    OutcomeHdrID: "1110111",
                    SLCode: "GetQuoteFLOW",
                    ApplicationID: "NA",
                    CorrelID: "177aaa-2-110333",
                    ClientUserID: "NA",
                    ClientReqDateTime: "03-JUL-2013 12.10.10",
                    OutcomeStatus: "SYS ERROR",
                    Timetaken: "",
                    ErrorCode: "SYS-002R"
                },
                {
                    OutcomeHdrID: "1110112",
                    SLCode: "GetQuoteFLOW",
                    ApplicationID: "NA",
                    CorrelID: "177aaa-2-110333",
                    ClientUserID: "NA",
                    ClientReqDateTime: "03-JUL-2013 12.10.10",
                    OutcomeStatus: "TECH ERROR",
                    Timetaken: "",
                    ErrorCode: "TECH-003R"
                }
            ]
};


// Order Details JSON
var OrderDetails = [
        {
            orderid: "1110112",
            executionsequence: "1",
            solutionlogicstepname: "GetQuoteFLOW",
            systemsourceerrorcode: "(null)",
            systemsourceerrordesc: "(null)",
            inputpayload: "",
            outputpayload: '<!(CDATA)<!-- $Header: /home/cvsroot/w3c-xml-schema/user/examples/first.xml,v1.22001/11/01 22:03:06 vdv Exp $ --><library><book id="b0836217462" available="true"><isbn>0836217462</isbn>'
        },
        {
            orderid: "1110112",
            executionsequence: "2",
            solutionlogicstepname: "GetQuoteFLOW2",
            systemsourceerrorcode: "(null)",
            systemsourceerrordesc: "(null)",
            inputpayload: "",
            outputpayload: "<!(CDATA)<!-- $Header: /home/cvsroot/w3c-xml-schema/user/examples/first.xml,v1.22001/11/01 22:03:06 vdv Exp $ --><library><book id=b0836217462 available=true><isbn>0836217462</isbn>"
        },
        {
            orderid: "1110222",
            executionsequence: "1",
            solutionlogicstepname: "GetQuoteSuccess",
            systemsourceerrorcode: "(null)",
            systemsourceerrordesc: "(null)",
            inputpayload: "",
            outputpayload: "<!(CDATA)<!-- $Header: /home/cvsroot/w3c-xml-schema/user/examples/first.xml,v1.22001/11/01 22:03:06 vdv Exp $ --><library><book id=b0836217462 available=true><isbn>0836217462</isbn>"
        }
];
