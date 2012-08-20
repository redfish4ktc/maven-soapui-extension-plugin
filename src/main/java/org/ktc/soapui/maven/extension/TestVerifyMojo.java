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

import static org.ktc.soapui.maven.extension.TestMojo.TEST_FAILURES_AND_ERRORS_KEY;

import org.apache.commons.lang.BooleanUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.ktc.soapui.maven.extension.impl.ProjectInfo;

public class TestVerifyMojo extends AbstractMojo {

    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        // TODO duplicate with TestMojo
        getLog().info("You are using " + ProjectInfo.getName() + " " + ProjectInfo.getVersion());

        getLog().info("Checking if soapui tests mojo fails");
        String soapuiTestsHaveFailuresOrErrors = project.getProperties().getProperty(TEST_FAILURES_AND_ERRORS_KEY);
        if (BooleanUtils.toBoolean(soapuiTestsHaveFailuresOrErrors)) {
            throw new MojoFailureException("SoapUI Test(s) failed: see logs and/or check the printReport"
                    + " (if necessary, set the option to true)");
        }
        getLog().info("No Soapui tests fail.");
    }

}
