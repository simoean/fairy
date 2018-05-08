package co.simoes.fairy.controller;

import co.simoes.fairy.model.Length;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AlternateControllerTest {

    private OutputStream console = null;

    @BeforeEach
    void captureConsoleOutput() {
        console = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console));
    }

    @Test
    void testBothControllersExecute() {
        final Length length = new Length(4, Arrays.asList("red", "green"));
        final Controller sequenceController = new SequenceController(length);
        final Controller colourController = new ColourController(length);
        final Controller controller = new AlternateController(sequenceController, colourController, length);

        controller.executeAlgorithm();

        assertTrue(console.toString().contains("Light 1 red on\nLight 1 red off\nLight 2 green on"));
        assertTrue(console.toString().contains("Light 1 red on\nLight 3 red on"));
        assertTrue(console.toString().contains("Light 2 green on\nLight 4 green on"));
    }
}
