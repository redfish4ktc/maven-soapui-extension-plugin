/*
 * Copyright 2011-2013 Thomas Bouffard (redfish4ktc)
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

package org.ktc.soapui.maven.extension;

import com.eviware.soapui.tools.SoapUITestCaseRunner;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ktc.soapui.maven.extension.impl.ErrorHandler;
import org.ktc.soapui.maven.extension.impl.RunnerType;
import org.ktc.soapui.maven.extension.impl.TestSuiteProperties;
import org.ktc.soapui.maven.extension.impl.enums.EnumConverter;
import org.ktc.soapui.maven.extension.impl.runner.SoapUIExtensionTestCaseRunner;
import org.ktc.soapui.maven.extension.impl.runner.SoapUIProExtensionTestCaseRunner;

public class TestMojo extends AbstractSoapuiRunnerMojo {
    
    public static final String TEST_FAILURES_AND_ERRORS_KEY = "soapui_extension_Mlx#ppp";
    
    private String testSuite;
    private String testCase;
    private String username;
    private String password;
    private String wssPasswordType;
    private String domain;
    private String host;
    private String endpoint;
    private String outputFolder;
    private boolean printReport;
    private boolean interactive;
    private boolean exportAll;
    private boolean junitReport;
    private boolean openReport;
    private boolean testFailIgnore;
    private boolean coverage;
    private String reportFormat;
    private String reportName;
    // new in soapui 4.5.0 (pro only)
    private String environment;

    // maven-soapui-extension additional parameters
    private TestSuiteProperties testSuiteProperties;
    private boolean junitHtmlReport = true;

    @Override
    public void performRunnerExecute() throws MojoExecutionException, MojoFailureException {
        RunnerType runnerTypeEnum = EnumConverter.toRunnerType(runnerType);
        SoapUITestCaseRunner runner = runnerTypeEnum.newTestRunner();
        configureWithSharedParameters(runner);

        if (endpoint != null) {
            runner.setEndpoint(endpoint);
        }
        if (testSuite != null) {
            runner.setTestSuite(testSuite);
        }
        if (testCase != null) {
            runner.setTestCase(testCase);
        }
        if (username != null) {
            runner.setUsername(username);
        }
        if (password != null) {
            runner.setPassword(password);
        }
        if (wssPasswordType != null) {
            runner.setWssPasswordType(wssPasswordType);
        }
        if (domain != null) {
            runner.setDomain(domain);
        }
        if (host != null) {
            runner.setHost(host);
        }
        if (outputFolder != null) {
            runner.setOutputFolder(outputFolder);
        }
        runner.setPrintReport(printReport);
        runner.setExportAll(exportAll);
        runner.setJUnitReport(junitReport);
        runner.setEnableUI(interactive);
        runner.setIgnoreError(true);
        runner.setSaveAfterRun(saveAfterRun);

        if(runnerTypeEnum.isProRunner()) {
            SoapUIProExtensionTestCaseRunner proRunner = (SoapUIProExtensionTestCaseRunner) runner;
            if (environment != null) {
                proRunner.setEnvironment(environment);
            }
            proRunner.setJunitHtmlReport(junitHtmlReport);
            proRunner.setOpenReport(openReport);
            if (coverage) {
                proRunner.initCoverageBuilder();
            }
            if (reportName != null) {
                proRunner.setReportName(reportName);
            }
            if (reportFormat != null) {
                proRunner.setReportFormats(reportFormat.split(","));
            }
        } else {
            ((SoapUIExtensionTestCaseRunner) runner).setTestSuiteProperties(testSuiteProperties.getProperties());
        }
        

        try {
            runner.run();
        } catch (Exception e) {
            getLog().debug(e);
            throw new MojoFailureException("SoapUI has errors: " + e.getMessage(), e);
        }
        boolean hasFailures = ErrorHandler.hasFailures(runner);
        if (hasFailures) {
            if (testFailIgnore) {
                getLog().warn(
                        "Some tests have failed (see logs and/or check the printReport,"
                                + " if necessary, set the option to true)");
                getLog().debug("Setting project property " + TEST_FAILURES_AND_ERRORS_KEY);
                project.getProperties().setProperty(TEST_FAILURES_AND_ERRORS_KEY, "true");
                getLog().debug(
                        "Property " + TEST_FAILURES_AND_ERRORS_KEY + " set to "
                                + project.getProperties().getProperty(TEST_FAILURES_AND_ERRORS_KEY));
            } else {
                throw new MojoFailureException("SoapUI Test(s) failed: see logs and/or check the printReport"
                        + " (if necessary, set the option to true)");
            }
        }
    }
    
}
