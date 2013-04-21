/*
 * Copyright 2013 Thomas Bouffard (redfish4ktc)
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

log("Check that google-guava dependency is missing");
checker.assertLogFileContains("INFO  [log] END OF groovy script that needs google-guava to work");
checker.assertLogFileContains("ERROR [SoapUI] An error occured [com/google/common/base/Charsets], see error log for details");
log("The google-guava dependency is missing");

return true;
