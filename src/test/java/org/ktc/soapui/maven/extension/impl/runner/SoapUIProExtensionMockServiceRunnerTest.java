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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SoapUIProExtensionMockServiceRunnerTest {
    
    @Test
    public void activateCoverageReport()  {
        SoapUIProExtensionMockServiceRunner runner = new SoapUIProExtensionMockServiceRunner(null);
        runner.activateCoverageReport(true);
        assertThat(runner.isActivateCoverageBuilder()).isTrue();
    }

}
