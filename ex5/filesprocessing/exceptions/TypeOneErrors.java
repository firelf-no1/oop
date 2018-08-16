package filesprocessing.exceptions;

/**
 * exception according to the described in the exercise.
 */
public class TypeOneErrors extends Exception {
    private static final long serialVersionUID = 1L;
    private final String WARNING_MASSAGE = "Warning in line ";

    public TypeOneErrors() { }

    public TypeOneErrors(String var1) { super(var1); }

    public void printError(int line) { System.err.println(WARNING_MASSAGE + line); }
}