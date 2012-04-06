package org.ktc.soapui.maven.extension;

import java.util.Iterator;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

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

    /** @deprecated */
    private boolean coverageBuilder;
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
        if ((this.skip) || (System.getProperty("maven.test.skip", "false").equals("true"))) {
            // ****************************
            // for issue #1
//            getLog().info("Skipping execution of soapUI Pro 3.6 Maven2");
            // ****************************
            return;
        }
        if (this.projectFile == null) {
            throw new MojoExecutionException("soapui-project-file setting is required");
        }

        SoapUIProTestCaseRunner runner = new SoapUIProTestCaseRunner("soapUI Pro 3.6 Maven2 TestCase Runner");

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
        if ((this.coverageBuilder) || (this.coverage)) {
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
            for (Iterator iterator = this.soapuiProperties.keySet().iterator(); iterator.hasNext();) {
                Object key = iterator.next();
                System.out.println("Setting " + ((String) key) + " value "
                        + this.soapuiProperties.getProperty((String) key));
                System.setProperty((String) key, this.soapuiProperties.getProperty((String) key));
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
