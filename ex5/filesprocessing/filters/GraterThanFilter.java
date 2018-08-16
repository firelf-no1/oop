package filesprocessing.filters;

import java.io.File;
import filesprocessing.exceptions.TypeOneErrors;

/**
 * filters all the files that their size is not greater than the number given as an argument.
 */
public class GraterThanFilter implements FilterInterface {
    public static final int argumentNumber = 2;
    private final static double CONVERT_BYTES = 1024;
    private double minLimit;

    /**
     * constructor
     * @param number the number that is the lower boarder of this filter
     */
    public GraterThanFilter(double number) throws TypeOneErrors {
        //checks if the val is not negative
        if (number < 0){ throw new TypeOneErrors(); }
        else { this.minLimit = number; }
    }

    /**
     * @param file the file we need to check the filter on.
     */
    @Override
    public boolean filterCheck(File file) { return ((double)file.length() / CONVERT_BYTES) > minLimit; }
}