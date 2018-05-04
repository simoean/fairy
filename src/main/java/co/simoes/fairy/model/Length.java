package co.simoes.fairy.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The length of fairy lights.
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
     * Number of lights in the length. Default: '20'
     */
    @Value("${length.lights:20}")
    private int numberOfLights;

    /**
     * Supported light colours. Default: 'red,green,white'
     */
    @Value("#{'${length.colours:red,green,white}'.split(',')}")
    private List<String> colours;

    /**
     *
     */
    public Length() {
    }

    /**
     * @param numberOfLights
     * @param colours
     */
    public Length(int numberOfLights, List<String> colours) {
        this.numberOfLights = numberOfLights;
        this.colours = colours;
    }

    /**
     * Initialize length.
     */
    @PostConstruct
    public void init() {
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
