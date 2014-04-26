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

import static org.ktc.soapui.maven.extension.impl.report.coverage.CoverageBuilderHolderFactory.getCoverageBuilderHolder;

import com.eviware.soapui.impl.coverage.report.CoverageBuilder;
import com.eviware.soapui.tools.SoapUIMockServiceRunner;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ktc.soapui.maven.extension.impl.runner.SoapUIProExtensionMockServiceRunner;
import org.ktc.soapui.maven.extension.impl.runner.wrapper.SoapUIMockRunnerWrapper;

public class MockServiceMojo extends AbstractSoapuiRunnerMojo {
    // already in smartbear implementation
    private String mockService;
    private String path;
    private String port;
    private boolean noBlock;

    // custom maven-soapui-extension-plugin
    private boolean coverageReport;
    // TODO find a better name
    // do not forget to update integration tests directory names
    private boolean coverageReportDelegated;

    @Override
    protected void performRunnerExecute() throws MojoExecutionException, MojoFailureException {
        SoapUIMockRunnerWrapper runnerWrapper = SoapUIMockRunnerWrapper.newSoapUIMockServiceRunner(runnerType);

        SoapUIMockServiceRunner runner = runnerWrapper.getRunner();
        configureWithSharedParameters(runner);

        runner.setBlock(!noBlock);
        if (mockService != null) {
            runner.setMockService(mockService);
        }
        if (path != null) {
            runner.setPath(path);
        }
        if (port != null) {
            runner.setPort(port);
        }
        runner.setSaveAfterRun(saveAfterRun);

        if (runnerWrapper.isProRunner()) {
            SoapUIProExtensionMockServiceRunner proRunner = (SoapUIProExtensionMockServiceRunner) runner;
            proRunner.setOutputFolder(outputFolder);

            if (coverageReport) {
                proRunner.setCoverageBuilder(newCoverageBuilder());
                // TODO improvement use adapter around the coverage builder to avoid duplicating the logic
                // currently it is done in both the holder and the runner
                // the runner should stay simple without having to know the coverage report is delegated
                proRunner.setCoverageReportDelegated(coverageReportDelegated);
            }
        }

        try {
            runner.run();
        } catch (Exception e) {
            getLog().debug(e);
            throw new MojoFailureException("SoapUI has errors: " + e.getMessage(), e);
        }
    }

    private CoverageBuilder newCoverageBuilder() {
        return getCoverageBuilderHolder().newCoverageBuilder(outputFolder, coverageReportDelegated);
    }

}
