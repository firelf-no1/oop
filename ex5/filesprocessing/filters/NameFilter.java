package filesprocessing.filters;

import java.io.File;

/**
 * checks if the value equals the file name
 */
public class NameFilter implements FilterInterface {
    public static final int argumentNumber = 2;
    private String equalNames;

    public NameFilter(String name) { this.equalNames = name; }

    /**
     * @param file the file we need to check the filter on.
     */
    @Override
    public boolean filterCheck(File file) { return equalNames.equals(file.getName()); }
}