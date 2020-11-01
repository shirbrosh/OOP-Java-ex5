package filters;

import java.io.File;

/**
 * An all filter object implementing the Filter class.
 */
public class All implements Filter  {

    /**
     * A method that checks if the given file can pass the filter
     * @param file the file to check
     * @return true- if the file passes the filter
     */
    public boolean isPass(File file){
        return true;
    }
}
