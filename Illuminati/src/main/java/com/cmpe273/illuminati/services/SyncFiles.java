package com.cmpe273.illuminati.services;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxWriteMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sneha on 5/12/2015.
 */
public class SyncFiles implements SyncFilesService {

    @Override
    public void downloadFromDropbox(DbxClient client, ArrayList<String> filesToDownload) throws IOException, DbxException {
        for (String fileName : filesToDownload) {

            FileOutputStream outputStream = new FileOutputStream(fileName);
            try {
                DbxEntry.File downloadedFile = client.getFile(fileName, null,
                        outputStream);
                System.out.println("Metadata: " + downloadedFile.toString());
            } finally {
                outputStream.close();
            }
        }
    }
    @Override
    public void uploadToDropbox(DbxClient client, ArrayList<String> filesToUpload) throws IOException, DbxException {
        DropboxServiceImpl dropboxObj = new DropboxServiceImpl();
        for (String fileName : filesToUpload) {
            File inputFile = new File(fileName);
            FileInputStream inputStream = new FileInputStream(inputFile);
            try {
                DbxEntry.File uploadedFile = client.uploadFile(fileName,
                        DbxWriteMode.add(), inputFile.length(), inputStream);
                System.out.println("Uploaded: " + uploadedFile.toString());
            } finally {
                inputStream.close();
            }
           /*
            File folder = new File("D:/HelloFeatureIDE");
            File folder = new File(fileName);
            System.out.println("Reading files under the folder " + folder.getAbsolutePath());

            dropboxObj.listFilesForFolder(folder, client);
            File inputFile = new File(file.getName());
            FileInputStream inputStream = new FileInputStream(inputFile);
            try {
            DbxEntry.File uploadedFile = client.uploadFile("/D:/HelloFeatureIDE",
                    DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
            } finally {
            inputStream.close();
        }
        */
        }
    }
}
