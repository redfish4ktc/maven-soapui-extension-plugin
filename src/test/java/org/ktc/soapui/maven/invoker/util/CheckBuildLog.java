/*
 * Copyright 2013-2014 Thomas Bouffard (redfish4ktc)
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

package org.ktc.soapui.maven.invoker.util;

import java.io.File;

public class CheckBuildLog extends CheckLog {

    public CheckBuildLog(File projectBaseDir) {
        super(new File(projectBaseDir, "build.log"));
    }

    // =================================================================================================================
    // OUR RUNNER EXECUTION CHECKS
    // =================================================================================================================

    public void assertOssTestRunnerHasBeenUsed() {
        log("Check that the OSS test runner has been used");
        assertLogFileContains("INFO  [SoapUIExtensionTestCaseRunner]");
        log("The OSS test runner has been used");
    }

    public void assertProTestRunnerHasBeenUsed() {
        log("Check that the PRO test runner has been used");
        assertLogFileContains("INFO  [SoapUIProExtensionTestCaseRunner]");
        log("The PRO test runner has been used");
    }

    public void assertOssMockRunnerHasBeenUsed() {
        log("Check that the OSS mock runner has been used");
        assertLogFileContains("INFO  [SoapUIExtensionMockServiceRunner]");
        log("The OSS mock runner has been used");
    }

    public void assertProMockRunnerHasBeenUsed() {
        log("Check that the PRO mock runner has been used");
        assertLogFileContains("INFO  [SoapUIProExtensionMockServiceRunner]");
        log("The PRO mock runner has been used");
    }

    public void assertOssWarGeneratorHasBeenUsed() {
        log("Check that the OSS war generator has been used");
        assertLogFileContains("INFO  [SoapUIExtensionMockAsWarGenerator] Creating WAR file with endpoint");
        assertLogFileContains("INFO  [SoapUIExtensionMockAsWarGenerator] WAR Generation complete");
        log("The OSS war generator has been used");
    }

    public void assertProWarGeneratorHasBeenUsed() {
        log("Check that the PRO war generator has been used");
        assertLogFileContains("INFO  [SoapUIProMockAsWarGenerator] Creating WAR file with endpoint");
        // the pro generator does not log war generation complete :-(
        log("The PRO war generator has been used");
    }

    // =================================================================================================================
    // SERVLET DEPLOYMENT CHECKS
    // =================================================================================================================

    public void assertOssMockAsWarServletHasBeenDeployed() {
        log("Check that the OSS MockAsWar Servlet has been deployed");
        assertLogFileContains("com.eviware.soapui.mockaswar.MockAsWarServlet initMockServiceParameters");
        assertLogFileDoesNotContain("com.eviware.soapui.mockaswar.MockAsWarProServlet initMockServiceParameters");
        log("The OSS MockAsWar Servlet has been deployed");
    }

    public void assertProMockAsWarServletHasBeenDeployed() {
        log("Check that the PRO MockAsWar Servlet has been deployed");
        assertLogFileContains("com.eviware.soapui.mockaswar.MockAsWarServlet initMockServiceParameters");
        assertLogFileContains("com.eviware.soapui.mockaswar.MockAsWarProServlet initMockServiceParameters");
        log("The PRO MockAsWar Servlet has been deployed");
    }
    
    // =================================================================================================================
    // SMARTBEAR RUNNER EXECUTION CHECKS
    // =================================================================================================================

    public void assertSmartBearOssTestRunnerHasBeenUsed() {
        log("Check that the SmartBear OSS test runner has been used");
        assertLogFileContains("INFO  [SoapUITestCaseRunner]");
        log("The SmartBear OSS test runner has been used");
    }
    
    public void assertSmartBearProTestRunnerHasBeenUsed() {
        log("Check that the SmartBear PRO test runner has been used");
        assertLogFileContains("INFO  [SoapUIProTestCaseRunner]");
        log("The SmartBear PRO test runner has been used");
    }

    public void assertSmartBearOssMockRunnerHasBeenUsed() {
        log("Check that the SmartBear OSS mock runner has been used");
        assertLogFileContains("INFO  [SoapUIMockServiceRunner]");
        log("The SmartBear OSS mock runner has been used");
    }

    public void assertSmartBearProMockRunnerHasBeenUsed() {
        log("Check that SmartBear the PRO mock runner has been used");
        assertLogFileContains("INFO  [SoapUIProMockServiceRunner]");
        log("The SmartBear PRO mock runner has been used");
    }

    public void assertSmartBearOssWarGeneratorHasBeenUsed() {
        log("Check that the SmartBear OSS war generator has been used");
        assertLogFileContains("INFO  [SoapUIMockAsWarGenerator] Creating WAR file with endpoint");
        assertLogFileContains("INFO  [SoapUIMockAsWarGenerator] WAR Generation complete");
        log("The OSS war generator has been used");
    }

    public void assertSmartBearProWarGeneratorHasBeenUsed() {
        log("Check that the SmartBear PRO war generator has been used");
        assertLogFileContains("INFO  [SoapUIProMockAsWarGenerator] Creating WAR file with endpoint");
        // the pro generator does not log war generation complete :-(
        log("The PRO war generator has been used");
    }

}
