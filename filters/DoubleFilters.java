package filters;

/**
 * Double filter - a parent class for the filters with double arguments.
 */
public class DoubleFilters {

    double valueToCompare1; // the first double argument to filter according to
    double valueToCompare2; // the second double argument to filter according to
    boolean isNot; //the flag signs if the its a not filter

    /**
     * A constructor for all filers receiving 1 double arguments - greater_than, smaller_than
     * @param value1 - the filer argument
     * @param not - the flag signs if the its a not filter
     */
    public DoubleFilters(double value1, boolean not){
        valueToCompare1 = value1;
        isNot = not;
    }
    /**
     * a constructor for filter receiving 2 double arguments - between
     * @param lowerValue - the lower value to check
     * @param higherValue- the higher value to check
     * @param not - the flag signs if the its a not filter
     */
    public DoubleFilters(double lowerValue,double higherValue, boolean not){
        valueToCompare1 = lowerValue;
        valueToCompare2 = higherValue;
        isNot = not;
    }
}
