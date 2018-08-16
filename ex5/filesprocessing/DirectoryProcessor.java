package filesprocessing;

import filesprocessing.exceptions.TypeOneErrors;
import filesprocessing.exceptions.TypeTwoErrors;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The processor that processes the given directory and command file
 */
public class DirectoryProcessor {
    private static final String WRONG_USAGE = "Wrong usage. Should receive 2 arguments",
            NO_FILES = "No files in sourcedir", IO = "I/O error";

    /**
     * @param args directory of files and the command file
     */
    public static void main(String[] args){
        try{
            if (args.length != 2){ throw new TypeTwoErrors(WRONG_USAGE); }
            else{
                File directory = new File(args[0]);
                File[] fileList = directory.listFiles();
                File[] sortedList;
                if (fileList == null){ throw new TypeTwoErrors(NO_FILES); } //can't find files
                else {
                    try {
                        ArrayList<SectionFactory> section = ParsingFactory.fileParsing(new File(args[1]));
                        for (int line = 0; line < section.size(); line++) {
                            for (int error = 0; error < section.get(line).getLine().length; error++) {
                                int errorLine = section.get(line).getLine()[error];
                                if (errorLine != 0) {
                                    String try1 = String.valueOf(errorLine);
                                    new TypeOneErrors(try1).printError(errorLine);
                                }
                            }
                            sortedList = section.get(line).getOrder().fileSorter(fileList);
                            for (int fileNum = 0; fileNum < sortedList.length; fileNum++) {
                                if (sortedList[fileNum].isFile()) {
                                    if (section.get(line).getFilter().filterCheck(sortedList[fileNum])) {
                                        System.out.println(sortedList[fileNum].getName());
                                    }
                                }
                            }
                        }
                    }
                    catch (IOException e){ throw new TypeTwoErrors(IO); }
                }
            }
        }
        catch (TypeTwoErrors e){ e.endTheProgram(); }
    }
}