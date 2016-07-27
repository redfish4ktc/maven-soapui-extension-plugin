/*
 * Copyright 2011-2012 Thomas Bouffard (redfish4ktc)
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

package org.ktc.soapui.maven.extension.impl;

import com.eviware.soapui.model.testsuite.TestAssertion;
import com.eviware.soapui.model.testsuite.TestCase;
import java.lang.reflect.Field;
import java.util.List;

import com.smartbear.ready.cmd.runner.SoapUITestCaseRunner;
import org.apache.commons.lang3.reflect.FieldUtils;

public class ErrorHandler {

    public static boolean hasFailures(SoapUITestCaseRunner runner) {
        List<TestCase> failedTests = getFailedTests(runner);
        if (failedTests.size() > 0) {
            return true;
        }
        List<TestAssertion> failedAssertions = getFailedAssertions(runner);
        if (failedAssertions.size() > 0) {
            return true;
        }

        return false;
    }

    private static List<TestCase> getFailedTests(SoapUITestCaseRunner runner) {
        String fieldName = "e";
        Field field = FieldUtils.getField(SoapUITestCaseRunner.class, fieldName, true);
        try {
            @SuppressWarnings("unchecked")
            List<TestCase> failedTests = (List<TestCase>) FieldUtils.readField(field, runner, true);
            return failedTests;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to read field " + fieldName, e);
        }
    }

    private static List<TestAssertion> getFailedAssertions(SoapUITestCaseRunner runner) {
        String fieldName = "assertions";
        Field field = FieldUtils.getField(SoapUITestCaseRunner.class, fieldName, true);
        try {
            @SuppressWarnings("unchecked")
            List<TestAssertion> failedAssertionss = (List<TestAssertion>) FieldUtils.readField(field, runner, true);
            return failedAssertionss;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to read field " + fieldName, e);
        }
    }

}
