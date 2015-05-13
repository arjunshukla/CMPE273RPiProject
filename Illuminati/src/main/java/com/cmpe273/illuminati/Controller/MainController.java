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

    @RequestMapping("/authorize")
    // Get your app key and secret from the Dropbox developers website.
    public void authorization() throws ServletException, IOException, DbxException {
        dropboxService.authorize();
        }

    @RequestMapping("/upload")
    public void uploadfile(DbxClient client) throws IOException, DbxException {
        dropboxService.upload(client);
    }

    @RequestMapping("/getMetadata")
    public void getMetadata(DbxClient client) throws DbxException {
        dropboxService.getMetadata(client);
    }

    @RequestMapping("/download")
    public void  downloadFile(DbxClient client) throws IOException, DbxException {
        dropboxService.download(client);
    }

    @RequestMapping("/getFilesInfoFromMedia")
    public void getFilesFromMedia()throws IOException, DbxException {
        getFolderInfoForMedia.getFolderInfoFromMedia();
    }

    @RequestMapping("/getFilesInfoFromDrpBox")
    public void getFilesfromDrpBox()throws IOException, DbxException {
        getFolderInfoForDrpBox.getFolderInfoFromDrpBox();
    }

    @RequestMapping("/testForArjun")
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
