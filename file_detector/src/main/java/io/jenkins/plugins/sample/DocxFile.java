package io.jenkins.plugins.sample;

import java.util.Date;
import java.util.HashMap;

public class DocxFile {



    public DocxFile(String name, String Directory){
        this.fileName = name;
        this.Directory = Directory;
    }

    private String Directory;
    private int wordCount;
    private int pageCount;
    private String author;
    private int fileSize;
    private Date dateOfCreation;
    private String fileName;
    //Stores each links response code
    private HashMap<String, Integer> linksInFile = new HashMap<String, Integer>();
    //Stores emails and if they're valid
    private HashMap<String, Integer> emailsInFile = new HashMap<String, Integer>();
    private HashMap<String, String> fileValidation = new HashMap<String, String>(){{
        put("emails","null");
        put("links", "null");
        put("grammar", "null");
    }};

    public void setWordCount(Integer count){
        this.wordCount = count;
    }
    public Integer getWordCount(){
        return this.wordCount;
    }
    public void setPageCount(int count){
        this.pageCount = count;
    }
    public int getPageCount(){
        return this.pageCount;
    }
    public void setAuthor(String name){
        this.author = name;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setFileSize(int size){
        this.fileSize = size;
    }
    public int getFileSize(){
        return this.fileSize;
    }
    public void setDateOfCreation(Date date){
        this.dateOfCreation = date;
    }
    public Date getDateOfCreation(){
        return this.dateOfCreation;
    }
    //returns whether there is a flag in emails, links, or grammar.
    public String getErrorFlag(String validate){
        return this.fileValidation.get(validate);
    }
    public void setErrorFlag(String validate, String error){
        this.fileValidation.put(validate, error);
    }

    //returns the response code for a validating a specific email
    public Integer getEmailResponse(String email){
        return this.emailsInFile.get(email);
    }
    public void setEmailResponse(String email, Integer code){
        this.emailsInFile.put(email, code);
    }

    public String getFileName() {
        return this.fileName = fileName;
    }
    public void setLinksResponseInFile(String link, Integer code){
        this.linksInFile.put(link, code);
    }
    public Integer getLinkResponseCode(String link){
        return this.linksInFile.get(link);
    }

    public void OutPutJson(){

    }



}
