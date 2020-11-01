package filters;

import java.io.File;

/**
 * A Executable Filter object implementing the filter class
 */
public class Executable extends StringFilter implements Filter {
    /**
     * a constructor for Executable filter
     * @param decision - A YES or NO string
     * @param isNot - the flag signs if the its a not filter
     */
    public Executable(String decision, boolean isNot){
        super(decision, isNot);
    }

    /**
     * A method that checks if a given file can pass this filter
     * @param file - the file to check
     * @return true if the file passed the filter or false otherwise
     */
    public boolean isPass(File file) {
        if(valueToCheck.equals(YES) && !isNot || valueToCheck.equals(NO) && isNot){
            return file.canExecute();
        }
        return !file.canExecute();
    }
}
