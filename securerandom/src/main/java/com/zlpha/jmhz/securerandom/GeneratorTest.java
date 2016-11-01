package com.zlpha.jmhz.securerandom;

import org.openjdk.jmh.annotations.*;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SampleTime)
@Warmup(iterations = 5)
@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(24)
@State(Scope.Benchmark)
public class GeneratorTest {

    private SecureRandom random;

    @Setup(Level.Trial)
    public void setup() {
        random = new SecureRandom();
    }

    @Benchmark
    public long randomWithNative() {
        return random.nextLong();
    }

    @Benchmark
    @Fork(jvmArgsAppend = "-Djava.security.egd=file:/dev/./urandom")
    public long randomWithSHA1() {
        return random.nextLong();
    }
}
