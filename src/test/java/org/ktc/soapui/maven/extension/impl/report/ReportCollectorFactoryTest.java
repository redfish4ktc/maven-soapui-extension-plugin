/*
 * Copyright 2013-2014 Thomas Bouffard (redfish4ktc)
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.ktc.soapui.maven.extension.impl.report.ReportCollectorFactory.newReportCollector;

import com.eviware.soapui.report.JUnitSecurityReportCollector;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;

public class ReportCollectorFactoryTest {

    private static final Class<JUnitSecurityReportCollector> DEFAULT_COLLECTOR_CLASS = JUnitSecurityReportCollector.class;
    private static final String REPORT_COLLECTOR_SYS_PROP = "soapui.junit.reportCollector";

    @Rule
    public final RestoreSystemProperties restoreSysProperties = new RestoreSystemProperties(REPORT_COLLECTOR_SYS_PROP);

    @Test
    public void newReportCollectorWithEmptySystemProperty() {
        System.setProperty(REPORT_COLLECTOR_SYS_PROP, "");
        assertThat(newReportCollector()).isInstanceOf(DEFAULT_COLLECTOR_CLASS);
    }

    @Test
    public void newReportCollectorWithNoSystemPropertyBlankValue() {
        System.setProperty(REPORT_COLLECTOR_SYS_PROP, "  ");
        assertThat(newReportCollector()).isInstanceOf(DEFAULT_COLLECTOR_CLASS);
    }

    @Test
    public void newReportCollectorSystemPropertyReferenceBadClass() {
        System.setProperty(REPORT_COLLECTOR_SYS_PROP, WrongReportCollector.class.getName());
        assertThat(newReportCollector()).isInstanceOf(DEFAULT_COLLECTOR_CLASS);
    }

    @Test
    public void newReportCollectorSystemPropertyReferenceReportCollectorExtension() {
        System.setProperty(REPORT_COLLECTOR_SYS_PROP, NoOpReportCollector.class.getName());
        assertThat(newReportCollector()).isInstanceOf(NoOpReportCollector.class);
    }

    @Test
    public void newReportCollectorWithNoSystemProperty() {
        System.clearProperty(REPORT_COLLECTOR_SYS_PROP);
        assertThat(newReportCollector()).isInstanceOf(DEFAULT_COLLECTOR_CLASS);
    }

    private static class WrongReportCollector {
        @SuppressWarnings("unused")
        public WrongReportCollector() {
            // do nothing
        }
    }

}
