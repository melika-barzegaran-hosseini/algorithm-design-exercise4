package base;

/**
 * Provides and interface to the output of the problem.
 *
 * @author melika barzegaran hosseini
 */
public class Output
{
    private Integer value;
    private String sequence;

    //added for memoization algorithm
    private Boolean isSolved;

    public Boolean isSolved()
    {
        return isSolved;
    }

    public void solve()
    {
        isSolved = true;
    }

    public Output()
    {
        value = 0;
        sequence = "";
        isSolved = false;
    }

    public Output clone()
    {
        Output output = new Output();
        output.value = this.value;
        output.sequence = this.sequence;

        return output;
    }

    public Output addToValue(Integer value)
    {
        this.value += value;
        return this;
    }

    public Output addToSequence(String step)
    {
        sequence += step;
        return this;
    }

    public Output addToTheStartOfTheSequence(String step)
    {
        sequence = step.concat(sequence);
        return this;
    }

    public Integer getValue()
    {
        return value;
    }

    public String getSequence()
    {
        return sequence;
    }

    public static Output compare(Output a, Output b)
    {
        if(a == null && b == null)
        {
            return null;
        }
        else if(a == null)
        {
            return b;
        }
        else if(b == null)
        {
            return a;
        }

        if(a.value > b.value)
        {
            return a;
        }
        else if(a.value < b.value)
        {
            return b;
        }

        return compareSequences(a, b);
    }

    private static Output compareSequences(Output a, Output b)
    {
        char[] aSequence = a.getSequence().toCharArray();
        char[] bSequence = b.getSequence().toCharArray();

        if (aSequence.length != bSequence.length)
        {
            System.err.println("error: bug detected!");
            return null;
        }

        for (int counter = 0; counter < aSequence.length; counter++)
        {
            if (aSequence[counter] > bSequence[counter])
            {
                return a;
            }
            else if(aSequence[counter] < bSequence[counter])
            {
                return b;
            }
        }

        //reaches here if the sequences with equal values are both empty.
        return a;
    }

    @Override
    public String toString()
    {
        return "the output:\n the max value which the thief can collect is '" + value + "'.\n the steps which the " +
                "thief should take are '" + sequence + "'.\n 0 is a step to the right and 1 is a step to the bottom.\n";
    }
}