package xyz.avli.jmac;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MACTest {

    private final static PrintStream originalOut = System.out;
    private final static ByteArrayOutputStream testOut = new ByteArrayOutputStream();


    @Before
    public void patchOutput() {
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void restoreOutput() {
        System.setOut(originalOut);
    }

    @Test
    public void testOutput() {
        MAC.run();
        final String output = testOut.toString();
        assertEquals("popped 11\ndone\n", output);
    }
}