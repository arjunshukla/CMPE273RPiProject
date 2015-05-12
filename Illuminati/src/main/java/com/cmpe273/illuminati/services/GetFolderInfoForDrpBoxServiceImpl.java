package com.cmpe273.illuminati.services;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by praveenkarkhile on 5/11/15.
 */


@Service
public class GetFolderInfoForDrpBoxServiceImpl implements GetFolderInfoForDrpBoxService{

    @Override
    public void getFolderInfoFromDrpBox() throws IOException, DbxException {
        List<File> fileList;
        DbxClient dbxclient= ProjectUtil.authorize();
        DbxEntry.WithChildren listing = dbxclient.getMetadataWithChildren("/");
        System.out.println("Files in the root path:");
        for (DbxEntry child : listing.children) {
            System.out.println("	" + child.name + ": " + child.toString());
        }
    }

    @Override
    public List<File>  getFilelistFromDrpBox(String directoryName) throws IOException, DbxException {
        getFolderInfoFromDrpBox();
        return null;
    }

    public DbxClient getDroboxClient() throws IOException, DbxException {
        DbxClient dbxclient= ProjectUtil.authorize();
        return dbxclient;
    }
}
