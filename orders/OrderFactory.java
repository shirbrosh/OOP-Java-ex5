package orders;
import filesprocessing.Section;

/**
 * This class acts as the driver to create all Order objects
 */
public class OrderFactory {
    /**
     * creates all the orders objects according to the order's name
     * @param section - the section to extract the order from
     * @return theOrder -the order of the given section
     */
    public static Order theSectionsOrder(Section section){
        final String TYPE= "type";
        final String SIZE = "size";
        final String SEPARATOR = "#";
        Order theOrder = null;
        boolean isReversed = false;

        //checks if the order needs reversing and saves the order name
        String order = section.getTheOrder();
        if (order==null){
            return new Abs(false);
        }
        if(order.contains(SEPARATOR)){
            String[] splitOrderArray = order.split(SEPARATOR);
            order = splitOrderArray[0];
            isReversed = true;
        }
        // switch case over the existing orders
        switch (order){
            case TYPE:
                theOrder = new Type(isReversed);
                break;
            case SIZE:
                theOrder = new Size(isReversed);
                break;
            default:
                theOrder = new Abs(isReversed);
                break;
        }
        return theOrder;
    }
}
