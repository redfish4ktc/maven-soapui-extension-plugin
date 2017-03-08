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

package org.ktc.soapui.maven.extension.impl.runner.wrapper;

import com.smartbear.ready.cmd.runner.AbstractSoapUIRunner;
import org.ktc.soapui.maven.extension.impl.RunnerType;

public abstract class AbstractRunnerWrapper<R extends AbstractSoapUIRunner> {

    private R runner;
    private RunnerType runnerType;

    protected AbstractRunnerWrapper(R runner, RunnerType runnerType) {
        this.runner = runner;
        this.runnerType = runnerType;
    }

    public boolean isProRunner() {
        return runnerType.isProRunner();
    }

    public R getRunner() {
        return runner;
    }

}