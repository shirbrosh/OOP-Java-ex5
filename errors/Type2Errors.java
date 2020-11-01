package errors;
import filesprocessing.Section;

/**
 * A Type2Error objects. finds error of type 2 in the command file
 */
public class Type2Errors{
    private final static String FILTER ="FILTER";
    private final static String ORDER = "ORDER";
    private final static String FILTER_EXCEPTION = "ERROR: There is a problem with the FILTER sub-section";
    private final static String ORDER_EXCEPTION = "ERROR: There is a problem with the ORDER sub-section";
    private final static String ARGS_EXCEPTION = "ERROR: There is a problem with the received arguments";
    private final static String MISSING_EXCEPTION = "ERROR: missing FILTER/ORDER";
    private final static int NO_LINE= 0;
    private final static int FILTER_LINE= 0;
    private final static int ORDER_LINE= 2;
    private final static int NUM_OF_ARGUMENTS= 2;
    private final static int LEGAL_SECTION_SIZE1 =3;
    private final static int LEGAL_SECTION_SIZE2 =4;

    Section section; //the section to test
    String[] theArgs; //the received arguments to the DirectoryProcessor

    /**
     * A Type2Error object constructor
     * @param thisSection the section to test
     * @param args the received arguments to the DirectoryProcessor
     */
    public Type2Errors(Section thisSection, String[] args){
        section = thisSection;
        theArgs = args;
    }

    public void checkErrors ()throws Exception{
        //checks the number of received arguments
        if(theArgs.length!=NUM_OF_ARGUMENTS){
            throw new Exception(ARGS_EXCEPTION);
        }

        //checks is the section is legal
        if(section.getSectionLines().size()!= LEGAL_SECTION_SIZE1 &&
                section.getSectionLines().size()!= LEGAL_SECTION_SIZE2){
            throw new Exception(MISSING_EXCEPTION);
        }

        //checks if the FILTER line exists and was written well
        if(!section.getSectionLines().get(FILTER_LINE).equals(FILTER)){
            throw new Exception(FILTER_EXCEPTION);
        }

        //checks if there is no FILTER line
        if (section.getFilterLine() ==NO_LINE){
            throw new Exception(FILTER_EXCEPTION);
        }

        //checks if the ORDER line exists and was written well
        if (!section.getSectionLines().get(ORDER_LINE).equals(ORDER)) {
            throw new Exception(ORDER_EXCEPTION);
        }
    }
}

