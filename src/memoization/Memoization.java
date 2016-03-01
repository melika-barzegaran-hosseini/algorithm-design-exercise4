package memoization;

import base.Input;
import base.Output;
import base.Problem;

/**
 * Provides a memoization algorithm which addresses the problem.
 *
 * @author melika barzegaran hosseini
 */
public class Memoization extends Problem
{
    private Output[][] outputs;

    protected Memoization(String path)
    {
        super(path);
    }

    @Override
    public void solve()
    {
        if(print)
        {
            System.out.println("MEMOIZATION:\n");
        }

        super.solve();

        outputs = new Output[input.matrix.length][input.matrix.length];
        for(int i = 0; i < input.matrix.length; i++)
        {
            for(int j = 0; j < input.matrix.length; j++)
            {
                outputs[i][j] = new Output();
            }
        }
        memoization(0, 0);

        output = outputs[0][0];

        if(output.getValue() == Integer.MIN_VALUE)
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

    private void memoization(int i, int j)
    {
        //reaching the destination
        if(i == input.matrix.length - 1 && j == input.matrix.length - 1)
        {
            //sets the value of the destination to its equivalent.
            outputs[i][j].addToValue(input.matrix[i][j]);
            return;
        }

        //being in an obstacle
        if (input.matrix[i][j] == -1)
        {
            //sets the value of the obstacle to the minimum possible value.
            outputs[i][j].addToValue(Input.MIN_VALUE);
            return;
        }

        //solving the right sub-problem if it exists and has not been solved yet
        if(i < input.matrix.length - 1 && !outputs[i + 1][j].isSolved())
        {
            //solves the sub-problem and marks it as solved.
            memoization(i + 1, j);
            outputs[i + 1][j].solve();
        }

        //solving the bottom sub-problem if it exists and has not been solved yet
        if(j < input.matrix.length - 1 && !outputs[i][j + 1].isSolved())
        {
            //solves the sub-problem and marks it as solved.
            memoization(i, j + 1);
            outputs[i][j + 1].solve();
        }

        //reaching here, if right and bottom sub-problems exist, they are solved for sure.

        //if there is no bottom sub-problem. it means we have just a right sub-problem which is solved.
        if(j == input.matrix.length - 1)
        {
            //sets the value of the sub-problem to the sum of its equivalent and the sub-problem.
            //forms the sequence as the concatenation of this step and the sequence of the sub-problem.
            outputs[i][j].addToValue(input.matrix[i][j] + outputs[i + 1][j].getValue());
            outputs[i][j].addToSequence(outputs[i + 1][j].getSequence()).addToTheStartOfTheSequence("1");
        }

        //if there is no right sub-problem. it means we have just a bottom sub-problem which is solved.
        else if(i == input.matrix.length - 1)
        {
            //sets the value of the sub-problem to the sum of its equivalent and the sub-problem.
            //forms the sequence as the concatenation of this step and the sequence of the sub-problem.
            outputs[i][j].addToValue(input.matrix[i][j] + outputs[i][j + 1].getValue());
            outputs[i][j].addToSequence(outputs[i][j + 1].getSequence()).addToTheStartOfTheSequence("0");
        }

        //if both sub-problems exist. they are both solved.
        else
        {
            //finds the best path.
            ///sets the value of the sub-problem to the sum of its equivalent and the sub-problem.
            //forms the sequence as the concatenation of this step and the sequence of the sub-problem.
            Output max = Output.compare(outputs[i + 1][j], outputs[i][j + 1]);
            outputs[i][j].addToValue(input.matrix[i][j] + max.getValue());
            outputs[i][j].addToSequence(max.getSequence()).addToTheStartOfTheSequence(max.equals(outputs[i + 1][j]) ?
                    "1" : "0");
        }

        //marks the problem as solved.
        outputs[i][j].solve();
    }

    public static void main(String args[])
    {
        if(args.length == 1)
        {
            Memoization memoization = new Memoization(args[0]);
            memoization.enableDetailedPrinting();
            memoization.solve();
        }
        else
        {
            System.err.println("error: no path to the input file is provided.");
        }
    }
}