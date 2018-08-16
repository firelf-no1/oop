package filesprocessing.filters;

import java.io.File;

/**
 * receives a string and checks if the file's name contains it.
 */
public class ContainsFilter implements FilterInterface {
    public static final int argumentNumber = 2;
    private String contain;

    /**
     * contains filter checks if the file's name contains the name given as an argument.
     * @param name String to check if the file's name contains it.
     */
    public ContainsFilter(String name) { this.contain = name; }

    /**
     * @param file the file we need to check the filter on.
     */
    @Override
    public boolean filterCheck(File file) { return file.getName().contains(contain); }
}