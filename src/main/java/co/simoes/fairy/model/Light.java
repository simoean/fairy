package co.simoes.fairy.model;

/**
 * Light object representation.
 *
 * @author Antonio Simoes
 */
public class Light {

    /**
     * The light number in the length.
     */
    private final long number;

    /**
     * The colour of the light.
     */
    private final String colour;

    /**
     * Whether or not it is lit.
     */
    private boolean lit;

    /**
     * Light instance constructor.
     *
     * @param number The light's number
     * @param colour The light's colour
     */
    Light(long number, String colour) {
        this.number = number;
        this.colour = colour;
    }

    /**
     * Returns the light's colour.
     *
     * @return Light's colour
     */
    String getColour() {
        return colour;
    }

    /**
     * Returns the light state.
     *
     * @return Whether light is lit
     */
    boolean isLit() {
        return lit;
    }

    /**
     * Toggle the light on/off.
     */
    public void toggle() {
        this.lit = !this.lit;
        System.out.printf(
                "Light %d %s %s\n",
                number, colour,
                lit ? "on" : "off"
        );
    }
}
