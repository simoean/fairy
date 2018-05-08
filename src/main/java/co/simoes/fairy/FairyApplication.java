package co.simoes.fairy;

import co.simoes.fairy.controller.Controller;
import co.simoes.fairy.controller.ControllerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h1>Fairy Lights Controller</h1>
 *
 * <p>This program controls a length of fairy lights given an algorithm as an input.
 * It will execute the algorithm provided until expressly terminated.</p>
 *
 * <h3>Controller algorithms</h3>
 * <p>The available algorithms are included in the {@code controller} package and
 * implement the {@link Controller} interface. The {@link ControllerFactory} is
 * responsible for returning the requested algorithm.</p>
 *
 * <h3>Length specification</h3>
 * <p>Classes modelling the length of fairy lights are contained in the
 * {@code model} package.</p>
 *
 * <h3>Parametrisation (application.properties)</h3>
 * <p>To change the number of lights in the length, set the new value of
 * the {@code length.lights} property.</p>
 * <p>To add new light colours, or change its order in the length, modify
 * the {@code length.colours} property.</p>
 *
 * @author Antonio Simoes
 */
@SpringBootApplication
public class FairyApplication implements CommandLineRunner {

    /**
     * Controller factory.
     */
    private final ControllerFactory controllerFactory;

    /**
     * Injects the controller factory.
     */
    @Autowired
    public FairyApplication(ControllerFactory controllerFactory) {
        this.controllerFactory = controllerFactory;
    }

    /**
     * Program entry point.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(FairyApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0) {
            controllerFactory
                    .getController(args[0])
                    .ifPresent(Controller::run);
        }
        printUsage();
    }

    /*
     * Prints correct program usage. New sequencing algorithms
     * should be added here.
     */
    private void printUsage() {
        final StringBuilder usage = new StringBuilder();

        usage.append("Usage:");
        usage.append("\n  java -jar $PATH/FairyApplication.jar [algorithm]\n");

        usage.append("\nAlgorithms:");
        usage.append("\n  sequence    Each light is turned on for 0.5 seconds then off in turn from first to last");
        usage.append("\n  colour      All of the red lights are turned on for 1 second, then all the green for 1 second then all the white for 1 second");
        usage.append("\n  alternate   The 'sequence' algorithm runs for 30 seconds, then the 'colour' algorithm for 30 seconds");

        System.out.println(usage);
    }
}
