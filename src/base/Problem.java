package base;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Provides the base implementation for the problem. various algorithms addressing the problem should extend this class.
 *
 * @author melika barzegaran hosseini
 */
public abstract class Problem
{
    private String path;
    protected Boolean print;

    protected Input input;
    protected Output output;

    protected Problem(String path)
    {
        this.path = path;
        this.print = false;
    }

    public void disableDetailedPrinting()
    {
        print = false;
    }

    public void enableDetailedPrinting()
    {
        print = true;
    }

    private void read()
    {
        if(path == null || path.isEmpty())
        {
            System.err.println("error: the path provided is null or empty.");
            System.exit(1);
        }

        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(path));

            String line;
            Integer rank;
            if((line = reader.readLine()) != null && !line.isEmpty())
            {
                try
                {
                    rank = Integer.parseInt(line);
                    if(rank <= 0)
                    {
                        throw new NumberFormatException();
                    }
                }
                catch(NumberFormatException e)
                {
                    throw new Exception("error: the first line of the file '" + path + "' should represent the rank " +
                            "of the input matrix.\n it is a positive number with the max value of '" + Input.MAX_VALUE
                            + "'.");
                }
            }
            else
            {
                throw new Exception("error: the first line of the file '" + path + "' is null or empty.");
            }

            input = new Input(rank);

            for(int i = 0; i < rank; i++)
            {
                if((line = reader.readLine()) != null && !line.isEmpty())
                {
                    String[] tokens = line.trim().split("\\s+");
                    if(tokens.length == rank)
                    {
                        for(int j = 0; j < rank; j++)
                        {
                            try
                            {
                                int value = Integer.parseInt(tokens[j]);

                                if(!(value >= -1))
                                {
                                    throw new NumberFormatException();
                                }

                                input.matrix[i][j] = value;
                            }
                            catch (NumberFormatException e)
                            {
                                throw new Exception("error: the '" + (i + 2) + "'th line of the file '" + path + "' " +
                                        "is not structured properly.\n lines should represent the values of the input" +
                                        " matrix.\n the values must be integer numbers equal to or greater than '-1' " +
                                        "with the max value of '" + Input.MAX_VALUE + "'.");
                            }
                        }
                    }
                    else
                    {
                        throw new Exception("error: the file '" + path + "' doesn't provide a square input matrix " +
                                "with the rank of '" + rank + "'.");
                    }
                }
                else
                {
                    throw new Exception("error: the '" + (i + 2) + "'th line of the file '" + path + "' is null or " +
                            "empty.\n it should provide the '" + (i + 1) + "'th row of the input matrix.");
                }
            }

            if(print)
            {
                System.out.println(input.toString());
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("error: the file '" + path + "' doesn't exist or is a directory.");
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println("error: an error occurred while reading from the file '" + path + "'.");
            System.exit(1);
        }
        catch (OutOfMemoryError e)
        {
            System.err.println("error: the input matrix is too big.");
            System.exit(1);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        finally
        {
            try
            {
                if(reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException e)
            {
                System.err.println("error: an error occurred while closing the file '" + path + "'.");
                System.exit(1);
            }
        }
    }

    public void solve()
    {
        read();
    }
}