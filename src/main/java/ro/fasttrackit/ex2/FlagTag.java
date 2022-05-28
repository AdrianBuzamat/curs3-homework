package ro.fasttrackit.ex2;


import java.time.Duration;

public enum FlagTag {
    RED(Duration.ofHours(0), Duration.ofHours(10)),
    YELLOW(Duration.ofHours(10), Duration.ofHours(30)),
    GREEN(Duration.ofHours(30), Duration.ofHours(Integer.MAX_VALUE));

    private final Duration low;
    private final Duration max;

    FlagTag(Duration low, Duration max) {
        this.low = low;
        this.max = max;
    }
}
