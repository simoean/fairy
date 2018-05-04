package co.simoes.fairy.controller;

import co.simoes.fairy.model.Length;
import co.simoes.fairy.model.Light;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static co.simoes.fairy.util.Time.sleep;

/**
 * This controller implements the logic of the 'colour' algorithm.
 *
 * @author Antonio Simoes
 */
@Service("colour")
public final class ColourController extends AbstractController {

    public ColourController(Length length) {
        this.length = length;
    }

    public ColourController() {
    }

    /**
     * Executes the sequencing algorithm until program is explicitly terminated.
     *
     * <p>All of the red lights are turned on for 1 second, then all the green
     * for 1 second then all the white for 1 second.</p>
     */
    @Override
    public void run() {
        while (true) {
            length.getColours()
                    .forEach(this::toggleLights);
        }
    }

    /*
     * Toggle lights of a colour on for one second.
     */
    private void toggleLights(String color) {
        length.filterLights(color)
                .forEach(Light::toggle);
        sleep(TimeUnit.SECONDS, 1);
        length.filterLights(color)
                .forEach(Light::toggle);
    }
}
