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

    public void runFile(String fileName) {
        try {
            String line;
            System.out.println("Building process");
            startTimer();
            Process p = Runtime.getRuntime().exec("java " + fileName);
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
}
