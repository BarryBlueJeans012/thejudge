import file.FileRunWithProcessBuilder;
import etc.Initialize;

public class Main
{
    public static void main(String []args)
    {

        FileRunWithProcessBuilder runner = new FileRunWithProcessBuilder("test_me.java");
        runner.compile();
        runner.run();
        runner.cleanOut();
    }
}
