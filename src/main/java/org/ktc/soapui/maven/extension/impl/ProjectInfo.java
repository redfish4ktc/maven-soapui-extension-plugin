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

package org.ktc.soapui.maven.extension.impl;

import java.io.IOException;
import java.util.jar.Manifest;

public class ProjectInfo {

    private static Manifest manifest = null;

    static {
        try {
            manifest = new Manifest(ProjectInfo.class.getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF"));
        } catch (IOException e) {
            System.err.println("[maven-soapui-extension] unable to load the project manifest file");
            e.printStackTrace();
        }
    }

    public static String getName() {
        return ProjectInfo.class.getPackage().getImplementationTitle();
    }

    private static String getVersion() {
        return ProjectInfo.class.getPackage().getImplementationVersion();
    }

    public static String getFullVersion() {
        return getVersion() + " (" + getValueOf("Project-build-scm-changeset") + "; " + getValueOf("Project-build-date") + ")";
    }
    
    public static String getSoapuiVersion() {
        return getValueOf("Soapui-version");
    }

    private static String getValueOf(String key) {
        try {
            return manifest.getMainAttributes().getValue(key);
        } catch (RuntimeException e) {
            return key;
        }
    }

}
