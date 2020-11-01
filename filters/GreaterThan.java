package filters;
import java.io.File;
/**
 * A GreaterThan Filter object implementing the filter class
 */
public class GreaterThan extends DoubleFilters implements Filter {
    /**
     * a constructor for Contains filter
     * @param TheValueToCompare - the argument to check
     * @param isNot - the flag signs if the its a not filter
     */
    public GreaterThan(double TheValueToCompare, boolean isNot){

        super(TheValueToCompare, isNot);
    }

    /**
     * A method that checks if a given file can pass this filter
     * @param file - the file to check
     * @return true if the file passed the filter or false otherwise
     */
    public boolean isPass(File file){
        if(isNot){
            return file.length() <= valueToCompare1*BYTES;
        }
        return file.length() > valueToCompare1*BYTES;
    }
}
