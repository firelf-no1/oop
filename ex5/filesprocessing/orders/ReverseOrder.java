package filesprocessing.orders;
import java.io.File;

/**
 * The reverse order - receives the order which was given and arranges it in reverse.
 */
public class ReverseOrder implements OrderInterface {
    private OrderInterface order;

    /**
     * ReverseOrder constructor
     * @param order - which order type should be reversed.
     */
    public ReverseOrder(OrderInterface order){ this.order = order; }

    /**
     * implementation of the file-sorting according to the order argument given in the constructor in reverse
     * @param files the directory of files
     * @return the sorted array in reverse.
     */
    @Override
    public File[] fileSorter(File[] files) {
        int length = files.length;
        File [] sortedFiles = order.fileSorter(files);
        File [] reversed = new File[length];
        for(int i=0; i<length; i++){ reversed[i] = sortedFiles[length-i-1]; }
        return reversed;
    }
}