package filesprocessing;

import java.util.ArrayList;

/**
 * A Section object, this will contains all the information of a section.
 */
public class Section {
    private final static int THE_FILTER_LINE = 1;
    private final static int THE_ORDER_LINE = 3;
    private final static int COMPLETE_SECTION = 4;
    private final static int NOT_COMPLETE_SECTION = 3;
    private final static String NO_DATA = "none";
    private final static int NO_LINE= 0;

    ArrayList<String> sectionLines; //the sections lines
    String theFilter; //the filter
    String theOrder; //the order
    int filterLine; //the line in the command file of this filter
    int orderLine; //the line in the command file of this order
    int startSectionLine; //the line in the command file this section starts

    /**
     * A constructor for Section object
     * @param thisSectionsLines the section lines
     * @param thisStartSectionLine the line in the command file this section starts
     */
    public Section(ArrayList<String> thisSectionsLines, int thisStartSectionLine){
        sectionLines = thisSectionsLines;
        startSectionLine = thisStartSectionLine;
        setTheParameter();

    }

    /**
     * A method that sets the data members of this class
     */
    public void setTheParameter() {

        // the case that the section is complete
        if (sectionLines.size() == COMPLETE_SECTION) {
            theFilter = sectionLines.get(THE_FILTER_LINE);
            theOrder = sectionLines.get(THE_ORDER_LINE);
            filterLine = startSectionLine + THE_FILTER_LINE;
            orderLine = startSectionLine + THE_ORDER_LINE;
        }

        //the case that the order line is missing
        if(sectionLines.size()== NOT_COMPLETE_SECTION){
            //the case that the section does not contains ORDER parameter
            theFilter = sectionLines.get(THE_FILTER_LINE);
            theOrder = NO_DATA;
            filterLine = startSectionLine + THE_FILTER_LINE;
            orderLine = NO_LINE;
        }
    }

    /**
     * A method that returns this sections filter.
     * @return this sections filter.
     */
    public String getTheFilter(){
        return theFilter;
    }

    /**
     * A method that returns this sections order.
     * @return this sections order.
     */
    public String getTheOrder(){
        return theOrder;
    }

    /**
     * A method that returns the line in the command file of this filter
     * @return  the line in the command file of this filter
     */
    public int getFilterLine(){
        return filterLine;
    }

    /**
     * A method that returns the line in the command file of this order
     * @return  the line in the command file of this order
     */
    public int getOrderLine(){
        return orderLine;
    }

    /**
     * A method that returns this sections lines
     * @return this sections lines
     */
    public ArrayList<String> getSectionLines() {
        return sectionLines;
    }

    /**
     * A method that sets this sections filter
     * @param theFilter the filter to set
     */
    public void setTheFilter(String theFilter) {
        this.theFilter = theFilter;
    }

    /**
     * A method that sets this sections order
     * @param theOrder the order to set
     */
    public void setTheOrder(String theOrder) {
        this.theOrder = theOrder;
    }

    /**
     * A method that returns this sections start line
     */
    public int getStartSectionLine() {
        return startSectionLine;
    }
}


