import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220509)
 **/
public class Prog33Test {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;

    @BeforeEach
    void before() {
        //back up binding
        originalIn  = System.in;
        originalOut = System.out;
        //modify binding
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        
        in = new StandardInputStream();
        System.setIn(in);
    }
    
    @AfterEach
    void after() {
       System.setOut(originalOut);
       System.setIn(originalIn);
    }
    
    @Test
    public void testNumLines()
    {
        // action
        Prog33.main(new String[]{"100"});

        // assertion
        String[] prints = bos.toString().split(System.lineSeparator());
        try {
            assertEquals(100, prints.length,"縦の文字数が実行時引数で与えられた正の数と一致しません!");
        } catch (AssertionError err) {
            after();
            throw err;
        }
    }

    @Test
    public void testNumColumns()
    {
        // action
        Prog33.main(new String[]{"130"});

        // assertion
        String[] prints = bos.toString().split(System.lineSeparator());
        try {
            assertEquals(130, prints[0].length(),"横の文字数が実行時引数で与えられた正の数と一致しません!");
        } catch (AssertionError err) {
            after();
            throw err;
        }        
    }

    @Test
    public void testNoAtmarkFirstLine()
    {
        // action
        Prog33.main(new String[]{"28"});

        // assertion
        String[] prints = bos.toString().split(System.lineSeparator());
        try {
            assertFalse(prints[0].contains("＠"),"四角形の一番上に＠が含まれています!");
            assertFalse(prints[0].contains("@"),"四角形の一番上に半角@が含まれています!"); //just in case            
        } catch (AssertionError err) {
            after();
            throw err;
        }        
    }

    @Test
    public void testAllOutputs()
    {
        // action
        Prog33.main(new String[]{"3"});

        // assertion
        String[] expected = new String[]{
                "＊＊＊",
                "＠＊＊",
                "＠＠＊"
        };
        String[] prints = bos.toString().split(System.lineSeparator());
        try {
            assertEquals(expected[0], prints[0]);
            assertEquals(expected[1], prints[1]);
            assertEquals(expected[2], prints[2]);
        } catch (AssertionError err) {
            after();
            AssertionError asErr = new AssertionError("＠と＊はどちらも全角文字であることが必要です(最初の行に＠があってはいけません) !");
            throw asErr;
        }
    }
}
