package com.cmpe273.illuminati.services;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;

import java.io.IOException;

/**
 * Created by ASHU on 16-04-2015.
 */
public interface DropboxService {

    void upload() throws IOException, DbxException;

    void getMetadata(DbxClient client) throws DbxException;

    void download()throws IOException, DbxException;
}
