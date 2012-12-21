<!--
~ Copyright 2012 Thomas Bouffard (redfish4ktc)
~
~ Licensed under the Apache License, Version 2.0 (the "License");
~ you may not use this file except in compliance with the License.
~ You may obtain a copy of the License at
~
~   http://www.apache.org/licenses/LICENSE-2.0
~
~ Unless required by applicable law or agreed to in writing,
~ software distributed under the License is distributed on an
~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
~ KIND, either express or implied.  See the License for the
~ specific language governing permissions and limitations
~ under the License.
-->

# Development todo list #

## Parent ##
- move to parent
  - complier config
  - animalsniffer
  - maven minimum version


## Experiments ##
- check if it is possible to export wsdl/wadl from soapui project to external file
  - could avoid us to sync the wsdl file and the wsdl definition in soapui project
  - check if bindind directory of composite projects is ok, see http://www.soapui.org/Working-with-Projects/team-testing-support.html


## Integration tests ##
  - all pom should use the project version as version (@project.version@)
  - avoid duplication in verify script (expecially log parsing): put duplicate code in a java class (see  
http://maven.apache.org/plugins/maven-invoker-plugin/examples/access-test-classes.html)
  - check if we need to force the "–Djava.headless=false" when running test
  - remove duplication in soapui configuration (settings, projects)
  - add tests with composite projects
  - test goal default log directory: also check they are no log file in base directory (cf related test of convert-project goal)
  - pretty print project. See http://www.soapui.org/Working-with-Projects/team-testing-support.html Tip: The “Pretty Print Project Files” setting in the global preferences WSDS Settings tab will make merging a single project file substantially easier (but will also increase the size of the project file).


## Mojo implementation ##
  - test and mock goal does not declare mandatory parameter as required (see plugin.xml). Instead, null check is done in mojo implementation
  - Check parameters that should be declared as File instead of String
  - plugin.xml: test goal should not declare a 'project' parameter, it should only be configured in the configuration section


### Documentation ###

**Tips page**

* document how to configure scripts and ext directory
* talk about default value (everything in basedir)
* put the 'Avoid log warnings at startup' section just before logs tips + modify link/text according to the new documentation about scripts and ext


### Have a look on invoker goal parameters ###
see http://maven.apache.org/plugins/maven-invoker-plugin/run-mojo.html


invoker.maven.version = 2.0.10+, !2.1.0, !2.2.0

cloneClean  
boolean	1.6	Ensure the cloneProjectsTo directory is not polluted with files from earlier invoker runs.  
Default value is: false.

showErrors  
boolean	1.0	Whether to show errors in the build output.  
Default value is: false.  

streamLogs
boolean	1.0	Flag used to determine whether the build logs should be output to the normal mojo log.  
Default value is: false.

reportsDirectory  
File	1.4	Base directory where all build reports are written to. Every execution of an integration test will produce an XML file which contains the information about success or failure of that particular build job. The format of the resulting XML file is documented in the given build-job reference.  
Default value is: ${project.build.directory}/invoker-reports.


