import java.io.*;
import java.util.Scanner;

import static java.lang.System.out;

public class LogFile {

    public static File data = new File ("/Users/Olivier/IdeaProjects/Assignment3SurLaSauce/src/Data");
    private PrintWriter logWriter;
    private Scanner logScan1;
    private Scanner logScan2;

    public LogFile() throws FileNotFoundException{
        logWriter = new PrintWriter(new FileOutputStream("log.txt"), true) ;
        logScan1 = new Scanner(new FileInputStream("log.txt"));
        logScan2 = new Scanner(new FileInputStream("log.txt"));
        displayDirectoryContents(data, logWriter);
    }

    public static void displayDirectoryContents(File dir, PrintWriter p) {
        try {
            File[] files = dir.listFiles();
            out.println(dir.listFiles());
            for (File file : files) {
                if (file.isDirectory()) {
                    p.println("directory:" + file.getCanonicalPath());
                    displayDirectoryContents(file, p);
                } else {
                    p.println("     file:" + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getData() {
        return data;
    }

    public Scanner getLogScan1() {
        return logScan1;
    }
    public Scanner getLogScan2(){
        return  logScan2;
    }

    public PrintWriter getLogWriter() {
        return logWriter;
    }
}
