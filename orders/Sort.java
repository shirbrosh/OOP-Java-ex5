package orders;

import java.io.File;
import java.util.ArrayList;

/**
 * The sort class - it will sort the files according to the selected comparator (order)
 */
public class Sort {
    private final int ONE=1;
    private static final int COMPARE_TO_ZERO=0;

    ArrayList<File> filesArray; //the files Array to sort
    Order comparator; //the order (comparator) to sort according to
    public Sort(ArrayList<File> thisFilesArray, Order thisComp){
        filesArray= thisFilesArray;
        comparator =thisComp;
    }

    /** This function takes last element as pivot, places the pivot element at its correct position in
     * sorted array, and places all smaller (smaller than pivot) to left of pivot and all greater
     * elements to right of pivot- according to the given comparator.
     * @param low- Starting index
     * @param high- Ending index
     * @return the partitioning index
     */
    private int partition(int low, int high){
        File pivot = filesArray.get(high);
        int i = low-ONE;
        for(int j=low; j<high; j++){
            if(comparator.compare(filesArray.get(j), pivot)<=COMPARE_TO_ZERO){
                i++;
                File temp =filesArray.get(i);
                filesArray.set(i,filesArray.get(j));
                filesArray.set(j,temp);
            }

        }
        File temp = filesArray.get(i+ONE);
        filesArray.set(i+ONE,filesArray.get(high));
        filesArray.set(high,temp);
        return i+ONE;
    }

    /**
     * The main function that implements QuickSort
     * @param low -Starting index
     * @param high - Ending index
     */
    public void sortFunc(int low, int high){
        if(low< high) {
            int pi = partition(low, high);

            sortFunc(low, pi - ONE);
            sortFunc(pi + ONE, high);
        }
    }
}
