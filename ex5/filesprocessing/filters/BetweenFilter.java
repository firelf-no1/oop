package filesprocessing.filters;

import filesprocessing.exceptions.TypeOneErrors;
import java.io.File;

/**
 * filters all the files that their size is between the two given numbers
 */
public class BetweenFilter implements FilterInterface {
    public static final int argumentNumber = 3;
    private final static int CONVERT_BYTES = 1024;
    private double maxLimit, minLimit;

    /**
     * the between filter constructor
     * @param firstNumber lowerBoarder
     * @param secondNumber upperBoarder
     * @throws TypeOneErrors in case of in correct input at the command line.
     */
    public BetweenFilter(double firstNumber, double secondNumber) throws TypeOneErrors  {
        //check if the first value is smaller. if not filter remain null
        if(firstNumber >= secondNumber) { throw new TypeOneErrors(); }
        else {
            //checks if the val is not negative
            if (firstNumber < 0 || secondNumber < 0) { throw new TypeOneErrors(); }
            else{
                this.minLimit = firstNumber;
                this.maxLimit = secondNumber;
            }
        }
    }

    /**
     * @param file the file we need to check the filter on.
     */
    @Override
    public boolean filterCheck(File file) {
        return (((double)file.length() / CONVERT_BYTES) >= minLimit) &&
                (((double)file.length() / CONVERT_BYTES) <= maxLimit); }
}