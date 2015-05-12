package com.cmpe273.illuminati.services;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveenkarkhile on 5/11/15.
 */


@Service
public class GetFolderInfoForDrpBoxServiceImpl implements GetFolderInfoForDrpBoxService{

    /*
            function to test
     */

    @Override
    public DbxEntry.WithChildren getFolderInfoFromDrpBox() throws IOException, DbxException {
        DbxEntry.WithChildren listing = getFilelistFromDrpBox("/Volumes/WININSTALL");
        for(DbxEntry child: listing.children){

            System.out.println(child);
        }
        return listing;
    }

    /*
            returns the list of files and folder under given directory
     */

    @Override
    public DbxEntry.WithChildren getFilelistFromDrpBox(String directoryName) throws IOException, DbxException {
        DbxClient dbxclient= ProjectUtil.authorize();
        DbxEntry.WithChildren list= dbxclient.getMetadataWithChildren(directoryName);;
        return list;
    }

    public DbxClient getDroboxClient() throws IOException, DbxException {
        DbxClient dbxclient= ProjectUtil.authorize();
        return dbxclient;
    }
}
