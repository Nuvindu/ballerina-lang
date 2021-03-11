import ballerina/io as x;

function testRestrictedElementPrefix() returns (xml) {
    xml x = xml `<xmlns:foo>hello</xmlns:foo>`;
    return x;
}

function xmlUndeclaredElementPrefix() returns (xml) {
    xml x = xml `<ns1:foo>hello</ns1:foo>`;
    return x;
}

function xmlTemplateWithNonXML() {
    map<any> m = {};
    xml x = xml `<root xmlns="http://default/namespace">${m}</root>`;
}

function defineEmptyNamespaceInline() {
    xml x = xml `<root xmlns:ns0=""/>`;
}

function testRedeclareNamespaces() {
    xmlns "http://sample.com/wso2/a2" as ns0;
    xmlns "http://sample.com/wso2/c2";
    xmlns "http://sample.com/wso2/d2" as ns3;
    
    if (true) {
        xmlns "http://sample.com/wso2/a3" as ns0;
    }
}

function foo() {
    xmlns "http:wso2.com/" as x;
}

function updateQname() {
    xmlns "http://wso2.com/" as ns0;
    ns0:foo = "{uri}localname";
}

function undefinedNamespace() {
    xml x = xml `<root/>`;
    if (true) {
        xmlns "http://wso2.com/" as ns0;
    }
    string|error s = x.ns0:foo;
}

function defineEmptyNamespace() {
    xmlns "" as ns0;
}

function testMismatchingElementTags() {
    var x = xml `<book></book12>`;
}

function dummyFunctionToUseMath() {
    x:println("text");
}

function testXmlNsInterpolation() returns xml {
    string ns = "http://wso2.com/";
    xml x = xml `<foo xmlns="${ns}" xmlns:foo="${ns}">hello</foo>`;
    return x;
}

function testXMLSequence() {
    xml<'xml:Text> x4 = xml `<!--comment-->text1`;
    'xml:Text x10 = xml `<!--comment-->text1`;
    xml<'xml:Text|'xml:Comment> x7 = xml `<root> text1<foo>100</foo><foo>200</foo></root>`;
    xml<'xml:Text|'xml:Comment> x8 = xml `<root> text1<foo>100</foo><foo>200</foo></root><?foo?>`;
    xml<'xml:Text>|xml<'xml:Comment> x11 = xml `<root> text1<foo>100</foo><foo>200</foo></root>`;
    xml<'xml:Text>|xml<'xml:Comment> x12 = xml `<root> text1<foo>100</foo><foo>200</foo></root><?foo?>`;
    'xml:Text|'xml:Comment x15 = xml `<root> text1<foo>100</foo><foo>200</foo></root>`;
    'xml:Text|'xml:Comment x16 = xml `<root> text1<foo>100</foo><foo>200</foo></root><?foo?>`;
}
