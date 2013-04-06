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

import com.eviware.soapui.report.JUnitSecurityReportCollector;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class ReportCollectorFactory {

    private static final Logger log = Logger.getLogger(ReportCollectorFactory.class);

    // TODO manage maxErrors
    // currently do not use it as SmartBear JUnitSecurityReportCollector have no constuctor with int argument
    public static JUnitSecurityReportCollector newReportCollector() {
        String className = System.getProperty("soapui.junit.reportCollector", null);
        if (StringUtils.isNotBlank(className)) {
            try {
                return (JUnitSecurityReportCollector) Class.forName(className).getConstructor().newInstance();
            } catch (Exception e) {
                log.warn("Failed to create JUnitReportCollector class [" + className + "] so use the default one;"
                        + " error cause: " + e.toString());
            }
        }
        return new JUnitSecurityReportCollector();
    }

}
