package com.cmpe273.illuminati.services;

import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxWriteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by arjunshukla on 5/9/15.
 */
@Service
public class CompareMetaDataServiceImpl implements CompareMetaDataService {
    @Autowired
    GetFolderInfoForMediaService getFolderInfoForMediaService;

    @Autowired
    GetFolderInfoForDrpBoxService getFolderInfoForDrpBoxService;

    DropboxServiceImpl dropboxService = new DropboxServiceImpl();

    @Override
    public void returnFileListToUploadAndDownload() throws IOException, DbxException {
        /*  Method implementation:
            1. Call Praveen’s method to get deltas as arrayList for upload and download
            2. Identify the modified files from given delta ArratLists
            3. return the result as an array of ArrayLists to Sneha for upload and download operations
        */

        String filepath = null;
        String filepath1 = null;

        List<File> localFileList;
        localFileList = getFolderInfoForMediaService.getFolderInfoFromMedia();

        List<DbxEntry> dropBoxFileList;
        dropBoxFileList = getFolderInfoForDrpBoxService.getFolderInfoFromDrpBox();

        for(File file: localFileList) {
            for (DbxEntry dbxEntry : dropBoxFileList)
            {
                if(file.getAbsolutePath().equals(dbxEntry.path.toString()))
                {
                    if(dbxEntry.isFile())
                    {
                        if(new Date(file.lastModified()).compareTo(dbxEntry.asFile().lastModified)>0)
                        {
                            ProjectUtil.authorize().delete(dbxEntry.path);

                            FileInputStream inputStream = new FileInputStream(file.getAbsoluteFile());
                            try {
                                DbxEntry.File uploadedFile = ProjectUtil.authorize().uploadFile(file.getAbsolutePath(),
                                        DbxWriteMode.add(), file.length(), inputStream);
                                System.out.println("Uploaded: " + uploadedFile.toString());
                            } finally {
                                inputStream.close();
                            }
                        }
                        else if(new Date(file.lastModified()).compareTo(dbxEntry.asFile().lastModified)<0)
                        {
                            Files.deleteIfExists(file.toPath());
                            FileOutputStream outputStream = new FileOutputStream(dbxEntry.asFile().path);
                            try {
                                DbxEntry.File downloadedFile = ProjectUtil.authorize().getFile(dbxEntry.asFile().path, null,
                                        outputStream);
                                System.out.println("Metadata: " + downloadedFile.toString());
                            } finally {
                                outputStream.close();
                            }
                        }
                    }
                }
            }
        }
        foundNewFilesinMedia();
        foundNewFilesinDropbox();
    }

    public void foundNewFilesinMedia() throws IOException, DbxException{
        Boolean flag = false;
        List<File> localFileList;
        localFileList = getFolderInfoForMediaService.getFolderInfoFromMedia();

        List<DbxEntry> dropBoxFileList;
        dropBoxFileList = getFolderInfoForDrpBoxService.getFolderInfoFromDrpBox();

        for(File file: localFileList) {
            for (DbxEntry dbxEntry : dropBoxFileList) {
                if (file.getAbsolutePath().equals(dbxEntry.path.toString())) {
                    flag = true;
                }
            }
            if(flag == false)
            {
                FileInputStream inputStream = new FileInputStream(file.getAbsoluteFile());
                try {
                    DbxEntry.File uploadedFile = ProjectUtil.authorize().uploadFile(file.getAbsolutePath(),
                            DbxWriteMode.add(), file.length(), inputStream);
                    System.out.println("Uploaded: " + uploadedFile.toString());
                } finally {
                    inputStream.close();
                }
            }
            else
            {
                flag = false;
            }
        }
    }

    public void foundNewFilesinDropbox() throws IOException, DbxException{
        Boolean flag = false;
        List<File> localFileList;
        localFileList = getFolderInfoForMediaService.getFolderInfoFromMedia();

        List<DbxEntry> dropBoxFileList;
        dropBoxFileList = getFolderInfoForDrpBoxService.getFolderInfoFromDrpBox();

        for(DbxEntry dbxEntry : dropBoxFileList) {
            for (File file: localFileList) {
                if (file.getAbsolutePath().equals(dbxEntry.path.toString())) {
                    flag = true;
                }
            }
            if(flag == false)
            {
                FileOutputStream outputStream = new FileOutputStream(dbxEntry.asFile().path);
                try {
                    DbxEntry.File downloadedFile = ProjectUtil.authorize().getFile(dbxEntry.asFile().path, null,
                            outputStream);
                    System.out.println("Metadata: " + downloadedFile.toString());
                } finally {
                    outputStream.close();
                }
            }
            else
            {
                flag = false;
            }
        }
    }
}
