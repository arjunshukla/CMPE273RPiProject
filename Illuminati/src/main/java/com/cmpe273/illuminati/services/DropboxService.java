package com.cmpe273.illuminati.services;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;

import java.io.IOException;

/**
 * Created by ASHU on 16-04-2015.
 */
public interface DropboxService {
    void authorize() throws IOException, DbxException;

    void upload(DbxClient client) throws IOException, DbxException;

    void getMetadata(DbxClient client) throws DbxException;

    void download(DbxClient client)throws IOException, DbxException;
}
