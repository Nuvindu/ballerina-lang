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
package io.ballerina.types.subtypedata;

import io.ballerina.types.Bdd;
import io.ballerina.types.ProperSubtypeData;

/**
 * Contains 2 bdds for readonly and readwrite mappings.
 *
 * @since 2201.8.0
 */
public class RwTableSubtype implements ProperSubtypeData {
    public final Bdd ro;
    public final Bdd rw;

    private RwTableSubtype(Bdd ro, Bdd rw) {
        this.ro = ro;
        this.rw = rw;
    }

    public static ProperSubtypeData createRwTableSubtype(Bdd ro, Bdd rw)  {
        if (ro instanceof AllOrNothingSubtype && rw instanceof AllOrNothingSubtype &&
                ((AllOrNothingSubtype) ro).isAllSubtype() == ((AllOrNothingSubtype) rw).isAllSubtype()) {
            return ro;
        }
        return new RwTableSubtype(ro, rw);
    }
}
