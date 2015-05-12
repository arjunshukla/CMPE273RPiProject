package com.cmpe273.illuminati.services;
import java.util.List;
import java.io.*;

/**
 * Created by praveenkarkhile on 5/11/15.
 */
public interface GetFolderInfoForMediaService {

    public List<File>  getFilelistFromMedia(String directoryName);
    public void displayFolderInfo(List<File> fileList);
    public void getFolderInfoFromMedia();


}
