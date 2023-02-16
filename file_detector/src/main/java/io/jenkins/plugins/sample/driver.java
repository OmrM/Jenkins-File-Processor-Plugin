package io.jenkins.plugins.sample;

import io.jenkins.plugins.sample.FileObjectCreation;

import java.io.IOException;
import java.util.ArrayList;

public class driver {

    //change to accept an array of arrays and create a for loop to enter each to make it.
    public static void main(ArrayList<String> pdfArray, String dir) throws IOException {

        System.out.print("Running second program\n");
        FileObjectCreation createobj = new FileObjectCreation();

        //createobj.createDocxObjects(docxarray);
        createobj.createPdfObjects(pdfArray, dir);

        //for(DocxFile docs: createobj.getListOfDocxObjects()){
         //   System.out.println(docs.getFileName()+"\n");
        //}

        for(PdfFile pdf: createobj.getListOfPdfObjects()){

            System.out.println("name of file: " + pdf.getFileName()+"\n");
            System.out.println("name of author: " + pdf.getAuthor()+"\n");
            System.out.println("page count: " + pdf.getPageCount()+"\n");
            System.out.println("file size: " + pdf.getFileSize()+"\n");
            System.out.println("word count: " + pdf.getWordCount()+"\n");
            System.out.println("date created: " + pdf.getDateOfCreation()+"\n\n-------------------");

        }

    }
}
