package etc;

import java.io.File;

public class Initialize
{
    public Initialize()
    {
        String homeDirectory = System.getenv("HOME");
        File rootDirectory = new File(homeDirectory + "/judge_boy");
        if ( ! rootDirectory.exists())
        {
            boolean success = rootDirectory.mkdir();
            if ( ! success)
            {
                System.out.println("There was a problem creating rootDirectory judge_boy");
            }
        }

        if ( ! )

    }

}

/*
files:

$HOME/
       judge_boy/
                    out/

 */