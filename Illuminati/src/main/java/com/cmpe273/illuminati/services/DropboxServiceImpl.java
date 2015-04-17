package com.cmpe273.illuminati.services;

import com.dropbox.core.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
        File inputFile = new File("hello.txt");
        FileInputStream inputStream = new FileInputStream(inputFile);
        try {
            DbxEntry.File uploadedFile = client.uploadFile("/hello.txt",
                    DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
        } finally {
            inputStream.close();
        }
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
}
