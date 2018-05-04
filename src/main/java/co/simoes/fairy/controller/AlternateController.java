package co.simoes.fairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static co.simoes.fairy.util.Time.sleep;

/**
 * This controller implements the logic of the 'alternate' algorithm.
 *
 * @author Antonio Simoes
 */
@Service("alternate")
public final class AlternateController extends AbstractController {

    private final Controller sequenceController;
    private final Controller colourController;

    /**
     * Alternate controller constructor, injects other controllers.
     *
     * @param sequenceController The sequence controller
     * @param colourController The colour controller
     */
    @Autowired
    AlternateController(@Qualifier("sequence") Controller sequenceController,
                        @Qualifier("colour") Controller colourController) {
        this.sequenceController = sequenceController;
        this.colourController = colourController;
    }

    /**
     * Executes the sequencing algorithm until explicitly terminated.
     *
     * <p>The 'sequence' algorithm runs for 30 seconds, then the 'colour' algorithm
     * for 30 seconds</p>
     */
    @Override
    public void run() {
        while (true) {
            runController(sequenceController);
            runController(colourController);
        }
    }

    /*
     * Runs controller for 30 seconds, then resets lights.
     */
    private void runController(Controller controller) {
        final Future execution = Executors
                .newSingleThreadExecutor()
                .submit(controller);

        sleep(TimeUnit.SECONDS, 30);
        execution.cancel(true);

        length.resetLights();
    }
}
