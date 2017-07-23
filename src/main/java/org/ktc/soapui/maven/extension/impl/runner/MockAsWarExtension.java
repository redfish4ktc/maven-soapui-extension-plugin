/*
 * Copyright 2013 Thomas Bouffard (redfish4ktc)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.ktc.soapui.maven.extension.impl.runner;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.tools.MockAsWar;

public class MockAsWarExtension extends MockAsWar {

    public MockAsWarExtension(String projectPath, String settingsPath, String warDir, String warFile,
            boolean includeExt, boolean actions, boolean listeners, String localEndpoint, boolean enableWebUI, WsdlProject project) {
        super(projectPath, settingsPath, warDir, warFile, includeExt, actions, listeners, localEndpoint, enableWebUI, project);
    }

    @Override
    protected void createContent(StringBuilder content) {
        super.createContent(content);
    }
}
