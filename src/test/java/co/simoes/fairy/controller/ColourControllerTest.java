package co.simoes.fairy.controller;

import co.simoes.fairy.model.Length;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ColourControllerTest {

    private OutputStream console = null;

    @BeforeEach
    void captureConsoleOutput() {
        console = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console));
    }

    @Test
    void testWhiteLightIsNotToggled() {
        Length length = new Length(2, Arrays.asList("red", "green", "white"));
        Controller controller = new ColourController(length);

        controller.executeAlgorithm();

        assertTrue(console.toString().contains("red"));
        assertFalse(console.toString().contains("white"));
    }

    @Test
    void testMoreThanTwentyLights() {
        Length length = new Length(30, Arrays.asList("white", "green", "red"));
        Controller controller = new ColourController(length);

        controller.executeAlgorithm();

        assertTrue(console.toString().contains("Light 30"));
        assertFalse(console.toString().contains("Light 31"));
    }

    @Test
    void testDifferentColourOrder() {
        Length length = new Length(3, Arrays.asList("white", "green", "red"));
        Controller controller = new ColourController(length);

        controller.executeAlgorithm();

        assertTrue(console.toString().startsWith("Light 1 white"));
        assertFalse(console.toString().startsWith("Light 1 red"));
    }

    @Test
    void testAddingNewColours() {
        Length length = new Length(4, Arrays.asList("green", "pink", "white", "blue"));
        Controller controller = new ColourController(length);

        controller.executeAlgorithm();

        assertTrue(console.toString().contains("green"));
        assertTrue(console.toString().contains("pink"));
        assertTrue(console.toString().contains("white"));
        assertTrue(console.toString().contains("blue"));
        assertFalse(console.toString().contains("red"));
    }
}
