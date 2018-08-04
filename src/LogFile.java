import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogFile {
    private static final String FILE_PREFIX = "    file:";
    private static final String DIRECTORY_PREFIX = "directory:";
    private static final String BASE_DATA_DIR = "Data/";

    private Scanner scanner;

    private final PrintWriter logWriter;
    private final File logFile;
    private String scannerLine;

    public LogFile(File dataDir) throws IOException {
        logFile = new File("log.txt");
        logWriter = new PrintWriter(new FileOutputStream(logFile), true);
        generateLogFile(dataDir);
    }

    private void generateLogFile(File dir) throws IOException {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                logWriter.println(DIRECTORY_PREFIX + file.getCanonicalPath());
                generateLogFile(file);
            } else {
                logWriter.println(FILE_PREFIX + file.getCanonicalPath());
            }
        }
    }

    public void openLogFile() throws InvalidFileException {
        try {
            scanner = new Scanner(new FileInputStream(logFile));
            readNextLine();
            if (scannerLine == null) {
                throw new InvalidFileException("empty log file");
            }
        } catch (FileNotFoundException e) {
            throw new InvalidFileException("file not found " + logFile);
        }
    }

    private void readNextLine() {
        if (scanner.hasNextLine()) {
            scannerLine = scanner.nextLine();
        } else {
            scannerLine = null;
        }
    }

    public boolean hasMoreLines() {
        return scannerLine != null;
    }

    public String getNextDirectory() throws InvalidFileException, EmptyFolderException {
        if (!isScannerLineDirectory()) {
            throw new InvalidFileException("expecting directory");
        }
        String directoryLine = scannerLine;
        String directoryPath = directoryLine.substring(DIRECTORY_PREFIX.length());
        validatePath(directoryPath);
        String relativeDirectoryPath = directoryPath.substring(directoryPath.lastIndexOf(BASE_DATA_DIR) + BASE_DATA_DIR.length());

        readNextLine();
        if (isScannerLineDirectory() || !hasMoreLines()) {
            throw new EmptyFolderException("empty directory");
        }
        return relativeDirectoryPath;
    }

    private void validatePath(String path) throws InvalidFileException {
        if (!new File(path).exists()) {
            throw new InvalidFileException("invalid path:" + path);
        }
    }

    private boolean isScannerLineDirectory() {
        return scannerLine != null && scannerLine.startsWith(DIRECTORY_PREFIX);
    }

    private boolean isScannerLineFile() {
        return scannerLine != null && scannerLine.startsWith(FILE_PREFIX);
    }

    public List<String> getNextFilePaths() throws InvalidFileException {
        List<String> relativeFilePaths = new ArrayList<>();
        while (isScannerLineFile()) {
            String fileLine = scannerLine;
            String filePath = fileLine.substring(FILE_PREFIX.length());
            validatePath(filePath);
            String relativeFilePath = filePath.substring(filePath.lastIndexOf(BASE_DATA_DIR) + BASE_DATA_DIR.length());
            relativeFilePaths.add(relativeFilePath);
            readNextLine();
        }
        return relativeFilePaths;
    }

}
