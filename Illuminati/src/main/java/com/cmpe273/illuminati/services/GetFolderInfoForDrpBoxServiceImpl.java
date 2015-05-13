package com.cmpe273.illuminati.services;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveenkarkhile on 5/11/15.
 */


@Service
public class GetFolderInfoForDrpBoxServiceImpl implements GetFolderInfoForDrpBoxService{

    /*
            function to test
     */

    @Override
    public List<DbxEntry> getFolderInfoFromDrpBox() throws IOException, DbxException {
        List<DbxEntry> fileList=new ArrayList<DbxEntry>();
        fileList = getFilelistFromDrpBox("/Volumes/WININSTALL",fileList);


        displayFolderInfo(fileList);

        return fileList;
    }

    /*
            returns the list of files and folder under given directory
     */

    public List<DbxEntry> getFilelistFromDrpBox(String directoryName, List<DbxEntry> fileList) throws IOException, DbxException {
        DbxClient dbxclient= ProjectUtil.authorize();
        DbxEntry.WithChildren list= dbxclient.getMetadataWithChildren(directoryName);
        for(DbxEntry child: list.children){
            if(child.isFolder()){
                fileList.add(child);
                getFilelistFromDrpBox(child.path.toString(),fileList);
            }else{
                if(child.isFile()){
                    fileList.add(child);
                }
            }
        }
        return fileList;
    }

    public void displayFolderInfo(List<DbxEntry> fileList){

        for(DbxEntry file:fileList){
            if(file.isFile())
            System.out.println("From Dropbox: "+file.path + "Modified at: "+ file.asFile().lastModified);
            else
            {
                System.out.println("From Dropbox: "+file.path);
            }
        }
    }


    public DbxClient getDroboxClient() throws IOException, DbxException {
        DbxClient dbxclient= ProjectUtil.authorize();
        return dbxclient;
    }
}
