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
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.codehaus.plexus.util.FileUtils;

public class CheckLog {

    private String logFileContent;

    public CheckLog(File logFile) {
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


    // =================================================================================================================
    // GENERIC LOG CONTENT
    // =================================================================================================================

    public void assertLogFileContains(String expectedContent) {
        log("Expect to find content in the log file: " + expectedContent);
        int matchCount = StringUtils.countMatches(logFileContent, expectedContent);
        log("Found " + matchCount + " occurences");
        if (matchCount < 1) {
            logAndFail("FAILED! Did not find expected content in the log file: " + expectedContent);
        }
    }

    public void assertLogFileContainsOneOf(String... expected) {
        log("Expect to find one of the following in the log file: " + ArrayUtils.toString(expected));
        for (String string : expected) {
            int matchCount = StringUtils.countMatches(logFileContent, string);
            log("  Checking: " + string);
            log("  Found " + matchCount + " occurences");
            if (matchCount > 0) {
                return;
            }
        }
        logAndFail("FAILED! Did not find one of expected content in the log file");
    }

    public void assertLogFileContainsStrictly(String expectedContent, int expectedCount) {
        log("Expect to find content " + expectedCount + " times in the log file. Content: " + expectedContent);
        int matchCount = StringUtils.countMatches(logFileContent, expectedContent);
        log("Found " + matchCount + " occurences");
        if (matchCount != expectedCount) {
            logAndFail("FAILED! Expect to find " + expectedCount + " times, found " + matchCount
                    + " times. Expected content: " + expectedContent);
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

    // =================================================================================================================
    // UTILS
    // =================================================================================================================

    protected void log(String message) {
        Check.log(getLogHeader(), message);
    }

    private String getLogHeader() {
        return getClass().getSimpleName();
    }

    private void logAndFail(String message) {
        Check.logAndFail(getLogHeader(), message);
    }

}
