/*
 * Copyright 2013 Thomas Bouffard (redfish4ktc)
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
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.FileUtils;

public class CheckBuildLog {

    private String logFileContent;

    public CheckBuildLog(File projectBaseDir) {
        File logFile = new File(projectBaseDir, "build.log");
        log("Scanning file " + logFile);
        if (!logFile.exists()) {
            logAndFail("FAILED! File " + logFile + " does not exist!!");
        }
        log("Reading the content of the log file " + logFile);
        try {
            logFileContent = FileUtils.fileRead(logFile, "UTF-8");
        } catch (IOException e) {
            logAndFail("Unable to read the log file: " + e.getLocalizedMessage());
        }
    }

    public void assertOssTestRunnerHasBeenUsed() {
        log("Check that the OSS runner has been used");
        assertLogFileContains("INFO  [SoapUIExtensionTestCaseRunner]");
        log("The OSS runner has been used");
    }

    public void assertProTestRunnerHasBeenUsed() {
        log("Check that the PRO test runner has been used");
        assertLogFileContains("INFO  [SoapUIProExtensionTestCaseRunner]");
        log("The PRO test runner has been used");
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

    public void assertLogFileContains(String expectedContent) {
        log("Expect to find content in the log file: " + expectedContent);
        int matchCount = StringUtils.countMatches(logFileContent, expectedContent);
        log("Found " + matchCount + " occurences");
        if (matchCount < 1) {
            logAndFail("FAILED! Did not find expected content in the log file: " + expectedContent);
        }
    }

    public void assertLogFileDoesNotContain(String content) {
        log("Expect not to find content in the log file: " + content);
        int matchCount = StringUtils.countMatches(logFileContent, content);
        log("Found " + matchCount + " occurences");
        if (matchCount > 0) {
            logAndFail("FAILED! Found unexpected content in the log file: " + content);
        }
    }

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
    
    public void assertSmartBearOssTestRunnerHasBeenUsed() {
        log("Check that the SmartBear OSS runner has been used");
        assertLogFileContains("INFO  [SoapUITestCaseRunner]");
        log("The SmartBear OSS runner has been used");
    }
    
    public void assertSmartBearProTestRunnerHasBeenUsed() {
        log("Check that the SmartBear PRO test runner has been used");
        assertLogFileContains("INFO  [SoapUIProTestCaseRunner]");
        log("The SmartBear PRO test runner has been used");
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

    private static void log(String message) {
        Check.log(getLogHeader(), message);
    }

    private static String getLogHeader() {
        return CheckBuildLog.class.getSimpleName();
    }

    private static void logAndFail(String message) {
        Check.logAndFail(getLogHeader(), message);
    }

}
