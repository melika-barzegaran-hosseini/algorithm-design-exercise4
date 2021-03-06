package divideandconquer;

import base.Output;
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
        if(print)
        {
            System.out.println("================================================================================");
            System.out.println("DIVIDE AND CONQUER:\n");
        }

        super.solve();

        long start = System.currentTimeMillis();
        Output output = divideAndConquer(0, 0, new Output());
        long end = System.currentTimeMillis();
        long taken = end - start;

        if(print)
        {
            System.out.println(String.format("%d milliseconds took to do the job.", taken));
        }

        if(output == null)
        {
            System.out.println("BUSTED");
        }
        else
        {
            if(print)
            {
                System.out.println(output.toString());
            }
            else
            {
                System.out.println(output.getValue());
                System.out.println(output.getSequence());
            }
        }
    }

    private Output divideAndConquer(int i, int j, Output output)
    {
        //reaching the destination
        if(i == input.matrix.length - 1 && j == input.matrix.length - 1)
        {
            return output;
        }

        //being in an obstacle
        if(input.matrix[i][j] == -1)
        {
            return null;
        }

        //unable to take more steps towards the bottom
        if(i == input.matrix.length - 1)
        {
            return goToTheRight(i, j, output);
        }

        //unable to take more steps towards the right
        if(j == input.matrix.length - 1)
        {
            return goToTheBottom(i, j, output);
        }

        //deciding which way is the best
        return Output.compare(goToTheRight(i, j, output), goToTheBottom(i, j, output));
    }

    private Output goToTheRight(int i, int j, Output output)
    {
        return divideAndConquer(i, j + 1, output.clone().addToSequence("0").addToValue(input.matrix[i][j + 1]));
    }

    private Output goToTheBottom(int i, int j, Output output)
    {
        return divideAndConquer(i + 1, j, output.clone().addToSequence("1").addToValue(input.matrix[i + 1][j]));
    }

    public static void main(String args[])
    {
        if(args.length != 0)
        {
            for(String arg : args)
            {
                DivideAndConquer divideAndConquer = new DivideAndConquer(arg);
                divideAndConquer.enableDetailedPrinting();
                divideAndConquer.solve();
            }
        }
        else
        {
            System.err.println("error: no path to the input file is provided.");
        }
    }
}