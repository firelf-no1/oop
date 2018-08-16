package filesprocessing.filters;

import java.io.File;

/**
 * checks if the value is the suffix of the file name
 */
public class SuffixFilter implements FilterInterface {
    public static final int argumentNumber = 2;
    private String suffix;

    public SuffixFilter(String val) { this.suffix = val; }

    /**
     * @param file the file we need to check the filter on.
     */
    @Override
    public boolean filterCheck(File file) { return file.getName().endsWith(suffix); }
}