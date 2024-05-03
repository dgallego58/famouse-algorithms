package co.com.dgallego58;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;

class BenchMarkRunnerTest {

    @Test
    void executeBenchmark() throws RunnerException {
        Options options = new OptionsBuilder()
                .include(BenchmarkAlgorithm.class.getSimpleName())
                .forks(1)
                .build();
        Collection<RunResult> run = new Runner(options).run();
        Assertions.assertFalse(run.isEmpty());
    }

}
