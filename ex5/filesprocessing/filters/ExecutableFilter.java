package filesprocessing.filters;

import filesprocessing.exceptions.TypeOneErrors;
import java.io.File;

/**
 * checks if the file has a executable permission for the current user
 */
public class ExecutableFilter extends YesOrNoFilter implements FilterInterface {
    public static final int argumentNumber = 2;

    /**
     * if YES, checker is true. if NO checker is false.
     * @param val the string of YES \ NO
     * @throws TypeOneErrors
     */
    public ExecutableFilter(String val) throws TypeOneErrors { super(val); }

    /**
     * @param file the file we need to check the filter on.
     */
    @Override
    public boolean filterCheck(File file) { return (file.canExecute() == checker); }
}