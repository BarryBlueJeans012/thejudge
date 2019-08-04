package file;

import java.io.File;

public class FileRunWithProcessBuilder
{
    private String fileName = null;
    private String className = null;
    private boolean hasCompiled = false;
    private String homeDirectory = System.getenv("HOME");
    private String projectDirectory = homeDirectory + "/IdeaProjects/thejudge";
    private String workingDirectory = homeDirectory + "/judge_boy";
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
        assert fileName != null;
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("javac", "-d", "out", fileName);
        builder.inheritIO();
        //builder.redirectOutput(new File(outputFile));
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
        FileRunWithProcessBuilder runner = new FileRunWithProcessBuilder("test_me.java");
        runner.compile();
        runner.run();
        runner.cleanOut();
    }
}
