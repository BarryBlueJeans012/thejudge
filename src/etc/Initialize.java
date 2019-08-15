package etc;

import java.io.File;

public class Initialize
{
    public Initialize()
    {
        String homeDirectory = System.getenv("HOME");
        File rootDirectory = new File(homeDirectory + "/judge_boy");
        if ( ! rootDirectory.isDirectory())
        {
            boolean success = rootDirectory.mkdir();
            if ( ! success)
            {
                System.out.println(
                                "There was a problem creating rootDirectory "
                                + rootDirectory.getAbsolutePath());
            }
        }
        File outDirectory = new File(rootDirectory.getAbsolutePath() + "/out");
        if ( ! outDirectory.isDirectory())
        {
            boolean success = outDirectory.mkdir();
            if ( ! success)
            {
                System.out.println(
                                "There was a problem creating outDirectory "
                                + outDirectory.getAbsolutePath());
            }
        }

        System.out.println("Files initialized");

    }

}

/*
files:

$HOME/
       judge_boy/
                    out/

 */