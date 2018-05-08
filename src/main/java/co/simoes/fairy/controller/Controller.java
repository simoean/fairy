package co.simoes.fairy.controller;

/**
 * A {@code Controller} is responsible for executing a sequencing algorithm to
 * control a length of Fairy Lights.
 *
 * @author Antonio Simoes
 */
public interface Controller extends Runnable {

    /**
     * Launches the sequencing algorithm execution until explicitly terminated.
     */
    @Override
    void run();

    /**
     * Executes the sequencing algorithm.
     */
    void executeAlgorithm();
}
