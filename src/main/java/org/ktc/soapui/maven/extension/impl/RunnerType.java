/*
 * Copyright 2012-2014 Thomas Bouffard (redfish4ktc)
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

package org.ktc.soapui.maven.extension.impl;

import com.smartbear.ready.cmd.runner.SoapUIMockAsWarGenerator;
import com.smartbear.ready.cmd.runner.SoapUIMockServiceRunner;
import com.smartbear.ready.cmd.runner.SoapUITestCaseRunner;
import com.smartbear.ready.cmd.runner.pro.SoapUIProMockAsWarGenerator;
import org.ktc.soapui.maven.extension.impl.runner.*;

public enum RunnerType {
    PRO {
        @Override
        public SoapUITestCaseRunner newTestRunner() {
            return new SoapUIProExtensionTestCaseRunner("SoapUI Pro Maven2 TestCase Runner");
        }

        @Override
        public SoapUIMockServiceRunner newMockRunner() {
            return new SoapUIProExtensionMockServiceRunner("SoapUI Pro Maven2 MockService Runner");
        }

        @Override
        public SoapUIMockAsWarGenerator newMockAsWarGenerator() {
            // currently no constructor with String arguments
            return new SoapUIProMockAsWarGenerator();
        }

        @Override
        public boolean isProRunner() {
            return true;
        }

    },
    OSS {
        @Override
        public SoapUITestCaseRunner newTestRunner() {
            return new SoapUIExtensionTestCaseRunner("SoapUI Maven2 TestCase Runner");
        }

        @Override
        public SoapUIMockServiceRunner newMockRunner() {
            return new SoapUIExtensionMockServiceRunner("SoapUI Maven2 MockService Runner");
        }

        @Override
        public SoapUIMockAsWarGenerator newMockAsWarGenerator() {
            return new SoapUIExtensionMockAsWarGenerator("SoapUI Maven2 MockAsWar Generator");
        }

        @Override
        public boolean isProRunner() {
            return false;
        }

    };

    public abstract SoapUITestCaseRunner newTestRunner();

    public abstract SoapUIMockServiceRunner newMockRunner();

    public abstract SoapUIMockAsWarGenerator newMockAsWarGenerator();

    public abstract boolean isProRunner();
}
