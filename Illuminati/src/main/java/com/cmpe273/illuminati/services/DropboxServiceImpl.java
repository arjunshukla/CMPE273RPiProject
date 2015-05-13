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
    public void upload() throws IOException, DbxException {
        File folder = new File("/Volumes/WININSTALL");
        DbxClient client = ProjectUtil.authorize();
        System.out.println("Reading files under the folder "+ folder.getAbsolutePath());
        listFilesForFolderUpload(folder, client);
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
    public void download()throws IOException, DbxException {
        DbxClient client = ProjectUtil.authorize();
        String path = "/Volumes/WININSTALL";
        listFilesForFolderDownload(path, client);
/*      FileOutputStream outputStream = new FileOutputStream("helloFromDropBox.txt");
        try {
            DbxEntry.File downloadedFile = client.getFile("/hello.txt", null,
                    outputStream);
            System.out.println("Metadata: " + downloadedFile.toString());
        } finally {
            outputStream.close();
        }*/
    }

    public void listFilesForFolderUpload(final File folder, DbxClient client) throws IOException, DbxException {
        String filepath = null;
        String filepath1 = null;
        for (final File fileEntry : folder.listFiles()) {

            if(!(fileEntry.getName().startsWith(".")||fileEntry.getName().startsWith("$") || fileEntry.getName().startsWith("._.") || fileEntry.getName().startsWith("$")
                    || fileEntry.getName().equals("thumbs.db")
                    || fileEntry.getName().equals("icon\\r"))) {
                if (fileEntry.isDirectory()) {
                    // System.out.println("Reading files under the folder "+folder.getAbsolutePath());
                    listFilesForFolderUpload(fileEntry, client);
                } else {
                    if (fileEntry.isFile()) {
                        System.out.println("Path= " + folder.getAbsolutePath().replaceAll("\\\\", "/") + "/" + fileEntry.getName());
                        //System.out.println("File= " + folder.getAbsolutePath()+ "\\" + fileEntry.getName());
                        filepath1 = "/" + folder.getAbsolutePath().replaceAll("\\\\", "/") + "/" + fileEntry.getName();
                        filepath = folder.getAbsolutePath().replaceAll("\\\\", "/") + "/" + fileEntry.getName();

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

    public void listFilesForFolderDownload(String path, DbxClient client) throws IOException, DbxException {
        DbxEntry.WithChildren list= client.getMetadataWithChildren(path);
        for(DbxEntry child: list.children){
            if(child.isFolder()){
                File folder = new File(child.path);
                System.out.println("creating: " + folder.getPath());
                folder.mkdir();
                listFilesForFolderDownload(child.path, client);
            }else{
                if(child.isFile()){
                    FileOutputStream outputStream = new FileOutputStream(child.path);
                    try {
                        DbxEntry.File downloadedFile = client.getFile(child.path, null,
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
