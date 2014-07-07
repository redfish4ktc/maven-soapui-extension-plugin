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

maven-soapui-extension-plugin
============

## 4.6.2.x VERSION MAINTENANCE BRANCH

This plugin adds new features and bug fixes to SmartBear soapui-pro-maven-plugin/soapui-maven-plugin.
For more information about how to use it, see the [wiki](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki).

Last released version: [4.6.2.0](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki#wiki-changelog) available on [Maven Central](http://search.maven.org/#search|gav|1|g%3A%22com.github.redfish4ktc.soapui%22%20AND%20a%3A%22maven-soapui-extension-plugin%22)


Main features
============

**Documentation**

  * give [tips](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips) about configuration expectially for [logs](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips#wiki-log-config)
  * provide full documentation of [goals and parameters](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals)


**New features**

  * `convert-project` additional goal
    * [convert-project](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#wiki-convert-project-goal) converts composite to standard projects or standard to composite projects
  * [`mock` goal](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#wiki-mock-goal-add-param)
    * the `runnerType` parameter lets choose to use the open source or pro runner
  * `mock-as-war` additional goal
    * [mock-as-war](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/MockAsWarGoal) generates war file (and/or exploded war) that contains the mockservices defined in the SoapUi project as this can be done from the GUI
  * [`test` goal](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#wiki-test-goal-add-param)
    * the `junitHtmlReport` parameter lets disable junit html report generation when using the pro runner
    * the `runnerType` parameter lets choose to use the open source or pro runner 
    * the `testsuiteProperties` parameter lets override custom properties in test suites
    * configure the JunitReportCollector to be able to modify xml junit files generation 
  * `test-verify` additional goal
    * [test-verify](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#wiki-test-verify-goal) lets user run soapui tests, perform post processing tasks and then fail the build if some tests have failed. This is very usefull to run [multiple projects](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/TestGoalMultipleProjects)


**Improvements**

  * 'test' goal
    * by default, logs are generated in a subdirectory of ${project.build.directory} see the [logs](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips#wiki-log-config) documentation
    * do not display details of errors as exception stack trace to avoid flooding of the maven console, see [#2](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues/2)
  * 'mock' goal
    * by default, logs are generated in a subdirectory of ${project.build.directory} see the [logs](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips#wiki-log-config) documentation


**Bug fixes**

  * starting from soapui 3.6.1, almost all SmartBear plugin versions have missing dependencies. This is fixed in maven-soapui-extension-plugin
  * fix the 'groovy.log' bug, see the [logs](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips#wiki-log-config) documentation
  * 'mock' goal
    * make the 'skip' parameter work, see [#35](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues/35)


**Tests**

SmartBear maven plugins have almost no tests. Have a look on the [soapui-maven-plugin-tester](https://github.com/SmartBear/soapui/tree/master/soapui-maven-plugin-tester).

maven-soapui-extension-plugin has both unit tests and high-level tests. These high-level tests are

  * executed with the maven-invoker-plugin, this means that these tests are runned with maven plugins on real soapui projects
  * created to show bug or missing feature in SmartBear implementations
  * created to show fix, improvement or feature in maven-soapui-extension-plugin



Supported java and maven versions
============
  * maven 2.2.1, 3.0.x (tested with 3.0.4 and 3.0.5) and 3.1.x (tested with 3.1.0 and 3.1.1)
  * java 6 and 7 (soapui needs java 6+ starting from 4.0.0), java 8 experimental support


License
============

maven-soapui-extension-plugin is licensed under the [Apache 2.0 software license](http://www.apache.org/licenses/LICENSE-2.0.html)
