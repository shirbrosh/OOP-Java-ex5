package filesprocessing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ReadCommandFile object, reads the given command file and helps divide it to sections
 */
public class ReadCommandFile {
    private final static String FILTER = "FILTER";
    private final static int ZERO = 0;
    private final static int ONE = 1;

    File commandFile; //the command file to read

    /**
     * A constructor for ReadCommandFile object
     * @param file
     */
    public ReadCommandFile(File file){
        commandFile = file;
    }

    /**
     * A method that reads the given command file and helps divide it to sections
     * @return a Section object Array containing all the sections in this command file
     * @throws IOException
     */
    public  List<Section> fileToArray() throws IOException {
        Reader inFile = new FileReader(commandFile);
        BufferedReader inBuffer = new BufferedReader(inFile);
        String line = (inBuffer).readLine();
        List<Section> sectionArray= new ArrayList<>();
        ArrayList<String> sectionLinesArray= new ArrayList<>();
        int startSectionLine=ONE;
        int currentLine=ONE;
        while (line!=null){

            //checks the case the section is empty and i start to fill if
            if (sectionLinesArray.size()==ZERO){
                sectionLinesArray.add(line);
                line = (inBuffer).readLine();
                currentLine +=ONE;
                continue;
            }

            //the case we reach a FILTER line and its for sure a new section
            if (line.equals(FILTER) && currentLine-startSectionLine!=ONE){
                Section newSection = new Section(sectionLinesArray, startSectionLine);
                sectionArray.add(newSection);
                sectionLinesArray= new ArrayList<>();
                startSectionLine=currentLine;
                continue;
            }

            //the case the line is in the middle of a section
            sectionLinesArray.add(line);
            currentLine+=1;
            line = (inBuffer).readLine();
        }

        //the case where the section is empty
        if(sectionLinesArray.size() == ZERO){
            return null;
        }
        Section newSection = new Section(sectionLinesArray, startSectionLine);
        sectionArray.add(newSection);
        return sectionArray;
    }
}
