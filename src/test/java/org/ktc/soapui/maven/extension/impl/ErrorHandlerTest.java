package org.ktc.soapui.maven.extension.impl;

import org.junit.Before;

import com.eviware.soapui.tools.SoapUITestCaseRunner;

public class ErrorHandlerTest extends AbstractTestErrorHandler {

  @Before
  public void setup() {
    runner = new SoapUITestCaseRunner();
  }

}
