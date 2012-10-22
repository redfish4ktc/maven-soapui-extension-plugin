# Development todo list #

## Parent ##
- move to parent (version 3)
  - complier config
  - animalsniffer
  - maven minimum version
  - release must be done with maven 3 (see https://github.com/redfish4ktc/redfish4ktc-maven-parent/wiki#wiki-release)
     - configuration of enforcer must be different when performing release (use the release profile to check this)
     - doc (build will failed if maven 3.0.4+ is not used)


## Integration tests ##
  - all pom should use the project version as version (@project.version@)
  - avoid duplication in verify script (expecially log parsing): put duplicate code in a java class (see  
http://maven.apache.org/plugins/maven-invoker-plugin/examples/access-test-classes.html)
  - check if we need to force the "–Djava.headless=false" when running test


## Mojo implementation ##
test and mock goal does not declare mandatory parameter as required (see plugin.xml). Instead, null check is done in mojo implementation


### Invoker bug ###

With maven 2.2.1, mergeUserSettings option generates a NPE (java 5 and 6)  
For instance, see  https://redfish4ktc.ci.cloudbees.com/job/maven-soapui-extension-plugin_jdk6_maven221/1/console

```java
java.lang.NullPointerException
        at org.apache.maven.settings.SettingsUtils.merge(SettingsUtils.java:110)
        at org.apache.maven.plugin.invoker.AbstractInvokerMojo.runBuilds(AbstractInvokerMojo.java:1035)
        at org.apache.maven.plugin.invoker.AbstractInvokerMojo.execute(AbstractInvokerMojo.java:670)
        at org.apache.maven.plugin.DefaultPluginManager.executeMojo(DefaultPluginManager.java:490)
        at hudson.maven.agent.PluginManagerInterceptor.executeMojo(PluginManagerInterceptor.java:182)
```

I've filled an issue: http://jira.codehaus.org/browse/MINVOKER-137
An integration test that shows the problem already exists:  
http://svn.apache.org/viewvc/maven/plugins/tags/maven-invoker-plugin-1.7/src/it/settings-merge/pom.xml

A workaround has been done in our project (we do not use the mergeUserSettings parameter, a profile is defined in a super parent pom used by all pom in integration tests)



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


