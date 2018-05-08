package co.simoes.fairy.controller;

import co.simoes.fairy.model.Length;

/**
 * Abstract class holding a {@code Length} instance, which is shared
 * among subclasses.
 *
 * @author Antonio Simoes
 */
public abstract class AbstractController implements Controller {

    /**
     * The length of Fairy Lights.
     */
    final Length length;

    /**
     * Abstract controller constructor, injects the length.
     *
     * @param length Length of fairy lights
     */
    public AbstractController(Length length) {
        this.length = length;
    }

    /**
     * Executes the sequencing algorithm until explicitly terminated.
     */
    @Override
    public void run() {
        while (true) {
            executeAlgorithm();
        }
    }
}
