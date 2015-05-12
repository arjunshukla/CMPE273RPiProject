package com.cmpe273.illuminati.services;

import com.dropbox.core.*;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by praveenkarkhile on 5/11/15.
 */
public class ProjectUtil {

    public static DbxClient authorize() throws IOException, DbxException {
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
        return client;
    }

}
