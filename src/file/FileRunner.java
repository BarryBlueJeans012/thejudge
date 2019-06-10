package file;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
Create a class with methods that will
Start a timer
Build a process for a .java file, write output to a text file
Wait for the process to complete
End the timer
 */
public class FileRunner {
    private long start;
    private long finish;

    private void startTimer() {
        start = System.currentTimeMillis();
    }

    private long stopTimer() {
        finish = System.currentTimeMillis();
        return finish - start;
    }

    public void runFile(String fileName, String fileOutName) {
        try {
            String line;
            System.out.println("Building process");
            startTimer();
            String className = fileName.replace(".java", "");
            Process p = Runtime.getRuntime()
                    .exec("javac " + fileName
                            + "&& java " + className + " | tee " + fileOutName);
            InputStream in = p.getInputStream();
            BufferedReader stream = new BufferedReader(new InputStreamReader(in));
            while ((line = stream.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println(stopTimer());
            stream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String []args)
    {
        FileRunner execute = new FileRunner();
        execute.runFile(args[0], args[1]);
        // you have to set intellij to pass the file names as args (absolute path)
        // go to run > edit configuration > program arguments
        System.out.println("Done");

    }
}
