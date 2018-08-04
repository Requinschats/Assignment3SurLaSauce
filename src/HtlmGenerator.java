import java.io.*;
import java.util.List;

public class HtlmGenerator {

    private final String dataDir;
    private final File htmlDir;
    private final LogFile logFile;

    public HtlmGenerator(LogFile logFile, File htmlDir, File dataDir) throws IOException {
        this.dataDir = dataDir.getCanonicalPath();
        this.htmlDir = htmlDir;
        this.logFile = logFile;
    }

    public void generateHtml() throws InvalidFileException, EmptyFolderException, FileNotFoundException {
        logFile.openLogFile();

        while (logFile.hasMoreLines()) {
            String directoryName = logFile.getNextDirectory();
            File htmlFile = new File(htmlDir, directoryName + ".html");
            PrintWriter writer = new PrintWriter(new FileOutputStream(htmlFile), true);

            printDirectoryHeader(writer, directoryName);

            List<String> filePaths = logFile.getNextFilePaths();

            printRowStart(writer);
            for (int i = 1; i <= filePaths.size(); ++i) {
                String filePath = filePaths.get(i - 1);
                printFile(writer, filePath);
                if (i % 4 == 0 && i != filePaths.size()) {
                    printRowEnd(writer);
                    printRowStart(writer);
                }
            }
            printRowEnd(writer);

            printDirectoryFooter(writer);
        }
    }

    private static void printRowStart(PrintWriter writer) {
        writer.println("<div class=\"row\">\n");
    }

    private static void printRowEnd(PrintWriter writer) {
        writer.println("</div>\n");
    }

    private static void printDirectoryHeader(PrintWriter writer, String directoryName) {
        writer.println("<html>\n" +
                "<head>\n" +
                "  <link rel=\"stylesheet\" href=\"assignment3.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div style=\"text-align:center\">\n" +
                "  <h1>" + directoryName + "</h1>\n" +
                "</div>\n");

    }

    private void printFile(PrintWriter writer, String filePath) {
        writer.println("  <div class=\"column\">\n" +
                "    <img src=\"../src/Data/" + filePath + "\">\n" +
                "  </div>\n");
    }

    private static void printDirectoryFooter(PrintWriter writer) {
        writer.println("  </div>\n " +
                "</div>  \n" +
                "</body>\n" +
                "</html>");
    }
}
