package filters;

/**
 * String Filter - a parent class for the filters with String arguments.
 */
public class StringFilter {
    String valueToCheck; // the String argument to filter according to
    boolean isNot; //the flag signs if the its a not filter

    /**
     * A constructor for all filers receiving String arguments
     * @param filename - the filer argument
     * @param not - the flag signs if the its a not filter
     */
    public StringFilter(String filename, boolean not){
        valueToCheck = filename;
        isNot = not;
    }
}
