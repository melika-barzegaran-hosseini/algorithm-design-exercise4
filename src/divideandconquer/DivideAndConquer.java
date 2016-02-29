package divideandconquer;

import base.Problem;

/**
 * Provides a divide and conquer algorithm which addresses the problem.
 *
 * @author melika barzegaran hosseini
 */
public class DivideAndConquer extends Problem
{
    public DivideAndConquer(String path)
    {
        super(path);
    }

    @Override
    public void solve()
    {
        super.solve();

        //todo
    }

    public static void main(String args[])
    {
        if(args.length == 1)
        {
            DivideAndConquer divideAndConquer = new DivideAndConquer(args[0]);
            divideAndConquer.enableDetailedPrinting();
            divideAndConquer.solve();
        }
        else
        {
            System.err.println("error: no path to the input file is provided.");
        }
    }
}