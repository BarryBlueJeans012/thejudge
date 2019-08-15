package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class FileRunWithProcessBuilder extends PathHolder {

    private String className;
    private boolean hasCompiled = false;
    private String outputFile = projectDirectory + "/src/test/output";
    private String inputFile = projectDirectory + "/src/test/input";
    private String errorFile = projectDirectory + "/src/test/error";

    public FileRunWithProcessBuilder(String fileName)
    {
        this.fileName = fileName;
        className = fileName.replace(".java", "");
        outputFile = outputFile.replace("output", className + "_output");
        errorFile = errorFile.replace("error", className + "_error");
    }

    public void compile()
    {
        assert fileName != null : "fileName is null";
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("javac", "-d", "out", fileName);
        builder.inheritIO();
        builder.redirectOutput(new File(outputFile));
        builder.redirectError(new File(errorFile));
        builder.directory(new File(workingDirectory));
        try
        {
            Process process = builder.start();
            int exitCode = process.waitFor();
            System.out.println("Compilation exited with code: " + exitCode);
            if (exitCode == 0)
            {
                hasCompiled = true;
            }
        }
        catch(Exception e)
        {
            System.out.println("There was a fatal error in compilation");
            e.printStackTrace();
        }
    }

    public void run()
    {
        assert hasCompiled;
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("java", className);
        builder.inheritIO();
        builder.redirectOutput(new File(outputFile));
        builder.redirectInput(new File(inputFile));
        builder.directory(new File(workingDirectory + "/out"));
        try
        {
            Process process = builder.start();
            int exitCode = process.waitFor();
            System.out.println("Execution exited with code: " + exitCode);
        }
        catch(Exception e)
        {
            System.out.println("There was a fatal error in execution");
            e.printStackTrace();
        }
    }

    /*
    So I spent several hours digging around and trying to figure out how to get a subprocess to take multiple lines of input
    which i could iterate through one by one, closing and reopening the process each time.
    I ended up settling on a (maybe very stupid) solution where the input file has every line of input I want to run
    Then I write a line from that file into a second input file, where processbuilder reads it and takes that as the input for the subprocess.
    The output for the subprocess appends to an output file for the class being run.
    After that subprocess closes, the next line from the first input file is written over the second input file.
    This way, each line from the first input file is tested in a new process. I'm not sure if this is much slower or if its very inefficient
    compared to another method, but this is all I could figure out.
    */
    public void runMultiple() {
        assert hasCompiled;
        BufferedReader in;
        Process process;
        try {
            in = new BufferedReader(new FileReader(new File(inputFile)));
            String line = in.readLine();
            while (line != null) {
                PrintWriter writer = new PrintWriter(inputFile + "2");
                writer.print(line);
                writer.close();
                ProcessBuilder builder = new ProcessBuilder();
                builder.command("java", className);
                builder.inheritIO();
                builder.redirectOutput(ProcessBuilder.Redirect.appendTo(new File(outputFile)));
                builder.redirectInput(new File(inputFile + "2"));
                builder.directory(new File(workingDirectory + "/out"));
                process = builder.start();
                int exitCode = process.waitFor();
                System.out.println("Execution exited with code: " + exitCode);
                line = in.readLine();
            }
        } catch (Exception e) {
            System.out.println("There was a fatal error in execution");
            e.printStackTrace();
        }
    }
    public void cleanOut()
    {
       File outDirectory = new File(workingDirectory + "/out");
       File[] arrayOfClasses = outDirectory.listFiles();
       int filesDeleted = 0;
       if (arrayOfClasses != null)
       {
           for (File file : arrayOfClasses)
           {
                System.out.println("Deleting " + file.getAbsolutePath());
                if (file.delete())
                {
                    System.out.println("Successfully deleted.");
                }
                else
                {
                    System.out.println("An error has occurred.");
                }
           }
       }
       System.out.println(filesDeleted + " files deleted.");


    }



    public static void main(String []args)
    {

    }
}
