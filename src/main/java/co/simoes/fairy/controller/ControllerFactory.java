package co.simoes.fairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The {@code ControllerFactory} provides a factory method to return a controller
 * given an input algorithm.
 *
 * @author Antonio Simoes
 */
@Component
public class ControllerFactory {

    /**
     * Map holding a reference to available algorithms.
     */
    private final Map<String, Controller> controllers = new HashMap<>();

    /**
     * Populates the map of controllers.
     *
     * @param sequenceController  Sequence Controller instance
     * @param colourController    Colour Controller instance
     * @param alternateController Alternate Controller instance
     */
    @Autowired
    public ControllerFactory(@Qualifier("sequence") Controller sequenceController,
                             @Qualifier("colour") Controller colourController,
                             @Qualifier("alternate") Controller alternateController) {
        controllers.put("sequence", sequenceController);
        controllers.put("colour", colourController);
        controllers.put("alternate", alternateController);
    }

    /**
     * Returns an instance of the algorithm controller requested.
     *
     * @param algorithm the qualifier
     * @return An instance of {@link Controller}
     */
    public Optional<Controller> getController(String algorithm) {
        try {
            return Optional.of(
                    controllers.get(algorithm)
            );
        } catch (NullPointerException npe) {
            System.out.printf("No such algorithm [%s]\n\n", algorithm);
        }
        return Optional.empty();
    }
}
