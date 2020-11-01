package filters;

import java.io.File;

/**
 * The filter interface
 */
public interface Filter {
    int BYTES = 1024;
    String YES = "YES";
    String NO = "NO";

    /**
     * A method that checks if a given file can pass this filter
     * @param file - the file to check
     * @return true if the file passed the filter or false otherwise
     */
    public boolean isPass(File file);
}
