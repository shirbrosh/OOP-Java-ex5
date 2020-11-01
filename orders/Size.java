package orders;
import java.io.File;

/**
 * An Size order object implementing the Order class.
 */
public class Size extends MainOrder implements Order {
    /**
     * A constructor for Size order
     * @param isReversed -the flag signs if the its a reversed order
     */
    public Size(boolean isReversed){
        super( isReversed);
    }

    /**
     * this orders compare method
     * @param file1 - the first file to compare
     * @param file2 - the second file to compare
     * @return 0- if their size is equal, less then 0 if file's 2 Absolute Path greater then
     * file 1's or greater then 0 if its the other way around. if the reversed flag is on, its reversed.
     */
    public int compare(File file1, File file2) {
        if(file2.length()==file1.length()){
            Order newAbsOrder = new Abs(isReversed);
            return newAbsOrder.compare(file1,file2);
        }

        if(isReversed) {
            return Double.compare(file2.length(),file1.length());
        }
        return Double.compare(file1.length(),file2.length());
    }
}
