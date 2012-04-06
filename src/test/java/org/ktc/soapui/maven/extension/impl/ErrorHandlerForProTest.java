package org.ktc.soapui.maven.extension.impl;

import org.junit.Before;

import com.eviware.soapui.SoapUIProTestCaseRunner;

public class ErrorHandlerForProTest extends AbstractTestErrorHandler {

  @Before
  public void setup() {
    runner = new SoapUIProTestCaseRunner();
  }

}
