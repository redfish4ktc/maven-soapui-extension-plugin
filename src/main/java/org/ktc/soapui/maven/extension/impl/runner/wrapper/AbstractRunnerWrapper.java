package org.ktc.soapui.maven.extension.impl.runner.wrapper;

import com.eviware.soapui.tools.AbstractSoapUIRunner;
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