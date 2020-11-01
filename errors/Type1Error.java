package errors;
import filesprocessing.Section;

/**
 * A Type1Error objects. finds error of type 1 in the command file
 */
public class Type1Error {
    private final String[] FILTERS = {"greater_than","between","smaller_than","file","contains","prefix",
            "suffix","writable","executable","hidden","all"};
    private final static String[] ORDERS = {"abs","type","size"};
    private final static String SEPARATOR = "#";
    private final static String WARNING_MSG="Warning in line ";
    private final static String NO = "NO";
    private final static String YES = "YES";
    private final static String GREATER_THAN = "greater_than";
    private final static String BETWEEN = "between";
    private final static String SMALLER_THAN = "smaller_than";
    private final static String WRITABLE = "writable";
    private final static String EXECUTABLE = "executable";
    private final static String HIDDEN = "hidden";
    private final static String ALL= "all";
    private final static String ABS= "abs";
    private final static String NOT= "NOT";
    private final static int EXPECTED_ARGUMENTS_NUM =2;
    private final static int EXPECTED_ARGUMENTS_NUM_BETWEEN =3;
    private final static int EXPECTED_ARGUMENTS_NUM_ALL =1;
    private final static int LOCATION_FIRST_ARG =1;
    private final static int LOCATION_SECOND_ARG =2;
    private final static int ZERO =0;
    private final static int ONE =1;


    Section section; //the section to test

    /**
     * A Type1Error object constructor
     * @param thisSection the section to test
     */
    public Type1Error(Section thisSection){
        section = thisSection;
    }

    public void checkWarningsFilter() throws Exception{
        int filterLine=section.getFilterLine()-section.getStartSectionLine();
        String theFilterToCheck = section.getSectionLines().get(filterLine).split(SEPARATOR)[ZERO];

        //checks if the filter name is valid
        validFilterName(theFilterToCheck);


        //checks that there is exactly 2 argument for all filters excluding between and all
        if(!theFilterToCheck.equals(BETWEEN)&& !theFilterToCheck.equals(ALL)){
            if(!checkNumArgs(EXPECTED_ARGUMENTS_NUM, filterLine)){
                section.setTheFilter(ALL);
                throw new Exception(WARNING_MSG+section.getFilterLine());
            }
        }

        //checks that there is exactly 3 argument for between filter
        if (theFilterToCheck.equals(BETWEEN)){
            if(!checkNumArgs(EXPECTED_ARGUMENTS_NUM_BETWEEN, filterLine)){
                section.setTheFilter(ALL);
                throw new Exception(WARNING_MSG+section.getFilterLine());
            }
        }

        //checks that there is exactly 1 argument for all filter
        if (theFilterToCheck.equals(ALL)){
            if(!checkNumArgs(EXPECTED_ARGUMENTS_NUM_ALL, filterLine)){
                section.setTheFilter(ALL);
                throw new Exception(WARNING_MSG+section.getFilterLine());
            }
        }

        //checks if the argument in the writable, executable or hidden is strictly YES or NO
        argumentsYESNO(filterLine, theFilterToCheck);

        //checks if the argument in the greater_than, smaller_than or between is are positive numbers,
        // and if the values for the between filter are valid
        argumentsGreaterSmallerBetween(filterLine, theFilterToCheck);

    }

    /**
     * checks if the argument in the greater_than, smaller_than or between is are positive numbers,
     * and if the values for the between filter are valid
     * @param filterLine - the line in the section of the given filter
     * @param theFilterToCheck - the filer to check
     * @throws Exception wrong arguments given to greater_than, smaller_than or between filers
     */
    private void argumentsGreaterSmallerBetween(int filterLine, String theFilterToCheck) throws Exception{
        if(theFilterToCheck.equals(GREATER_THAN)|| theFilterToCheck.equals(SMALLER_THAN)||
                theFilterToCheck.equals(BETWEEN)){
            double filterParam1 = Double.parseDouble(section.getSectionLines().get(filterLine)
                    .split(SEPARATOR)[LOCATION_FIRST_ARG]);
            double filterParam2 =ZERO;
            if(theFilterToCheck.equals(BETWEEN)) {
                filterParam2 = Double.parseDouble(section.getSectionLines().get(filterLine)
                        .split(SEPARATOR)[LOCATION_SECOND_ARG]);
                if(filterParam1>filterParam2){
                    section.setTheFilter(ALL);
                    throw new Exception(WARNING_MSG+section.getFilterLine());
                }
            }
            if(filterParam1 <ZERO || filterParam2 <ZERO){
                section.setTheFilter(ALL);
                throw new Exception(WARNING_MSG+section.getFilterLine());
            }
        }
    }

    /**
     * checks if the argument in the writable, executable or hidden is strictly YES or NO
     * @param filterLine- the line in the section of the given filter
     * @param theFilterToCheck the filer to check
     * @throws Exception wrong arguments given to writable, executable or hidden filter
     */
    private void argumentsYESNO(int filterLine, String theFilterToCheck) throws Exception {
        if(theFilterToCheck.equals(HIDDEN)|| theFilterToCheck.equals(WRITABLE)||
                theFilterToCheck.equals(EXECUTABLE)){
            String filterParam = section.getSectionLines().get(filterLine)
                    .split(SEPARATOR)[LOCATION_FIRST_ARG];
            if(!filterParam.equals(YES) && !filterParam.equals(NO)){
                section.setTheFilter(ALL);
                throw new Exception(WARNING_MSG+section.getFilterLine());
            }
        }
    }

    /**
     * A method that checks if the filter name is valid
     * @param theFilterToCheck -the filter name to check
     * @throws Exception - wrong Filter name
     */
    private void validFilterName(String theFilterToCheck) throws Exception {
        boolean filterLegal = false;
        for(String filter:FILTERS){
            if(theFilterToCheck.equals(filter)){
                filterLegal = true;
                break;
            }
        }
        if (!filterLegal){
            section.setTheFilter(ALL);
            throw new Exception(WARNING_MSG+section.getFilterLine());
        }
    }

    /**
     *  method that checks if the order name is valid
     * @throws Exception  wrong order name
     */
    public void checkWarningsOrder() throws Exception {
        int orderLine=section.getOrderLine()-section.getStartSectionLine();

        String theOrderToCheck;
        theOrderToCheck = section.getSectionLines().get(orderLine);
        if(theOrderToCheck.contains(SEPARATOR)) {
            theOrderToCheck = theOrderToCheck.split(SEPARATOR)[ZERO];
        }
        boolean orderLegal = false;
        for(String order:ORDERS){
            if(theOrderToCheck.equals(order)){
                orderLegal = true;
                break;
            }
        }
        if (!orderLegal){
            section.setTheOrder(ABS);
            throw new Exception(WARNING_MSG+section.getOrderLine());
        }
    }

    /**
     * A method that checks the number of arguments given to the filter
     * @param expected the number of arguments expected
     * @param filterLine the number of arguments received
     * @return true - if the expected matches the actual
     */
    public boolean checkNumArgs(int expected, int filterLine){
        String [] filerSeparated = section.getSectionLines().get(filterLine).split(SEPARATOR);
        int numArguments =  filerSeparated.length;
        boolean isNot = false;
        if (filerSeparated[filerSeparated.length-ONE].equals(NOT)) {
            isNot = true;
        }
        if(isNot){
            numArguments -=ONE;
        }
        if(expected == numArguments){
            return true;
        }
        return false;
    }
}
