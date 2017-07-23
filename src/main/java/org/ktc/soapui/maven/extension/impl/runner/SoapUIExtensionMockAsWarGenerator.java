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
import com.eviware.soapui.model.project.ProjectFactoryRegistry;
import com.eviware.soapui.settings.ProjectSettings;
import com.eviware.soapui.support.StringUtils;
import com.eviware.soapui.tools.SoapUIMockAsWarGenerator;
import java.io.File;

public class SoapUIExtensionMockAsWarGenerator extends SoapUIMockAsWarGenerator {

    public SoapUIExtensionMockAsWarGenerator() {
        super();
    }

    public SoapUIExtensionMockAsWarGenerator(String title) {
        super(title);
    }

    // TODO found a way to remove duplication with SmartBear implementation
    // here, except usage of getters to access fields and instanciation of a custom MockAsWar, everything is duplicated
    // we should provide a PR to let us inject implementation of the MockAsWar
    @Override
    protected boolean runRunner() throws Exception {
        WsdlProject project = (WsdlProject) ProjectFactoryRegistry.getProjectFactory("wsdl").createNew(
                getProjectFile(), getProjectPassword());

        String pFile = getProjectFile();

        project.getSettings().setString(ProjectSettings.SHADOW_PASSWORD, null);

        File tmpProjectFile = new File(System.getProperty("java.io.tmpdir"));
        tmpProjectFile = new File(tmpProjectFile, project.getName() + "-project.xml");

        project.beforeSave();
        project.saveIn(tmpProjectFile);

        pFile = tmpProjectFile.getAbsolutePath();

        String endpoint = (StringUtils.hasContent(this.getLocalEndpoint())) ? this.getLocalEndpoint() : project
                .getName();

        this.log.info("Creating WAR file with endpoint [" + endpoint + "]");

        // TODO the temporary file should be removed at the end of the process
        MockAsWarExtension mockAsWar = new MockAsWarExtension(pFile, getSettingsFile(), getOutputFolder(),
                this.getWarFile(), this.isIncludeLibraries(), this.isIncludeActions(), this.isIncludeListeners(),
                endpoint, this.isEnableWebUI(), project);

        mockAsWar.createMockAsWarArchive();
        this.log.info("WAR Generation complete");
        return true;
    }

}
