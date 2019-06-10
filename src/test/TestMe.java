package test;

public class TestMe
{
    public static void main(String []args)
    {
        System.out.println("This is a print statements");
        System.out.println("Now to do some real quick maths");
        int x = 10;
        for (int i = 0; i < 100000; i++)
        {
            x += i;
        }
        System.out.println("All done, and x is " + x);
    }
}
