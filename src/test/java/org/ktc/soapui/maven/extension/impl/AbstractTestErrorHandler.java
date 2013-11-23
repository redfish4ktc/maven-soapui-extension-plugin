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

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.eviware.soapui.SoapUIProTestCaseRunner;
import com.eviware.soapui.model.testsuite.TestAssertion;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.tools.SoapUITestCaseRunner;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

public abstract class AbstractTestErrorHandler {

  protected SoapUITestCaseRunner runner;

  @Test
  public void hasFailures_whenEverythingIsOk() {
    assertThat(ErrorHandler.hasFailures(runner)).isFalse();
  }

  @Test
  public void hasFailures_whenRunnerHasFailedTests() throws IllegalAccessException {
    initializeFailedTests();
    assertThat(ErrorHandler.hasFailures(runner)).isTrue();
  }

  @Test
  public void hasFailures_whenRunnerHasFailedAssertions() throws IllegalAccessException {
    initializeFailedAssertions();
    assertThat(ErrorHandler.hasFailures(runner)).isTrue();
  }

  private void initializeFailedTests() throws IllegalAccessException {
    List<TestCase> failedTests = new ArrayList<TestCase>();
    failedTests.add(mock(TestCase.class));
    Field field = FieldUtils.getField(SoapUIProTestCaseRunner.class, "failedTests", true);
    FieldUtils.writeField(field, runner, failedTests, true);
  }

  private void initializeFailedAssertions() throws IllegalAccessException {
    List<TestAssertion> failedAssertions = new ArrayList<TestAssertion>();
    failedAssertions.add(mock(TestAssertion.class));
    Field field = FieldUtils.getField(SoapUIProTestCaseRunner.class, "assertions", true);
    FieldUtils.writeField(field, runner, failedAssertions, true);
  }

}
