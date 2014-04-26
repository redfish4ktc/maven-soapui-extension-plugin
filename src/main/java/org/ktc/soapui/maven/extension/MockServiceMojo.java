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

import com.eviware.soapui.tools.SoapUIMockServiceRunner;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ktc.soapui.maven.extension.impl.RunnerType;
import org.ktc.soapui.maven.extension.impl.enums.EnumConverter;

public class MockServiceMojo extends AbstractSoapuiRunnerMojo {
    private String mockService;
    private String path;
    private String port;
    private boolean noBlock;
    
    @Override
    protected void performRunnerExecute() throws MojoExecutionException, MojoFailureException {
        RunnerType runnerTypeEnum = EnumConverter.toRunnerType(runnerType);
        SoapUIMockServiceRunner runner = runnerTypeEnum.newMockRunner();
        configureWithSharedParameters(runner);

        runner.setBlock(!noBlock);
        runner.setMockService(mockService);
        runner.setPath(path);
        runner.setPort(port);
        runner.setSaveAfterRun(saveAfterRun);

        try {
            runner.run();
        } catch (Exception e) {
            getLog().debug(e);
            throw new MojoFailureException("SoapUI has errors: " + e.getMessage(), e);
        }
    }
}
