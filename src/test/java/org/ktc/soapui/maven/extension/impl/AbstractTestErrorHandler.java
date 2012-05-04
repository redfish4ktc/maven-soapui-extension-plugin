package org.ktc.soapui.maven.extension.impl;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import com.eviware.soapui.SoapUIProTestCaseRunner;
import com.eviware.soapui.model.testsuite.TestAssertion;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.tools.SoapUITestCaseRunner;

public abstract class AbstractTestErrorHandler {

  SoapUITestCaseRunner runner;

  @Test
  public void hasErrors_whenEverythingIsOk() {
    assertThat(ErrorHandler.hasErrors(runner)).isFalse();
  }

  @Test
  public void hasErrors_whenRunnerHasFailedTests() throws IllegalAccessException {
    initializeFailedTests();
    assertThat(ErrorHandler.hasErrors(runner)).isTrue();
  }

  @Test
  public void hasErrors_whenRunnerHasFailedAssertions() throws IllegalAccessException {
    initializeFailedAssertions();
    assertThat(ErrorHandler.hasErrors(runner)).isTrue();
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
