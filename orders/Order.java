package orders;

import java.io.File;
import java.util.Comparator;

/**
 * The order interface
 */
public interface Order extends Comparator<File> {
    /**
     * the orders compare method
     * @param file1 - the first file to compare
     * @param file2 - the second file to compare
     * @return 0- if their value to order accordingly is equal, less then 0 if file's 2 Absolute Path
     * greater then file 1's or greater then 0 if its the other way around. if the reversed flag is on,
     * its reversed.
     */
    public int compare(File file1, File file2);

}
