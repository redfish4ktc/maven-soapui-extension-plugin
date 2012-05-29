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

import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.ktc.soapui.maven.extension.impl.ProjectInfo;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.SoapUIProTestCaseRunner;

public class TestMojo extends AbstractMojo {
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

    // ****************************
    // for issue #2 and #3
    private MavenProject project;

    // ****************************

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("You are using " + ProjectInfo.getName() + " " + ProjectInfo.getVersion());

        if ((this.skip) || (System.getProperty("maven.test.skip", "false").equals("true"))) {
            // #1 add log when skipping tests
            getLog().info("SoapUI tests are skipped.");
            return;
        }
        if (this.projectFile == null) {
            throw new MojoExecutionException("soapui-project-file setting is required");
        }

        SoapUIProTestCaseRunner runner = new SoapUIProTestCaseRunner("SoapUI Pro " + SoapUI.SOAPUI_VERSION + " Maven2 TestCase Runner");

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
        runner.setOpenReport(this.openReport);
        runner.setIgnoreError(this.testFailIgnore);
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
        if (this.coverage) {
            runner.initCoverageBuilder();
        }
        if (this.globalProperties != null) {
            runner.setGlobalProperties(this.globalProperties);
        }
        if (this.projectProperties != null) {
            runner.setProjectProperties(this.projectProperties);
        }
        if (this.reportName != null) {
            runner.setReportName(this.reportName);
        }
        if (this.reportFormat != null)
            runner.setReportFormats(this.reportFormat.split(","));
        if ((this.soapuiProperties != null) && (this.soapuiProperties.size() > 0)) {
            for (Object keyObject : this.soapuiProperties.keySet()) {
                String key = (String) keyObject;
                System.out.println("Setting " + key + " value " + this.soapuiProperties.getProperty(key));
                System.setProperty(key, this.soapuiProperties.getProperty(key));
            }
        }
        try {
            runner.run();
            // ****************************
            // for issue #2 and #3
            // if (testFailIgnore) {
            // getLog().warn("The testFailIgnore parameter has been set to true but some tests may have failed");
            // boolean hasErrors = ErrorHandler.hasErrors(runner);
            // getLog().info("Has failing tests? " + hasErrors);
            // if (hasErrors) {
            // final String propertyName = "soapui.tests.have.failed";
            // getLog().info("Setting project property " + propertyName);
            // project.getProperties().setProperty(propertyName, "true");
            // getLog().info(
            // "Property " + propertyName + " set to " + project.getProperties().getProperty(propertyName));
            // }
            // }
            // ****************************
        } catch (Exception e) {
            getLog().error(e.toString());
            throw new MojoFailureException(this, "SoapUI Test(s) failed", e.getMessage());
        }
    }
    
}
