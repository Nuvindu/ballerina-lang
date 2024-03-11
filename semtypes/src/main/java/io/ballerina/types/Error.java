/*
 *  Copyright (c) 2023, WSO2 LLC. (https://www.wso2.com).
 *
 *  WSO2 LLC. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied. See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package io.ballerina.types;

import io.ballerina.types.subtypedata.AllOrNothingSubtype;
import io.ballerina.types.subtypedata.BddNode;
import io.ballerina.types.typeops.BddCommonOps;

/**
 * Contain functions found in error.bal file.
 *
 * @since 2201.8.0
 */
public class Error {
    public static SemType errorDetail(SemType detail) {
        SubtypeData sd = Core.subtypeData(detail, BasicTypeCode.UT_MAPPING_RO);
        if (sd instanceof AllOrNothingSubtype) {
            if (((AllOrNothingSubtype) sd).isAllSubtype()) {
                return PredefinedType.ERROR;
            } else {
                // XXX This should be reported as an error
                return PredefinedType.NEVER;
            }
        } else {
            return PredefinedType.basicSubtype(BasicTypeCode.BT_ERROR, (ProperSubtypeData) sd);
        }
    }

    // distinctId must be >= 0
    public SemType errorDistinct(int distinctId) {
        BddNode bdd = BddCommonOps.bddAtom(RecAtom.createRecAtom(-distinctId - 1));
        return PredefinedType.basicSubtype(BasicTypeCode.BT_ERROR, bdd);
    }
}
