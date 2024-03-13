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

import static io.ballerina.types.CellAtomicType.CELL_ATOMIC_NEVER;
import static io.ballerina.types.CellAtomicType.CELL_ATOMIC_VAL;

/**
 * Represent a TypeAtom.
 *
 * @since 2201.8.0
 */
public class TypeAtom implements Atom {
    public final long index;
    public final AtomicType atomicType;

    public static final TypeAtom ATOM_CELL_VAL = createTypeAtom(0, CELL_ATOMIC_VAL);
    public static final TypeAtom ATOM_CELL_NEVER = createTypeAtom(1, CELL_ATOMIC_NEVER);

    private TypeAtom(long index, AtomicType atomicType) {
        this.index = index;
        this.atomicType = atomicType;
    }

    public static TypeAtom createTypeAtom(long index, AtomicType atomicType) {
        return new TypeAtom(index, atomicType);
    }
}
