<!--
~ Copyright 2012-2017 Thomas Bouffard (redfish4ktc)
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


UPDATE 2021-08-27: **This repository is now archived**. Thanks to all contributors and users of this plugin. \
I haven't used SoapUI since a long time and I no longer have time to maintain this plugin.



maven-soapui-extension-plugin [![Travis Build Status](https://secure.travis-ci.org/redfish4ktc/maven-soapui-extension-plugin.png?branch=master)](https://travis-ci.org/redfish4ktc/maven-soapui-extension-plugin)
============

This plugin adds new features and bug fixes to SmartBear plugins: soapui-pro-maven-plugin and soapui-maven-plugin.  
For more information about how to use it, see the [wiki](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki).

Last released version: [4.6.4.2](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki#changelog) (22-November-2014) available on [Maven Central](http://search.maven.org/#search|gav|1|g%3A%22com.github.redfish4ktc.soapui%22%20AND%20a%3A%22maven-soapui-extension-plugin%22)



News (06-March-2017)
============

**Note**: News archives are available in the [wiki](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki#news-archives).

This plugin is dormant but it may be updated in the future.  
Pull requests are welcomed and I still answer to new and pending issues.



Main features
============

**Documentation**

  * give [tips](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips) about maven plugin configuration (both for maven-soapui-extension-plugin and SmartBear plugins)
  * provide full documentation of [goals and parameters](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals)


**New features**

  * general
    * only one plugin for both SoapUI OSS and PRO support (SmartBear provides 2 plugin implementations)
  * `convert-project` additional goal
    * [convert-project](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#convert-project) converts composite to standard projects or standard to composite projects
  * [`mock` goal](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#mock-additional-parameters)
    * add several parameters to activate and control the coverage report generation when using the pro runner
    * the `runnerType` parameter lets choose to use the open source or pro runner
  * `mock-as-war` additional goal
    * [mock-as-war](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/MockAsWarGoal) generates war file (and/or exploded war) that contains the mockservices defined in the SoapUi project as this can be done from the GUI
  * [`test` goal](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#test-additional-parameters)
    * the `junitHtmlReport` parameter lets disable junit html report generation when using the pro runner
    * the `runnerType` parameter lets choose to use the open source or pro runner 
    * the `testsuiteProperties` parameter lets override custom properties in test suites
    * configure the JunitReportCollector to be able to modify xml junit files generation
  * `test-multi` additional goal
    * [test-multi](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/TestGoalMultipleProjects) allows to run several projects in the same plugin execution. Choice of projects to be runned is done by scanning one or several directories and selecting which projects to include/exclude 
  * `test-verify` additional goal
    * [test-verify](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#test-verify) lets user run soapui tests, perform post processing tasks and then fail the build if some tests have failed. This is very usefull to run [multiple projects](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/TestGoalMultipleProjects)


**Improvements**

  * [`test` goal](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#test)
    * by default, logs are generated in a subdirectory of ${project.build.directory} see the [logs](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips#log-configuration) documentation
    * do not display details of errors as exception stack trace to avoid flooding of the maven console, see [#2](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues/2)
  * [`mock` goal](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#mock)
    * by default, logs are generated in a subdirectory of ${project.build.directory} see the [logs](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips#log-configuration) documentation


**Bug fixes**

  * almost all SmartBear plugin versions have missing dependencies. This is fixed in maven-soapui-extension-plugin, see the dedicated [dependency issues](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/DependencyIssues) page
  * fix the 'groovy.log' bug even in pre SoapUI 5 versions, see the [logs](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips#log-configuration) documentation
  * [`mock` goal](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#mock)
    * make the 'skip' parameter work, see [#35](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues/35)
    * append groovy log messages only once in the console, see [#68](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues/68)
  * [`test` goal](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#test)
    * append groovy log messages only once in the console, see [#68](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues/68) 

**Tests**

SmartBear maven plugins have almost no tests. Have a look on the [soapui-maven-plugin-tester](https://github.com/SmartBear/soapui/tree/master/soapui-maven-plugin-tester).

maven-soapui-extension-plugin has both unit tests and high-level tests. These high-level tests are

  * executed with the maven-invoker-plugin, this means that these tests are runned with maven plugins on real soapui projects
  * created to show bug or missing feature in SmartBear implementations
  * created to show fix, improvement or feature in maven-soapui-extension-plugin



Roadmap
============

**Short term**

* support SoapUI 5.0.0, 5.1.0, 5.1.1 and 5.1.2
* improve the ```test-multi``` goal to run [multiple soapui projects](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/TestGoalMultipleProjects). See [opened issues](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues?labels=goal%3A%3Atest-multi&page=1&state=open)
* provide a new `JunitReportCollector` to have more details about failures (steps, assertions) in the junit report. See [#42](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues/42)


**Mid term**

* improve the ```mock-as-war``` goal for generating war from [mock services](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/MockAsWarGoal). See [opened issues](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues?labels=goal%3A%3Amock-as-war&milestone=&page=1&state=open)
* have a specific implementation for the ```security-test``` goal and implement improvements. See [opened issues](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues?labels=goal%3A%3Asecurity-test&milestone=&page=1&state=open)

**Long term**

* found a way to make report generation work without having a SoapUI installation (PRO feature)
* add a goal to export wsdl interface from a SoapUI project
* does not rely on SmartBear maven plugin



Supported java and maven versions
============
  * maven 2.2.1, 3.0.x (tested with 3.0.5), 3.1.x (tested with 3.1.1) and 3.2.x (tested with 3.2.1 and 3.2.3)
  * java 6 and 7 (soapui needs java 6+ as of version 4.0.0), java 8 experimental support


  
CI Build status
============

If it is not specified, the CI job

* only builds the master branch
* uses a shared local maven repository across builds
* is runned
  * once a day if code modification occurs
  * on Linux OS 


**List of CI jobs**

* maven 3.3.9, oracle jdk7 (CloudBees) [![Build Status](https://redfish4ktc-oss.ci.cloudbees.com/buildStatus/icon?job=maven-soapui-extension-plugin_maven-3.3.9_oracle_jdk7)](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-3.3.9_oracle_jdk7/)
* maven 3.2.3, openjdk6, openjdk7, oracle jdk7 and oraclejdk8 (Travis) [![Travis Build Status](https://secure.travis-ci.org/redfish4ktc/maven-soapui-extension-plugin.png?branch=master)](https://travis-ci.org/redfish4ktc/maven-soapui-extension-plugin) - builds all pushes in all branches and pull requests, uses a fresh maven local repository at each build
* maven 3.2.3, oracle jdk7, Windows OS (AppVeyor) [![Build status](https://ci.appveyor.com/api/projects/status/l2xyyxcy5ixt7fn9?svg=true)](https://ci.appveyor.com/project/redfish4ktc/maven-soapui-extension-plugin)
* maven 3.2.1, openjdk8 (CloudBees) [![CloudBees Build Status](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-3.2.1_openjdk8/badge/icon)](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-3.2.1_openjdk8/)
* maven 3.2.1, oracle jdk6 (CloudBees) [![CloudBees Build Status](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-3.2.1_oracle_jdk6/badge/icon)](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-3.2.1_oracle_jdk6/)
* maven 3.1.1, oracle jdk8 (CloudBees) [![Build Status](https://redfish4ktc-oss.ci.cloudbees.com/buildStatus/icon?job=maven-soapui-extension-plugin_maven-3.1.1_oracle_jdk8)](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-3.1.1_oracle_jdk8/)
* maven 3.1.1, oracle jdk7 (CloudBees) [![Build Status](https://redfish4ktc-oss.ci.cloudbees.com/buildStatus/icon?job=maven-soapui-extension-plugin_maven-3.1.1_oracle_jdk7)](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-3.1.1_oracle_jdk7/)
* maven 3.1.1, oracle jdk6 (CloudBees) [![Build Status](https://redfish4ktc-oss.ci.cloudbees.com/buildStatus/icon?job=maven-soapui-extension-plugin_maven-3.1.1_oracle_jdk6)](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-3.1.1_oracle_jdk6/)
* maven 3.0.5, oracle jdk8 (CloudBees) [![Build Status](https://redfish4ktc-oss.ci.cloudbees.com/buildStatus/icon?job=maven-soapui-extension-plugin_maven-3.0.5_oracle_jdk8)](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-3.0.5_oracle_jdk8/)
* maven 3.0.5, openjdk8 (CloudBees) [![CloudBees Build Status](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-3.0.5_openjdk8/badge/icon)](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-3.0.5_openjdk8/)
* maven 3.0.5, oracle jdk6 (CloudBees) [![Build Status](https://redfish4ktc-oss.ci.cloudbees.com/buildStatus/icon?job=maven-soapui-extension-plugin_maven-3.0.5_oracle_jdk6)](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-3.0.5_oracle_jdk6/)
* maven 2.2.1, openjdk8 (CloudBees) [![CloudBees Build Status](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-2.2.1_openjdk8/badge/icon)](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-2.2.1_openjdk8/)
* maven 2.2.1, oracle jdk6 (CloudBees) [![CloudBees Build Status](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-2.2.1_oracle_jdk6/badge/icon)](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-2.2.1_oracle_jdk6/)
* maven 2.2.1, oracle jdk7 (CloudBees) [![CloudBees Build Status](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-2.2.1_oracle_jdk7/badge/icon)](https://redfish4ktc-oss.ci.cloudbees.com/job/maven-soapui-extension-plugin_maven-2.2.1_oracle_jdk7/)


[![Built on CloudBees](http://www.cloudbees.com/sites/default/files/Button-Built-on-CB-1.png)](http://www.cloudbees.com/dev.cb)

[![Built on Travis](http://about.travis-ci.org/images/travis-mascot-200px.png)](https://travis-ci.org/)

<!--
http://about.travis-ci.org/images/travisci-small.png
-->

License
============

maven-soapui-extension-plugin is licensed under the [Apache 2.0 software license](http://www.apache.org/licenses/LICENSE-2.0.html)
