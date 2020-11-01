package orders;
import java.io.File;

/**
 * An Type order object implementing the Order class.
 */
public class Type extends MainOrder implements Order {
    final int ZERO =0;
    final int ONE=1;

    /**
     * A constructor for Type order
     * @param isReversed -the flag signs if the its a reversed order
     */
    public Type(boolean isReversed){
        super(isReversed);
    }

    /**
     * this orders compare method
     * @param file1 - the first file to compare
     * @param file2 - the second file to compare
     * @return 0- if their size is equal, less then 0 if file's 2 type greater then
     * file 1's or greater then 0 if its the other way around. if the reversed flag is on, its reversed.
     */
    public int compare(File file1, File file2){
        String file1Type = getType(file1);
        String file2Type = getType(file2);
        if(file1Type.equals(file2Type)){
            Order newAbsOrder = new Abs(isReversed);
            return newAbsOrder.compare(file1,file2);
        }
        if(isReversed) {
            return file2Type.compareTo(file1Type);
        }
        return file1Type.compareTo(file2Type);
    }

    /**
     * A method that returns the file's type
     * @param file - the file to which were searching the type
     * @return the type of the file
     */
    public String getType (File file){
        if (!file.getName().contains(PERIOD)){
            return "";
        }
        else {
            String theName =file.getName();
            String[] fileToArray = theName.split(PERIOD_TO_SPLIT);
            if(fileToArray.length ==ZERO){
                return "";
            }
            return fileToArray[fileToArray.length-ONE];
        }
    }
}
