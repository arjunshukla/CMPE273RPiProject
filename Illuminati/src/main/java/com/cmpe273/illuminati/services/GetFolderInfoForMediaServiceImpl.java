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


    /*
            function to test
     */

    @Override
    public void getFolderInfoFromMedia(){
        List<File> fileList;
        fileList=getFilelistFromMedia("/Volumes/WININSTALL");
        System.out.println("Files under : /Volumes/WININSTALL : " );
        displayFolderInfo(fileList);
    }



    /*
            returns the list of files and folder under given directory
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
    public void displayFolderInfo(List<File> fileList){

        for(File file:fileList){
            System.out.println(file.getAbsolutePath());
        }

    }

}


