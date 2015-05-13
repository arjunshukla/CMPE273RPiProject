package com.cmpe273.illuminati.services;

/**
 * Created by arjunshukla on 5/9/15.
 */

import com.dropbox.core.DbxException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface CompareMetaDataService {

public List<File> returnFileListToUploadAndDownload() throws IOException, DbxException;

}
