package orders;

import java.io.File;
import java.util.Comparator;

/**
 * A parent class for the orders objects
 */
public abstract class MainOrder implements Comparator<File>{
    String PERIOD_TO_SPLIT = "\\.";
    String PERIOD = ".";

    boolean isReversed; // the flag signs if the its a reversed order

    /**
     * A constructor for the MainOrder class
     * @param reverse -the flag signs if the its a reversed order
     */
    public MainOrder (boolean reverse){
        isReversed = reverse;
    }

}
