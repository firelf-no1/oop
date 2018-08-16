package filesprocessing.filters;

import filesprocessing.exceptions.TypeOneErrors;

/**
 * checks if the val is yes or no
 */
public class YesOrNoFilter  {
    protected boolean checker;
    private static final String YES = "YES", NO = "NO";

    /**
     * yes or no filter constructor
     * @param val YES or NO strings
     * @throws TypeOneErrors type one as written in the exercise.
     */
    public YesOrNoFilter(String val) throws TypeOneErrors {
        if (val.equals(YES)) { checker = true; }
        else {
            if (val.equals(NO)) { checker = false; }
            else { throw new TypeOneErrors(); } }
    }
}