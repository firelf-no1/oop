package filesprocessing.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * typeOrder - orders the files type alphabetically
 */
public class TypeOrder implements OrderInterface {
    /**
     *
     * @return comparator that compares between the sting that are the type of the strings.
     */
    private static Comparator<File> sortByType() {
        Comparator<File> comparator = new Comparator<File>() {
            /*
            gets the type by char '.' the name of the file. In case there is no dot in the file's name or
            the dot is in the end of the name.for example: for "file", "file." the condition in the if will
            return true.
             */
            private String getType(File file){
                char DOT ='.';
                String name = file.getName();
                int index = name.length()-1;
                // while we didn't encounter '.' and there is more name to go
                while((index >= 0 ) && (DOT != ( name.charAt(index)))) { index--; }
                if((index == 0) && ( index == name.length() - 1)) { return ""; }
                // the substring from the dot to the end of the file's name.
                else { return name.substring(index+1); }
            }

            /**
             * the compare implementation
             * @param val1 first file
             * @param val2 second file
             * @return similar to the compare in the regular case: 0 if equal, 1 if val1>val2, -1 if val1<val2
             */
            @Override
            public int compare(File val1, File val2) { return (getType(val1).compareTo(getType(val2))); }
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
        Arrays.sort(orderByName.fileSorter(files),sortByType());
        return files;
    }
}