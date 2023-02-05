package com.florentin.imageanalyzer.service;

import com.google.api.services.drive.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface GoogleDriveFileManagerService {
  public List<File> listEverything() throws IOException, GeneralSecurityException;
  public List<File> listFolderContent(String parentId) throws IOException, GeneralSecurityException;
  public String uploadFile(MultipartFile file, String filePath);
}
