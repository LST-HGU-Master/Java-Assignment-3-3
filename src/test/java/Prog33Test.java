import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import java.io.*;

public class Prog33Test {

    @Test
    public void testNumLines()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Prog33.main(new String[]{"100"});

        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals(100, prints.length);

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testNumColumns()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Prog33.main(new String[]{"130"});

        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals(130, prints[0].length());

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testNoAtmarkFirstLine()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Prog33.main(new String[]{"28"});

        // assertion
        String[] prints = bos.toString().split("\n");
        assertFalse(prints[0].contains("@"));

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testAllOutputs()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Prog33.main(new String[]{"3"});

        // assertion
        String[] expected = new String[]{
                "＊＊＊",
                "＠＊＊",
                "＠＠＊"
        };
        String[] prints = bos.toString().split("\n");
        assertEquals(expected[0], prints[0]);
        assertEquals(expected[1], prints[1]);
        assertEquals(expected[2], prints[2]);

        // undo the binding in System
        System.setOut(originalOut);
    }
}
