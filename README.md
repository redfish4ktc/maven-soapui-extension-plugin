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

Content
============

This plugin adds new features and bug fixes to the smartbear maven-soapui-pro-plugin/maven-soapui-plugin.  
For more information about how to use it, see the [wiki](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki).

Last released version: [4.5.1.0](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki#wiki-changelog) available on [Maven Central](http://search.maven.org/#search|gav|1|g%3A%22com.github.redfish4ktc.soapui%22%20AND%20a%3A%22maven-soapui-extension-plugin%22)


Main features
============

**Documentation**

  * give [tips](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips) about configuration expectially for [logs](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips#wiki-log-config)
  * provide full documentation of [goals and parameters](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals)


**New features**

  * 'test' goal
    * provide a new [test-verify](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#wiki-test-verify-goal) goal that let user run soapui tests, perform post processing tasks and then fail the build if tests failed. This is very usefull to run [multiple projects](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/TestGoalMultipleProjects)
    * add the `runnerType` parameter to choose to use the open source or pro runner (see [goal documentation](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#wiki-test-goal-add-param))

  * 'convert-project' goal
    * [convert-project](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Goals#wiki-convert-project-goal) convert composite to standard or standard


**Improvements**

  * 'test' goal
    * by default, logs are generated in a subdirectory of ${project.build.directory} see the [logs](https://github.com/redfish4ktc/maven-soapui-extension-plugin/wiki/Tips#wiki-log-config) documentation
   * do not display details of errors as exception stack trace to avoid flooding of the maven console, see [#2](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues/2)



**Bug fixes**

  * fix the 'groovy.log' bug, see [#6](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues/6)
  * do not display details of errors as exception stack trace to avoid flooding of the maven console, see [#2](https://github.com/redfish4ktc/maven-soapui-extension-plugin/issues/2)


Supported java and maven versions
============
  * maven 2.2.1 and 3.0.4
  * java 6 and 7 (soapui needs java 6+ starting from 4.0.0)

  
Build status
============ 
* [maven 3.0.4, oracle jdk6] Buildhive: [![Build Status](https://buildhive.cloudbees.com/job/redfish4ktc/job/maven-soapui-extension-plugin/badge/icon)](https://buildhive.cloudbees.com/job/redfish4ktc/job/maven-soapui-extension-plugin/)  
* [maven 3.0.4, openjdk6, openjdk7 and oracle jdk7] Travis: [![Build Status](https://secure.travis-ci.org/redfish4ktc/maven-soapui-extension-plugin.png?branch=master)](http://next.travis-ci.org/redfish4ktc/maven-soapui-extension-plugin)
* Coming soon [maven 2.2.1, oracle jdk6 and jdk7] Cloudbees + Jenkins Embeddable Build Status Plugin


License
============

maven-soapui-extension-plugin is licensed under the [Apache 2.0 software license](http://www.apache.org/licenses/LICENSE-2.0.html)
