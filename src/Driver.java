import java.io.*;
import java.util.Scanner;

import static java.lang.System.out;


public class Driver {

    private static Scanner keyboard = new Scanner(System.in);

    private static int promptMenu() {
        int choice;
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


    public static void main(String[] args) {
        try {
            LogFile logFile = null;

            entryMessage();

            File projectDir = new File("").getAbsoluteFile();

            File dataDir = new File(projectDir, "src/Data");
            boolean done = false;
            do {
                switch (promptMenu()) {
                    case 1: {
                        logFile = new LogFile(dataDir);
                        break;
                    }
                    case 2: {
                        HtlmGenerator htlmGenerator = new HtlmGenerator(logFile, new File(projectDir, "html"), dataDir);
                        htlmGenerator.generateHtml();
                        break;
                    }
                    case 3: {
                        done = true;
                    }
                }
            } while (!done);

            closingMessage();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            keyboard.close();
        }
    }
}





