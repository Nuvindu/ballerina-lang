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
package org.ballerinalang.langserver;

import org.ballerinalang.langserver.commons.capability.ExperimentalClientCapabilities;
import org.ballerinalang.langserver.commons.capability.InitializationOptions;
import org.ballerinalang.langserver.commons.capability.LSClientCapabilities;
import org.eclipse.lsp4j.TextDocumentClientCapabilities;
import org.eclipse.lsp4j.WorkspaceClientCapabilities;

import java.util.Map;

import static org.ballerinalang.langserver.Experimental.INTROSPECTION;
import static org.ballerinalang.langserver.Experimental.SHOW_TEXT_DOCUMENT;

/**
 * This class holds the resolved capabilities for the language server.
 *
 * @since 1.2.0
 */
public class LSClientCapabilitiesImpl implements LSClientCapabilities {
    private final ExperimentalClientCapabilities experimentalCapabilities;
    private final InitializationOptions initializationOptions;
    private final WorkspaceClientCapabilities workspaceCapabilities;
    private final TextDocumentClientCapabilities textDocCapabilities;

    LSClientCapabilitiesImpl(TextDocumentClientCapabilities textDocCapabilities,
                             WorkspaceClientCapabilities workspaceCapabilities,
                             Map experimentalClientCapabilities, 
                             Map initializationOptionsMap) {
        this.textDocCapabilities = (textDocCapabilities != null) ?
                textDocCapabilities : new TextDocumentClientCapabilities();

        this.workspaceCapabilities = (workspaceCapabilities != null) ?
                workspaceCapabilities : new WorkspaceClientCapabilities();

        this.experimentalCapabilities = (experimentalClientCapabilities != null) ?
                parseCapabilities(experimentalClientCapabilities) : new ExperimentalClientCapabilitiesImpl();

        this.initializationOptions = initializationOptionsMap != null ?
                parseInitializationOptions(initializationOptionsMap) : new InitializationOptionsImpl();
    }

    /**
     * Returns map of capabilities.
     *
     * @return {@link Map} of capabilities
     */
    @Override
    public ExperimentalClientCapabilities getExperimentalCapabilities() {
        return experimentalCapabilities;
    }
    
    @Override
    public InitializationOptions getInitializationOptions() {
        return initializationOptions;
    }

    /**
     * Returns Workspace client capabilities.
     *
     * @return {@link WorkspaceClientCapabilities}
     */
    @Override
    public WorkspaceClientCapabilities getWorkspaceCapabilities() {
        return workspaceCapabilities;
    }

    /**
     * Returns text document client capabilities.
     *
     * @return {@link TextDocumentClientCapabilities}
     */
    @Override
    public TextDocumentClientCapabilities getTextDocCapabilities() {
        return textDocCapabilities;
    }

    private ExperimentalClientCapabilities parseCapabilities(Map<String, Object> experimentalCapabilities) {
        Object introspection = experimentalCapabilities.get(INTROSPECTION.getValue());
        boolean introspectionEnabled = introspection instanceof Boolean && (Boolean) introspection;

        Object showTextDocument = experimentalCapabilities.get(SHOW_TEXT_DOCUMENT.getValue());
        boolean showTextDocumentEnabled = showTextDocument instanceof Boolean && (Boolean) showTextDocument;

        ExperimentalClientCapabilitiesImpl capabilities = new ExperimentalClientCapabilitiesImpl();
        capabilities.setIntrospectionEnabled(introspectionEnabled);
        capabilities.setShowTextDocumentEnabled(showTextDocumentEnabled);

        return capabilities;
    }

    /**
     * Parse initialization options from the initialization options map received.
     *
     * @param initOptions Receied initialization options map
     * @return Initialization options.
     */
    private InitializationOptions parseInitializationOptions(Map<String, Object> initOptions) {
        Object pullModuleSupport = initOptions.get(InitializationOptions.KEY_PULL_MODULE_SUPPORT);
        boolean pullModuleSupported = Boolean.parseBoolean(String.valueOf(pullModuleSupport));
        InitializationOptionsImpl initializationOptions = new InitializationOptionsImpl();
        initializationOptions.setPullModuleSupported(pullModuleSupported);
        return initializationOptions;
    }

    /**
     * Represents Extended LSP capabilities.
     */
    public static class ExperimentalClientCapabilitiesImpl implements ExperimentalClientCapabilities {
        private boolean introspectionEnabled = false;
        private boolean showTextDocumentEnabled = false;

        /**
         * Returns whether the introspection enabled or not.
         *
         * @return True when enabled, False otherwise
         */
        @Override
        public boolean isIntrospectionEnabled() {
            return introspectionEnabled;
        }

        /**
         * Returns whether the show text document enabled or not.
         *
         * @return True when enabled, False otherwise
         */
        @Override
        public boolean isShowTextDocumentEnabled() {
            return showTextDocumentEnabled;
        }

        private void setIntrospectionEnabled(boolean introspectionEnabled) {
            this.introspectionEnabled = introspectionEnabled;
        }

        private void setShowTextDocumentEnabled(boolean showTextDocumentEnabled) {
            this.showTextDocumentEnabled = showTextDocumentEnabled;
        }
    }

    /**
     * Represents the initialization options the LS client will be sending.
     */
    public static class InitializationOptionsImpl implements InitializationOptions {
        private boolean isPullModuleSupported = false;
        
        @Override
        public boolean isPullModuleSupported() {
            return isPullModuleSupported;
        }

        public void setPullModuleSupported(boolean pullModuleSupported) {
            isPullModuleSupported = pullModuleSupported;
        }
    }
}
