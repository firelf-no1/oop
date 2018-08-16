package filesprocessing.filters;

import java.io.File;
import filesprocessing.exceptions.TypeOneErrors;

/**
 * checks if the size of the file is smaller than a given argument.
 */
public class SmallerThenFilter implements FilterInterface {
    public static final int argumentNumber = 2;
    private final static int CONVERT_BYTES = 1024;
    private double maxLimit;

    /**
     * constructor
     * @param number the given number argument - cheking smaller than compare to it.
     */
    public SmallerThenFilter(double number) throws TypeOneErrors {
        //checks if the val is not negative
        if (number < 0){ throw new TypeOneErrors(); }
        else { this.maxLimit = number; }
    }

    /**
     * @param file the file we need to check the filter on.
     */
    @Override
    public boolean filterCheck(File file) { return ((double)file.length() / CONVERT_BYTES) < maxLimit; }
}