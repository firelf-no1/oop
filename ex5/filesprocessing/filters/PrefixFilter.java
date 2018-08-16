package filesprocessing.filters;

import java.io.File;

/**
 * checks if thr value is the prefix of the file name
 */
public class PrefixFilter implements FilterInterface {
    public static final int argumentNumber = 2;
    private String prefix;

    public PrefixFilter(String val) { this.prefix = val; }

    /**
     * @param file the file we need to check the filter on.
     */
    @Override
    public boolean filterCheck(File file) { return file.getName().startsWith(prefix); }
}
