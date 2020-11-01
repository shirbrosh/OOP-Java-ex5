package orders;
import java.io.File;

/**
 * An abs order object implementing the Order class.
 */
public class Abs extends MainOrder implements Order {
    /**
     * A constructor for abs order
     * @param isReversed -the flag signs if the its a reversed order
     */
    public Abs(boolean isReversed) {
        super(isReversed);
    }

    /**
     * this orders compare method
     * @param file1 - the first file to compare
     * @param file2 - the second file to compare
     * @return 0- if their Absolute Path is equal, less then 0 if file's 2 Absolute Path greater then
     * file 1's or greater then 0 if its the other way around. if the reversed flag is on, its reversed.
     */
    public int compare(File file1, File file2) {
        if (isReversed) {
            return file2.getAbsolutePath().compareTo(file1.getAbsolutePath());
        }
        return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
    }

}
