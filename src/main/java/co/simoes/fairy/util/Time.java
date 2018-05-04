package co.simoes.fairy.util;

import java.util.concurrent.TimeUnit;

/**
 * Time utility class, to improve code readability.
 *
 * @author Antonio Simoes
 */
public final class Time {

    /**
     * Helper method that blocks execution for the specified amount
     * of time.
     *
     * @param timeUnit Time unit (seconds, milliseconds, etc.)
     * @param amount   The amount of time units
     */
    public static void sleep(TimeUnit timeUnit, long amount) {
        try {
            timeUnit.sleep(amount);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
