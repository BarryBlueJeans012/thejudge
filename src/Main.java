import etc.ETC;
import file.FileRunWithProcessBuilder;

public class Main
{
    public static void main(String []args)
    {
        ETC.initializeFiles();
        FileRunWithProcessBuilder runner = new FileRunWithProcessBuilder("test_me.java");
        runner.compile();
        runner.run();
        runner.cleanOut();
        runner = new FileRunWithProcessBuilder("test_my_input.java");
        runner.compile();
        runner.runMultiple();
        runner.cleanOut();
    }
}
