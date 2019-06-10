package file;

// args[] : /Users/jessehenrick/IdeaProjects/thejudge/src/test/TestCase.java /Users/jessehenrick/IdeaProjects/thejudge/src/test/output.txt

import java.io.File;

public class FileRunWithProcessBuilder
{
    private static String className = null;


    public static void compile(String fileName)
    {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("javac", "test/TestMe.java");
        builder.inheritIO();
        builder.directory(new File("/Users/jessehenrick/IdeaProjects/thejudge/src"));
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
        builder.command("java", "test.TestMe");
        builder.inheritIO();
        builder.directory(new File("/Users/jessehenrick/IdeaProjects/thejudge/src"));
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
        compile(args[0]);
        run();
    }
}
