package com.cmpe273.illuminati.services;

import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by praveenkarkhile on 5/11/15.
 */
public interface GetFolderInfoForDrpBoxService {

    public DbxEntry.WithChildren getFolderInfoFromDrpBox() throws IOException, DbxException;
    public DbxEntry.WithChildren getFilelistFromDrpBox(String directoryName) throws IOException, DbxException;

}
