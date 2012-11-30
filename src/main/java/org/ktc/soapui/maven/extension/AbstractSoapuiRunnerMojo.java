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

import java.util.Properties;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public abstract class AbstractSoapuiRunnerMojo extends AbstractSoapuiMojo {
    private boolean skip;
    
    protected String[] globalProperties;
    protected String projectFile;
    protected String projectPassword;
    protected String[] projectProperties;
    protected boolean saveAfterRun;
    protected String settingsFile;
    protected String settingsPassword;
    protected Properties soapuiProperties;

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

    public abstract void performRunnerExecute() throws MojoExecutionException, MojoFailureException;

}
