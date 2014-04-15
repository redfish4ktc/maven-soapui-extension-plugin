<!--
~ Copyright 2012-2013 Thomas Bouffard (redfish4ktc)
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

Here is a list of tasks/ideas that will be implemented in the future.

## Experiments ##

* remove null check in mojo
  * in all mojo, null checks are done before setting parameters to the related runner
  * runners are supposed to deal with this
  * so test removing the supposed extra null checks and run the integration tests
  * if ok, create an issue (type: task) for all mojo


## Integration tests ##

  - poms
    - all poms should use the project version as version (@project.version@)
    - add intermediate parent pom to avoid duplication for a given tested feature
    - create a parent pom for all tests. It should depends on redfish4ktc parent to set plugin versions
  - migrate verify scripts to groovy because groovy provides more feature than beanshell
    - should be documented in the development wiki page
    - modify the invoker plugin to only support groovy scripts (it currently accept groovy and beanshell)
    - use common code already available (java invoker util code) to avoid duplication (especially log parsing)
    - always check the runner type used in the tests (available in java invoker util code)
  - invoker plugin configuration
    - check if we need to force the "–Djava.headless=false" when running test
    - remove the `invokerParallelThreads` property in the pom.xml file. Overriding the invoker plugin parameter should be done by passing `-Dinvoker.parallelThreads` parameter  - remove duplication in soapui configuration (settings, projects)
  - add tests with composite projects for all features using the pro runner
  - ```test``` goal
    - default log directory: also check they are no log file in base directory (cf related test of convert-project goal)
    - check the globalProperties bug with maven plugin (http://www.soapui.org/forum/viewtopic.php?f=2&t=8210&p=23818&hilit=maven+globalproperties#p23818). May affect only 4.0.0 version
  - pretty print project. See http://www.soapui.org/Working-with-Projects/team-testing-support.html Tip: The “Pretty Print Project Files” setting in the global preferences WSDLS Settings tab will make merging a single project file substantially easier (but will also increase the size of the project file).

  

## POM ##

  - manifest files
    - javadoc jar: add info in the manifest file (same as in the regular jar)
    - sources jar: same as for the javadoc jar
    - move all these configuration in the parent pom 


## Mojo implementation ##
  - test and mock goal does not declare mandatory parameter as required (see plugin.xml). Instead, null check is done in mojo implementation
  - Check parameters that should be declared as File instead of String
    - because of this, multimodule builds fail when the parameters are set with  relative path
    - found more information in the soapui forums and in my internal archives (expecially, this problem is documented in the developer maven plugin documentation
    - create an integration test to reproduce this issue
    - create an issue
    - documentation (Tips and Goals pages)
      - inform the users they should better use absolute path
      - document the issue for SmartBear implementation
  - plugin.xml: test goal should not declare a 'project' parameter, it should only be configured in the configuration section
  - test-multi
    - 1st implementation rely on plexus-utils (for the DirectoryScanner). See use of maven-shared-utils instead

## Custom runners implementation ##
  - constructors without argument should call the super constructor with a `null` title parameter. Otherwise, a default name is used and a trace is displayed in the standard output. This currently only impacts unit tests because runner used in mojo are always built using a custom title
  - warn: some pro runner may have only a constructor without argument



### Documentation ###

**Tips page**

* document how to configure scripts and ext directory
* talk about default value (everything in basedir)
* put the 'Avoid log warnings at startup' section just before logs tips + modify link/text according to the new documentation about scripts and ext
* log4j configuration: document the java system property to set an alternative path to the configuration file + add an integration test

**Test strategy**

The developer documentation should also point on this

* mostly with the invoker plugin
* i use groovy test step and check logs in output (explain why)
* technical code is stored in java utility class to avoid duplication across script

**Contributing**

What

* existing issue: add a comment to notify me about your starting work or if you have any question
* pick up idea from this todo file: create a new issue to notify me

How

* use pull requests (please create a feature branch from master)
* if it fixes an issue in the SmartBear implementation, create integration tests that show the issue (in oss and pro runners if needed)
* create tests (at least integration plugin tests) to show the new feature or the fixed bug


**Goal implementation status**

* currently, some goals are not implemented directly, we rely on SmartBear implementation
* create a table to describe what is implemented
  * which goal
  * from which version 
  * pro/oss support (from which version)
* tool goal:
  * should be remove in the future as it rely on external tools.
  * Instead, create a goal to export the wsdl and directly use maven plugin provided by each tool (cxf, axis, axis2, ...)
* describe the plugin.xml hack (because smartbear impl does not use maven plugin annotation so cannot extends the plugin directly)

**Goal page**

* add an entry for the "tool" goal and mark it as "to be removed" (see above)
* mock goal
  * possible missing options (no documented by Smartbear or not implemented)
  * the following are available when running the command line
    * f : Sets the output folder to export results to ( soapUI Pro only )
    * o : Opens the Coverage Report in a browser (with the -g option) ( soapUI Pro only )
    * g : Sets the output to include Coverage HTML reports ( soapUI Pro only )
  * if not implemented, create issues (no need to open the coverage report)


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


### New Features ###

New issue needs to be created

**mock-as-war goal**

We will already have a "include lib in ext folder" parameter.  
This will be nice to be able to declare maven dependencies to be added instead of putting jar in the ext folder (current workaround: use dependency:copy first before calling the mock-as-war goal)  
The workaround need to be documented

**extract-wsdl**

Extract wsdl from soapui project

* avoid duplication: no need to store the wsdl both in the soapui project and in the build directory
* then can be use
* multi interface definition in a soapui project
  * add a parameter (in another issue
  * if not set, use the 1st interface found
* implem: the "tool" goal/utility may already perform this export to let external tool used the wsdl to generate client/service for instance)
* check if binding directory of composite projects is ok, see http://www.soapui.org/Working-with-Projects/team-testing-support.html

**test goal: testsuite properties**

With #38, we have introduced the new testsuiteproperties parameter to override/create properties in **ALL** test suite.  
This is the format that will let us extend this feature: override/create properties for a given test suite.

```xml
<testsuiteproperties>
  <properties>
    <property></property>
  </properties>
  <suites>
      <suite>
        <name></name>
        <properties>
          <property></property>
        </properties>
      </suite>
  </suites>
</testsuiteproperties>
```
