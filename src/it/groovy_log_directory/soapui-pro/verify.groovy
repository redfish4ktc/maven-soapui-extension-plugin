/*
 * Copyright 2012-2014 Thomas Bouffard (redfish4ktc)
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

import org.ktc.soapui.maven.invoker.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ktc.soapui.maven.invoker.util.Check.*;

log("Verifying groovy log file");
CheckBuildLog buildLogChecker = new CheckBuildLog(basedir);
buildLogChecker.assertSmartBearProTestRunnerHasBeenUsed();

CheckLog groovyLogChecker = new CheckLog(new File(basedir, "target/soapui/custom-logs/global-groovy.log"));
groovyLogChecker.assertLogFileContains("groovy script that does not need external files to work");

log("Groovy log file is OK");
