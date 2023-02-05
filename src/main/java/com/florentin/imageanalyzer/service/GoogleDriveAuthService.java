package com.florentin.imageanalyzer.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleDriveAuthService {
  public Drive getInstance() throws GeneralSecurityException, IOException;
}
