package com.cmpe273.illuminati.services;

import com.dropbox.core.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

/**
 * Created by ASHU on 16-04-2015.
 */
@Service
public class DropboxServiceImpl extends HttpServlet implements DropboxService {

    @Override
    public void authorize() throws  IOException, DbxException {
        upload(ProjectUtil.authorize());
    }

    @Override
    public void upload(DbxClient client) throws IOException, DbxException {
        File folder = new File("Volumes/YOSEMITE/Users/arjunshukla/Dropbox/Public");//"/Volumes/WININSTALL");
//        /Users/arjunshukla/Dropbox
        System.out.println("Reading files under the folder "+ folder.getAbsolutePath());
        listFilesForFolder(folder, client);
/*      File inputFile = new File(file.getName());
        FileInputStream inputStream = new FileInputStream(inputFile);
        try {
            DbxEntry.File uploadedFile = client.uploadFile("/D:/HelloFeatureIDE",
                    DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
        } finally {
            inputStream.close();
        }*/
    }

    @Override
    public void getMetadata(DbxClient client) throws DbxException {
        DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
        System.out.println("Files in the root path:");
        for (DbxEntry child : listing.children) {
            System.out.println("	" + child.name + ": " + child.toString());
        }
    }

    @Override
    public void download(DbxClient client)throws IOException, DbxException {
        FileOutputStream outputStream = new FileOutputStream("helloFromDropBox.txt");
        try {
            DbxEntry.File downloadedFile = client.getFile("/hello.txt", null,
                    outputStream);
            System.out.println("Metadata: " + downloadedFile.toString());
        } finally {
            outputStream.close();
        }
    }

    public void listFilesForFolder(final File folder, DbxClient client) throws IOException, DbxException {
        String filepath = null;
        String filepath1 = null;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                // System.out.println("Reading files under the folder "+folder.getAbsolutePath());
                listFilesForFolder(fileEntry, client);
            } else {
                if (fileEntry.isFile()) {
                    System.out.println("Path= " + folder.getAbsolutePath().replaceAll("\\\\","/")+"/"+fileEntry.getName());
                        //System.out.println("File= " + folder.getAbsolutePath()+ "\\" + fileEntry.getName());
                    filepath1 = "/"+folder.getAbsolutePath().replaceAll("\\\\","/")+"/"+fileEntry.getName();
                    filepath = folder.getAbsolutePath().replaceAll("\\\\","/")+"/"+fileEntry.getName();

                    System.out.println("filePath1:" + filepath1 + "\n" + "FilePath: " + filepath);

                    File inputFile = new File(filepath);
                    FileInputStream inputStream = new FileInputStream(inputFile);
                    try {
                        DbxEntry.File uploadedFile = client.uploadFile(filepath1,
                                DbxWriteMode.add(), inputFile.length(), inputStream);
                        System.out.println("Uploaded: " + uploadedFile.toString());
                    } finally {
                        inputStream.close();
                    }
                }

            }
        }
    }
}
