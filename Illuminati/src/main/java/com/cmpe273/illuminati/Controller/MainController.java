package com.cmpe273.illuminati.Controller;

import com.cmpe273.illuminati.services.CompareMetaDataService;
import com.cmpe273.illuminati.services.DropboxService;
import com.cmpe273.illuminati.services.GetFolderInfoForDrpBoxService;
import com.cmpe273.illuminati.services.GetFolderInfoForMediaService;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by ASHU on 16-04-2015.
 */

@RestController
@RequestMapping("/illuminati")
public class MainController {

    @Autowired
    DropboxService dropboxService;

    @Autowired
    GetFolderInfoForMediaService getFolderInfoForMedia;

    @Autowired
    GetFolderInfoForDrpBoxService getFolderInfoForDrpBox;

    @Autowired
    CompareMetaDataService compareMetaDataService;

/*    @RequestMapping("/authorize")
    // Get your app key and secret from the Dropbox developers website.
    public void authorization() throws ServletException, IOException, DbxException {
        dropboxService.authorize();
        }*/

    @RequestMapping("/upload")
    public String uploadfile() throws IOException, DbxException {
        dropboxService.upload();
        return "Success";
    }

    @RequestMapping("/getMetadata")
    public String getMetadata(DbxClient client) throws DbxException {
        dropboxService.getMetadata(client);
        return "Success";
    }

    @RequestMapping("/download")
    public String  downloadFile() throws IOException, DbxException {
        dropboxService.download();
        return "Success";
    }

    @RequestMapping("/getFilesInfoFromMedia")
    public String getFilesFromMedia()throws IOException, DbxException {
        getFolderInfoForMedia.getFolderInfoFromMedia();
        return "Success";
    }

    @RequestMapping("/getFilesInfoFromDrpBox")
    public String getFilesfromDrpBox()throws IOException, DbxException {
        getFolderInfoForDrpBox.getFolderInfoFromDrpBox();
        return "Success";
    }

    @RequestMapping("/compareMetaData")
    public String testForArjun_CompareMetaDataService(){
        try {
            compareMetaDataService.returnFileListToUploadAndDownload();
            return "Success";
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (DbxException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
