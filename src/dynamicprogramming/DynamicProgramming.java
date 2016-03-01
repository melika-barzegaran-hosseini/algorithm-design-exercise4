package dynamicprogramming;

import base.Output;
import base.Problem;

/**
 * Provides a dynamic programming algorithm which addresses the problem.
 *
 * @author melika barzegaran hosseini
 */
public class DynamicProgramming extends Problem
{
    private Output[][] outputs;

    protected DynamicProgramming(String path)
    {
        super(path);
    }

    @Override
    public void solve()
    {
        if(print)
        {
            System.out.println("================================================================================");
            System.out.println("DYNAMIC PROGRAMMING:\n");
        }

        super.solve();

        long start = System.currentTimeMillis();
        Output output = dynamicProgramming();
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

    private Output dynamicProgramming()
    {
        Integer n = input.matrix.length;
        outputs = new Output[n][n];

        //traverses the input matrix from the bottom right to the the top left horizontally
        for(Integer i = n - 1; i >= 0; i--)
        {
            for(Integer j = n - 1; j >= 0; j--)
            {
                //being in the destination
                if(i == n - 1 && j == n - 1)
                {
                    outputs[i][j] = new Output();
                }

                //being in an obstacle
                else if(input.matrix[i][j] == -1)
                {
                    outputs[i][j] = null;
                }

                //unable to take more steps towards the bottom
                else if(i == n - 1)
                {
                    outputs[i][j] = checkRight(i, j);
                }

                //unable to take more steps towards the right
                else if(j == n - 1)
                {
                    outputs[i][j] = checkBottom(i, j);
                }

                //deciding which way is the best
                else
                {
                    Output ifGoesToTheRight = checkRight(i, j);
                    Output ifGoesToTheBottom = checkBottom(i, j);

                    outputs[i][j] = Output.compare(ifGoesToTheRight, ifGoesToTheBottom);
                }
            }
        }

        return outputs[0][0];
    }

    private Output checkRight(int i, int j)
    {
        if(outputs[i][j + 1] == null)
        {
            return null;
        }
        else
        {
            return outputs[i][j + 1].clone().addToTheStartOfTheSequence("0").addToValue(input.matrix[i][j]);
        }
    }

    private Output checkBottom(int i, int j)
    {
        if(outputs[i + 1][j] == null)
        {
            return null;
        }
        else
        {
            return outputs[i + 1][j].clone().addToTheStartOfTheSequence("1").addToValue(input.matrix[i][j]);
        }
    }

    public static void main(String args[])
    {
        if(args.length != 1)
        {
            for(String arg : args)
            {
                DynamicProgramming dynamicProgramming = new DynamicProgramming(arg);
                dynamicProgramming.enableDetailedPrinting();
                dynamicProgramming.solve();
            }
        }
        else
        {
            System.err.println("error: no path to the input file is provided.");
        }
    }
}