/*
 * Copyright 2011-2012 Thomas Bouffard (redfish4ktc)
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

import com.eviware.soapui.SoapUIProTestCaseRunner;
import com.eviware.soapui.tools.SoapUITestCaseRunner;
import java.util.Properties;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ktc.soapui.maven.extension.impl.ErrorHandler;
import org.ktc.soapui.maven.extension.impl.RunnerType;
import org.ktc.soapui.maven.extension.impl.enums.EnumConverter;

public class TestMojo extends AbstractSoapuiMojo {
    
    public static final String TEST_FAILURES_AND_ERRORS_KEY = "soapui_extension_Mlx#ppp";
    
    private String projectFile;
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
    private String settingsFile;
    private boolean skip;
    private String projectPassword;
    private String settingsPassword;
    private boolean testFailIgnore;
    private boolean coverage;
    private String[] globalProperties;
    private String[] projectProperties;
    private boolean saveAfterRun;
    private String reportFormat;
    private String reportName;
    private Properties soapuiProperties;
    // new in soapui 4.5.0 (pro only)
    private String environment;
    
    // custom maven-soapui-extension-plugin (#14)
    private String runnerType;

    @Override
    public void performExecute() throws MojoExecutionException, MojoFailureException {
        if ((this.skip) || (System.getProperty("maven.test.skip", "false").equals("true"))) {
            // #1 add log when skipping tests
            getLog().info("SoapUI tests are skipped.");
            return;
        }
        if (this.projectFile == null) {
            throw new MojoExecutionException("soapui-project-file setting is required");
        }
        
        RunnerType runnerTypeEnum = EnumConverter.toRunnerType(runnerType);
        SoapUITestCaseRunner runner = runnerTypeEnum.newTestRunner();
        
        runner.setProjectFile(this.projectFile);

        if (this.endpoint != null) {
            runner.setEndpoint(this.endpoint);
        }
        if (this.testSuite != null) {
            runner.setTestSuite(this.testSuite);
        }
        if (this.testCase != null) {
            runner.setTestCase(this.testCase);
        }
        if (this.username != null) {
            runner.setUsername(this.username);
        }
        if (this.password != null) {
            runner.setPassword(this.password);
        }
        if (this.wssPasswordType != null) {
            runner.setWssPasswordType(this.wssPasswordType);
        }
        if (this.domain != null) {
            runner.setDomain(this.domain);
        }
        if (this.host != null) {
            runner.setHost(this.host);
        }
        if (this.outputFolder != null) {
            runner.setOutputFolder(this.outputFolder);
        }
        runner.setPrintReport(this.printReport);
        runner.setExportAll(this.exportAll);
        runner.setJUnitReport(this.junitReport);
        runner.setEnableUI(this.interactive);
        runner.setIgnoreError(true);
        runner.setSaveAfterRun(this.saveAfterRun);

        if (this.settingsFile != null) {
            runner.setSettingsFile(this.settingsFile);
        }
        if (this.projectPassword != null) {
            runner.setProjectPassword(this.projectPassword);
        }
        if (this.settingsPassword != null) {
            runner.setSoapUISettingsPassword(this.settingsPassword);
        }
        if (this.globalProperties != null) {
            runner.setGlobalProperties(this.globalProperties);
        }
        if (this.projectProperties != null) {
            runner.setProjectProperties(this.projectProperties);
        }
        if (this.soapuiProperties != null && !this.soapuiProperties.isEmpty()) {
            for (Object keyObject : this.soapuiProperties.keySet()) {
                String key = (String) keyObject;
                getLog().info("Setting " + key + " value " + this.soapuiProperties.getProperty(key));
                System.setProperty(key, this.soapuiProperties.getProperty(key));
            }
        }

        if(runnerTypeEnum.isProRunner()) {
            SoapUIProTestCaseRunner proRunner = (SoapUIProTestCaseRunner) runner;
            if (environment != null) {
                proRunner.setEnvironment(environment);
            }
            proRunner.setOpenReport(this.openReport);
            if (this.coverage) {
                proRunner.initCoverageBuilder();
            }
            if (this.reportName != null) {
                proRunner.setReportName(this.reportName);
            }
            if (this.reportFormat != null) {
                proRunner.setReportFormats(this.reportFormat.split(","));
            }
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
