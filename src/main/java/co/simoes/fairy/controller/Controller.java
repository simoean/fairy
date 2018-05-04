package co.simoes.fairy.controller;

/**
 * A {@code Controller} is responsible for executing a sequencing algorithm to
 * control a length of Fairy Lights.
 *
 * <p><b>Implementations must be registered within the {@link ControllerFactory}</b>,
 * more specifically added to the {@code controllers} map. Instances must be
 * {@code Autowired} in the class constructor.</p>
 *
 * @author Antonio Simoes
 */
public interface Controller extends Runnable {

    /**
     * Executes the sequencing algorithm until explicitly terminated.
     */
    @Override
    void run();

}
