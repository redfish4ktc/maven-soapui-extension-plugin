/*
 * Copyright 2012 Thomas Bouffard (redfish4ktc)
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

import com.eviware.soapui.tools.AbstractSoapUIRunner;
import java.util.Properties;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public abstract class AbstractSoapuiRunnerMojo extends AbstractSoapuiMojo {
    // already in smartbear implementation
    private String[] globalProperties;
    private String projectFile;
    private String projectPassword;
    private String[] projectProperties;
    protected boolean saveAfterRun;
    private String settingsFile;
    private String settingsPassword;
    private boolean skip;
    private Properties soapuiProperties;

    // custom maven-soapui-extension-plugin
    protected String runnerType;

    @Override
    protected void performExecute() throws MojoExecutionException, MojoFailureException {
        if (skip || (System.getProperty("maven.test.skip", "false").equals("true"))) {
            getLog().info("SoapUI execution is skipped.");
            return;
        }
        if (projectFile == null) {
            throw new MojoExecutionException("soapui-project-file setting is required");
        }
        performRunnerExecute();
    }

    protected abstract void performRunnerExecute() throws MojoExecutionException, MojoFailureException;

    protected void configureWithSharedParameters(AbstractSoapUIRunner runner) {
        runner.setProjectFile(projectFile);
        if (projectPassword != null) {
            runner.setProjectPassword(projectPassword);
        }
        if (settingsFile != null) {
            runner.setSettingsFile(settingsFile);
        }
        if (settingsPassword != null) {
            runner.setSoapUISettingsPassword(settingsPassword);
        }
        if (globalProperties != null) {
            runner.setGlobalProperties(globalProperties);
        }
        if (projectProperties != null) {
            runner.setProjectProperties(projectProperties);
        }
        if (soapuiProperties != null && !soapuiProperties.isEmpty()) {
            for (Object keyObject : soapuiProperties.keySet()) {
                String key = (String) keyObject;
                getLog().info("Setting " + key + " value " + soapuiProperties.getProperty(key));
                System.setProperty(key, soapuiProperties.getProperty(key));
            }
        }
    }

}
