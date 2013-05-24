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

package org.ktc.soapui.maven.extension.impl.report;

import com.eviware.soapui.model.security.SecurityScan;
import com.eviware.soapui.model.testsuite.ProjectRunContext;
import com.eviware.soapui.model.testsuite.ProjectRunner;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunContext;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.model.testsuite.TestStepResult;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.model.testsuite.TestSuiteRunContext;
import com.eviware.soapui.model.testsuite.TestSuiteRunner;
import com.eviware.soapui.report.JUnitReport;
import com.eviware.soapui.report.JUnitSecurityReportCollector;
import com.eviware.soapui.security.SecurityTestRunContext;
import com.eviware.soapui.security.result.SecurityScanRequestResult;
import com.eviware.soapui.security.result.SecurityScanResult;
import com.eviware.soapui.security.result.SecurityTestStepResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

// TODO should be moved in the test directory
// this class is only used in unit and plugin tests
// we do not need it for regular execution
public class NoOpReportCollector extends JUnitSecurityReportCollector {
    private static final Logger log = Logger.getLogger(NoOpReportCollector.class);

    @Override
    public void beforeRun(ProjectRunner testScenarioRunner, ProjectRunContext runContext) {
        log.info("Start project run");
    }

    @Override
    public void afterRun(ProjectRunner testScenarioRunner, ProjectRunContext runContext) {
        log.info("End of project run");
    }

    @Override
    public void afterOriginalStep(TestCaseRunner testRunner, SecurityTestRunContext runContext,
            SecurityTestStepResult result) {
        // do nothing
    }

    @Override
    public void afterRun(TestCaseRunner testRunner, SecurityTestRunContext runContext) {
        // do nothing
    }

    @Override
    public void afterSecurityScan(TestCaseRunner testRunner, SecurityTestRunContext runContext,
            SecurityScanResult securityScanResult) {
        // do nothing
    }

    @Override
    public void afterSecurityScanRequest(TestCaseRunner testRunner, SecurityTestRunContext runContext,
            SecurityScanRequestResult securityScanReqResult) {
        // do nothing
    }

    @Override
    public void afterStep(TestCaseRunner testRunner, SecurityTestRunContext runContext, SecurityTestStepResult result) {
        // do nothing
    }

    @Override
    public void beforeRun(TestCaseRunner testRunner, SecurityTestRunContext runContext) {
        // do nothing
    }

    @Override
    public void beforeSecurityScan(TestCaseRunner testRunner, SecurityTestRunContext runContext,
            SecurityScan securityScan) {
        // do nothing
    }

    @Override
    public void beforeStep(TestCaseRunner testRunner, SecurityTestRunContext runContext, TestStepResult testStepResult) {
        // do nothing
    }

    @Override
    public List<String> saveReports(String path) throws Exception {
        log.info("Do nothing on save reports call");
        return new ArrayList<String>();
    }

    @Override
    public HashMap<String,JUnitReport> getReports() {
        return new HashMap<String,JUnitReport>();
    }

    @Override
    public void saveReport(JUnitReport report, String filename) throws Exception {
        // do nothing
    }

    @Override
    public String getReport() {
        return StringUtils.EMPTY;
    }

    @Override
    public void afterRun(TestCaseRunner testRunner, TestCaseRunContext runContext) {
        // do nothing
    }

    @Override
    public void afterStep(TestCaseRunner testRunner, TestCaseRunContext runContext, TestStepResult result) {
        // do nothing
    }

    @Override
    public void beforeRun(TestCaseRunner testRunner, TestCaseRunContext runContext) {
        // do nothing
    }

    @Override
    public void beforeStep(TestCaseRunner testRunner, TestCaseRunContext runContext) {
        // do nothing
    }

    @Override
    public void beforeStep(TestCaseRunner testRunner, TestCaseRunContext runContext, TestStep testStep) {
        // do nothing
    }

    @Override
    public void reset() {
        // do nothing
    }

    @Override
    public void afterRun(TestSuiteRunner testRunner, TestSuiteRunContext runContext) {
        // do nothing
    }

    @Override
    public void beforeRun(TestSuiteRunner testRunner, TestSuiteRunContext runContext) {
        // do nothing
    }

    @Override
    public void beforeTestCase(TestSuiteRunner testRunner, TestSuiteRunContext runContext, TestCase testCase) {
        // do nothing
    }

    @Override
    public void afterTestSuite(ProjectRunner testScenarioRunner, ProjectRunContext runContext,
            TestSuiteRunner testRunner) {
        // do nothing
    }

    @Override
    public void beforeTestSuite(ProjectRunner testScenarioRunner, ProjectRunContext runContext, TestSuite testSuite) {
        // do nothing
    }

}
