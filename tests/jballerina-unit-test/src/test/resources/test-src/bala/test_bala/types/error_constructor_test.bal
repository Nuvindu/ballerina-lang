// Copyright (c) 2022 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

import testorg/test_project_error_constructor as er;

public function testErrorConstructor() {
    er:E2 e = error("error-A", body = "error-A2");
    error e2 = error er:E2("error-B", body = "error-B2");

    assertEquality(e is er:E2, true);
    assertEquality(e2 is er:E2, true);
    assertEquality(e.message(), "error-A");
    assertEquality(e2.message(), "error-B");
}

function assertEquality(any|error expected, any|error actual) {
    if expected is anydata && actual is anydata && expected == actual {
        return;
    }

    if expected === actual {
        return;
    }

    string expectedValAsString = expected is error ? expected.toString() : expected.toString();
    string actualValAsString = actual is error ? actual.toString() : actual.toString();
    panic error("Assertion Error",
            message = "expected '" + expectedValAsString + "', found '" + actualValAsString + "'");
}
