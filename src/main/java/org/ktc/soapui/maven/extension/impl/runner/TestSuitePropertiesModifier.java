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

import java.util.List;

import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import org.apache.log4j.Logger;

import com.eviware.soapui.impl.wsdl.WsdlProject;

public class TestSuitePropertiesModifier {
    private static final Logger log = Logger.getLogger(TestSuitePropertiesModifier.class);

    public static void overrideTestSuiteProperties(WsdlProject project, String[] testsuiteProperties) {
        log.info("Configuring test suite properties");
        List<WsdlTestSuite> suites = project.getTestSuiteList();
        for (WsdlTestSuite suite : suites) {
            overrideTestSuiteProperties(suite, testsuiteProperties);
        }
        log.info("Test suite properties configuration done");
    }

    public static void overrideTestSuiteProperties(WsdlTestSuite testSuite, String[] testsuiteProperties) {
        if (testsuiteProperties != null) {
            for (String option : testsuiteProperties) {
                int positionOfKeyValueSeparator = option.indexOf('=');
                if (positionOfKeyValueSeparator != -1) {
                    String name = option.substring(0, positionOfKeyValueSeparator);
                    String value = option.substring(positionOfKeyValueSeparator + 1);
                    log.info("TestSuite [" + testSuite.getName() + "], setting property [" + name + "] to [" + value + "]");
                    testSuite.setPropertyValue(name, value);
                }
            }
        }
    }

}