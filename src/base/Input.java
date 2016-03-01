package base;

/**
 * Provides an interface to the input of the problem.
 *
 * @author melika barzegaran hosseini
 */
public class Input
{
    public static final Integer MAX_VALUE = Integer.MAX_VALUE;
    public static final Integer MIN_VALUE = Integer.MIN_VALUE;
    public Integer[][] matrix;

    public Input(Integer rank)
    {
        matrix = new Integer[rank][rank];
    }

    @Override
    public String toString()
    {
        StringBuilder message = new StringBuilder("the input:\n");

        for(Integer[] values : matrix)
        {
            for(Integer value : values)
            {
                message.append(String.format("%10d ", value));
            }
            message.append("\n");
        }

        return message.toString();
    }
}