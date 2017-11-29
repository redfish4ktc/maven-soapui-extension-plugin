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

package org.ktc.soapui.maven.extension;

import static org.ktc.soapui.maven.extension.impl.runner.wrapper.SoapUITestCaseRunnerWrapper.newSoapUITestCaseRunnerWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.DirectoryScanner;

import com.eviware.soapui.tools.SoapUITestCaseRunner;

public class TestMultiMojo extends TestMojo {

    // use array to support maven 2.2.1
    private ProjectFilesScan[] projectFiles;
    private boolean useOutputFolderPerProject;
    private boolean failBuildIfSomeTestFail;

    @Override
    protected void performRunnerExecute() throws MojoExecutionException, MojoFailureException {
        List<File> resolvedProjectFiles = resolveProjectFiles();
        List<File> failedProjects = new ArrayList<File>();
        for (File currentProjectFile : resolvedProjectFiles) {
            try{
            	configureAndRun(newSoapUITestCaseRunnerWrapper(runnerType), currentProjectFile.getAbsolutePath());
            }catch (MojoFailureException e){
            	if (failBuildIfSomeTestFail){
            		failedProjects.add(currentProjectFile);
            	}else{
            		throw e;
            	}
            }
        }
        
        if(failBuildIfSomeTestFail && failedProjects.size()>0){
        	MultiMojoFailureException error = new MultiMojoFailureException(failedProjects); 
        	getLog().error(error);
        	throw error;
        }
    }

    @Override
    protected void configureOuputFolder(SoapUITestCaseRunner runner, String currentProjectFile) {
        // TODO manage already existing directory
        // TODO manage case where composite project path end with end separator
        // String projectFileName = FilenameUtils.normalizeNoEndSeparator(currentProjectFile);
        if (useOutputFolderPerProject) {
            String projectFileName = FilenameUtils.getBaseName(currentProjectFile);
            File projectOuputFolder = new File(outputFolder, projectFileName);
            runner.setOutputFolder(projectOuputFolder.getAbsolutePath());
        } else {
            super.configureOuputFolder(runner, currentProjectFile);
        }
    }

    private List<File> resolveProjectFiles() {
        List<File> resolved = new ArrayList<File>();
        for (ProjectFilesScan scan : projectFiles) {
            DirectoryScanner scanner = new DirectoryScanner();
            File baseDirectory = scan.baseDirectory;
            // TODO add FollowSymlink?
            scanner.setBasedir(baseDirectory);
            // null check to keep includes default ("**")
            if (scan.includes != null) {
                scanner.setIncludes(toArray(scan.includes));
            }
            scanner.setExcludes(toArray(scan.excludes));
            scanner.scan();

            for (String includedFile : scanner.getIncludedFiles()) {
                resolved.add(new File(baseDirectory, includedFile));
            }
            // composite project are directories
            for (String includedDirectory : scanner.getIncludedDirectories()) {
                resolved.add(new File(baseDirectory, includedDirectory));
            }
        }
        return resolved;
    }

    private static String[] toArray(Set<String> set) {
        return set == null ? null : set.toArray(new String[set.size()]);
    }

    public static class ProjectFilesScan {
        public File baseDirectory;
        public Set<String> includes;
        public Set<String> excludes;
    }

}
