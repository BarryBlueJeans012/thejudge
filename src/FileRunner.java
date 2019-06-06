/*
Create a class with methods that will
Start a timer
Build a process for a .java file
Wait for the process to complete
End the timer
 */
public class FileRunner {
    private long start;
    private long finish;

    private void startTimer() {
        start = System.nanoTime();
    }

    private long stopTimer() {
        finish = System.nanoTime();
        return finish - start;
    }

    public void runFile(String fileName) {
        try {
            System.out.println("Building process");
            startTimer();
            Process p = Runtime.getRuntime().exec("java " + fileName);
            System.out.println(stopTimer());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
