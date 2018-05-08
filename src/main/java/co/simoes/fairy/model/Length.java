package co.simoes.fairy.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The length of fairy lights.
 *
 * <p>Unless present in application properties, by default the length is
 * initialised with 20 lights, alternating the colours red, green and white,
 * in this order.</p>
 *
 * @author Antonio Simoes
 */
@Component
public class Length {

    /**
     * List of lights composing the length.
     */
    private final List<Light> lights = new ArrayList<>();

    /**
     * Supported light colours. Default: 'red,green,white'
     */
    private final List<String> colours;

    /**
     * Length constructor, initialises the length of fairy lights.
     *
     * @param numberOfLights Number of lights in the length
     * @param colours The colours of lights
     */
    @Autowired
    public Length(@Value("${length.lights:20}") int numberOfLights,
                  @Value("#{'${length.colours:red,green,white}'.split(',')}") List<String> colours) {
        this.colours = colours;
        for (int number = 0; number < numberOfLights; number++) {
            lights.add(
                    new Light(number + 1, colours.get(number % colours.size()))
            );
        }
    }

    /**
     * Returns the length's lights.
     *
     * @return Length's lights
     */
    public List<Light> getLights() {
        return lights;
    }

    /**
     * Turns off the length's lights which are lit.
     */
    public void resetLights() {
        lights.stream()
                .filter(Light::isLit)
                .forEach(Light::toggle);
    }

    /**
     * Returns the length's lights filtered by colour.
     *
     * @return Length's lights of a colour
     */
    public List<Light> filterLights(String colour) {
        return lights.stream()
                .filter(light -> colour.equals(light.getColour()))
                .collect(Collectors.toList());
    }

    /**
     * Returns the length's light colours.
     *
     * @return Length's light colours
     */
    public List<String> getColours() {
        return colours;
    }
}
