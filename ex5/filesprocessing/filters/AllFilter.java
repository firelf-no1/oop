package filesprocessing.filters;

import java.io.File;

/**
 * all files are matched
 */
public class AllFilter implements FilterInterface {
    public static final int argumentNumber = 1;

    /**
     * every file passes, so always return true.
     * @param file the file we need to check the filter on.
     */
    @Override
    public boolean filterCheck(File file) { return true; }
}