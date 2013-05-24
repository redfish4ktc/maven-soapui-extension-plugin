/*
 * Copyright 2012-2013 Thomas Bouffard (redfish4ktc)
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
log("Verifying signing issue");
checker.assertLogFileContains("class \"com.eviware.soapui.SoapUICore\"'s signer information does not match signer information of other classes in the same package");
log("Signing issue checked");

return true;
