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

import org.ktc.soapui.maven.invoker.util.*;
import static org.ktc.soapui.maven.invoker.util.Check.*;

CheckBuildLog checker = new CheckBuildLog(basedir);
checker.assertOssTestRunnerHasBeenUsed();

// sucessfull "project 1" has runned
checker.assertLogFileContains("Project [simple-sucess-1] finished with status [FINISHED]");
// sucessfull "project 2" has runned
checker.assertLogFileContains("Project [simple-sucess-2] finished with status [FINISHED]");

// Groovy logs
checker.assertLogFileContainsStrictly("INFO  [log] I am project 1 and I succeed", 1);
checker.assertLogFileContainsStrictly("INFO  [log] I am project 2 and I succeed", 1);

return true;
