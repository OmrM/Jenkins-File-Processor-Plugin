package io.jenkins.plugins.fileProcessor;

import java.io.IOException;
import java.util.ArrayList;

public class FileObjectCreation {

    private static String DIRECTORY ;
    private ArrayList<DocxFile> listOfDocxObjects = new ArrayList<>();
    private ArrayList<DocxFile> listOfPptxObjects = new ArrayList<>();
    private ArrayList<PdfFile> listOfPdfObjects = new ArrayList<>();


    public ArrayList<DocxFile> getListOfDocxObjects() {
        return this.listOfDocxObjects;
    }
    public ArrayList<PdfFile> getListOfPdfObjects(){ return this.listOfPdfObjects;}
    public void addDocxObject(DocxFile file){
        this.listOfDocxObjects.add(file);
    }
    public void addPdfObject(PdfFile file){ this.listOfPdfObjects.add(file);};
    public void createDocxObjects(ArrayList<String> docx){
        for(String file: docx){
            addDocxObject(new DocxFile(file,this.DIRECTORY));
        }
    }
    public void createPdfObjects(ArrayList<String> pdf, String dir) throws IOException {
        DIRECTORY = dir;
        for(String file:pdf) {
            addPdfObject(new PdfFile(file, this.DIRECTORY));
        }
    }



    public static void main(String dir){

    }

}
