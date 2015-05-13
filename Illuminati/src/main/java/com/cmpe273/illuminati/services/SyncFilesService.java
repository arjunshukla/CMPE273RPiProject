package com.cmpe273.illuminati.services;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sneha on 5/12/2015.
 */
public interface SyncFilesService {

    void downloadFromDropbox(DbxClient client, ArrayList<String> filesToDownload) throws IOException, DbxException;

    void uploadToDropbox(DbxClient client, ArrayList<String> filesToUpload) throws IOException, DbxException;

}
