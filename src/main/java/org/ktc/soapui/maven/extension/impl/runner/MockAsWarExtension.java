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
import com.eviware.soapui.mockaswar.MockAsWarProServlet;
import com.eviware.soapui.mockaswar.MockAsWarServlet;
import com.eviware.soapui.tools.MockAsWar;

public class MockAsWarExtension extends MockAsWar {

    private static String SMARTBEAR_OSS_SERVLET_CLASS_NAME = MockAsWarServlet.class.getName();
    private static String SMARTBEAR_PRO_SERVLET_CLASS_NAME = MockAsWarProServlet.class.getName();

    public MockAsWarExtension(String projectPath, String settingsPath, String warDir, String warFile,
            boolean includeExt, boolean actions, boolean listeners, String localEndpoint, boolean enableWebUI, WsdlProject project) {
        super(projectPath, settingsPath, warDir, warFile, includeExt, actions, listeners, localEndpoint, enableWebUI, project);
    }

    @Override
    protected void createContent(StringBuilder content) {
        super.createContent(content);

        // modify the servlet name because, by default, the SmartBear PRO Servlet is used
        replace(content, SMARTBEAR_PRO_SERVLET_CLASS_NAME, SMARTBEAR_OSS_SERVLET_CLASS_NAME);
    }

    private static void replace(StringBuilder content, String old, String replacement) {
        content.replace(content.indexOf(old), content.indexOf(old) + old.length(), replacement);
    }

}
