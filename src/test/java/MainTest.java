import org.junit.*;
import ru.liga.Main;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MainTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final static String DELIMITER = "\n";

    @After
    public void after() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void tomorrow_USD() {
        ByteArrayInputStream in = new ByteArrayInputStream("rate USD tomorrow".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
        Main.main(null);
        Assert.assertTrue(Math.abs(Double.parseDouble(out.toString()) - 66.53) < 0.01);
    }

    @Test
    public void tomorrow_TRY() {
        ByteArrayInputStream in = new ByteArrayInputStream("rate TRY tomorrow".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
        Main.main(null);
        Assert.assertTrue(Math.abs(Double.parseDouble(out.toString()) - 5.11) < 0.01);
    }

    @Test
    public void tomorrow_EVR() {
        ByteArrayInputStream in = new ByteArrayInputStream("rate EVR tomorrow".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
        Main.main(null);
        Assert.assertTrue(Math.abs(Double.parseDouble(out.toString()) - 72.08) < 0.01);
    }

    @Test
    public void week_USD() {
        ByteArrayInputStream in = new ByteArrayInputStream("rate USD week".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
        Main.main(null);
        String[] predicts = out.toString().split(DELIMITER);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[0]) - 66.53) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[1]) - 66.98) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[2]) - 67.92) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[3]) - 66.83) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[4]) - 67.37) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[5]) - 67.05) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[6]) - 68.09) < 0.01);
    }

    @Test
    public void week_TRY() {
        ByteArrayInputStream in = new ByteArrayInputStream("rate TRY week".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
        Main.main(null);
        String[] predicts = out.toString().split(DELIMITER);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[0]) - 5.11) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[1]) - 5.36) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[2]) - 5.66) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[3]) - 5.57) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[4]) - 5.88) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[5]) - 5.67) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[6]) - 6.02) < 0.01);
    }

    @Test
    public void week_EVR() {
        ByteArrayInputStream in = new ByteArrayInputStream("rate EVR week".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
        Main.main(null);
        String[] predicts = out.toString().split(DELIMITER);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[0]) - 72.08) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[1]) - 72.75) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[2]) - 74.26) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[3]) - 72.73) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[4]) - 74.20) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[5]) - 73.27) < 0.01);
        Assert.assertTrue(Math.abs(Double.parseDouble(predicts[6]) - 75.19) < 0.01);
    }
}