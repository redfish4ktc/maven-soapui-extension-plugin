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

package org.ktc.soapui.maven.extension.impl.report.coverage;

import com.eviware.soapui.impl.coverage.report.CoverageBuilder;
import java.util.ArrayList;
import java.util.Collection;

public class CoverageBuilderHolder {

    Collection<CoverageBuilderWrapper> builders = new ArrayList<CoverageBuilderHolder.CoverageBuilderWrapper>();

    public CoverageBuilder newCoverageBuilder(String ouputFolder, boolean generationDelegated) {
        CoverageBuilder coverageBuilder = new CoverageBuilder();
        if (generationDelegated) {
            builders.add(new CoverageBuilderWrapper(ouputFolder, coverageBuilder));
        }
        return coverageBuilder;
    }

    public void exportReports() {
        for (CoverageBuilderWrapper builder : builders) {
            builder.exportReport();
        }
    }

    private static class CoverageBuilderWrapper {

        private final String ouputFolder;
        private final CoverageBuilder coverageBuilder;

        public CoverageBuilderWrapper(String ouputFolder, CoverageBuilder coverageBuilder) {
            this.ouputFolder = ouputFolder;
            this.coverageBuilder = coverageBuilder;
        }

        public void exportReport() {
            coverageBuilder.exportReport(ouputFolder, false);
        }

    }

}
