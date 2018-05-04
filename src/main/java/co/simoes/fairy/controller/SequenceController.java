package co.simoes.fairy.controller;

import co.simoes.fairy.model.Length;
import co.simoes.fairy.model.Light;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static co.simoes.fairy.util.Time.sleep;

/**
 * This controller implements the logic of the 'sequence' algorithm.
 *
 * @author Antonio Simoes
 */
@Service("sequence")
public final class SequenceController extends AbstractController {

    public SequenceController(Length length) {
        this.length = length;
    }

    public SequenceController() {
    }

    /**
     * Executes the sequencing algorithm until explicitly terminated.
     *
     * <p>Each light is turned on for 0.5 seconds then off in turn from first to last.</p>
     */
    @Override
    public void run() {
        while (true) {
            length.getLights()
                    .forEach(this::toggleLights);
        }
    }

    /*
     * Toggle each light on for half a second, in order.
     */
    private void toggleLights(Light light) {
        light.toggle();
        sleep(TimeUnit.MILLISECONDS, 500);
        light.toggle();
    }
}
