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

function testCheckingExprWithNoErrorType() {
    testCheckingExprWithNoErrorType1();
    testCheckingExprWithNoErrorType2();
}

function testCheckingExprWithNoErrorType1() {
    if checkpanic true {
        return;
    }

    panic error("You are not supposed to reach this line");
}

function testCheckingExprWithNoErrorType2() {
    boolean b = true;
    error err = error("Error!");
    error err1 = checkpanic b ? err : err;
    assertEquality(err1.message(), "Error!");
}

const ASSERTION_ERROR_REASON = "AssertionError";

function assertEquality(anydata actual, anydata expected) {
    if (actual == expected) {
        return;
    }

    panic error(ASSERTION_ERROR_REASON,
                message = "expected '" + expected.toString() + "', found '" + actual.toString() + "'");
}
