package co.simoes.fairy.controller;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

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
     * Application context.
     */
    private final ApplicationContext context;

    /**
     * Injects the application context.
     *
     * @param context Spring application context
     */
    @Autowired
    public ControllerFactory(ApplicationContext context) {
        this.context = context;
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
                    (Controller) context.getBean(algorithm)
            );
        } catch (NoSuchBeanDefinitionException e) {
            System.out.printf("No such algorithm [%s]\n\n", algorithm);
        }
        return Optional.empty();
    }
}
