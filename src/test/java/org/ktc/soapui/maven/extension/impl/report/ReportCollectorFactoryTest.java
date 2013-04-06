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

package org.ktc.soapui.maven.extension.impl.report;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.ktc.soapui.maven.extension.impl.report.ReportCollectorFactory.newReportCollector;

import com.eviware.soapui.report.JUnitSecurityReportCollector;
import org.junit.Test;

public class ReportCollectorFactoryTest {

    @Test
    public void newReportCollectorWithNoSystemProperty() {
        System.setProperty("soapui.junit.reportCollector", "");
        assertThat(newReportCollector()).isNotNull();
    }

    @Test
    public void newReportCollectorWithNoSystemPropertyBlankValue() {
        System.setProperty("soapui.junit.reportCollector", "  ");
        assertThat(newReportCollector()).isNotNull();
    }

    @Test
    public void newReportCollectorSystemPropertyReferenceBadClass() {
        System.setProperty("soapui.junit.reportCollector", WrongReportCollector.class.getName());
        assertThat(newReportCollector()).isNotNull();
    }

    @Test
    public void newReportCollectorSystemPropertyReferenceReportCollectorExtension() {
        System.setProperty("soapui.junit.reportCollector", CustomReportCollector.class.getName());
        assertThat(newReportCollector()).isInstanceOf(CustomReportCollector.class);
    }

    private static class WrongReportCollector {
        @SuppressWarnings("unused")
        public WrongReportCollector() {
            // do nothing
        }
    }

    private static class CustomReportCollector extends JUnitSecurityReportCollector {
        @SuppressWarnings("unused")
        public CustomReportCollector() {
            // do nothing
        }
    }

}
