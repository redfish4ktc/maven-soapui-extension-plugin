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

package org.ktc.soapui.maven.extension.impl.runner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.model.testsuite.TestProperty;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSuitePropertiesModifierTest {

    private WsdlTestSuite testSuite;

    @BeforeClass
    public static void initSoapuiLogs() {
        System.setProperty("soapui.logroot", "target/test-output/soapui/logs/");
    }

    @Before
    public void initTestSuite() throws Exception {
        WsdlProject project = new WsdlProject();
        testSuite = project.addNewTestSuite("mySuite");
    }

    @Test
    public void createNewPropertiesAndManageProvidedPropertiesWithoutKeyValueSeparator() throws Exception {
        TestSuitePropertiesModifier.overrideTestSuiteProperties(testSuite, new String[] {"prop1=value1", "prop2",
                "prop3=value3"});
        List<TestProperty> testProperties = testSuite.getPropertyList();
        assertThat(extractProperty("name").from(testProperties)).containsExactly("prop1", "prop3");
        assertThat(extractProperty("value").from(testProperties)).containsExactly("value1", "value3");
    }

    @Test
    public void providingNullPropertiesDoesNotModifyTestSuiteProperties() throws Exception {
        TestSuitePropertiesModifier.overrideTestSuiteProperties(testSuite, null);
        assertThat(testSuite.getPropertyList()).isEmpty();
    }

    @Test
    public void overrideExistingProperties() throws Exception {
        testSuite.setPropertyValue("prop2", "oldValue2");

        TestSuitePropertiesModifier.overrideTestSuiteProperties(testSuite, new String[] {"prop2=newValue2"});
        List<TestProperty> testProperties = testSuite.getPropertyList();
        assertThat(extractProperty("name").from(testProperties)).containsExactly("prop2");
        assertThat(extractProperty("value").from(testProperties)).containsExactly("newValue2");
    }

    @Test
    public void updatePropertiesOnRealSoapuiProject() throws Exception {
        WsdlProject project = new WsdlProject("src/test/resources/soapui/testsuites-properties-soapui-project.xml");
        WsdlTestSuite suite = project.getTestSuiteByName("TestSuite1");
        TestSuitePropertiesModifier.overrideTestSuiteProperties(suite,
                new String[] {"property2=new super salue for property2"});
        List<TestProperty> testProperties = suite.getPropertyList();
        assertThat(extractProperty("name").from(testProperties)).containsExactly("property1", "property2");
        assertThat(extractProperty("value").from(testProperties)).containsExactly("testsuite1-value1",
                "new super salue for property2");
    }

    @Test
    public void createPropertiesOnRealSoapuiProject() throws Exception {
        WsdlProject project = new WsdlProject("src/test/resources/soapui/testsuites-properties-soapui-project.xml");
        WsdlTestSuite suite = project.getTestSuiteByName("TestSuite3");
        TestSuitePropertiesModifier.overrideTestSuiteProperties(suite,
                new String[] {"property1=new super salue for property1"});
        List<TestProperty> testProperties = suite.getPropertyList();
        assertThat(extractProperty("name").from(testProperties)).containsExactly("property2", "property1");
        assertThat(extractProperty("value").from(testProperties)).containsExactly("testsuite3-value2",
                "new super salue for property1");
    }

    @Test
    public void updatePropertiesInTheWholeProject() throws Exception {
        WsdlProject project = new WsdlProject();
        WsdlTestSuite suite1 = project.addNewTestSuite("suite1");
        WsdlTestSuite suite2 = project.addNewTestSuite("suite2");

        TestSuitePropertiesModifier.overrideTestSuiteProperties(project, new String[] {"property=new property"});
        assertThat(extractProperty("name").from(suite1.getPropertyList())).containsExactly("property");
        assertThat(extractProperty("name").from(suite2.getPropertyList())).containsExactly("property");
    }

    @Test
    public void updatePropertiesInTheWholeProjectWhichHasNoTestSuite() throws Exception {
        WsdlProject project = new WsdlProject();
        assertThat(project.getTestSuiteCount()).isEqualTo(0);
        TestSuitePropertiesModifier.overrideTestSuiteProperties(project, new String[] {"property=new property"});
    }

}
