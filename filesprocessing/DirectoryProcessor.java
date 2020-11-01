package filesprocessing;

import errors.Type1Error;
import errors.Type2Errors;
import filters.Filter;
import filters.FilterFactory;
import orders.Order;
import orders.OrderFactory;
import orders.Sort;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A DirectoryProcessor Object
 */
public class DirectoryProcessor {
    public static void main(String[] args) {
        final int ZERO=0;
        final int ONE=1;
        File directory = new File(args[0]);
        File[] allFiles = directory.listFiles();
        File commandFile = new File(args[1]);

        File[] onlyFiles = Arrays.stream(allFiles).filter(x-> x.isFile()).toArray(File[]::new);
        ArrayList<File> onlyFilesArrayList= new ArrayList<>(Arrays.asList(onlyFiles));
        List<Section> SectionArray;

        //try to catch an I/O ERROR
        try {
            ReadCommandFile readCommandFile = new ReadCommandFile(commandFile);
            SectionArray = readCommandFile.fileToArray();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return;
        }

        //handles the case of empty section
        if(SectionArray == null){
            return;
        }
        //catch all type2 errors
        for(Section section:SectionArray) {
            try {
                Type2Errors type2Errors = new Type2Errors(section, args);
                type2Errors.checkErrors();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return;
            }
        }
        // for every section preforms the following:
        for(Section section:SectionArray) {

            //print warnings
            Type1Error type1Error = new Type1Error(section);
            //warnings related to filters
            try {
                type1Error.checkWarningsFilter();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            //warnings related to orders
            try {
                if(section.getOrderLine()!=ZERO) {
                    type1Error.checkWarningsOrder();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            //builds the matching filter
            FilterFactory filterFactory = new FilterFactory();
            Filter theFilter = filterFactory.theSectionsFilter(section);

            //builds the matching order
            OrderFactory orderFactory = new OrderFactory();
            Order theOrder = orderFactory.theSectionsOrder(section);

            //sort the files according to the given order, using my implement of quick sort
            Sort sort = new Sort(onlyFilesArrayList, theOrder);
            sort.sortFunc(0,onlyFilesArrayList.size()-ONE);

            //loops over the files, filters according to the given filter and prints the matches
            for (File file : onlyFilesArrayList) {
                if (theFilter.isPass(file)) {
                    System.out.println(file.getName());
                }
            }
        }
    }
}
