import file.FileRunWithProcessBuilder;
import etc.ETC;

public class Main
{
    public static void main(String []args)
    {
        ETC.initializeFiles();
        FileRunWithProcessBuilder runner = new FileRunWithProcessBuilder("test_me.java");
        runner.compile();
        runner.run();
        runner.cleanOut();
    }
}
