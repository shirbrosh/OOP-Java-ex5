package filters;
import java.io.File;

/**
 * A file Filter object implementing the filter class
 */
public class FileFilter extends StringFilter implements Filter {
    /**
     * A constructor for file filter
     * @param fileName - the argument to check
     * @param isNot- the flag signs if the its a not filter
     */
    public FileFilter(String fileName, boolean isNot ){
        super(fileName, isNot);
    }

    /**
     * A method that checks if a given file can pass this filter
     * @param file - the file to check
     * @return true if the file passed the filter or false otherwise
     */
    public boolean isPass(File file){
        if(isNot){
            return !file.getName().equals(valueToCheck);
        }
        return file.getName().equals(valueToCheck);
    }
}
