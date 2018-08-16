package filesprocessing.exceptions;

/**
 * exception according to the described in the exercise.
 */
public class TypeTwoErrors extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String FATAL_ERROR = "ERROR: ";
    private String msg;

    public TypeTwoErrors(String var1) { this.msg = var1; }

    public void endTheProgram() { System.err.println(FATAL_ERROR + this.msg); }
}