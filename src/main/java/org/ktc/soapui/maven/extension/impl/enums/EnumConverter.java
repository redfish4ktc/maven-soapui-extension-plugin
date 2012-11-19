/*
 * Copyright 2012 Thomas Bouffard (redfish4ktc)
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

import org.apache.commons.lang.StringUtils;
import org.ktc.soapui.maven.extension.impl.ProjectConversionType;

// needed because maven 2.2.1 does not support enum parameter
// this let us provide convenient error message to end user (available enum values)
public class EnumConverter {

    public static ProjectConversionType toProjectConversionType(String value) {
        try {
            return ProjectConversionType.valueOf(value);
        } catch (RuntimeException e) {
            throw new UnknownEnumException("Unsupported project conversion type " + value + ". Valid values are "
                    + buildProjectConversionTypeListAsString() + "", e);
        }
    }

    private static String buildProjectConversionTypeListAsString() {
        return "[" + StringUtils.join(ProjectConversionType.values(), ',') + "]";
    }

}
