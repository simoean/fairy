package co.simoes.fairy.controller;

import co.simoes.fairy.model.Length;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    Length length;
}
