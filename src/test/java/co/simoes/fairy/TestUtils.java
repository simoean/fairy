package co.simoes.fairy;

import co.simoes.fairy.controller.Controller;
import co.simoes.fairy.util.Time;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TestUtils {

    public static void runController(Controller controller, long timeout) {
        final Future execution = Executors
                .newSingleThreadExecutor()
                .submit(controller);
        Time.sleep(TimeUnit.SECONDS, timeout);
        execution.cancel(true);
    }
}