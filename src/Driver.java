import java.io.*;
import java.util.Scanner;

import static java.lang.System.out;



//What's left to do:
// Still missing one html file in the process section
// Fix "comment code" in htmFile Content
// Javadocs
// Implementation of the custom exception classes
// Implementation of the relative paths



public class Driver {

    private static Scanner keyboard = new Scanner(System.in);
    private static int choice;

    private static int promptMenu() {
        do {
            out.println("Please chose one of the following options: ");
            out.println("     1. List files");
            out.println("     2. Process files");
            out.println("     3. Exit");
            out.print("Choice: ");
            choice = keyboard.nextInt();
            if (choice < 1 || choice > 3) {
                out.println("This is not a valid option. Please try again.");
            }
        }
        while (choice < 1 || choice > 3);
        return choice;
    }


    private static void entryMessage() {
        out.println("------------------------------------------------------------------");
        out.println("Welcome to our Java based system for automated web page generation");
        out.println("------------------------------------------------------------------");
    }

    private static void closingMessage() {
        out.println("------------------------------------------------------------------");
        out.println("           Thank you for using our system. Goodbye!");
        out.println("------------------------------------------------------------------");
    }

    private static void adjustNumberOfPicturesInDirectories(LogFile logfile, int[] numberOfPicturesInEachDirectories) {
        int numberOfPicturesInDirectories[] = new int[1000];//Solve with arrayList
        int count = -1;

        do {
            if (logfile.getLogScan1().nextLine().startsWith("directory:")) {
                count = count + 1;
                numberOfPicturesInDirectories[count] = 0;
                out.println("directory detected"); // for checking purposes

            } else if (logfile.getLogScan1().hasNextLine() && logfile.getLogScan1().nextLine().endsWith("jpeg")) {
                numberOfPicturesInDirectories[count] = numberOfPicturesInDirectories[count] + 1;
                out.println("a" + numberOfPicturesInDirectories[count]);
            }
        }while (logfile.getLogScan1().hasNextLine());

        for (int i = 0; i < numberOfPicturesInEachDirectories.length; i++) {
            numberOfPicturesInEachDirectories[i] = numberOfPicturesInDirectories[i];
        }
    }

    private static void createArrayOfFileNames(LogFile logFile, String[] htmlFilesName) {
        String tempPath;
        int count = 0;
        do {
            tempPath = logFile.getLogScan2().nextLine();
            if (tempPath.startsWith("directory")) {
                tempPath = tempPath.substring(tempPath.lastIndexOf("Data/", tempPath.length()));
                tempPath = tempPath.replace("Data/", "");
                htmlFilesName[count] = tempPath;
                count = count + 1;
            }
        } while (logFile.getLogScan2().hasNextLine());
    }

    public static void main(String[] args) {

        int[] numberOfPicturesInEachDirectories = new int[1000]; //solve with arrayList
        String[] htmlFilesName = new String[1000]; //solve with arrayList
        HtmlFile[] htmlFiles = new HtmlFile[1000]; //solve with arrayList
        LogFile logFile = null;

        entryMessage();

        do {
            switch (promptMenu()) {
                case 1: {
                    try {
                        logFile = new LogFile();
                    } catch (FileNotFoundException e) {
                        out.println(" We will catch this exception later");
                    }
                    break;
                }
                case 2: {
                    adjustNumberOfPicturesInDirectories(logFile, numberOfPicturesInEachDirectories);
                    createArrayOfFileNames(logFile, htmlFilesName);
                    for (int i = 0; i < htmlFiles.length; i++) {
                        if (numberOfPicturesInEachDirectories[i] != 0) {
                            try {
                                htmlFiles[i] = new HtmlFile(numberOfPicturesInEachDirectories[i], htmlFilesName[i]);
                                htmlFiles[i].writeContentToFile();
                            } catch (FileNotFoundException f) {
                                out.println("File not found");
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    closingMessage();
                    System.exit(0);
                }
            }
        } while (choice != 3);


        closingMessage();
        keyboard.close();
    }
}





