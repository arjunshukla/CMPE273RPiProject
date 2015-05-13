package com.cmpe273.illuminati.services;

/**
 * Created by arjunshukla on 5/9/15.
 */

import com.dropbox.core.DbxException;

import java.io.IOException;
import java.util.ArrayList;

public interface CompareMetaDataService {

public ArrayList<String>[] returnFileListToUploadAndDownload() throws IOException, DbxException;

}
