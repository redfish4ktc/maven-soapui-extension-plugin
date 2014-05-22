/*
 * Copyright 2012-2014 Thomas Bouffard (redfish4ktc)
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

import static org.apache.commons.lang3.ArrayUtils.nullToEmpty;

import com.eviware.soapui.tools.AbstractSoapUIRunner;
import java.util.Properties;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public abstract class AbstractSoapuiRunnerMojo extends AbstractSoapuiMojo {
    // already in smartbear implementation
    private String[] globalProperties;
    protected String outputFolder;
    protected String projectFile;
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

        performRunnerExecute();
    }

    protected abstract void performRunnerExecute() throws MojoExecutionException, MojoFailureException;

    protected void configureWithSharedParameters(AbstractSoapUIRunner runner) {
        configureWithSharedParameters(runner, projectFile);
    }

    protected void configureWithSharedParameters(AbstractSoapUIRunner runner, String currentProjectFile) {
        runner.setProjectFile(currentProjectFile);
        runner.setProjectPassword(projectPassword);
        runner.setSettingsFile(settingsFile);
        runner.setSoapUISettingsPassword(settingsPassword);
        runner.setGlobalProperties(nullToEmpty(globalProperties));
        runner.setProjectProperties(nullToEmpty(projectProperties));

        initializeSystemProperties();
    }

    private void initializeSystemProperties() {
        if (!isNullOrEmpty(soapuiProperties)) {
            for (Object keyObject : soapuiProperties.keySet()) {
                String key = (String) keyObject;
                getLog().info("Setting " + key + " value " + soapuiProperties.getProperty(key));
                System.setProperty(key, soapuiProperties.getProperty(key));
            }
        }
    }

    private static boolean isNullOrEmpty(Properties props) {
        return props == null || props.isEmpty();
    }

}
