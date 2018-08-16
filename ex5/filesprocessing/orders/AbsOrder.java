package filesprocessing.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * AbsOrder orders the files names alphabetically.
 */
public class AbsOrder implements OrderInterface {

    /**
     * @return Comparator that sort by name.
     */
    private static Comparator<File> sortByName() {
        Comparator<File> comparator = new Comparator<File>() {

            /**
             * the compare implementation
             * @param val1 first file
             * @param val2 second file
             * @return similar to the compare in the regular case: 0 if equal, 1 if val1>val2, -1 if val1<val2
             */
            @Override
            public int compare(File val1, File val2) {
                return (val1.getAbsolutePath().compareTo(val2.getAbsolutePath())); }
        };
        return comparator;
    }

    /**
     * @param files the files we are sorting.
     */
    @Override
    public File[] fileSorter(File[] files) {
        Arrays.sort(files,sortByName());
        return files;
    }
}