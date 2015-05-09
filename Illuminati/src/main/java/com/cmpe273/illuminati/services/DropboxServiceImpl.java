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
        final String APP_KEY = "td0hyjgv47wprzb";
        final String APP_SECRET = "zal1xu97e6s6z6s";

        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
                Locale.getDefault().toString());
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

        // Have the user sign in and authorize your app.
        String authorizeUrl = webAuth.start();

/*        System.out.println("1. Go to: " + authorizeUrl);
        System.out.println("2. Click \"Allow\" (you might have to log in first)");
        System.out.println("3. Copy the authorization code. Paste below and hit Enter");
        String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();*/

        // This will fail if the user enters an invalid authorization code.
/*        DbxAuthFinish authFinish = webAuth.finish(code);
        String accessToken = authFinish.accessToken;*/
        String accessToken = "a6J3X_s-ZKAAAAAAAAAAIbf25z7usQ1NBYohrnkcGuzwxkV4c-O8uX8dYL4GJV2N";
        DbxClient client = new DbxClient(config, accessToken);

        System.out.println("Linked account: " + client.getAccountInfo().displayName);

        upload(client);
    }

    @Override
    public void upload(DbxClient client) throws IOException, DbxException {
        File folder = new File("D:/HelloFeatureIDE");
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
