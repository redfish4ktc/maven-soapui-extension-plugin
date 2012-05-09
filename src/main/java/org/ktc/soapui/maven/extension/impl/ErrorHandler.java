package org.ktc.soapui.maven.extension.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang.reflect.FieldUtils;

import com.eviware.soapui.model.testsuite.TestAssertion;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.tools.SoapUITestCaseRunner;

public class ErrorHandler {

  public static boolean hasErrors(SoapUITestCaseRunner runner) {
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
    String fieldName = "failedTests";
    Field field = FieldUtils.getField(SoapUITestCaseRunner.class, fieldName, true);
    try {
      @SuppressWarnings("unchecked")
      List<TestCase> failedTests = (List<TestCase>) FieldUtils.readField(field, runner, true);
      return failedTests;
    }
    catch (IllegalAccessException e) {
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
    }
    catch (IllegalAccessException e) {
      throw new RuntimeException("Unable to read field " + fieldName, e);
    }
  }

}
