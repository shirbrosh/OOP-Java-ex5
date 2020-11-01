package filters;
import java.io.File;

/**
 * A Hidden Filter object implementing the filter class
 */
public class Hidden extends StringFilter implements Filter {
    /**
     * a constructor for Contains filter
     * @param decision - A YES or NO string
     * @param isNot - the flag signs if the its a not filter
     */
    public Hidden(String decision, boolean isNot){
        super(decision, isNot);
    }

    /**
     * A method that checks if a given file can pass this filter
     * @param file - the file to check
     * @return true if the file passed the filter or false otherwise
     */
    public boolean isPass(File file) {
        if(valueToCheck.equals(YES) && !isNot || valueToCheck.equals(NO) && isNot ){
            return file.isHidden();
        }
        return !file.isHidden();
    }
}
