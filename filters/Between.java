package filters;

import java.io.File;

/**
 * A between Filter object implementing the filter class
 */
public class Between extends DoubleFilters implements Filter {
    /**
     * a constructor for between filter
     * @param lowerValue - the lower value to check
     * @param higherValue- the higher value to check
     * @param isNot - the flag signs if the its a not filter
     */
    public Between(double lowerValue,double higherValue, boolean isNot){
       super(lowerValue,higherValue, isNot);
    }

    /**
     * A method that checks if a given file can pass this filter
     * @param file - the file to check
     * @return true if the file passed the filter or false otherwise
     */
    public boolean isPass(File file){
        if(isNot){
            return file.length() <= valueToCompare1*BYTES ||
                    file.length() >= valueToCompare2*BYTES;
        }
        return file.length() >= valueToCompare1*BYTES &&
                file.length() <= valueToCompare2*BYTES;
    }
}
