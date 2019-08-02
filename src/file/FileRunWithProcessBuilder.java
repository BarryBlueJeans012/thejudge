package file;

import java.io.File;

public class FileRunWithProcessBuilder
{
    private static String className = null;

    private static String home_var = System.getenv("HOME") + "/";



    public static void compile(String fileName)
    {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("javac", fileName);
        builder.inheritIO();
        builder.directory(new File(home_var + "judge_boy"));
        try
        {
            Process process = builder.start();
            int exitCode = process.waitFor();
            System.out.println("Compilation exited with code: " + exitCode);
            if (exitCode == 0)
            {
                className = fileName.replace(".java", "");
            }
        }
        catch(Exception e)
        {
            System.out.println("There was a fatal error");
            e.printStackTrace();
        }


    }

    public static void run()
    {
        assert className != null;
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("java", className);
        builder.inheritIO();
        builder.directory(new File(home_var + "judge_boy"));
        try
        {
            Process process = builder.start();
            int exitCode = process.waitFor();
            System.out.println("Execution exited with code: " + exitCode);
        }
        catch(Exception e)
        {
            System.out.println("There was a fatal error");
            e.printStackTrace();
        }
    }




    public static void main(String []args)
    {
        compile("test_me.java");
        run();
    }
}
