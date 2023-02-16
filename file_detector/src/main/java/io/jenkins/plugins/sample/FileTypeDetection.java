package io.jenkins.plugins.sample;

import java.io.FileFilter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileTypeDetection {
    public FileTypeDetection(String DIRECTORY){
        this.DIRECTORY = DIRECTORY;
    }
    private static String DIRECTORY;//absolute path to input

    public static String getDirectory(){
        return DIRECTORY;
    }
    public static void setDIRECTORY(String dir){
        DIRECTORY = dir;

    }

    //getting file counts --> convert counts to hashmaps "name / count" easier to access and less commands.

    private static HashMap<String, Integer> fileCount = new HashMap<>();

    private static int totalCount = 0;

    public static int updateTotalCount(){
        return totalCount = totalCount+1;
    }
    public static int getTotalCount(){
        return totalCount;
    }
    //data structures to store names of files in dependent on file type
    private static ArrayList<String> pptxNames = new ArrayList<>();
    private static ArrayList<String> docxNames= new ArrayList<>();
    private static ArrayList<String> pdfNames = new ArrayList<>();

    //if data structure for a specific file type is not created and added to the file organization then it will be added here
    private static ArrayList<String> unknownNames = new ArrayList<>();

    public static ArrayList<ArrayList<String>>  fileArrayOfArrays= new ArrayList<>();

    public static void addArrayToArrayOfArrays(ArrayList<String> fileArray){
        fileArrayOfArrays.add(fileArray);
    }
    public static ArrayList<ArrayList<String>> getfileArrayOfArrays(){
        return fileArrayOfArrays;
    }


    public static void main(String[] args) throws IOException {

        setDIRECTORY(args[0]);

        System.out.println("\n\t Traversing files in directory: " + DIRECTORY + "\n");

        //Begins traversing file directory
        try {

            for (File f : new File(DIRECTORY).listFiles()) {

                updateTotalCount();
                String fileName = f.toString();
                System.out.println("File name: " + f.getName());

                //takes extension of a file
                int index = fileName.lastIndexOf('.');
                if (index > 0) {

                    String extension = fileName.substring(index + 1);

                    if (!fileCount.containsKey(extension)) {
                        fileCount.put(extension, 1);

                    } else {

                        fileCount.put(extension, fileCount.get(extension) + 1);

                    }
                    //storing doc names into appropriate data structures
                    if(extension.equals("docx")){
                        docxNames.add(f.getName());
                    }
                    else if(extension.equals("pptx")){
                        pptxNames.add(f.getName());
                    }
                    else if(extension.equals("pdf")){
                        pdfNames.add(f.getName());
                    }
                    else{
                        unknownNames.add(f.getName());
                    }
                }
            }

            //printing content of each array
            System.out.println(
                    "---------------------------------------------\n" +
                    "\t\tfiles in docx\n" +
                    "---------------------------------------------");

            for (String s : docxNames) {
                System.out.println("|\t"+ s + "\t\t\t|\n");
            }
            System.out.println(
                    "---------------------------------------------\n" +
                            "\t\tfiles in pptx\n" +
                            "---------------------------------------------");

            for (String s : pptxNames) {
                System.out.println("|\t"+ s + "\t\t\t|\n");
            }
            System.out.println(
                    "---------------------------------------------\n" +
                            "\t\tfiles in pdf\n" +
                            "---------------------------------------------");

            for (String s : pdfNames) {
                System.out.println("|\t"+ s + "\t\t\t|\n");
            }
            System.out.println(
                    "---------------------------------------------\n" +
                            "\t\tOther files\n" +
                            "---------------------------------------------");

            for (String s : unknownNames) {
                System.out.println("|\t"+ s + "\t\t\t|\n");
            }
        }catch(NullPointerException e){
            System.out.println("File not found: " + e);
        }

        driver.main(pdfNames, getDirectory());




    }

}
