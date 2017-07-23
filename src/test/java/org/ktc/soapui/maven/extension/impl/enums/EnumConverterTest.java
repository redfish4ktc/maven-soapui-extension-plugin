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

package org.ktc.soapui.maven.extension.impl.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import org.junit.Test;
import org.ktc.soapui.maven.extension.impl.ProjectConversionType;
import org.ktc.soapui.maven.extension.impl.RunnerType;

public class EnumConverterTest {

    @Test
    public void toProjectConversionType_valid_value() {
        assertThat(EnumConverter.toProjectConversionType("TO_COMPOSITE")).isEqualTo(ProjectConversionType.TO_COMPOSITE);
    }

    @Test
    public void toProjectConversionType_from_unknown_value() {
        try {
            EnumConverter.toProjectConversionType("un4658$$$");
            failBecauseExceptionWasNotThrown(UnknownEnumException.class);
        } catch (UnknownEnumException e) {
            assertThat(e.getMessage()).isEqualTo(
                    "Unsupported project conversion type un4658$$$. Valid values are [TO_STANDARD,TO_COMPOSITE]");
        }
    }

    @Test
    public void toRunnerType_valid_value() {
        assertThat(EnumConverter.toRunnerType("OSS")).isEqualTo(RunnerType.OSS);
    }

    @Test
    public void toRunnerType_from_unknown_value() {
        try {
            EnumConverter.toRunnerType("%%%oooo$$$");
            failBecauseExceptionWasNotThrown(UnknownEnumException.class);
        } catch (UnknownEnumException e) {
            assertThat(e.getMessage()).isEqualTo("Unsupported runner type %%%oooo$$$. Valid values are [OSS]");
        }
    }

}
