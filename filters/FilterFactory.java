package filters;

import filesprocessing.Section;

/**
 * This class acts as the driver to create all Filter objects
 */
public class FilterFactory {
    /**
     * creates all the Filter objects according to the filer's name
     * @param section - the section to extract the filter from
     * @return theFilter -the filter of the given section
     */
    public static Filter theSectionsFilter(Section section){
        final String GREATER_THAN = "greater_than";
        final String BETWEEN = "between";
        final String SMALLER_THAN = "smaller_than";
        final String FILE = "file";
        final String CONTAINS = "contains";
        final String PREFIX = "prefix";
        final String SUFFIX = "suffix";
        final String WRITABLE = "writable";
        final String EXECUTABLE = "executable";
        final String HIDDEN = "hidden";
        final String SEPARATOR = "#";
        final String NOT = "NOT";
        final int ZERO=0;
        final int ONE=1;
        final int ARGUMENT_INDEX=1;
        final int SECOND_ARGUMENT_INDEX=2;

        Filter theFilter = null;
        boolean isNot =false;
        String [] splitFilterArray  =section.getTheFilter().split(SEPARATOR);
        String filter = splitFilterArray[ZERO];
        if(splitFilterArray[splitFilterArray.length-ONE].equals(NOT)){
            isNot= true;
        }

        // switch case over the existing filters
        switch (filter){
            case GREATER_THAN:
                theFilter = new GreaterThan(Double.parseDouble(splitFilterArray[ARGUMENT_INDEX]), isNot);
                break;
            case BETWEEN:
                theFilter = new Between(Double.parseDouble(splitFilterArray[ARGUMENT_INDEX]),
                        Double.parseDouble(splitFilterArray[SECOND_ARGUMENT_INDEX]),isNot);
                break;
            case SMALLER_THAN:
                theFilter = new SmallerThan(Double.parseDouble(splitFilterArray[ARGUMENT_INDEX]),isNot);
                break;
            case FILE:
                theFilter = new FileFilter(splitFilterArray[ARGUMENT_INDEX],isNot);
                break;
            case CONTAINS:
                theFilter = new Contains(splitFilterArray[ARGUMENT_INDEX],isNot);
                break;
            case PREFIX:
                theFilter = new Prefix(splitFilterArray[ARGUMENT_INDEX],isNot);
                break;
            case SUFFIX:
                theFilter = new Suffix(splitFilterArray[ARGUMENT_INDEX],isNot);
                break;
            case WRITABLE:
                theFilter = new Writable(splitFilterArray[ARGUMENT_INDEX],isNot);
                break;
            case EXECUTABLE:
                theFilter = new Executable(splitFilterArray[ARGUMENT_INDEX],isNot);
                break;
            case HIDDEN:
                theFilter = new Hidden(splitFilterArray[ARGUMENT_INDEX],isNot);
                break;
            default:
                theFilter = new All();
                break;
        }
        return theFilter;
    }
}
