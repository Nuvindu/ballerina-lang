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
package io.ballerina.types.definition;

import io.ballerina.types.BasicTypeCode;
import io.ballerina.types.Definition;
import io.ballerina.types.Env;
import io.ballerina.types.FunctionAtomicType;
import io.ballerina.types.PredefinedType;
import io.ballerina.types.RecAtom;
import io.ballerina.types.SemType;
import io.ballerina.types.typeops.BddCommonOps;

/**
 * Represent function type desc.
 *
 * @since 2201.8.0
 */
public class FunctionDefinition implements Definition {

    private RecAtom atom;
    private SemType semType;

    public FunctionDefinition(Env env) {
        FunctionAtomicType dummy = FunctionAtomicType.from(PredefinedType.NEVER, PredefinedType.NEVER);
        this.atom = env.recFunctionAtom();
        this.semType = PredefinedType.basicSubtype(BasicTypeCode.BT_FUNCTION, BddCommonOps.bddAtom(this.atom));
    }

    @Override
    public SemType getSemType(Env env) {
        return this.semType;
    }

    public SemType define(Env env, SemType args, SemType ret) {
        FunctionAtomicType t = FunctionAtomicType.from(args, ret);
        env.setRecFunctionAtomType(this.atom, t);
        return this.semType;
    }
}
