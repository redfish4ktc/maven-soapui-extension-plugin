/*
 * Copyright 2013-2014 Thomas Bouffard (redfish4ktc)
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

import com.eviware.soapui.SoapUIProTestCaseRunner;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.report.JUnitReportCollector;
import com.eviware.soapui.report.JUnitSecurityReportCollector;
import org.ktc.soapui.maven.extension.impl.report.ReportCollectorFactory;

public class SoapUIProExtensionTestCaseRunner extends SoapUIProTestCaseRunner {

    private boolean junitHtmlReport = true;
    private String[] testSuiteProperties = {};

    public SoapUIProExtensionTestCaseRunner() {
        super();
    }

    public SoapUIProExtensionTestCaseRunner(String title) {
        super(title);
    }

    public boolean isJunitHtmlReport() {
        return junitHtmlReport;
    }

    public void setJunitHtmlReport(boolean junitHtmlReport) {
        this.junitHtmlReport = junitHtmlReport;
    }

    public void setTestSuiteProperties(String[] testSuiteProperties) {
        this.testSuiteProperties = testSuiteProperties;
    }

    @Override
    protected void initProject(WsdlProject project) {
        super.initProject(project);
        initTestSuiteProperties(project);
    }

    private void initTestSuiteProperties(WsdlProject project) {
        TestSuitePropertiesModifier.overrideTestSuiteProperties(project, testSuiteProperties);
    }
    
    @Override
    public void exportJUnitReports(JUnitReportCollector collector, String folder, WsdlProject project) {
        if (junitHtmlReport) {
            super.exportJUnitReports(collector, folder, project);
        } else {
            // copy from SoapUITestCaseRunner
            try {
                collector.saveReports(folder == null ? "" : folder);
            } catch (Exception e) {
                log.error("Failed to create JUnit reports", e);
            }
        }
    }

    @Override
    protected JUnitSecurityReportCollector createJUnitSecurityReportCollector() {
        return ReportCollectorFactory.newReportCollector();
    }

    @Override
    public void setEndpoint(String endpoint) {
        if (endpoint != null) {
            super.setEndpoint(endpoint);
        }
    }

    protected void initGroovyLog() {
    	//stubbed to prevent multiple appenders, groovy.log is configured in soapui-log4j.xml
    }
}
