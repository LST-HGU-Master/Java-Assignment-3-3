import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220509)
 * @version (20230417)  supporting both println and print("\n") on Windows
 * @version (20261008)  revised
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
        Prog33.main(new String[]{"100"});

        String[] prints = bos.toString().replace("\r\n", "\n").split("\n");
        assertEquals(100, prints.length, "縦の行数が実行時引数で与えられた数値（N=100）と一致しません!");
    }

    @Test
    public void testNumColumns()
    {
        Prog33.main(new String[]{"130"});

        String[] prints = bos.toString().replace("\r\n", "\n").split("\n");
        assertTrue(prints.length > 0, "出力結果が空です。");
        assertEquals(130, prints[0].length(), "1行目の横の文字数が実行時引数で与えられた数値（N=130）と一致しません!");
    }

    @Test
    public void testNoAtmarkFirstLine()
    {
        Prog33.main(new String[]{"28"});

        String[] prints = bos.toString().replace("\r\n", "\n").split("\n");
        assertTrue(prints.length > 0, "出力結果が空です。");
        assertFalse(prints[0].contains("＠"), "四角形の一番上（1行目）に全角の「＠」が含まれています!");
        assertFalse(prints[0].contains("@"), "四角形の一番上（1行目）に半角の「@」が含まれています!");
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
            
        String[] prints = bos.toString().replace("\r\n", "\n").split("\n");

        assertEquals(expected.length, prints.length, "N=3 の場合の出力行数が3行になっていません。");

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], prints[i], 
                (i + 1) + "行目の出力が異なります（「＠」と「＊」は全角文字を使用し、1行目に「＠」が含まれないようにしてください）。"
            );
        }
    }
}
