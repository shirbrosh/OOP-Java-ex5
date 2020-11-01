package filters;

import java.io.File;

/**
 * A contains Filter object
 */
public class Contains extends StringFilter implements Filter {
    /**
     * a constructor for Contains filter
     * @param fileName - the argument to check
     * @param isNot - the flag signs if the its a not filter
     */
    public Contains(String fileName, boolean isNot){
        super(fileName, isNot);
    }

    /**
     * A method that checks if a given file can pass this filter
     * @param file - the file to check
     * @return true if the file passed the filter or false otherwise
     */
    public boolean isPass(File file){
        if(isNot){
            return !file.getName().contains(valueToCheck);
        }
        return file.getName().contains(valueToCheck);
    }
}
