
import java.io.*;
import java.util.Scanner;

import static java.lang.System.out;

public class HtmlFile {

    private String[] jPegFiles;
    private String name;
    private String content;
    private File logFile;
    private File location;
    private PrintWriter writer;

    public HtmlFile(int numberOfPicturesInDirectory, String name) throws FileNotFoundException {
        this.name = name;
        logFile = new File("/Users/Olivier/IdeaProjects/Assignment3August2/src/logFile");
        content = generateContent(numberOfPicturesInDirectory, name);
        jPegFiles=new String[numberOfPicturesInDirectory];
       location = new File(LogFile.data,name + ".html");
       writer = new PrintWriter(new FileOutputStream(name+".html"), true); //insert location
    }

    public String generateContent(int numberOfPicturesInDirectory, String name) {
        String content = "<html>\n" +
                "<head>\n" +
                "  <link rel=\"stylesheet\" href=\"assignment3.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div style=\"text-align:center\">\n" +
                "  <h1>" + name + "</h1>\n" +
                "</div>\n" +
                "\n" +
                "<!-- Four columns per row-->\n" +
                "<div class=\"row\">\n";

        for (int i = 0; i < numberOfPicturesInDirectory; i++) {
            content +=
                    "  <div class=\"column\">\n" +
                    "    <img src=" + name + "/" + jPegFiles[i] + "\n" +
                    "  </div>\n";
        }
        content += "  </div>\n " +
                "</div>  \n" +
                "</body>\n" +
                "</html>";
        return content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String[] getjPegFiles() {
        return jPegFiles;
    }

    public File getLocation() {
        return location;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void writeContentToFile(){
        writer.println(content);
    }

}
