import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LogFileTest {
    public static void main(String[] args) throws IOException {
        File projectDir = new File("").getAbsoluteFile();
        new LogFile(new File(projectDir, "src/Data"));
    }
}
