/*
 * Copyright (c) 2020, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ballerinalang.debugadapter.variable.types;

import com.sun.jdi.Value;
import org.ballerinalang.debugadapter.variable.BCompoundVariable;
import org.ballerinalang.debugadapter.variable.BVariableType;
import org.ballerinalang.debugadapter.variable.VariableContext;
import org.eclipse.lsp4j.debug.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.ballerinalang.debugadapter.variable.VariableUtils.UNKNOWN_VALUE;
import static org.ballerinalang.debugadapter.variable.VariableUtils.getFieldValue;
import static org.ballerinalang.debugadapter.variable.VariableUtils.getStringValue;

/**
 * Ballerina xml variable type.
 */
public class BXmlItem extends BCompoundVariable {

    private static final String FIELD_CHILDREN = "children";
    private static final String FIELD_ATTRIBUTES = "attributes";

    public BXmlItem(VariableContext context, Value value, Variable dapVariable) {
        super(context, BVariableType.XML, value, dapVariable);
    }

    @Override
    public String computeValue() {
        try {
            return getStringValue(context, jvmValue);
        } catch (Exception ignored) {
            return UNKNOWN_VALUE;
        }
    }

    @Override
    public Map<String, Value> computeChildVariables() {
        Map<String, Value> childMap = new HashMap<>();
        try {
            Optional<Value> children = getFieldValue(jvmValue, FIELD_CHILDREN);
            Optional<Value> attributes = getFieldValue(jvmValue, FIELD_ATTRIBUTES);
            children.ifPresent(value -> childMap.put(FIELD_CHILDREN, value));
            attributes.ifPresent(value -> childMap.put(FIELD_ATTRIBUTES, value));
            return childMap;
        } catch (Exception e) {
            return childMap;
        }
    }
}
