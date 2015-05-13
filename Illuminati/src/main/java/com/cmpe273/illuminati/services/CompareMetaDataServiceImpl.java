package com.cmpe273.illuminati.services;

import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjunshukla on 5/9/15.
 */
@Service
public class CompareMetaDataServiceImpl implements CompareMetaDataService {

    @Override
    public ArrayList<String>[] returnFileListToUploadAndDownload() throws IOException, DbxException {
        /*  Method implementation:
            1. Call Praveen’s method to get deltas as arrayList for upload and download
            2. Identify the modified files from given delta ArratLists
            3. return the result as an array of ArrayLists to Sneha for upload and download operations
        */
        try {
//        List<File> arrListLocal = new List<String>();
        GetFolderInfoForDrpBoxServiceImpl objGetFolderInfo = new GetFolderInfoForDrpBoxServiceImpl();
        DbxEntry.WithChildren arrFileListFromServer;
        arrFileListFromServer = objGetFolderInfo.getFilelistFromDrpBox("/Volumes");
        for(DbxEntry child: arrFileListFromServer.children){
                System.out.println(child);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        return null;//new ArrayList<String>();
    }
}
