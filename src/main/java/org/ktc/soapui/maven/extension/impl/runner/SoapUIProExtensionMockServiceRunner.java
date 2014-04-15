/*
 * Copyright 2014 Thomas Bouffard (redfish4ktc)
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

import com.eviware.soapui.SoapUIProMockServiceRunner;
import com.eviware.soapui.impl.coverage.report.CoverageBuilder;
import org.apache.commons.lang3.reflect.FieldUtils;

public class SoapUIProExtensionMockServiceRunner extends SoapUIProMockServiceRunner {

    private static final String COVERAGE_BUILDER_FIELD_NAME = "c";

    public SoapUIProExtensionMockServiceRunner() {
        super();
    }

    public SoapUIProExtensionMockServiceRunner(String title) {
        super(title);
    }

    public void activateCoverageReport(boolean activate) {
        if (activate) {
            setCoverageBuilder(new CoverageBuilder());
        }
    }

    private void setCoverageBuilder(CoverageBuilder coverageBuilder) {
        try {
            FieldUtils.writeField(this, COVERAGE_BUILDER_FIELD_NAME, coverageBuilder, true);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to write field " + COVERAGE_BUILDER_FIELD_NAME, e);
        }
    }

    public boolean isActivateCoverageBuilder() {
        return getCoverageBuilder() != null;
    }

    private CoverageBuilder getCoverageBuilder() {
        try {
            return (CoverageBuilder) FieldUtils.readField(this, COVERAGE_BUILDER_FIELD_NAME, true);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to read field " + COVERAGE_BUILDER_FIELD_NAME, e);
        }
    }

}
