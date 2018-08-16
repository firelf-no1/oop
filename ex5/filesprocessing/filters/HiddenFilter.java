package filesprocessing.filters;

import filesprocessing.exceptions.TypeOneErrors;
import java.io.File;

/**
 * checks if the file is hidden
 */
public class HiddenFilter extends YesOrNoFilter implements FilterInterface  {
    public static final int argumentNumber = 2;

    /**
     * if YES, checker is true. if NO checker is false.
     * @param val the string of YES \ NO
     * @throws TypeOneErrors
     */
    public HiddenFilter(String val) throws TypeOneErrors { super(val); }

    /**
     * @param file the file we need to check the filter on.
     */
    @Override
    public boolean filterCheck(File file) { return (file.isHidden() == checker); }
}