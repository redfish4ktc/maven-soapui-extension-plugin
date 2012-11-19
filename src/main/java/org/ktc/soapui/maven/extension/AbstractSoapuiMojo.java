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

import org.apache.maven.model.Build;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

public abstract class AbstractSoapuiMojo extends AbstractMojo {
    protected MavenProject project;

    protected abstract void performExecute() throws MojoExecutionException, MojoFailureException;

    @Override
    public final void execute() throws MojoExecutionException, MojoFailureException {
        configureDefaultLogDirectory();
        performExecute();
    }

    private void configureDefaultLogDirectory() {
        Build build = project.getBuild();
        // Be careful with the trailing / (see
        // https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips#wiki-log-config)
        String defaultLogDirectoryPath = build.getDirectory() + "/soapui/logs/";
        String soapuiLogRootKey = "soapui.logroot";
        System.setProperty(soapuiLogRootKey, defaultLogDirectoryPath);
        if(getLog().isDebugEnabled()) {
            getLog().debug("Default log directory is set to " + System.getProperty(soapuiLogRootKey));
        }
    }

}
