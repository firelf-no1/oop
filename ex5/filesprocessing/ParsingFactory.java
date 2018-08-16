package filesprocessing;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import filesprocessing.exceptions.*;
import filesprocessing.filters.*;
import filesprocessing.orders.*;

/**
 * initiates the print of the messages and manages the interaction with the user according to the given
 * arguments.
 */
public class ParsingFactory {
    private static ArrayList<SectionFactory> section;
    private static Scanner scanner;
    private static final int LENGTH_OF_SECTION = 4;
    private static final String FILTER = "FILTER", ORDER = "ORDER", IO = "I/O error", ABS = "abs",
            BAD_FORMAT_ERROR = "Bad format of Commands File", BAD_SUB_SECTION = "Bad subsection name", EMPTY = "";

    /**
     * divides each section to an array according to the parsing factory.
     * @param commandFile - the commands given
     * @return section
     * @throws TypeTwoErrors
     * @throws IOException
     */
    public static ArrayList<SectionFactory> fileParsing(File commandFile) throws TypeTwoErrors, IOException {
        try {
            section = new ArrayList<SectionFactory>();
            FilterInterface filter = null;
            OrderInterface order;
            String sectionLine;
            int[] lineNum = new int[2];
            int skippedLines = 0;
            scanner = new Scanner(commandFile);
            for (int line = 1; scanner.hasNextLine() || line%LENGTH_OF_SECTION != 1; line++){
                if (scanner.hasNextLine()) {sectionLine = scanner.nextLine();}
                else {sectionLine = EMPTY;}
                int val1 = line%LENGTH_OF_SECTION;
                switch (val1){
                    case 1:
                        if (!sectionLine.equals(FILTER)){ throw new TypeTwoErrors(BAD_SUB_SECTION); }
                        break;
                    case 2:
                        if (!scanner.hasNextLine()) {throw new TypeTwoErrors(BAD_FORMAT_ERROR); }
                        else {
                            try {
                                filter = FilterFactory.createFilter(sectionLine);
                            }
                            catch (TypeOneErrors e) {
                                lineNum[0] = line - skippedLines;
                                filter = new AllFilter();
                            }
                        }
                        break;
                    case 3:
                        if (!sectionLine.equals(ORDER)) { throw new TypeTwoErrors(BAD_SUB_SECTION); }
                        break;
                    case 0:
                        if (sectionLine.equals(EMPTY)) { sectionLine = ABS; }
                        try{ order = OrderFactory.createOrder(sectionLine); }
                        catch (TypeOneErrors e) {
                            order = new AbsOrder();
                            if (sectionLine.equals(FILTER)) {
                                line++;
                                skippedLines++;
                            }
                            else { lineNum[1] = line - skippedLines; }
                        }
                        section.add(new SectionFactory(filter, order, lineNum));
                        lineNum = new int[2];
                        break;
                }
            }
            return section;
        }
        catch (IOException d){ throw new TypeTwoErrors(IO); }
    }
}