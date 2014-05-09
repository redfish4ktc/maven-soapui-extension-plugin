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
import static groovy.io.FileType.FILES
import static org.assertj.core.api.Assertions.assertThat;
import static org.ktc.soapui.maven.invoker.util.Check.*;

CheckBuildLog checker = new CheckBuildLog(basedir);
checker.assertOssWarGeneratorHasBeenUsed();

File buildDirectory = new File(basedir, "build");

log("Verifying that exploded war has been created in the default folder");
File explodedWarDirectory = new File(buildDirectory, "soapui/mock-as-war/explodedWar"); // we could also check header_logo.jpg
assertThat(explodedWarDirectory).exists();

log("Verifying that no war file has been generated");
def foundWars = new ArrayList<String>()
buildDirectory.eachFileRecurse(FILES) {
    if(it.name.endsWith('.war')) {
        foundWars.add("${it.absolutePath}");
    }
}
assertThat(foundWars).describedAs("Should not have found wars").isEmpty()

return true;
