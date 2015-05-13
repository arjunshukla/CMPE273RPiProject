package com.cmpe273.illuminati.services;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by praveenkarkhile on 5/9/15.
 */

@Service
public class GetFolderInfoForMediaServiceImpl implements GetFolderInfoForMediaService{

    /*
            returns the list of files and folder under given directory
     */

    @Override
    public List<File> getFolderInfoFromMedia() throws IOException {
        List<File> fileList= new ArrayList<File>();
        File folder = new File("/Volumes/WININSTALL");
        System.out.println("Reading files under the folder "+ folder.getAbsolutePath());
        listFilesForFolder(folder, fileList);
        displayFolderInfo(fileList);
        return fileList;

    }

    /*
            Display file list
     */


    @Override
    public void displayFolderInfo(List<File> fileList){

        for(File file:fileList){
            System.out.println("From Media: "+file.getAbsolutePath() + ": Modified at : " + new Date(file.lastModified()));
        }
    }

    public void listFilesForFolder(final File folder, List<File> fileList) throws IOException{
        for (final File fileEntry : folder.listFiles()) {
            if(!(fileEntry.getName().startsWith(".")||fileEntry.getName().startsWith("$") || fileEntry.getName().startsWith("._.") || fileEntry.getName().startsWith("$")
                    || fileEntry.getName().equals("thumbs.db")
                    || fileEntry.getName().equals("icon\\r"))){
                if (fileEntry.isDirectory()) {
                    fileList.add(fileEntry);
                    listFilesForFolder(fileEntry, fileList);
                } else {
                    if (fileEntry.isFile()) {
                        fileList.add(fileEntry);
                    }
                }
            }
        }
    }
}


