package filesprocessing.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * sizeOrder orders the files according to their size.
 */
public class SizeOrder implements OrderInterface {

    /*
     * @return Comparator that compares between sizes.
     */
    private static Comparator<File> sortBySize() {
        Comparator<File> comparator = new Comparator<File>() {

            /**
             * the compare implementation
             * @param val1 first file
             * @param val2 second file
             * @return similar to the compare in the regular case: 0 if equal, 1 if val1>val2, -1 if val1<val2
             */
            @Override
            public int compare(File val1, File val2) {
                double val1Length = val1.length();
                double val2Length = val2.length();
                return Double.compare(val1Length, val2Length);
            }
        };
        return comparator;
    }

    /**
     *
     * @param files the files directory (array of files)
     * @return sorted files array
     */
    @Override
    public File[] fileSorter(File[] files) {
        AbsOrder orderByName = new AbsOrder();
        Arrays.sort(orderByName.fileSorter(files),sortBySize());
        return files;
    }
}