package com.cmpe273.illuminati;

import com.cmpe273.illuminati.services.CompareMetaDataService;
import com.cmpe273.illuminati.services.DropboxService;
import com.dropbox.core.DbxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by ASHU on 13-05-2015.
 */
@Component
public class SchedularConf {
    @Autowired
    CompareMetaDataService compareMetaDataService;

    @Scheduled(fixedRate = 30000)
    public void uploadScheduler() throws IOException, DbxException {
        System.out.println("Scheduler Started");
        compareMetaDataService.returnFileListToUploadAndDownload();
    }
}
