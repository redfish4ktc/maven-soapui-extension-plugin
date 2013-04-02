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
import com.eviware.soapui.tools.SoapUITestCaseRunner;

public class SoapUIExtensionTestCaseRunner extends SoapUITestCaseRunner {

    private String[] testSuiteProperties = {};

    public SoapUIExtensionTestCaseRunner() {
        super();
    }

    public SoapUIExtensionTestCaseRunner(String title) {
        super(title);
    }

    public void setTestSuiteProperties(String[] testSuiteProperties) {
        this.testSuiteProperties = testSuiteProperties;
    }

    @Override
    protected void initProject(WsdlProject project) throws Exception {
        super.initProject(project);
        initTestSuiteProperties(project);
    }

    private void initTestSuiteProperties(WsdlProject project) {
        TestSuitePropertiesModifier.overrideTestSuiteProperties(project, testSuiteProperties);
    }

}
