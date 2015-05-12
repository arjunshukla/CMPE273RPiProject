package com.cmpe273.illuminati.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveenkarkhile on 5/9/15.
 */


@Service
public class GetFolderInfoForMediaServiceImpl implements GetFolderInfoForMediaService{
    /**
     * Checks for the new file added in Local drive
     * @return array list of new files
     */

    @Override
    public List<File> getFilelistFromMedia(String directoryName){
        File directory = new File(directoryName);
        List<File> fileList =new ArrayList<File>();
        File[] fList = directory.listFiles();
        for(File file : fList){
            fileList.add(file);
        }
        return fileList;
    }

    @Override
    public void getFolderInfoFromMedia(){
        List<File> fileList;
        fileList=getFilelistFromMedia("/Users/praveenkarkhile/Documents/MortgageApplication");
        System.out.println("Files under : /Users/praveenkarkhile/MortgageApplication : " );
        displayFolderInfo(fileList);
    }

    @Override
    public void displayFolderInfo(List<File> fileList){

        for(File file:fileList){
            System.out.println(file.getAbsolutePath());
        }

    }

}


