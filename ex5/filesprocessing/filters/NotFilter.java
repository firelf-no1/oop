package filesprocessing.filters;

import java.io.File;

/**
 * checks if not has been applied.
 */

public class NotFilter implements FilterInterface {
    public static final int argumentNumber = 2;
    private FilterInterface filterToNegative;

    /**
     * constructor
     * @param val type of filter
     */
    public NotFilter(FilterInterface val) { this.filterToNegative = val; }

    /**
     * return the opposite of the filter we need to negation.
     * @param file the file we need to check the filter on.
     */
    @Override
    public boolean filterCheck(File file) { return !this.filterToNegative.filterCheck(file); }
}