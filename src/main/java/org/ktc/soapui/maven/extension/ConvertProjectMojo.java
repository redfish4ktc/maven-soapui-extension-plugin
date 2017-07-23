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

package org.ktc.soapui.maven.extension;

import java.io.File;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ktc.soapui.maven.extension.impl.ProjectConversionType;
import org.ktc.soapui.maven.extension.impl.enums.EnumConverter;

import com.eviware.soapui.impl.wsdl.WsdlProject;

public class ConvertProjectMojo extends AbstractSoapuiMojo {
    private File inputProject;
    private File outputProject;
    private String conversionType;

    @Override
    public void performExecute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Converting project " + conversionType);
        getLog().info("from " + inputProject);
        getLog().info("to " + outputProject);

        ProjectConversionType conversionTypeEnum = EnumConverter.toProjectConversionType(conversionType);
        
        // needed because soapui does not create missing directories
        outputProject.getParentFile().mkdirs();

        // soapui bug : try to use the saveIn method, so project should only be saved in ouputProject but all opened
        // projects are saved when closing soapui
        // see http://www.soapui.org/forum/viewtopic.php?f=4&t=15631
        // maybe this is fixed by providing settings file (auto-save option in the global UI-Settings which will save
        // all open projects when exiting, is that what you are looking for?)
        try {
            WsdlProject wsdlProject = new WsdlProject(inputProject.getAbsolutePath());
            wsdlProject.saveAs(outputProject.getAbsolutePath());
        } catch (Exception e) {
            throw new MojoFailureException("SoapUI has errors: " + e.getMessage(), e);
        }
    }
    
}
