package org.ktc.soapui.maven.extension.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import com.eviware.soapui.SoapUIProTestCaseRunner;
import com.eviware.soapui.model.ModelItem;
import com.eviware.soapui.model.settings.Settings;
import com.eviware.soapui.model.testsuite.Assertable;
import com.eviware.soapui.model.testsuite.Assertable.AssertionStatus;
import com.eviware.soapui.model.testsuite.AssertionError;
import com.eviware.soapui.model.testsuite.LoadTest;
import com.eviware.soapui.model.testsuite.TestAssertion;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunContext;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestProperty;
import com.eviware.soapui.model.testsuite.TestPropertyListener;
import com.eviware.soapui.model.testsuite.TestRunListener;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.security.SecurityTest;
import com.eviware.soapui.support.types.StringToObjectMap;
import com.eviware.soapui.tools.SoapUITestCaseRunner;

public abstract class AbstractTestErrorHandler {

  SoapUITestCaseRunner runner;

  @Test
  public void hasErrors_whenEverythingIsOk() {
    assertThat(ErrorHandler.hasErrors(runner), is(false));
  }

  @Test
  public void hasErrors_whenRunnerHasFailedTests() throws IllegalAccessException {
    initializeFailedTests();
    assertThat(ErrorHandler.hasErrors(runner), is(true));
  }

  @Test
  public void hasErrors_whenRunnerHasFailedAssertions() throws IllegalAccessException {
    initializeFailedAssertions();
    assertThat(ErrorHandler.hasErrors(runner), is(true));
  }

  private void initializeFailedTests() throws IllegalAccessException {
    List<TestCase> failedTests = new ArrayList<TestCase>();
    failedTests.add(new FakeTestCase());
    Field field = FieldUtils.getField(SoapUIProTestCaseRunner.class, "failedTests", true);
    FieldUtils.writeField(field, runner, failedTests, true);
  }

  private void initializeFailedAssertions() throws IllegalAccessException {
    List<TestAssertion> failedAssertions = new ArrayList<TestAssertion>();
    failedAssertions.add(new FakeTestAssertion());
    Field field = FieldUtils.getField(SoapUIProTestCaseRunner.class, "assertions", true);
    FieldUtils.writeField(field, runner, failedAssertions, true);
  }

  // TODO use mockito for stubbing
  static class FakeTestCase implements TestCase {

    public String getName() {
      return null;
    }

    public String getId() {
      return null;
    }

    public ImageIcon getIcon() {
      return null;
    }

    public String getDescription() {
      return null;
    }

    public Settings getSettings() {
      return null;
    }

    public List<? extends ModelItem> getChildren() {
      return null;
    }

    public ModelItem getParent() {
      return null;
    }

    public void addPropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener) {
      // do nothing
    }

    public void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener) {
      // do nothing
    }

    public void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener) {
      // do nothing
    }

    public void removePropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener) {
      // do nothing
    }

    public String[] getPropertyNames() {
      return null;
    }

    public void setPropertyValue(String paramString1, String paramString2) {
      // do nothing
    }

    public String getPropertyValue(String paramString) {
      return null;
    }

    public TestProperty getProperty(String paramString) {
      return null;
    }

    public Map<String, TestProperty> getProperties() {
      return null;
    }

    public void addTestPropertyListener(TestPropertyListener paramTestPropertyListener) {
      // do nothing
    }

    public void removeTestPropertyListener(TestPropertyListener paramTestPropertyListener) {
      // do nothing
    }

    public boolean hasProperty(String paramString) {
      return false;
    }

    public ModelItem getModelItem() {
      return null;
    }

    public int getPropertyCount() {
      return 0;
    }

    public List<TestProperty> getPropertyList() {
      return null;
    }

    public TestProperty getPropertyAt(int paramInt) {
      return null;
    }

    public String getPropertiesLabel() {
      return null;
    }

    public TestSuite getTestSuite() {
      return null;
    }

    public TestStep getTestStepAt(int paramInt) {
      return null;
    }

    public int getIndexOfTestStep(TestStep paramTestStep) {
      return 0;
    }

    public int getTestStepCount() {
      return 0;
    }

    public List<TestStep> getTestStepList() {
      return null;
    }

    public LoadTest getLoadTestAt(int paramInt) {
      return null;
    }

    public LoadTest getLoadTestByName(String paramString) {
      return null;
    }

    public int getIndexOfLoadTest(LoadTest paramLoadTest) {
      return 0;
    }

    public int getLoadTestCount() {
      return 0;
    }

    public List<LoadTest> getLoadTestList() {
      return null;
    }

    public TestCaseRunner run(StringToObjectMap paramStringToObjectMap, boolean paramBoolean) {
      return null;
    }

    public void addTestRunListener(TestRunListener paramTestRunListener) {
      // do nothing
    }

    public void removeTestRunListener(TestRunListener paramTestRunListener) {
      // do nothing
    }

    public int getTestStepIndexByName(String paramString) {
      return 0;
    }

    public <T extends TestStep> T findPreviousStepOfType(TestStep paramTestStep, Class<T> paramClass) {
      return null;
    }

    public <T extends TestStep> T findNextStepOfType(TestStep paramTestStep, Class<T> paramClass) {
      return null;
    }

    public <T extends TestStep> List<T> getTestStepsOfType(Class<T> paramClass) {
      return null;
    }

    public void moveTestStep(int paramInt1, int paramInt2) {
      // do nothing
    }

    public TestStep getTestStepByName(String paramString) {
      return null;
    }

    public boolean isDisabled() {
      return false;
    }

    public String getLabel() {
      return null;
    }

    public int getIndexOfSecurityTest(SecurityTest arg0) {
        return 0;
    }

    public SecurityTest getSecurityTestAt(int arg0) {
        return null;
    }

    public SecurityTest getSecurityTestByName(String arg0) {
        return null;
    }

    public int getSecurityTestCount() {
        return 0;
    }

    public List < SecurityTest > getSecurityTestList() {
        return null;
    }

  }

  static class FakeTestAssertion implements TestAssertion {

    public String getName() {
      return null;
    }

    public String getId() {
      return null;
    }

    public ImageIcon getIcon() {
      return null;
    }

    public String getDescription() {
      return null;
    }

    public Settings getSettings() {
      return null;
    }

    public List<? extends ModelItem> getChildren() {
      return null;
    }

    public ModelItem getParent() {
      return null;
    }

    public void addPropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener) {
      // do nothing
    }

    public void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener) {
      // do nothing
    }

    public void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener) {
      // do nothing
    }

    public void removePropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener) {
      // do nothing
    }

    public AssertionStatus getStatus() {
      return null;
    }

    public AssertionError[] getErrors() {
      return null;
    }

    public boolean isAllowMultiple() {
      return false;
    }

    public boolean isConfigurable() {
      return false;
    }

    public boolean isClonable() {
      return false;
    }

    public boolean configure() {
      return false;
    }

    public Assertable getAssertable() {
      return null;
    }

    public String getLabel() {
      return null;
    }

    public boolean isDisabled() {
      return false;
    }

    public void prepare(TestCaseRunner paramTestCaseRunner, TestCaseRunContext paramTestCaseRunContext)
        throws Exception {
      // do nothing
    }

  }

}
